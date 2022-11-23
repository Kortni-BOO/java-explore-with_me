package ru.practicum.statistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.statistic.model.EndpointHit;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticRepository extends JpaRepository<EndpointHit, Long> {
    List<EndpointHit> findAllByTimestampAfterAndTimestampBeforeAndUri(LocalDateTime start, LocalDateTime end, String uris);

    @Query("SELECT h FROM EndpointHit h " +
            "WHERE h.id in (SELECT max(hh.id) " +
            "FROM EndpointHit hh GROUP BY hh.ip)")
    List<EndpointHit> findWithUniqueIp(LocalDateTime start, LocalDateTime end, String uri);

}
