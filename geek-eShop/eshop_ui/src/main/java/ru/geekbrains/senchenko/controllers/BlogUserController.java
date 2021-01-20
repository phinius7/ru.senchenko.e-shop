package ru.geekbrains.senchenko.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.senchenko.entities.Blog;
import ru.geekbrains.senchenko.services.BlogUserService;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogUserController {

    private final BlogUserService blogUserService;

    @Autowired
    public BlogUserController(BlogUserService blogUserService) {
        this.blogUserService = blogUserService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping
    public String showAllBlogs(Model model) {
        model.addAttribute("activePage", "Blogs");
        List<Blog> blogs = blogUserService.findAllBlogs();
        model.addAttribute("blogs", blogs);
        return "blogs";
    }

    @GetMapping("/{id}")
    public String showBlogDescription(@PathVariable Long id, Model model) {
        model.addAttribute("activePage", "Blogs");
        model.addAttribute("blog", blogUserService.getBlogById(id));
        return "blog_description";
    }
}
