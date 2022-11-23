package ru.practucum.explore.category.mapper;

import org.springframework.stereotype.Component;
import ru.practucum.explore.category.dto.CategoryDto;
import ru.practucum.explore.category.model.Category;

@Component
public class CategoryMapper {
    public Category toCategory(CategoryDto categoryDto) {
        return new Category(
                categoryDto.getId(),
                categoryDto.getName()
        );
    }

    public CategoryDto toCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }
}
