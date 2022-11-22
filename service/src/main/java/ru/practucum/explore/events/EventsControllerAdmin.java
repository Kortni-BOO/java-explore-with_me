package ru.practucum.explore.events;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practucum.explore.events.dto.AdminUpdateEventRequest;
import ru.practucum.explore.events.dto.EventFullDto;
import ru.practucum.explore.events.enums.State;
import ru.practucum.explore.events.mapper.EventMapper;
import ru.practucum.explore.events.service.EventService;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class EventsControllerAdmin {
    private final EventService eventService;
    private final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @GetMapping
    //Поиск событий
    public List<EventFullDto> adminSearchEventsFilter(@RequestParam(required = false) Collection<Long> users,
                                                      @RequestParam(required = false) Collection<State> states,
                                                      @RequestParam(required = false) Collection<Integer> categories,
                                                      @RequestParam(required = false) @DateTimeFormat(pattern = DATE_PATTERN) LocalDateTime rangeStart,
                                                      @RequestParam(required = false) @DateTimeFormat(pattern = DATE_PATTERN) LocalDateTime rangeEnd,
                                                      @RequestParam(defaultValue = "0") int from,
                                                      @RequestParam(defaultValue = "10") int size) {

        return eventService.adminSearchEvents(users, states,
                categories, rangeStart,
                 rangeEnd, from, size);
    }

    @PutMapping("/{eventId}")
    //Редактирование события
    public EventFullDto adminEditedEvent(@RequestBody AdminUpdateEventRequest eventDto, @PathVariable long eventId) {
        return eventService.adminEditedEvent(eventDto, eventId);
    }

    @PatchMapping("/{eventId}/publish")
    //Публикация события
    public EventFullDto adminPublishEvent(@PathVariable long eventId) {
        return eventService.adminPublishEvent(eventId);
    }

    @PatchMapping("/{eventId}/reject")
    //Отклонение события
    public EventFullDto adminRejectEvent(@PathVariable long eventId) {
        return eventService.adminRejectEvent(eventId);
    }
}
