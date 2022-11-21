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
    long id; // — уникальный идентификатор пользователя;

    @Column(name = "name")
    String name; // — имя или логин пользователя;

    @Column(name = "email")
    String email; // — адрес электронной почты;
}
