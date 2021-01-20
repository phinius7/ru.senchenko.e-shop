package ru.geekbrains.senchenko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.senchenko.controllers.error.NotFoundException;
import ru.geekbrains.senchenko.controllers.repr.ProductRepr;
import ru.geekbrains.senchenko.entities.Picture;
import ru.geekbrains.senchenko.entities.Product;
import ru.geekbrains.senchenko.repositories.ProductRepository;
import ru.geekbrains.senchenko.service.PictureService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final PictureService pictureService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, PictureService pictureService) {
        this.productRepository = productRepository;
        this.pictureService = pictureService;
    }

    @Override
    public void save(ProductRepr repr) throws IOException {
        Product product = (repr.getId() != null) ? productRepository.findById(repr.getId())
                .orElseThrow(NotFoundException::new) : new Product();
        product.setCreateDate(repr.getCreateDate());
        product.setModifyDate(repr.getModifyDate());
        product.setTitle(repr.getTitle());
        product.setBrand(repr.getBrand());
        product.setPrice(repr.getPrice());
        product.setDescription(repr.getDescription());
        product.setCategory(repr.getCategory());

        if (repr.getNewPictures() != null) {
            for (MultipartFile newPicture : repr.getNewPictures()) {
                if (product.getPictures() == null) {
                    product.setPictures(new ArrayList<>());
                }
                product.getPictures().add(new Picture(
                        newPicture.getOriginalFilename(),
                        newPicture.getContentType(),
                        pictureService.createPictureData(newPicture.getBytes()),
                        product
                ));
            }
        }
        productRepository.save(product);
    }

    @Override
    public List<ProductRepr> findAll() {
        return productRepository.findAll().stream().map(ProductRepr::new).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductRepr> findById(Long id) {
        return productRepository.findById(id).map(ProductRepr::new);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
