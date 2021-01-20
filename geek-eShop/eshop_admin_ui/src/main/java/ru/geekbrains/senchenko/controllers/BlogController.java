package ru.geekbrains.senchenko.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.senchenko.controllers.error.NotFoundException;
import ru.geekbrains.senchenko.controllers.repr.BlogRepr;
import ru.geekbrains.senchenko.repositories.BlogRepository;
import ru.geekbrains.senchenko.service.PictureService;
import ru.geekbrains.senchenko.services.BlogService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private final BlogRepository blogRepository;
    private final BlogService blogService;
    private final PictureService pictureService;

    @Autowired
    public BlogController(BlogRepository blogRepository, BlogService blogService, PictureService pictureService) {
        this.blogRepository = blogRepository;
        this.blogService = blogService;
        this.pictureService = pictureService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping
    public String showAllBlogs(Model model) {
        List<BlogRepr> blogs = blogService.findAll();
        model.addAttribute("blogs", blogs);
        return "blogs";
    }

    @GetMapping("/add")
    public String addBlog(Model model) {
        model.addAttribute("activePage", "Blogs");
        model.addAttribute("blog", new BlogRepr());
        return "blog_add_form";
    }

    @PostMapping("/add")
    public String addBlog(@Valid @ModelAttribute BlogRepr blog, Model model, BindingResult bindingResult) throws IOException {
        model.addAttribute("activePage", "Blogs");
        if (bindingResult.hasErrors()) {
            return "blog_add_form";
        }
        blogService.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/edit/{id}")
    public String editBlog(@PathVariable Long id, Model model) {
        model.addAttribute("activePage", "Blogs");
        model.addAttribute("blog", blogService.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("pictures", pictureService.getPicturesByBlog(blogRepository.findBlogById(id)));
        return "blog_edit_form";
    }

    @PostMapping("/edit")
    public String editBlog(@ModelAttribute BlogRepr blog, Model model, BindingResult bindingResult) throws IOException {
        model.addAttribute("activePage", "blogs");
        if (bindingResult.hasErrors()) {
            return "blog_edit_form";
        }
        blogService.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("delete/{id}")
    public String deleteBlog(@PathVariable Long id) {
        pictureService.deleteAllPicturesByBlog(blogRepository.findBlogById(id));
        blogService.delete(id);
        return "redirect:/blogs";
    }
}
