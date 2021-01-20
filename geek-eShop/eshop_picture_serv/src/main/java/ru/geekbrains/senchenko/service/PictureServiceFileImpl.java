package ru.geekbrains.senchenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.geekbrains.senchenko.controller.repr.PictureRepr;
import ru.geekbrains.senchenko.entities.*;
import ru.geekbrains.senchenko.repositories.PictureRepository;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "picture.storage.type", havingValue = "file")
public class PictureServiceFileImpl implements PictureService {

    @Value("${picture.storage.path}")
    private String storagePath;

    private PictureRepository pictureRepository;

    @Autowired
    public PictureServiceFileImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Optional<String> getPictureContentTypeById(long id) {
        return pictureRepository.findPictureContentTypeByDataId(id);
    }

    @Override
    public Optional<byte[]> getPictureDataById(long id) {
        String fileName = pictureRepository.findPictureFileNameByDataId(id);
        Path path = Path.of(storagePath, fileName);
        try {
            return Optional.of(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public PictureData createPictureData(byte[] picture) {
        String fileName = UUID.randomUUID().toString();
        try (OutputStream os = Files.newOutputStream(Path.of(storagePath, fileName))) {
            os.write(picture);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new PictureData(fileName);
    }

    @Override
    public void deleteById(Long id) {
        String fileName = pictureRepository.findPictureFileNameByDataId(id);
        if (Files.exists(Path.of(storagePath, fileName))) {
            try {
                Files.delete(Path.of(storagePath, fileName));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
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
        for (Picture p: list) {
            String fileName = pictureRepository.findPictureFileNameByDataId(p.getId());
            if (Files.exists(Path.of(storagePath, fileName))) {
                try {
                    Files.delete(Path.of(storagePath, fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
        for (Picture p : list) {
            pictureRepository.deleteById(p.getId());
        }
    }

    @Override
    public void deletePictureByCategory(Category category) {
        List<Picture> list = pictureRepository.findPicturesForCategory(category);
        for (Picture p: list) {
            String fileName = pictureRepository.findPictureFileNameByDataId(p.getId());
            if (Files.exists(Path.of(storagePath, fileName))) {
                try {
                    Files.delete(Path.of(storagePath, fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
        for (Picture p : list) {
            pictureRepository.deleteById(p.getId());
        }
    }

    @Override
    public void deleteAllPicturesByBlog(Blog blog) {
        List<Picture> list = pictureRepository.findAllPicturesForBlog(blog);
        for (Picture p: list) {
            String fileName = pictureRepository.findPictureFileNameByDataId(p.getId());
            if (Files.exists(Path.of(storagePath, fileName))) {
                try {
                    Files.delete(Path.of(storagePath, fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
        for (Picture p : list) {
            pictureRepository.deleteById(p.getId());
        }
    }
}
