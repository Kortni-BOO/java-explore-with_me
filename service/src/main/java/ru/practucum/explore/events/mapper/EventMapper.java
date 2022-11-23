package ru.practucum.explore.events.mapper;

import org.springframework.stereotype.Component;
import ru.practucum.explore.category.model.Category;
import ru.practucum.explore.events.dto.*;
import ru.practucum.explore.events.model.Event;
import ru.practucum.explore.events.model.Location;
import ru.practucum.explore.user.dto.UserShortDto;

@Component
public class EventMapper {
    public Event toEventFromFull(EventFullDto eventFullDto) {
        Event event = Event.builder()
                .id(eventFullDto.getId())
                .annotation(eventFullDto.getAnnotation())
                .category(eventFullDto.getCategory())
                .confirmedRequests(eventFullDto.getConfirmedRequests())
                .createdOn(eventFullDto.getCreatedOn())
                .description(eventFullDto.getDescription())
                .lat(eventFullDto.getLocation().getLat())
                .lon(eventFullDto.getLocation().getLon())
                .paid(eventFullDto.getPaid())
                .participantLimit(eventFullDto.getParticipantLimit())
                .publishedOn(eventFullDto.getPublishedOn())
                .requestModeration(eventFullDto.getRequestModeration())
                .state(eventFullDto.getState())
                .title(eventFullDto.getTitle())
                .views(eventFullDto.getViews())
                .build();
        return event;
    }

    public Event toEventFromShort(EventShortDto eventShortDto) {
        Event event = Event.builder()
                .id(eventShortDto.getId())
                .annotation(eventShortDto.getAnnotation())
                .category(eventShortDto.getCategory())
                .confirmedRequests(eventShortDto.getConfirmedRequests())
                .description(eventShortDto.getDescription())
                .paid(eventShortDto.getPaid())
                .title(eventShortDto.getTitle())
                .views(eventShortDto.getViews())
                .build();
        return event;
    }

    public EventFullDto toEventFullDto(Event event) {
        EventFullDto eventFullDto = EventFullDto.builder()
                .annotation(event.getAnnotation())
                .category(new Category(event.getCategory().getId(), event.getCategory().getName()))
                .confirmedRequests(event.getConfirmedRequests())
                .createdOn(event.getCreatedOn())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .id(event.getId())
                .initiator(new UserShortDto(event.getInitiator().getId(), event.getInitiator().getName()))
                .location(new Location(event.getLat(), event.getLon()))
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(event.getPublishedOn())
                .requestModeration(event.getRequestModeration())
                .state(event.getState())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
        return eventFullDto;
    }

    public EventShortDto toEventShortDto(Event event) {
        EventShortDto eventShortDto = EventShortDto.builder()
                .id(event.getId())
                .description(event.getDescription())
                .annotation(event.getAnnotation())
                .category(event.getCategory())
                .confirmedRequests(event.getConfirmedRequests())
                .eventDate(event.getEventDate())
                .initiator(event.getInitiator())
                .paid(event.getPaid())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
        return eventShortDto;
    }

    public Event fromNewEventDto(NewEventDto dto) {
        Event event = Event.builder()
                .id(dto.getId())
                .annotation(dto.getAnnotation())
                .category(new Category(dto.getCategory(),""))
                .description(dto.getDescription())
                .eventDate(dto.getEventDate())
                .lat(dto.getLocation().getLat())
                .lon(dto.getLocation().getLon())
                .paid(dto.getPaid())
                .participantLimit(dto.getParticipantLimit())
                .requestModeration(dto.getRequestModeration())
                .title(dto.getTitle())
                .build();
        return event;

    }
}
