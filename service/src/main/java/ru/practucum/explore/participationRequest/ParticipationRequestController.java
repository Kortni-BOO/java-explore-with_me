package ru.practucum.explore.participationRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practucum.explore.participationRequest.dto.ParticipationRequestDto;
import ru.practucum.explore.participationRequest.service.ParticipationRequestService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class ParticipationRequestController {

    private final ParticipationRequestService requestService;

    @Autowired
    public ParticipationRequestController(ParticipationRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/{userId}/requests")
    //Получение информации о заявках текущего пользователя на участие в чужих событиях
    public List<ParticipationRequestDto> getAllUserRequests(@PathVariable long userId) {
        return requestService.getAllUserRequests(userId);
    }

    @PostMapping("/{userId}/requests")
    //Добавление запроса от текущего пользователя на участие в событии
    public ParticipationRequestDto createRequest(@PathVariable @NotNull long userId, @Valid @RequestParam @NotNull long eventId) {
        return requestService.createRequest(userId, eventId);
    }

    @PatchMapping("/{userId}/requests/{requestId}/cancel")
    //Отмена своего запроса на участие в событии
    public ParticipationRequestDto cancelRequest(@PathVariable long userId, @PathVariable long requestId) {
        return requestService.cancelRequest(userId, requestId);
    }

    @GetMapping("/{userId}/events/{eventId}/requests")
    //Получение информации о запросах на участие в событии текущего пользователя
    public List<ParticipationRequestDto> getAllUserEventsRequest(@PathVariable long userId,
                                                                 @PathVariable @NotNull long eventId) {
        return requestService.getAllUserEventsRequest(userId, eventId);
    }

    @PatchMapping("/{userId}/events/{eventId}/requests/{reqId}/confirm")
    //Подтверждение чужой заявки на участие в событии текущего пользователя
    public ParticipationRequestDto confirmRequest(@PathVariable long userId,
                                                  @PathVariable long eventId,
                                                  @PathVariable long reqId) {
        return requestService.confirmRequest(userId, eventId, reqId);
    }

    @PatchMapping("/{userId}/events/{eventId}/requests/{reqId}/reject")
    //Отклонение чужой заявки на участие в событиях текущего пользователя
    ParticipationRequestDto rejectRequest(@PathVariable long userId,
                                          @PathVariable long eventId,
                                          @PathVariable long reqId) {
        return requestService.rejectRequest(userId, eventId, reqId);
    }

}
