package ru.geekbrains.senchenko.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.senchenko.entities.Category;
import ru.geekbrains.senchenko.repositories.CategoryRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    private CategoryRepository categoryRepository;
    private CategoryUserService categoryService;

    @BeforeEach
    public void init() {
        categoryRepository = mock(CategoryRepository.class);
        categoryService = new CategoryUserService(categoryRepository);
    }

    @Test
    public void testFindById() {
        Category expectedCategory = new Category();
        expectedCategory.setId(1L);
        expectedCategory.setCode("0001");
        expectedCategory.setTitle("Category title");

        when(categoryRepository.findById(eq(1L)))
                .thenReturn(Optional.of(expectedCategory));

        Optional<Category> categoryTest = categoryService.findById(1L);

        assertNotNull(categoryTest);
        assertEquals(expectedCategory.getId(), categoryTest.get().getId());
        assertEquals(expectedCategory.getTitle(), categoryTest.get().getTitle());

    }
}
