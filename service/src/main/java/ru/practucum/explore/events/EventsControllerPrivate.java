package ru.practucum.explore.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practucum.explore.events.dto.EventFullDto;
import ru.practucum.explore.events.dto.EventShortDto;
import ru.practucum.explore.events.dto.NewEventDto;
import ru.practucum.explore.events.dto.UpdateEventRequest;
import ru.practucum.explore.events.service.EventService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class EventsControllerPrivate {
    private final EventService eventService;

    @Autowired
    public EventsControllerPrivate(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{userId}/events")
    //Получение событий, добавленных текущем пользователем
    public List<EventShortDto> getAllUserEvents(@PathVariable long userId,
                                                @RequestParam(defaultValue = "0") int from,
                                                @RequestParam(defaultValue = "10") int size) {
        return eventService.getAllUserEvents(userId, from, size);
    }

    @PatchMapping("/{userId}/events")
    //Изменение события добавленного текущим пользователем
    public EventFullDto update(@PathVariable long userId, @RequestBody UpdateEventRequest eventDto) {
        return eventService.update(eventDto, userId);
    }

    @PostMapping("/{userId}/events")
    //Добавление нового события
    public EventFullDto create(@RequestBody @Valid NewEventDto event, @PathVariable long userId) {
        return eventService.create(event, userId);
    }

    @GetMapping("/{userId}/events/{eventId}")
    //Получение полной информации о событии добавленном текущим пользователем
    public EventFullDto getFullEvent(@PathVariable long userId, @PathVariable long eventId) {
        return eventService.getFullEvent(eventId, userId);
    }

    @PatchMapping("/{userId}/events/{eventId}")
    /*Отмена события добавленного текущим пользователем.
    Отменить можно только событие в состоянии ожидания модерации.*/
    public EventFullDto cancelEvent(@PathVariable long userId, @PathVariable long eventId) {
        return eventService.cancelEvent(eventId, userId);
    }

}
