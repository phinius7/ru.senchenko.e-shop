package ru.geekbrains.senchenko.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.senchenko.entities.Brand;
import ru.geekbrains.senchenko.entities.Category;
import ru.geekbrains.senchenko.entities.Product;
import ru.geekbrains.senchenko.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductUserService productService;
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductUserService(productRepository);
    }

    @Test
    public void testFindById() {
        Category expectedCategory = new Category();
        expectedCategory.setId(1L);
        expectedCategory.setCode("0001");
        expectedCategory.setTitle("Category title");

        Brand expectedBrand = new Brand();
        expectedBrand.setId(1L);
        expectedBrand.setTitle("Brand title");

        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setTitle("Product name");
        expectedProduct.setDescription("Product description");
        expectedProduct.setCategory(expectedCategory);
        expectedProduct.setBrand(expectedBrand);
        expectedProduct.setPictures(new ArrayList<>());
        expectedProduct.setPrice(new BigDecimal(123));

        when(productRepository.findById(eq(1L)))
                .thenReturn(Optional.of(expectedProduct));

        Optional<Product> opt = productService.findById(1L);

        assertTrue(opt.isPresent());
        assertEquals(expectedProduct.getId(), opt.get().getId());
        assertEquals(expectedProduct.getTitle(), opt.get().getTitle());
        assertEquals(expectedProduct.getBrand(), opt.get().getBrand());
        assertEquals(expectedProduct.getCategory(), opt.get().getCategory());
    }

}
