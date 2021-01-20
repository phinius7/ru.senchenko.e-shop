package ru.geekbrains.senchenko.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.senchenko.controllers.error.NotFoundException;
import ru.geekbrains.senchenko.controllers.repr.RoleRepr;
import ru.geekbrains.senchenko.services.RoleService;
import ru.geekbrains.senchenko.services.RoleServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping
    public String showAllRoles(Model model) {
        List<RoleRepr> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "roles";
    }

    @GetMapping("/add")
    public String addRole(Model model) {
        model.addAttribute("activePage", "Roles");
        model.addAttribute("role", new RoleRepr());
        return "role_add_form";
    }

    @PostMapping("/add")
    public String addRole(@Valid @ModelAttribute RoleRepr role, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Roles");
        if (bindingResult.hasErrors()) {
            return "role_add_form";
        }
        roleService.save(role);
        return "redirect:/roles";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("activePage", "Users");
        model.addAttribute("role", roleService.findById(id).orElseThrow(NotFoundException::new));
        return "role_edit_form";
    }

    @PostMapping("/edit")
    public String showEditForm(@ModelAttribute RoleRepr role, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Users");
        if (bindingResult.hasErrors()) {
            return "role_edit_form";
        }
        roleService.save(role);
        return "redirect:/roles";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        roleService.delete(id);
        return "redirect:/roles";
    }
}
