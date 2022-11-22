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
@Builder
public class EventShortDto {
    private long id;

    private String description;

    @NonNull
    @NotBlank
    private String annotation; //Краткое описание

    @NonNull
    private Category category; //Категория

    private Integer confirmedRequests; //Количество одобренных заявок на участие в данном событии

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate; //Дата и время на которые намечено событие

    @NonNull
    private User initiator; //Пользователь (краткая информация) UserShortDto

    @NonNull
    private Boolean paid; //Нужно ли оплачивать участие

    @NonNull
    @NotBlank
    private String title; //Заголовок

    private long views; //Количество просмотров события
}
