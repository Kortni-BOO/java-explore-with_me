package ru.practucum.explore.category.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practucum.explore.category.dto.CategoryDto;
import ru.practucum.explore.category.mapper.CategoryMapper;
import ru.practucum.explore.category.model.Category;
import ru.practucum.explore.category.repository.CategoryRepository;
import ru.practucum.explore.exception.ConflictException;
import ru.practucum.explore.exception.UserNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Admin: Категории
 * API для работы с категориями
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        try {
            Category category = mapper.toCategory(getById(categoryDto.getId()));
            if (category.getName() != null) {
                category.setName(categoryDto.getName());
            }
            repository.save(category);
            return mapper.toCategoryDto(category);
        } catch (DataIntegrityViolationException e) {
            log.error("CategoryServiceImpl: updateCategory — Категория с таким названием {} уже существует.", categoryDto.getName());
            throw new ConflictException(String.format("Категория с таким названием %s уже существует.",
                    categoryDto.getName()));
        }

    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        try {
            Category category = repository.save(mapper.toCategory(categoryDto));
            return mapper.toCategoryDto(category);
        } catch (DataIntegrityViolationException e) {
            log.error("CategoryService: createCategory — Категория с таким названием {} уже существует.",
                    categoryDto.getName());
            throw new ConflictException(String.format("Категория с таким названием %s уже существует.",
                    categoryDto.getName()));
        }
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        getById(categoryId);
        repository.deleteById(categoryId);
    }

    @Override
    public CategoryDto getById(Integer categoryId) {
        Category category = repository.findById(categoryId).orElseThrow(() -> {
            throw new UserNotFoundException(String.format("Категория с id %d не найдена", categoryId));
        });
        return mapper.toCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories(int from, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable page = PageRequest.of(from, size, sort);
        return repository.findAll(page).stream().map(mapper::toCategoryDto).collect(Collectors.toList());
    }
}
