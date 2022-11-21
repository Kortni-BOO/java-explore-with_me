package ru.practucum.explore.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.practucum.explore.events.model.Location;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewEventDto {
    long id;
    @NonNull
    @NotBlank
    String annotation; //Краткое описание

    Integer category; //Категория

    @NonNull
    @NotBlank
    String description; //Полное описание события

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate; //Дата и время на которые намечено событие

    //User initiator; //Пользователь (краткая информация) UserShortDto

    Location location;

    Boolean paid; //Нужно ли оплачивать участие

    Integer participantLimit = 0; //Ограничение на количество участников. Значение 0 - означает отсутствие ограничения

    Boolean requestModeration; //Нужна ли пре-модерация заявок на участие

    @NonNull
    @NotBlank
    String title; //Заголовок
}
