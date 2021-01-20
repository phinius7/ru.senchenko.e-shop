package ru.geekbrains.senchenko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.senchenko.controllers.repr.CategoryRepr;
import ru.geekbrains.senchenko.entities.Category;
import ru.geekbrains.senchenko.entities.Picture;
import ru.geekbrains.senchenko.repositories.CategoryRepository;
import ru.geekbrains.senchenko.service.PictureService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final PictureService pictureService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, PictureService pictureService) {
        this.categoryRepository = categoryRepository;
        this.pictureService = pictureService;
    }

    @Override
    public void save(CategoryRepr repr) throws IOException {
        Category category = new Category();
        category.setId(repr.getId());
        category.setCreateDate(repr.getCreateDate());
        category.setModifyDate(repr.getModifyDate());
        category.setTitle(repr.getTitle());
        category.setCode(repr.getCode());

        if (repr.getPictures() != null) {
            for (MultipartFile newPicture : repr.getPictures()) {
                if (category.getPictures() == null) {
                    category.setPicture(new ArrayList<>());
                }
                category.getPictures().add(new Picture(
                        newPicture.getOriginalFilename(),
                        newPicture.getContentType(),
                        pictureService.createPictureData(newPicture.getBytes()),
                        category
                ));
            }
        }
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryRepr> findAll() {
        return categoryRepository.findAll().stream().map(CategoryRepr::new).collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryRepr> findById(Long id) {
        return categoryRepository.findById(id).map(CategoryRepr::new);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
