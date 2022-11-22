package ru.practucum.explore.category.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Integer id;
    @NotBlank
    private String name;
}
