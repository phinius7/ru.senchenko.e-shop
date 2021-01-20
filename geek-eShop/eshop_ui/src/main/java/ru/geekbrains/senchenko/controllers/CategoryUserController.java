package ru.geekbrains.senchenko.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.senchenko.entities.Category;
import ru.geekbrains.senchenko.services.CategoryUserService;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryUserController {

    private final CategoryUserService categoryUserService;

    @Autowired
    public CategoryUserController(CategoryUserService categoryUserService) {
        this.categoryUserService = categoryUserService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping
    public String showAllCategories(Model model) {
        model.addAttribute("activePage", "Categories");
        List<Category> categories = categoryUserService.findAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/{id}")
    public String showCategoryDescription(@PathVariable Long id, Model model) {
        model.addAttribute("activePage", "Categories");
        model.addAttribute("categoriy", categoryUserService.findCategoryById(id));
        return "category_description";
    }
}
