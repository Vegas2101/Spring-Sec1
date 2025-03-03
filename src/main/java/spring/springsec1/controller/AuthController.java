package spring.springsec1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/authorize")
    public String showAuthenticatedPage() {
        return "authenticated";
    }
}