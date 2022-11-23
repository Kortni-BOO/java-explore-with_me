package ru.practicum.statistic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import ru.practicum.statistic.dto.ViewStats;
import ru.practicum.statistic.model.EndpointHit;

import ru.practicum.statistic.service.StatisticService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticService service;

    //Сохранение информации о том, что на uri конкретного сервиса был отправлен запрос пользователем.
    // Название сервиса, uri и ip пользователя указаны в теле запроса.
    @PostMapping("/hit")
    public void create(@RequestBody EndpointHit endpointHit) {
        service.create(endpointHit);
    }

    //Получение статики по посещениям
    @GetMapping("/stats")
    public List<ViewStats> search(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                  @RequestParam(required = false) List<String> uris,
                                  @RequestParam(required = false, defaultValue = "false") Boolean unique) {
        return service.search(start, end, uris, unique);
    }
}
