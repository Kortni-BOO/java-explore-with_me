package ru.practucum.explore.user.service;

import ru.practucum.explore.user.dto.UserDto;

import java.util.Collection;
import java.util.List;

public interface UserService {
    //получение информации о пользователях
    List<UserDto> getAllUsers(Collection<Long> ids, Integer from, Integer size);

    //добавление нового пользователя
    UserDto createUser(UserDto userDto);

    //удаление пользователя
    void deleteUser(long id);

    //получение информации о пользователе по id
    UserDto getById(long id);
}
