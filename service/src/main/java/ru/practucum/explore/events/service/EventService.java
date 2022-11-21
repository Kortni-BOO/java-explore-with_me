package ru.practucum.explore.events.service;

import ru.practucum.explore.events.dto.*;
import ru.practucum.explore.events.enums.SortType;
import ru.practucum.explore.events.enums.State;
import ru.practucum.explore.events.model.Event;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface EventService {

    /**
     * Работа с событиями Public
     * path = "/events"
     */
    //Получение событий с возможностью фильтрации
    List<EventFullDto> searchEvents(String text, Collection<Integer> categories, Boolean paid,
                                    LocalDateTime rangeStart,
                                    LocalDateTime rangeEnd,
                                    Boolean onlyAvailable, SortType sort, int from, int size);


    //Получение подробной информации об опубликованном событии по его id
    public EventFullDto getFullEventById(long eventId);

    /**
     * Работа с событиями для Users
     * path = "/users/{userId}/events"
     */
    //Получение событий, добавленных текущем пользователем
    List<EventShortDto> getAllUserEvents(long userId, int from, int size);

    //Изменение события добавленного текущим пользователем
    EventFullDto update(UpdateEventRequest eventDto, long userId);

    //Добавление нового события
    EventFullDto create(NewEventDto event, long userId);

    //Получение полной информации о событии добавленном текущим пользователем
    EventFullDto getFullEvent(long eventId, long userId);

    /*Отмена события добавленного текущим пользователем.
    Отменить можно только событие в состоянии ожидания модерации.*/
    EventFullDto cancelEvent(long eventId, long userId);

    Event getById(long id);

    /**
     * Работа с событиями для Admin
     * path = "/admin/events"
     */

    //Поиск событий
    List<EventFullDto> adminSearchEvents(Collection<Long> users, Collection<State> states,
                                    Collection<Integer> categories,LocalDateTime rangeStart,
                                    LocalDateTime rangeEnd, int from, int size);

    //Редактирование события
    EventFullDto adminEditedEvent(AdminUpdateEventRequest eventDto, long eventId);

    //Публикация события
    EventFullDto adminPublishEvent(long eventId);

    //Отклонение события
    EventFullDto adminRejectEvent(long eventId);
}
