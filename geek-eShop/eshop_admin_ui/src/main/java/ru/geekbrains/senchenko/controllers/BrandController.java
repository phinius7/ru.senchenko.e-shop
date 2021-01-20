package ru.geekbrains.senchenko.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.senchenko.controllers.error.NotFoundException;
import ru.geekbrains.senchenko.entities.Brand;
import ru.geekbrains.senchenko.repositories.BrandRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/brands")
public class BrandController {

    private BrandRepository brandRepository;

    @Autowired
    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping
    public String showAllBrands(Model model) {
        model.addAttribute("activePage", "Brands");
        model.addAttribute("brands", brandRepository.findAll());
        return "brands";
    }

    @GetMapping("/add")
    public String addBrandForm(Model model) {
        model.addAttribute("activePage", "Brands");
        model.addAttribute("brand", new Brand());
        return "brand_add_form";
    }

    @PostMapping("/add")
    public String addBrandForm(@Valid @ModelAttribute Brand brand, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Brands");
        if (bindingResult.hasErrors()) {
            return "brand_add_form";
        }
        brandRepository.save(brand);
        return "redirect:/brands";
    }

    @GetMapping("/edit/{id}")
    public String brandEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("activePage", "Brands");
        model.addAttribute("brand", brandRepository.findById(id).orElseThrow(NotFoundException::new));
        return "brand_edit_form";
    }

    @PostMapping("/edit")
    public String brandEditForm(@ModelAttribute Brand brand, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Brands");
        if (bindingResult.hasErrors()) {
            return "brand_edit_form";
        }
        brandRepository.save(brand);
        return "redirect:/brands";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        brandRepository.deleteById(id);
        return "redirect:/brands";
    }
}
