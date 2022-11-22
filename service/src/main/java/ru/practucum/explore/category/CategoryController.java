package ru.practucum.explore.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practucum.explore.category.dto.CategoryDto;
import ru.practucum.explore.category.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{catId}")
    public CategoryDto getById(@PathVariable Integer catId) {
        return categoryService.getById(catId);
    }

    @GetMapping
    public List<CategoryDto> getAll(@RequestParam(defaultValue = "0") int from,
                                    @RequestParam(defaultValue = "20") int size) {
        return categoryService.getAllCategories(from, size);
    }
}
