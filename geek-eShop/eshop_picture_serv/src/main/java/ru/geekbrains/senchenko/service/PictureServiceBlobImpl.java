package ru.geekbrains.senchenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.geekbrains.senchenko.controller.repr.PictureRepr;
import ru.geekbrains.senchenko.entities.*;
import ru.geekbrains.senchenko.repositories.PictureRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "picture.storage.type", havingValue = "database")
public class PictureServiceBlobImpl implements PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceBlobImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Optional<String> getPictureContentTypeById(long id) {
        return pictureRepository.findPictureContentTypeByDataId(id);
    }

    @Override
    public Optional<byte[]> getPictureDataById(long id) {
        return Optional.of(pictureRepository.findPictureDataById(id));
    }

    @Override
    public PictureData createPictureData(byte[] picture) {
        return new PictureData(picture);
    }

    @Override
    public void deleteById(Long id) {
        pictureRepository.deleteById(id);
    }

    @Override
    public List<PictureRepr> getPicturesByProduct(Product product) {
        return pictureRepository.findAllPicturesForProduct(product).stream()
                .map(PictureRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PictureRepr> getPictureByCategory(Category category) {
        return pictureRepository.findPicturesForCategory(category).stream()
                .map(PictureRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PictureRepr> getPicturesByBlog(Blog blog) {
        return pictureRepository.findAllPicturesForBlog(blog).stream()
                .map(PictureRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllPicturesByProduct(Product product) {
        List<Picture> list = pictureRepository.findAllPicturesForProduct(product);
        for (Picture p : list) {
            pictureRepository.deleteById(p.getId());
        }
    }

    @Override
    public void deletePictureByCategory(Category category) {
        List<Picture> list = pictureRepository.findPicturesForCategory(category);
        for (Picture p : list) {
            pictureRepository.deleteById(p.getId());
        }
    }

    @Override
    public void deleteAllPicturesByBlog(Blog blog) {
        List<Picture> list = pictureRepository.findAllPicturesForBlog(blog);
        for (Picture p : list) {
            pictureRepository.deleteById(p.getId());
        }
    }
}
