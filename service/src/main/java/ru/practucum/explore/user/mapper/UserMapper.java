package ru.practucum.explore.user.mapper;

import org.springframework.stereotype.Component;
import ru.practucum.explore.user.dto.UserDto;
import ru.practucum.explore.user.model.User;

@Component
public class UserMapper {
    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getEmail(),
                user.getId(),
                user.getName()
        );
    }

    public User toUser(UserDto user) {
        return new User(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
