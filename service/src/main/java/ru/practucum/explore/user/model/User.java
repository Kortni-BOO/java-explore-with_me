package ru.practucum.explore.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id; // — уникальный идентификатор пользователя;

    @Column(name = "name")
    private String name; // — имя или логин пользователя;

    @Column(name = "email")
    private String email; // — адрес электронной почты;
}
