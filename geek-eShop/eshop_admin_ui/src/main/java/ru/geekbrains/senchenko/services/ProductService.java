package ru.geekbrains.senchenko.services;

import ru.geekbrains.senchenko.controllers.repr.ProductRepr;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    void save(ProductRepr productRepr) throws IOException;

    List<ProductRepr> findAll();

    Optional<ProductRepr> findById(Long id);

    void delete(Long id);
}
