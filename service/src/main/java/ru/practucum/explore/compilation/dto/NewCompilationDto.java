package ru.practucum.explore.compilation.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewCompilationDto {


    List<Long> events; //Список событий входящих в подборку

    Boolean pinned; //Закреплена ли подборка на главной странице сайта

    @NonNull
    @NotBlank
    String title; //Заголовок подборки
}
