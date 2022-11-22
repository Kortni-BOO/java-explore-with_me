package ru.practucum.explore.compilation.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCompilationDto {


    private List<Long> events; //Список событий входящих в подборку

    private Boolean pinned; //Закреплена ли подборка на главной странице сайта

    @NonNull
    @NotBlank
    private String title; //Заголовок подборки
}
