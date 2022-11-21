package ru.practucum.explore.participationRequest.mapper;

import org.springframework.stereotype.Component;
import ru.practucum.explore.events.model.Event;
import ru.practucum.explore.participationRequest.dto.ParticipationRequestDto;
import ru.practucum.explore.participationRequest.model.ParticipationRequest;
import ru.practucum.explore.user.model.User;

@Component
public class ParticipationRequestMapper {
    public ParticipationRequestDto toRequestDto(ParticipationRequest request) {
        return new ParticipationRequestDto(
                request.getId(),
                request.getRequester().getId(),
                request.getEvent().getId(),
                request.getStatus(),
                request.getCreated()
        );
    }

    public ParticipationRequest roRequest(ParticipationRequestDto requestDto) {
        return new ParticipationRequest(
                requestDto.getId(),
                requestDto.getCreated(),
                new Event(requestDto.getEvent(), null, null, null, null, null, null, null, null, null,
                        null, null, null, null, null, null, null),
                new User(requestDto.getRequester(), null, null),
                requestDto.getStatus()
        );
    }
}
