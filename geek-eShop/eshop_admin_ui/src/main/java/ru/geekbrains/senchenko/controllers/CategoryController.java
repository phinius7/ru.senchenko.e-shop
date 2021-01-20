package ru.geekbrains.senchenko.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.senchenko.controllers.error.NotFoundException;
import ru.geekbrains.senchenko.controllers.repr.CategoryRepr;
import ru.geekbrains.senchenko.repositories.CategoryRepository;
import ru.geekbrains.senchenko.service.PictureService;
import ru.geekbrains.senchenko.services.CategoryService;
import ru.geekbrains.senchenko.services.CategoryServiceImpl;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final PictureService pictureService;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService, PictureService pictureService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
        this.pictureService = pictureService;

    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping
    public String showAllCategories(Model model) {
        List<CategoryRepr> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", new CategoryRepr());
        return "category_add_form";
    }

    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute CategoryRepr category, Model model, BindingResult bindingResult) throws IOException {
        model.addAttribute("activePage", "Categories");
        if (bindingResult.hasErrors()) {
            return "category_add_form";
        }
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable Long id, Model model) {
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", categoryService.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("pictures", pictureService.getPictureByCategory(categoryRepository.findCategoryById(id)));
        return "category_edit_form";
    }

    @PostMapping("/edit")
    public String editCategory(@ModelAttribute CategoryRepr category, Model model, BindingResult bindingResult) throws IOException {
        model.addAttribute("activePage", "Categories");
        if (bindingResult.hasErrors()) {
            return "category_edit_form";
        }
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        pictureService.deletePictureByCategory(categoryRepository.findCategoryById(id));
        categoryService.delete(id);
        return "redirect:/categories";
    }

}
