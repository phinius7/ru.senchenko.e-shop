package ru.geekbrains.senchenko.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.senchenko.entities.Product;
import ru.geekbrains.senchenko.services.ProductUserService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductUserController {

    private final ProductUserService productUserService;

    @Autowired
    public ProductUserController(ProductUserService productUserService) {
        this.productUserService = productUserService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping
    public String showAllProducts(Model model) {
        model.addAttribute("activePage", "Products");
        List<Product> products = productUserService.findAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/see/{id}")
    public String showProductDescription(@PathVariable Long id, Model model) {
        model.addAttribute("activePage", "Products");
        model.addAttribute("products", productUserService.findAllProducts());
        model.addAttribute("product", productUserService.findProductById(id));
        return "product_detail";
    }
}
