package ru.practucum.explore.compilation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practucum.explore.events.model.Event;

import javax.persistence.*;

@Entity
@Table(name = "compilations_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompilationEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @ManyToOne()
    @JoinColumn(name = "event_id")
    Event event;

    @ManyToOne()
    @JoinColumn(name = "compilation_id")
    Compilation compilation;
}
