package ru.practucum.explore.user.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewUserRequest {

    @NonNull
    @NotBlank
    @Email
    private String email;

    @NonNull
    @NotBlank
    private String name;
}
