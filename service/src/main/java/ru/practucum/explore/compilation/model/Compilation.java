package ru.practucum.explore.compilation.model;

import lombok.*;
import ru.practucum.explore.events.model.Event;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "compilations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compilation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "compilations_events",
            joinColumns = @JoinColumn(name = "compilation_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> events; //Список событий входящих в подборку(в одном экземпляре)

    @Column(name = "pinned")
    private Boolean pinned; //Закреплена ли подборка на главной странице сайта

    @Column(name = "title")
    private String title; //Заголовок подборки
}
