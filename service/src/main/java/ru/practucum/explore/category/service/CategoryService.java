package ru.practucum.explore.category.service;

import ru.practucum.explore.category.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    //изменение категории
    CategoryDto updateCategory(CategoryDto categoryDto);

    //добавление новой категории
    CategoryDto createCategory(CategoryDto categoryDto);

    //удаление категории
    void deleteCategory(Integer categoryId);

    CategoryDto getById(Integer categoryId);

    List<CategoryDto> getAllCategories(int from, int size);
}
