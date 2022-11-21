package ru.practucum.explore.participationRequest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practucum.explore.events.model.Event;
import ru.practucum.explore.participationRequest.enums.Status;
import ru.practucum.explore.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "participation_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "created")
    LocalDateTime created; //Дата и время создания заявки

    @ManyToOne()
    @JoinColumn(name = "event_id")
    Event event; //Идентификатор события

    @ManyToOne()
    @JoinColumn(name = "requester_id")
    User requester; //Идентификатор пользователя, отправившего заявку

    @Enumerated(EnumType.STRING)
    Status status;//Статус заявки

}
