package ru.practucum.explore.participationRequest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.practucum.explore.participationRequest.enums.Status;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipationRequestDto {
    private long id;

    private Long requester; //Идентификатор пользователя, отправившего заявку

    private Long event; //Идентификатор события

    private Status status;//Статус заявки

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created; //Дата и время создания заявки
}
