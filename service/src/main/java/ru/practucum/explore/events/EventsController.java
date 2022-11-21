package ru.practucum.explore.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practucum.explore.client.Client;
import ru.practucum.explore.events.dto.EventFullDto;
import ru.practucum.explore.events.enums.SortType;
import ru.practucum.explore.events.mapper.EventMapper;
import ru.practucum.explore.events.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventsController {
    private final EventService eventService;
    private final EventMapper eventMapper;

    private final Client client;

    @Autowired
    public EventsController(EventService eventService, EventMapper eventMapper, Client client) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
        this.client = client;
    }


    @GetMapping
    //Получение событий с возможностью фильтрации
    public List<EventFullDto> searchEvents(@RequestParam String text,
                                           @RequestParam(required = false) Collection<Integer> categories,
                                           @RequestParam(required = false) Boolean paid,
                                           @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                           @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                           @RequestParam(required = false) Boolean onlyAvailable,
                                           @RequestParam(required = false) SortType sort,
                                           @RequestParam(defaultValue = "0") int from,
                                           @RequestParam(defaultValue = "20") int size,
                                           HttpServletRequest request) {

        client.create(request);
        return eventService.searchEvents(text, categories, paid,
                rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }


    @GetMapping("/{eventId}")
    //Получение подробной информации об опубликованном событии по его id
    public EventFullDto getFullEventById(@PathVariable long eventId, HttpServletRequest request) {
        client.create(request);
        return eventService.getFullEventById(eventId);
    }
}
