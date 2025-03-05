package spring.springsec1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.springsec1.entity.User;
import spring.springsec1.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping ("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @GetMapping("/gt/{userId}")
    public String gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
    }

    @PostMapping
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", userService.getAllRoles()); // Передаем все роли в форму
        return "createUser";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam("roles") Set<Long> roleIds) { // Принимаем выбранные роли
        if (roleIds == null || roleIds.isEmpty()) {
            throw new IllegalArgumentException("Роли не выбраны");
        }
        userService.saveUser(user, roleIds); // Передаем роли в сервис
        return "redirect:/admin";
    }

    @GetMapping("/edit/{userId}")
    public String editUserForm(@PathVariable("userId") Long userId, Model model) {
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("AllRoles", userService.getAllRoles());
        return "editUser";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("roles") List<Long> roleIds) { // Принимаем выбранные роли
        if (roleIds == null || roleIds.isEmpty()) {
            throw new IllegalArgumentException("Роли не выбраны");
        }
        userService.updateUser(user, roleIds); // Передаем роли в сервис
        return "redirect:/admin";
    }
}