package ru.practucum.explore.compilation.dto;

import lombok.*;
import ru.practucum.explore.events.model.Event;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompilationDto {
    private Integer id;

    private Set<Event> events; //Список событий входящих в подборку

    @NonNull
    private Boolean pinned; //Закреплена ли подборка на главной странице сайта

    @NonNull
    @NotBlank
    private String title; //Заголовок подборки
}
