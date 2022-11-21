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
        return new Event(
                eventFullDto.getId(),
                eventFullDto.getAnnotation(),
                eventFullDto.getCategory(),
                eventFullDto.getConfirmedRequests(),
                eventFullDto.getCreatedOn(),
                eventFullDto.getDescription(),
                eventFullDto.getEventDate(),
                null,
                eventFullDto.getLocation().getLat(),
                eventFullDto.getLocation().getLon(),
                eventFullDto.getPaid(),
                eventFullDto.getParticipantLimit(),
                eventFullDto.getPublishedOn(),
                eventFullDto.getRequestModeration(),
                eventFullDto.getState(),
                eventFullDto.getTitle(),
                eventFullDto.getViews()
        );
    }

    public Event toEventFromShort(EventShortDto eventShortDto) {
        return new Event(
                eventShortDto.getId(),
                eventShortDto.getAnnotation(),
                eventShortDto.getCategory(),
                eventShortDto.getConfirmedRequests(),
                null,
                eventShortDto.getDescription(),
                eventShortDto.getEventDate(),
                eventShortDto.getInitiator(),
                0f,
                0f,
                eventShortDto.getPaid(),
                0,
                null,
                null,
                null,
                eventShortDto.getTitle(),
                eventShortDto.getViews()
        );
    }

    public EventFullDto toEventFullDto(Event event) {
        return new EventFullDto(
                event.getAnnotation(),
                new Category(event.getCategory().getId(), event.getCategory().getName()),
                event.getConfirmedRequests(),
                event.getCreatedOn(),
                event.getDescription(),
                event.getEventDate(),
                event.getId(),
                new UserShortDto(event.getInitiator().getId(), event.getInitiator().getName()),
                new Location(event.getLat(), event.getLon()),
                event.getPaid(),
                event.getParticipantLimit(),
                event.getPublishedOn(),
                event.getRequestModeration(),
                event.getState(),
                event.getTitle(),
                event.getViews()
        );
    }

    public EventShortDto toEventShortDto(Event event) {
        return new EventShortDto(
                event.getId(),
                event.getDescription(),
                event.getAnnotation(),
                event.getCategory(),
                event.getConfirmedRequests(),
                event.getEventDate(),
                event.getInitiator(),
                event.getPaid(),
                event.getTitle(),
                event.getViews()
        );
    }

    public UpdateEventRequest toUpdateEventReq(Event event) {
        return new UpdateEventRequest(
                event.getId(),
                event.getAnnotation(),
                event.getCategory().getId(),
                event.getDescription(),
                event.getEventDate(),
                event.getId(),
                event.getPaid(),
                event.getParticipantLimit(),
                event.getTitle()
        );
    }

    public Event fromNewEventDto(NewEventDto dto) {
        return new Event(
                dto.getId(),
                dto.getAnnotation(),
                new Category(dto.getCategory(),""),
                null,
                null,
                dto.getDescription(),
                dto.getEventDate(),
                null,
                dto.getLocation().getLat(),
                dto.getLocation().getLon(),
                dto.getPaid(),
                dto.getParticipantLimit(),
                null,
                dto.getRequestModeration(),
                null,
                dto.getTitle(),
                null
                );

    }

    public Event toEventForAdminUpdate(AdminUpdateEventRequest eventRequest) {
        return new Event(
                eventRequest.getId(),
                eventRequest.getAnnotation(),
                new Category(eventRequest.getCategory(), ""),
                null,
                null,
                eventRequest.getDescription(),
                eventRequest.getEventDate(),
                null,
                eventRequest.getLocation().getLat(),
                eventRequest.getLocation().getLon(),
                eventRequest.getPaid(),
                eventRequest.getParticipantLimit(),
                null,
                eventRequest.getRequestModeration(),
                null,
                eventRequest.getTitle(),
                null

        );
    }
}
