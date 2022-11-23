package ru.practucum.explore.events.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practucum.explore.events.enums.State;
import ru.practucum.explore.events.model.Event;

import java.util.List;

//JpaSpecificationExecutor<Event>
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByInitiatorId(long userId);

    List<Event> findAllByState(State state);

    Page<Event> findAll(Specification<Event> specification, Pageable pageable);
}
