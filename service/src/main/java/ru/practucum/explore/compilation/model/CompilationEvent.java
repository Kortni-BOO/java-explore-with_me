package ru.practucum.explore.compilation.model;

import lombok.*;
import ru.practucum.explore.events.model.Event;

import javax.persistence.*;

@Entity
@Table(name = "compilations_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompilationEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne()
    @JoinColumn(name = "compilation_id")
    private Compilation compilation;
}
