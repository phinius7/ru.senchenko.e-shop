package ru.geekbrains.senchenko.service;

import ru.geekbrains.senchenko.controller.repr.PictureRepr;
import ru.geekbrains.senchenko.entities.Blog;
import ru.geekbrains.senchenko.entities.Category;
import ru.geekbrains.senchenko.entities.PictureData;
import ru.geekbrains.senchenko.entities.Product;

import java.util.List;
import java.util.Optional;

public interface PictureService {

    Optional<String> getPictureContentTypeById(long id);

    Optional<byte[]> getPictureDataById(long id);

    PictureData createPictureData(byte[] picture);

    void deleteById(Long id);

    List<PictureRepr> getPicturesByProduct(Product product);

    List<PictureRepr> getPictureByCategory(Category category);

    List<PictureRepr> getPicturesByBlog(Blog blog);

    void deleteAllPicturesByProduct(Product product);

    void deletePictureByCategory(Category category);

    void deleteAllPicturesByBlog(Blog blog);
}
