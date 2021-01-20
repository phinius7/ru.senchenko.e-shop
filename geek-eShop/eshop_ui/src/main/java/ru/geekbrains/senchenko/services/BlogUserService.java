package ru.geekbrains.senchenko.services;

import org.springframework.stereotype.Service;
import ru.geekbrains.senchenko.entities.Blog;
import ru.geekbrains.senchenko.repositories.BlogRepository;

import java.util.List;

@Service
public class BlogUserService {

    private BlogRepository blogRepository;

    public BlogUserService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> findAllBlogs() {
        return blogRepository.findAll();
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findBlogById(id);
    }
}
