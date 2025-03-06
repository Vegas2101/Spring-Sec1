package spring.springsec1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.springsec1.entity.Role;
import spring.springsec1.entity.User;
import spring.springsec1.service.UserService;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/authorize")
    public String showAuthenticatedPage() {
        return "authenticated";
    }

    @GetMapping
    public String userPage(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);

        Set<String> roles = user.getRoles().stream()
                .map(Role::getAuthority)
                .collect(Collectors.toSet());
        model.addAttribute("username", username);
        model.addAttribute("roles", roles); // Передаем роли в модель
        return "user";
    }
}
