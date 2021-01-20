package ru.geekbrains.senchenko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.senchenko.controllers.error.NotFoundException;
import ru.geekbrains.senchenko.controllers.repr.BlogRepr;
import ru.geekbrains.senchenko.entities.Blog;
import ru.geekbrains.senchenko.entities.Picture;
import ru.geekbrains.senchenko.repositories.BlogRepository;
import ru.geekbrains.senchenko.service.PictureService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final PictureService pictureService;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, PictureService pictureService) {
        this.blogRepository = blogRepository;
        this.pictureService = pictureService;
    }

    @Override
    public void save(BlogRepr repr) throws IOException {
        Blog blog = (repr.getId() != null) ? blogRepository.findById(repr.getId())
                .orElseThrow(NotFoundException::new) : new Blog();
        blog.setCreateDate(repr.getCreateDate());
        blog.setModifyDate(repr.getModifyDate());
        blog.setTitle(repr.getTitle());
        blog.setContent(repr.getContent());

        if (repr.getNewPictures() != null) {
            for (MultipartFile newPicture : repr.getNewPictures()) {
                if (blog.getPictures() == null) {
                    blog.setPictures(new ArrayList<>());
                }
                blog.getPictures().add(new Picture(
                        newPicture.getOriginalFilename(),
                        newPicture.getContentType(),
                        pictureService.createPictureData(newPicture.getBytes()),
                        blog
                ));
            }
        }
        blogRepository.save(blog);
    }

    @Override
    public List<BlogRepr> findAll() {
        return blogRepository.findAll().stream().map(BlogRepr::new).collect(Collectors.toList());
    }

    @Override
    public Optional<BlogRepr> findById(Long id) {
        return blogRepository.findById(id).map(BlogRepr::new);
    }

    @Override
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }
}
