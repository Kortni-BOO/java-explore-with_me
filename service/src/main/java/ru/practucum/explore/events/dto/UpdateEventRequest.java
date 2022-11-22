package ru.practucum.explore.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateEventRequest {
    private long id;

    private String annotation; //Краткое описание

    private Integer category; //Категория

    private String description; //Полное описание события

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate; //Дата и время на которые намечено событие

    private long eventId;

    private Boolean paid; //Нужно ли оплачивать участие

    private Integer participantLimit; //Ограничение на количество участников. Значение 0 - означает отсутствие ограничения

    private String title; //Заголовок
}
