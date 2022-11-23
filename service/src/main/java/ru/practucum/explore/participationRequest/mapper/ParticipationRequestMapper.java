package ru.practucum.explore.participationRequest.mapper;

import org.springframework.stereotype.Component;
import ru.practucum.explore.events.model.Event;
import ru.practucum.explore.participationRequest.dto.ParticipationRequestDto;
import ru.practucum.explore.participationRequest.model.ParticipationRequest;
import ru.practucum.explore.user.model.User;

@Component
public class ParticipationRequestMapper {
    public ParticipationRequestDto toRequestDto(ParticipationRequest request) {
        ParticipationRequestDto dto = ParticipationRequestDto.builder()
                .id(request.getId())
                .requester(request.getRequester().getId())
                .event(request.getEvent().getId())
                .status(request.getStatus())
                .created(request.getCreated())
                .build();
        return dto;
    }

}
