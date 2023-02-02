package pl.jdacewicz.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jdacewicz.socialmedia.domain.User;
import pl.jdacewicz.socialmedia.service.DBUserDetailsService;

import java.util.Map;

@Controller
public class UserController {

    private DBUserDetailsService detailsService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(DBUserDetailsService detailsService, PasswordEncoder passwordEncoder) {
        this.detailsService = detailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@RequestParam Map<String, String> body) {
        User user = new User();
        user.setUsername(body.get("username"));
        user.setPassword(passwordEncoder.encode(body.get("password")));

        detailsService.createUser(user);
        return "redirect:/about-you";
    }
}
