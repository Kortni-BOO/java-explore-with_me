package ru.practucum.explore.events.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practucum.explore.category.mapper.CategoryMapper;
import ru.practucum.explore.category.model.Category;
import ru.practucum.explore.category.service.CategoryService;
import ru.practucum.explore.events.dto.*;
import ru.practucum.explore.events.enums.SortType;
import ru.practucum.explore.events.enums.State;
import ru.practucum.explore.events.mapper.EventMapper;
import ru.practucum.explore.events.model.Event;
import ru.practucum.explore.events.repository.EventRepository;
import ru.practucum.explore.exception.DateException;
import ru.practucum.explore.exception.NoAccessException;
import ru.practucum.explore.exception.ObjectNotFoundException;
import ru.practucum.explore.user.mapper.UserMapper;
import ru.practucum.explore.user.model.User;
import ru.practucum.explore.user.service.UserService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository repository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final EventMapper mapper;
    private final UserMapper userMapper;
    private final CategoryMapper categoryMapper;

    /**
     * Public: События
     */

    //Получение событий с возможностью фильтрации
    @Override
    public List<EventFullDto> searchEvents(String text,Collection<Integer> categories, Boolean paid,
                                           LocalDateTime rangeStart,
                                           LocalDateTime rangeEnd,
                                           Boolean onlyAvailable, SortType sort, int from, int size) {

        if (sort == null) {
            sort = SortType.VIEWS;
        }
        Specification<Event> specification = null;
        System.out.println(text);

        specification = Specification.where(specification).and(
                (event, query, cb) -> cb.equal(event.get("state"), State.PUBLISHED));

        if (text.isEmpty()) {
            Specification<Event> specificationSearch = Specification.where(
                    (root, criteriaQuery, criteriaBuilder) ->
                            criteriaBuilder.like(root.get("annotation"), "%" + text + "%")
            );

            specificationSearch = Specification.where(specificationSearch)
                    .or(
                            (root, criteriaQuery, criteriaBuilder) ->
                                    criteriaBuilder.like(root.get("description"), "%" + text + "%")
                    );

            specification = Specification.where(specification)
                    .and(specificationSearch);
        }

        //Фильтр: categories
        if (categories != null) {
            specification = Specification.where(
                    (event, query, cb) ->
                            cb.in(event.get("category").get("id")).value(categories)
            );
        }

        //поиск только платных/бесплатных событий(paid)
        if (paid != null) {
            specification = (event, query, cb) -> cb.equal(event.get("paid"), paid);
        }

        //isAvailable
        if (rangeStart != null) {
            specification = Specification.where(specification).and(
                    (event, criteriaQuery, cb) ->
                            cb.greaterThan(event.get("eventDate").as(LocalDateTime.class), rangeStart)
            );
        }

        if (rangeEnd != null) {
            specification = Specification.where(specification).and(
                    (event, criteriaQuery, cb) ->
                            cb.lessThan(event.get("eventDate").as(LocalDateTime.class), rangeEnd)
            );
        }

        if (rangeStart == null && rangeEnd == null) {
            specification = (event, query, cb) ->
                    cb.lessThanOrEqualTo(event.get("eventDate"), LocalDateTime.now());
        }

        if (onlyAvailable != null) {
            if (Boolean.TRUE.equals(onlyAvailable)) {
                specification = Specification.where(specification).and(
                        (root, criteriaQuery, criteriaBuilder) ->
                                criteriaBuilder.lessThan(root.get("confirmedRequest"), root.get("participantLimit"))
                );
            }
        }
        Pageable page;

        if (sort.equals(SortType.VIEWS)) {
            page = PageRequest.of(from, size, Sort.by(Sort.Direction.ASC, "views"));
        } else if (sort.equals(SortType.EVENT_DATE)) {
            page = PageRequest.of(from, size, Sort.by(Sort.Direction.ASC, "eventDate"));
        } else {
            page = PageRequest.of(from, size, Sort.by(Sort.Direction.ASC, "views"));
        }

        return repository.findAll(specification, page)
                .stream()
                .map(mapper::toEventFullDto)
                .collect(Collectors.toList());

    }

    @Override
    public EventFullDto getFullEventById(long eventId) {
        Event event = getById(eventId);
        return mapper.toEventFullDto(event);
    }

    /**
     * Private: События
     */
    @Override
    public List<EventShortDto> getAllUserEvents(long userId, int from, int size) {
        return repository.findAllByInitiatorId(userId)
                .stream()
                .map(mapper::toEventShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventFullDto update(UpdateEventRequest eventDto, long userId) {
        //Event event = getById(eventDto.getId());
        Event event = getById(eventDto.getEventId());
        if (event.getInitiator().getId() != userId) {
            throw new NoAccessException(String.format(
                    "Данное событие не создавал пользователь с id %d.", userId)
            );
        }
        if (event.getState().equals(State.PUBLISHED)) {
            throw new NoAccessException("Опубликованное событие изменить не получится.");
        }
        if (event.getState().equals(State.CANCELED)) {
            event.setState(State.PENDING);
        }
        if (event.getEventDate().isBefore(LocalDateTime.now().plusHours(2))) {
            throw new DateException("Время на которые намечено событие не может быть раньше, " +
                    "чем через два часа от текущего момента");
        }
        //annotation
        if (eventDto.getAnnotation() != null) {
            event.setAnnotation(eventDto.getAnnotation());
        }

        //category
        if (eventDto.getCategory() != null) {
            Category category = categoryMapper.toCategory(categoryService.getById(eventDto.getCategory()));
            event.setCategory(category);
        }

        //description
        if (eventDto.getDescription() != null) {
            event.setDescription(eventDto.getDescription());
        }

        //eventDate
        if (eventDto.getEventDate() != null) {
            event.setEventDate(eventDto.getEventDate());
        }

        //paid
        if (eventDto.getPaid() != null) {
            event.setPaid(eventDto.getPaid());
        }

        //participantLimit
        if (eventDto.getParticipantLimit() != null) {
            event.setParticipantLimit(eventDto.getParticipantLimit());
        }

        //title
        if (eventDto.getTitle() != null) {
            event.setTitle(eventDto.getTitle());
        }
        event.setState(State.PENDING);
        Event eventUpdate = repository.save(event);
        return mapper.toEventFullDto(eventUpdate);
    }

    @Override
    @Transactional
    public EventFullDto create(NewEventDto eventDto, long userId) {
        if (eventDto.getEventDate().isBefore(LocalDateTime.now().plusHours(2))) {
            throw new DateException("Время на которые намечено событие не может быть раньше, " +
                    "чем через два часа от текущего момента");
        }
        User user = userMapper.toUser(userService.getById(userId));
        Category category = categoryMapper.toCategory(categoryService.getById(eventDto.getCategory()));
        Event event = mapper.fromNewEventDto(eventDto);
        event.setCategory(category);
        event.setConfirmedRequests(0);
        event.setCreatedOn(LocalDateTime.now());
        event.setInitiator(user);
        event.setLat(eventDto.getLocation().getLat());
        event.setLon(eventDto.getLocation().getLon());
        event.setPublishedOn(null);
        event.setState(State.PENDING);
        event.setViews(0L);

        return mapper.toEventFullDto(repository.save(event));

    }

    @Override
    public EventFullDto getFullEvent(long eventId, long userId) {
        Event event = getById(eventId);
        if (event.getInitiator().getId() != userId) {
            throw new NoAccessException(String.format(
                    "Данное событие не создавал пользователь с id %d.", userId)
            );
        }
        return mapper.toEventFullDto(event);
    }

    @Override
    @Transactional
    public EventFullDto cancelEvent(long eventId, long userId) {
        Event event = getById(eventId);
        if (event.getInitiator().getId() != userId) {
            throw new NoAccessException(String.format(
                    "У пользователь с id %d нет прав доступа к событию.", userId)
            );
        }
        if (event.getState().equals(State.PENDING)) {
            event.setState(State.CANCELED);
        } else {
            throw new NoAccessException("Опубликованное событие отменить нельзя.");
        }

        return mapper.toEventFullDto(event);
    }

    @Override
    public Event getById(long id) {
        Event event = repository.findById(id).orElseThrow(() -> {
            throw  new ObjectNotFoundException(String.format("Событие с id %d не найдено", id));
        });
        return event;
    }

    /**
     * Admin: События
     */
    //Поиск событий
    public List<EventFullDto> adminSearchEvents(Collection<Long> users,  Collection<State> states,
                                         Collection<Integer> categories, LocalDateTime rangeStart,
                                         LocalDateTime rangeEnd, int from, int size) {

        //Pageable
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable page = PageRequest.of(from, size, sort);

        Specification<Event> specification = null;

        //User
        if (!users.isEmpty() && users != null) {
            specification = Specification.where(specification).and(
                    (event, query, cb) ->
                            cb.in(event.get("initiator").get("id")).value(users)
            );
        }

        //State !states.isEmpty() && states != null
        if (states != null) {
            //specification = (event, query, cb) -> cb.equal(event.get("state"), states);
            specification = Specification.where(specification).and(
                    (event, query, cb) -> cb.equal(event.get("state"), states)
            );
        }

        //Category есть отдельный метод
        if (categories != null) {
            //specification = belongsToCategory(categories);
            specification = Specification.where(specification).and(
                    (event, query, cb) ->
                            cb.in(event.get("category").get("id")).value(categories)
            );
        }

        if (rangeStart != null) {
            specification = Specification.where(specification).and(
                    (event, criteriaQuery, cb) ->
                            cb.greaterThan(event.get("eventDate").as(LocalDateTime.class), rangeStart)
            );
        }

        if (rangeEnd != null) {
            specification = Specification.where(specification).and(
                    (event, criteriaQuery, cb) ->
                            cb.lessThan(event.get("eventDate").as(LocalDateTime.class), rangeEnd)
            );
        }


        Specification<Event> nullRangeSpec = (event, query, cb) ->
                cb.lessThanOrEqualTo(event.get("eventDate"), LocalDateTime.now());




        return repository.findAll(specification, page)
                .stream()
                .map(mapper::toEventFullDto)
                .collect(Collectors.toList());

    }


    //Редактирование события
    public EventFullDto adminEditedEvent(AdminUpdateEventRequest eventDto, long eventId) {
        Event event = getById(eventId);
        Category category = categoryMapper.toCategory(categoryService.getById(eventDto.getCategory()));
        //annotation

        if (eventDto.getAnnotation() != null) {
            event.setAnnotation(eventDto.getAnnotation());
        }

        if (eventDto.getCategory() != null) {
            event.setCategory(event.getCategory());
        }

        if (eventDto.getDescription() != null) {
            event.setDescription(eventDto.getDescription());
        }

        if (eventDto.getEventDate() != null) {
            event.setEventDate(eventDto.getEventDate());
        }

        if (eventDto.getLocation() != null) {
            event.setLat(eventDto.getLocation().getLat());
            event.setLon(eventDto.getLocation().getLon());
        }

        if (eventDto.getPaid() != null) {
            event.setPaid(eventDto.getPaid());
        }

        if (eventDto.getParticipantLimit() != null) {
            event.setParticipantLimit(eventDto.getParticipantLimit());
        }

        if (eventDto.getRequestModeration() != null) {
            event.setRequestModeration(eventDto.getRequestModeration());
        }

        if (eventDto.getTitle() != null) {
            event.setTitle(eventDto.getTitle());
        }


        repository.save(event);
        return mapper.toEventFullDto(event);
    }

    //Публикация события
    @Transactional
    public EventFullDto adminPublishEvent(long eventId) {
        Event event = getById(eventId);
        //Событие должно быть в состоянии ожидания публикации
        if (!event.getState().equals(State.PENDING)) {
            throw new NoAccessException("Событие должно быть в состоянии ожидания публикации");
        }
        //Дата начала события должна быть не ранее чем за час от даты публикации
        if (event.getEventDate().isBefore(LocalDateTime.now().plusHours(1))) {
            throw new DateException("Дата начала события должна быть не ранее чем за час от даты публикации");
        }
        event.setState(State.PUBLISHED);
        repository.save(event);
        return mapper.toEventFullDto(event);
    }

    //Отклонение события
    @Transactional
    public EventFullDto adminRejectEvent(long eventId) {
        Event event = getById(eventId);
        if (event.getState().equals(State.PUBLISHED)) {
            throw  new NoAccessException(String.format("Опубликованное событие id %d не может быть отклонено", eventId));
        }
        event.setState(State.CANCELED);
        repository.save(event);
        return mapper.toEventFullDto(event);
    }
}