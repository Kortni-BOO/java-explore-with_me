package ru.practicum.statistic.mapper;

import ru.practicum.statistic.dto.ViewStats;
import ru.practicum.statistic.model.EndpointHit;

public class StatMapper {
    public ViewStats toViewStats(EndpointHit hit) {
        return new ViewStats(hit.getApp(), hit.getUri(), null);
    }
}
