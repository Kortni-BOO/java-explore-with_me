package ru.practucum.explore.events.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practucum.explore.category.model.Category;
import ru.practucum.explore.events.enums.State;
import ru.practucum.explore.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "annotation", columnDefinition = "text")
    String annotation; //Краткое описание

    @ManyToOne()
    @JoinColumn(name = "category_id")
    Category category; //Категория

    Integer confirmedRequests; //Количество одобренных заявок на участие в данном событии

    @Column(name = "created_at")
    LocalDateTime createdOn; //Дата и время создания события (в формате "yyyy-MM-dd HH:mm:ss")

    @Column(name = "description", columnDefinition = "text")
    String description; //Полное описание события

    @Column(name = "event_date")
    LocalDateTime eventDate; //Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")

    @ManyToOne()
    @JoinColumn(name = "initiator_id")
    User initiator; //Пользователь (краткая информация) UserShortDto

    Float lat;
    Float lon;

    @Column(name = "paid")
    Boolean paid; //Нужно ли оплачивать участие

    @Column(name = "participant_limit")
    Integer participantLimit; //Ограничение на количество участников. Значение 0 - означает отсутствие ограничения

    @Column(name = "publishedOn")
    LocalDateTime publishedOn; //Дата и время публикации события (в формате "yyyy-MM-dd HH:mm:ss")

    @Column(name = "request_moderation")
    Boolean requestModeration; //Нужна ли пре-модерация заявок на участие

    @JoinColumn(name = "state")
    @Enumerated(EnumType.STRING)
    State state; //Список состояний жизненного цикла события

    @Column(name = "title")
    String title; //Заголовок

    @Column(name = "views")
    Long views; //Количество просмотров события
}
