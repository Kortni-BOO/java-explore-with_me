package ru.practucum.explore.participationRequest.service;

import ru.practucum.explore.participationRequest.dto.ParticipationRequestDto;
import ru.practucum.explore.participationRequest.model.ParticipationRequest;

import java.util.List;

public interface ParticipationRequestService {
    /**
     * Запросы на участие для Users
     * path = "/users/{userId}/events/{eventsId}/requests"
     */
    //получение заявки по id
    ParticipationRequest getById(long reqId);

    //Получение информации о заявках текущего пользователя на участие в чужих событиях
    List<ParticipationRequestDto> getAllUserRequests(long userId);

    //Добавление запроса от текущего пользователя на участие в событии
    ParticipationRequestDto createRequest(long userId, long eventId);

    //Отмена своего запроса на участие в событии
    ParticipationRequestDto cancelRequest(long userId, long requestId);

    //Получение информации о запросах на участие в событии текущего пользователя
    List<ParticipationRequestDto> getAllUserEventsRequest(long userId, long eventId);

    //Подтверждение чужой заявки на участие в событии текущего пользователя
    ParticipationRequestDto confirmRequest(long userId, long eventId, long reqId);

    //Отклонение чужой заявки на участие в событиях текущего пользователя
    ParticipationRequestDto rejectRequest(long userId, long eventId, long reqId);

}
