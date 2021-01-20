package ru.geekbrains.senchenko.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.senchenko.controllers.error.NotFoundException;
import ru.geekbrains.senchenko.controllers.repr.UserRepr;
import ru.geekbrains.senchenko.services.RoleService;
import ru.geekbrains.senchenko.services.RoleServiceImpl;
import ru.geekbrains.senchenko.services.UserService;
import ru.geekbrains.senchenko.services.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping
    public String showAllUsers(Model model) {
        List<UserRepr> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", new UserRepr());
        model.addAttribute("roles", roleService.findAll());
        return "user_add_form";
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute UserRepr user, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Users");
        if (bindingResult.hasErrors()) {
            return "user_add_form";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", userService.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("roles", roleService.findAll());
        return "user_edit_form";
    }

    @PostMapping("/edit")
    public String showEditForm(@ModelAttribute UserRepr user, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Users");
        if (bindingResult.hasErrors()) {
            return "user_edit_form";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
