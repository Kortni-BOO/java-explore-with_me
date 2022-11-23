package ru.practucum.explore.events.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
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
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "annotation", columnDefinition = "text")
    private String annotation; //Краткое описание

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category; //Категория

    private Integer confirmedRequests; //Количество одобренных заявок на участие в данном событии

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn; //Дата и время создания события (в формате "yyyy-MM-dd HH:mm:ss")

    @Column(name = "description", columnDefinition = "text")
    private String description; //Полное описание события

    @Column(name = "event_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate; //Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")

    @ManyToOne()
    @JoinColumn(name = "initiator_id")
    private User initiator; //Пользователь (краткая информация) UserShortDto

    private Float lat;
    private Float lon;

    @Column(name = "paid")
    private Boolean paid; //Нужно ли оплачивать участие

    @Column(name = "participant_limit")
    private Integer participantLimit; //Ограничение на количество участников. Значение 0 - означает отсутствие ограничения

    @Column(name = "publishedOn")
    private LocalDateTime publishedOn; //Дата и время публикации события (в формате "yyyy-MM-dd HH:mm:ss")

    @Column(name = "request_moderation")
    private Boolean requestModeration; //Нужна ли пре-модерация заявок на участие

    @JoinColumn(name = "state")
    @Enumerated(EnumType.STRING)
    private State state; //Список состояний жизненного цикла события

    @Column(name = "title")
    private String title; //Заголовок

    @Column(name = "views")
    private Long views; //Количество просмотров события
}
