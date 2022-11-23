package ru.practucum.explore.participationRequest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practucum.explore.participationRequest.model.ParticipationRequest;

import java.util.List;

public interface ParticipationRequestRepository extends JpaRepository<ParticipationRequest, Long> {
    List<ParticipationRequest> findAllByRequesterId(long userId);

    List<ParticipationRequest> findByEventId(long eventId);
}
