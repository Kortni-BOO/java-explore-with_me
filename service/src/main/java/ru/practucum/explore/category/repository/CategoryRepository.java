package ru.practucum.explore.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practucum.explore.category.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
