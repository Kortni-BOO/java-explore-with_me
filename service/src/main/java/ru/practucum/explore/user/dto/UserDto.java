package ru.practucum.explore.user.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NonNull
    @NotBlank
    @Email
    private String email;

    private long id;

    @NonNull
    @NotBlank
    private String name;

}
