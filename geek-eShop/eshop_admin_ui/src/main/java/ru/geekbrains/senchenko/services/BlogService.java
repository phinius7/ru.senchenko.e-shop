package ru.geekbrains.senchenko.services;

import ru.geekbrains.senchenko.controllers.repr.BlogRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BlogService {

    void save(BlogRepr repr) throws IOException;

    List<BlogRepr> findAll();

    Optional<BlogRepr> findById(Long id);

    void delete(Long id);
}
