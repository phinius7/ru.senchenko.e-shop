package ru.geekbrains.senchenko.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.senchenko.services.BlogUserService;
import ru.geekbrains.senchenko.services.CategoryUserService;
import ru.geekbrains.senchenko.services.ProductUserService;

@Controller
public class MainUserController {

    private final CategoryUserService categoryUserService;
    private final ProductUserService productUserService;
    private final BlogUserService blogUserService;

    @Autowired
    public MainUserController(CategoryUserService categoryUserService, ProductUserService productUserService, BlogUserService blogUserService) {
        this.categoryUserService = categoryUserService;
        this.productUserService = productUserService;
        this.blogUserService = blogUserService;
    }

    @RequestMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("activePage", "Home");
        model.addAttribute("products", productUserService.findAllProducts());
        model.addAttribute("categories", categoryUserService.findAllCategories());
        model.addAttribute("blogs", blogUserService.findAllBlogs());
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("activePage", "About");
        return "about";
    }


}
