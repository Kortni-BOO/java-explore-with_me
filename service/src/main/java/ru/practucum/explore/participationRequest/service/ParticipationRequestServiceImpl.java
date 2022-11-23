package ru.practucum.explore.participationRequest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practucum.explore.events.enums.State;
import ru.practucum.explore.events.model.Event;
import ru.practucum.explore.events.service.EventService;
import ru.practucum.explore.exception.NoAccessException;
import ru.practucum.explore.exception.ObjectNotFoundException;
import ru.practucum.explore.participationRequest.dto.ParticipationRequestDto;
import ru.practucum.explore.participationRequest.enums.Status;
import ru.practucum.explore.participationRequest.mapper.ParticipationRequestMapper;
import ru.practucum.explore.participationRequest.model.ParticipationRequest;
import ru.practucum.explore.participationRequest.repository.ParticipationRequestRepository;
import ru.practucum.explore.user.mapper.UserMapper;
import ru.practucum.explore.user.model.User;
import ru.practucum.explore.user.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Закрытый API для работы с запросами текущего
 * пользователя на участие в событиях
 */
@Service
@RequiredArgsConstructor
public class ParticipationRequestServiceImpl implements ParticipationRequestService {
    private final ParticipationRequestRepository repository;
    private final ParticipationRequestMapper mapper;
    private final UserService userService;
    private final EventService eventService;
    private final UserMapper userMapper;

    //получение заявки по id
    public ParticipationRequest getById(long reqId) {
        ParticipationRequest request = repository.findById(reqId).orElseThrow(() -> {
            throw  new ObjectNotFoundException(String.format("Заявка с id %d не найдена", reqId));
        });
        return request;
    }

    //Получение информации о заявках текущего пользователя на участие в чужих событиях
    @Override
    public List<ParticipationRequestDto> getAllUserRequests(long userId) {

        return repository.findAllByRequesterId(userId)
                .stream()
                .map(mapper::toRequestDto)
                .collect(Collectors.toList());
    }

    //Добавление запроса от текущего пользователя на участие в событии
    @Override
    @Transactional
    public ParticipationRequestDto createRequest(long userId, long eventId) {
        User user = userMapper.toUser(userService.getById(userId));
        Event event = eventService.getById(eventId);

        if (event.getInitiator().getId() == userId) {
            throw new NoAccessException("Инициатор события не может добавить запрос на участие в своём событии");
        }
        if (!event.getState().equals(State.PUBLISHED)) {
            throw new NoAccessException("Нельзя участвовать в неопубликованном событии");
        }
        /*если у события достигнут лимит запросов на участие - необходимо вернуть ошибку
          если для события отключена пре-модерация запросов на участие,
          то запрос должен автоматически перейти в состояние подтвержденного*/
        ParticipationRequest request = new ParticipationRequest();
        if (event.getParticipantLimit() != 0 && event.getConfirmedRequests().equals(event.getParticipantLimit())) {
            throw new NoAccessException("У события достигнут лимит запросов на участие ");
        }
        if (!event.getRequestModeration()) {
            request.setStatus(Status.CONFIRMED);
        } else {
            request.setStatus(Status.PENDING);
        }
        request.setCreated(LocalDateTime.now());
        request.setEvent(event);
        request.setRequester(user);

        return mapper.toRequestDto(repository.save(request));
    }

    //Отмена своего запроса на участие в событии
    @Override
    @Transactional
    public ParticipationRequestDto cancelRequest(long userId, long requestId) {
        ParticipationRequest request = getById(requestId);

        if (request.getRequester().getId() != userId) {
            throw new NoAccessException("Вы не можете отменить не свой запрос.");
        }
        request.setStatus(Status.CANCELED);
        return mapper.toRequestDto(repository.save(request));
    }

    //Получение информации о запросах на участие в событии текущего пользователя
    @Override
    public List<ParticipationRequestDto> getAllUserEventsRequest(long userId, long eventId) {
        Event event = eventService.getById(eventId);
        if (event.getInitiator().getId() != userId) {
            throw new NoAccessException("У вас нет доступа.");
        }
        List<ParticipationRequest> request = repository.findByEventId(eventId);
        //repository.

        return request.stream().map(mapper::toRequestDto).collect(Collectors.toList());
    }

    //Подтверждение чужой заявки на участие в событии текущего пользователя
    @Override
    @Transactional
    public ParticipationRequestDto confirmRequest(long userId, long eventId, long reqId) {
        ParticipationRequest request = getById(reqId);
        Event event = eventService.getById(eventId);
        /* - если для события лимит заявок равен 0 или отключена пре-модерация заявок,
             то подтверждение заявок не требуется CONFIRMED
           - нельзя подтвердить заявку, если уже достигнут лимит по заявкам на данное событие
           - если при подтверждении данной заявки, лимит заявок для события исчерпан,
             то все неподтверждённые заявки необходимо отклонить*/
        if (event.getParticipantLimit().equals(event.getConfirmedRequests())) {
            throw new NoAccessException(String.format("Нельзя подтвердить заявку id %d," +
                    " уже достигнут лимит по заявкам на данное событие", reqId));
        }
        //переменна для хранения одобренных заявок confirmedRequests
        long count = event.getConfirmedRequests();
        if (count + 1 >= event.getParticipantLimit()) {
            rejectRequest(userId, eventId, reqId);
        }
        if (event.getParticipantLimit() == 0 && !event.getRequestModeration() && event.getState().equals(State.PUBLISHED)) {
            request.setStatus(Status.CONFIRMED);
        }
        request.setStatus(Status.CONFIRMED);

        return mapper.toRequestDto(repository.save(request));
    }

    //Отклонение чужой заявки на участие в событиях текущего пользователя
    @Override
    @Transactional
    public ParticipationRequestDto rejectRequest(long userId, long eventId, long reqId) {
        ParticipationRequest request = getById(reqId);
        request.setStatus(Status.REJECTED);
        return mapper.toRequestDto(repository.save(request));
    }
}
