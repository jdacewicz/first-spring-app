package pl.jdacewicz.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jdacewicz.socialmedia.domain.User;
import pl.jdacewicz.socialmedia.service.DBUserDetailsService;

import java.util.Optional;

@Controller
public class AppController {

    private DBUserDetailsService userDetailsService;

    @Autowired
    public AppController(DBUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/")
    public String showMainPage(Model model) {
        //TODO Consider putting it in other class.
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> loggedInUser = userDetailsService.findUserByUsername(loggedInUsername);

        if (loggedInUser.isPresent()) {
            model.addAttribute("user", loggedInUser.get());
            return "main";
        }
        return "redirect:/register";
    }
}
