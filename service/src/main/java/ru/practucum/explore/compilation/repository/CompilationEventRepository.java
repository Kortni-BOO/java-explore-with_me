package ru.practucum.explore.compilation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practucum.explore.compilation.model.CompilationEvent;

import java.util.Optional;

public interface CompilationEventRepository extends JpaRepository<CompilationEvent, Integer> {
    Optional<CompilationEvent> findByEventIdAndCompilationId(Long eventId, Integer compilationId);
}
