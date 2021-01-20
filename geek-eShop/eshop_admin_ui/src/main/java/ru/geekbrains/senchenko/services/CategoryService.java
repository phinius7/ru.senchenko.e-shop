package ru.geekbrains.senchenko.services;

import ru.geekbrains.senchenko.controllers.repr.CategoryRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

    void save(CategoryRepr categoryRepr) throws IOException;

    List<CategoryRepr> findAll();

    Optional<CategoryRepr> findById(Long id);

    void delete(Long id);
}
