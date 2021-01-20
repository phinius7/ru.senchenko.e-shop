package ru.geekbrains.senchenko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.senchenko.entities.Product;
import ru.geekbrains.senchenko.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductUserService {

    private ProductRepository productRepository;

    @Autowired
    public ProductUserService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(Long id) {
        return productRepository.findProductById(id);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
