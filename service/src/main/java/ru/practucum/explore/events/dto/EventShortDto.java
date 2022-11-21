package ru.practucum.explore.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.practucum.explore.category.model.Category;
import ru.practucum.explore.user.model.User;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventShortDto {
    long id;

    String description;

    @NonNull
    @NotBlank
    String annotation; //Краткое описание

    @NonNull
    Category category; //Категория

    Integer confirmedRequests; //Количество одобренных заявок на участие в данном событии

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate; //Дата и время на которые намечено событие

    @NonNull
    User initiator; //Пользователь (краткая информация) UserShortDto

    @NonNull
    Boolean paid; //Нужно ли оплачивать участие

    @NonNull
    @NotBlank
    String title; //Заголовок

    long views; //Количество просмотров события
}
