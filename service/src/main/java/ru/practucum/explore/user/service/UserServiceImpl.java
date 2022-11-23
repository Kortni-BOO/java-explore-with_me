package ru.practucum.explore.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practucum.explore.exception.ConflictException;
import ru.practucum.explore.exception.UserNotFoundException;
import ru.practucum.explore.user.dto.UserDto;
import ru.practucum.explore.user.mapper.UserMapper;
import ru.practucum.explore.user.model.User;
import ru.practucum.explore.user.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Admin: Пользователи
 * API для работы с пользователями
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    //Поиск пользователя id
    @Override
    public UserDto getById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException(String.format("Пользователь с id %d не найден", id));
        });
        return userMapper.toUserDto(user);
    }

    //Получение информации о пользователях
    @Override
    public List<UserDto> getAllUsers(Collection<Long> ids, Integer from, Integer size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable page = PageRequest.of(from, size, sort);
        return userRepository.findAllByIdIn(ids, page)
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    //Добавление нового пользователя
    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        try {
            User user = userRepository.save(userMapper.toUser(userDto));
            return userMapper.toUserDto(user);
        } catch (DataIntegrityViolationException e) {
            log.error("CategoryUser: createUser — User с таким name {} уже существует.",
                    userDto.getName());
            throw new ConflictException(String.format("User с таким name %s уже существует.",
                    userDto.getName()));
        }


    }

    //Удаление пользователя
    @Override
    @Transactional
    public void deleteUser(long id) {
        getById(id);
        userRepository.deleteById(id);
    }

}
