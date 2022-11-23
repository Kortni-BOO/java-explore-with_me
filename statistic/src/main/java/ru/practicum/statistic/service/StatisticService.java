package ru.practicum.statistic.service;

import ru.practicum.statistic.dto.ViewStats;
import ru.practicum.statistic.model.EndpointHit;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface StatisticService {
    //Сохранение информации о том, что к эндпоинту был запрос
    void create(EndpointHit endpointHit);

    //Получение статистики по посещениям.
    List<ViewStats> search(LocalDateTime start, LocalDateTime end,
                           Collection<String> uris, boolean unique);

}
