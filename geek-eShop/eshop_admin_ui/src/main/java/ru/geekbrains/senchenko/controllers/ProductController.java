package ru.geekbrains.senchenko.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.senchenko.controllers.error.NotFoundException;
import ru.geekbrains.senchenko.controllers.repr.ProductRepr;
import ru.geekbrains.senchenko.repositories.BrandRepository;
import ru.geekbrains.senchenko.repositories.ProductRepository;
import ru.geekbrains.senchenko.service.PictureService;
import ru.geekbrains.senchenko.services.CategoryService;
import ru.geekbrains.senchenko.services.ProductService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;
    private BrandRepository brandRepository;
    private PictureService pictureService;
    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             BrandRepository brandRepository,
                             PictureService pictureService,
                             ProductRepository productRepository) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandRepository = brandRepository;
        this.pictureService = pictureService;
        this.productRepository = productRepository;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping
    public String showAllProducts(Model model) {
        model.addAttribute("activePage", "Products");
        List<ProductRepr> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("activePage", "Products");
        model.addAttribute("product", new ProductRepr());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        return "product_add_form";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute ProductRepr product, Model model, BindingResult bindingResult) throws IOException {
        model.addAttribute("activePage", "Products");
        if (bindingResult.hasErrors()) {
            return "product_add_form";
        }
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("activePage", "Products");
        model.addAttribute("product", productService.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("pictures", pictureService.getPicturesByProduct(productRepository.findProductById(id)));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        return "product_edit_form";
    }

    @PostMapping("/edit")
    public String showEditForm(@ModelAttribute ProductRepr product, Model model, BindingResult bindingResult) throws IOException {
        model.addAttribute("activePage", "Products");
        if (bindingResult.hasErrors()) {
            return "product_edit_form";
        }
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        pictureService.deleteAllPicturesByProduct(productRepository.findProductById(id));
        productService.delete(id);
        return "redirect:/products";
    }
}
