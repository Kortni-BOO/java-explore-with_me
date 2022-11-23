package ru.practucum.explore.compilation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practucum.explore.compilation.dto.NewCompilationDto;
import ru.practucum.explore.compilation.mapper.CompilationMapper;
import ru.practucum.explore.compilation.model.Compilation;
import ru.practucum.explore.compilation.model.CompilationEvent;
import ru.practucum.explore.compilation.repository.CompilationEventRepository;
import ru.practucum.explore.compilation.repository.CompilationRepository;
import ru.practucum.explore.events.mapper.EventMapper;
import ru.practucum.explore.events.model.Event;
import ru.practucum.explore.events.service.EventService;
import ru.practucum.explore.exception.ObjectNotFoundException;
import ru.practucum.explore.exception.UserNotFoundException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service для работы с подборками событий
 */
@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {
    private final CompilationRepository repository;
    private final CompilationEventRepository compRepository;
    private final EventService eventService;

    /**
     * Public: Подборки событий
     */
    //Получение подборки событий по его id
    @Override
    public Compilation getById(Integer compId) {
        Compilation compilation = repository.findById(compId).orElseThrow(() -> {
            throw  new ObjectNotFoundException(String.format("Подборка с id %d не найдена", compId));
        });
        return compilation;
    }

    //Получение подборок событий
    @Override
    public List<Compilation> getAllCompilations(Boolean pinned, int from, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable page = PageRequest.of(from, size, sort);
        return repository.findAllByPinned(pinned, page);
    }

    /**
     * Admin: Подборки событий
     */
    //Добавление новой подборки.
    @Override
    @Transactional
    public Compilation createCompilation(NewCompilationDto newCompilation) {
        Set<Event> events = newCompilation.getEvents().stream().map(eventService::getById).collect(Collectors.toSet());
        Compilation compilation = new Compilation(
                null, events, newCompilation.getPinned(), newCompilation.getTitle());

        return repository.save(compilation);
    }

    //Удаление подборки.
    @Override
    @Transactional
    public void deleteCompilation(Integer compId) {
        //getById(compId);
        repository.deleteById(compId);
    }

    //Удалить событие из подборки.
    @Override
    @Transactional
    public void deleteEventCompilation(Integer compId, long eventId) {
        Compilation compilation = getById(compId);
        Event event = eventService.getById(eventId);
        CompilationEvent compilationEvent = compRepository.findByEventIdAndCompilationId(eventId, compId)
                .orElseThrow(() -> new UserNotFoundException(String.format("В подборке с id = %d не найдено событие с id = %d", compId, eventId)));
        compRepository.deleteById(compilationEvent.getId());
        compilation.getEvents().remove(event);
        repository.save(compilation);

    }

    //Добавить событие в подборку.
    @Override
    @Transactional
    public void addEventCompilation(Integer compId, long eventId) {
        Compilation compilation = getById(compId);
        Event event = eventService.getById(eventId);
        compilation.getEvents().add(event);
        CompilationEvent compEvent = new CompilationEvent(0, event, compilation);
        compRepository.save(compEvent);
        CompilationEvent compilationEvent = new CompilationEvent(null, event,
                repository.findById(compId)
                        .orElseThrow(() -> new UserNotFoundException(String.format("подборка с id = %d не найден", compId))));
        compRepository.save(compilationEvent);
    }

    //Открепить подборку на главной странице.
    @Override
    @Transactional
    public void unpinCompilation(Integer compId) {
        Compilation compilation = getById(compId);
        compilation.setPinned(false);
        repository.save(compilation);
    }

    //Закрепить подборку на главной странице.
    @Override
    @Transactional
    public void pinCompilation(Integer compId) {
        Compilation compilation = getById(compId);
        compilation.setPinned(true);
        repository.save(compilation);
    }
}
