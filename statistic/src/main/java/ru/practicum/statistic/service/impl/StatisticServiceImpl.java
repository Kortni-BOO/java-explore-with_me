package ru.practicum.statistic.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.statistic.dto.ViewStats;
import ru.practicum.statistic.model.EndpointHit;
import ru.practicum.statistic.repository.StatisticRepository;
import ru.practicum.statistic.service.StatisticService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository repository;

    @Override
    public void create(EndpointHit endpointHit) {
        repository.save(endpointHit);
    }

    @Override
    public List<ViewStats> search(LocalDateTime start, LocalDateTime end,
                                  Collection<String> uris, boolean unique) {
        List<EndpointHit> result;
        List<ViewStats> resultV = new ArrayList<>();

        for (String uri : uris) {
            if (unique) {
                result = repository.findWithUniqueIp(start, end, uri);
            } else {
                result = repository.findAllByTimestampAfterAndTimestampBeforeAndUri(start, end, uri);
            }
            if (result.size() != 0) {
                for (EndpointHit vs : result) {
                    resultV.add(new ViewStats(vs.getApp(), vs.getUri(), result.size()));
                }
            } else {
                resultV.add(new ViewStats(null, uri, 0));
            }
        }
        return resultV;
    }
}
