package ru.practucum.explore.participationRequest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practucum.explore.participationRequest.enums.Status;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationRequestDto {
    long id;

    Long requester; //Идентификатор пользователя, отправившего заявку

    Long event; //Идентификатор события

    Status status;//Статус заявки

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime created; //Дата и время создания заявки
}
