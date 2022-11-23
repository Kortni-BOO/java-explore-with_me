package ru.practucum.explore.events.enums;

/**
 * Сортировка списка событий должна быть организована либо по количеству просмотров(views),
 * которое должно запрашиваться в сервисе статистики, либо по датам событий(eventDate).
 */
public enum SortType {
    VIEWS,
    EVENT_DATE
}
