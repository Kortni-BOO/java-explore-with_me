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
@Builder
public class NewEventDto {
    private long id;
    @NonNull
    @NotBlank
    private String annotation; //Краткое описание

    private Integer category; //Категория

    @NonNull
    @NotBlank
    private String description; //Полное описание события

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate; //Дата и время на которые намечено событие

    //User initiator; //Пользователь (краткая информация) UserShortDto

    private Location location;

    private Boolean paid; //Нужно ли оплачивать участие

    private Integer participantLimit = 0; //Ограничение на количество участников. Значение 0 - означает отсутствие ограничения

    private Boolean requestModeration; //Нужна ли пре-модерация заявок на участие

    @NonNull
    @NotBlank
    private String title; //Заголовок
}
