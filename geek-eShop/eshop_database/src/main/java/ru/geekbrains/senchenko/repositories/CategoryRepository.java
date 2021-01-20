package ru.geekbrains.senchenko.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.senchenko.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryById(Long id);
}
