package ru.geekbrains.senchenko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.senchenko.entities.Category;
import ru.geekbrains.senchenko.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryUserService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryUserService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findCategoryById(id);
    }
}
