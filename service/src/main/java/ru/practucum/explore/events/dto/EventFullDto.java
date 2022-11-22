package ru.practucum.explore.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.practucum.explore.category.model.Category;
import ru.practucum.explore.events.enums.State;
import ru.practucum.explore.events.model.Location;
import ru.practucum.explore.user.dto.UserShortDto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventFullDto {
    @NonNull
    @NotBlank
    private String annotation; //Краткое описание

    @NonNull
    private Category category; //Категория

    private Integer confirmedRequests; //Количество одобренных заявок на участие в данном событии

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn; //Дата и время создания события

    private String description; //Полное описание события

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate; //Дата и время на которые намечено событие

    private Long id;

    @NonNull
    private UserShortDto initiator; //Пользователь (краткая информация) UserShortDto

    private Location location;

    @NonNull
    private Boolean paid; //Нужно ли оплачивать участие

    private Integer participantLimit; //Ограничение на количество участников. Значение 0 - означает отсутствие ограничения

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn; //Дата и время публикации события

    private Boolean requestModeration; //Нужна ли пре-модерация заявок на участие

    private State state; //Список состояний жизненного цикла события

    @NonNull
    @NotBlank
    private String title; //Заголовок

    private long views; //Количество просмотров события
}
