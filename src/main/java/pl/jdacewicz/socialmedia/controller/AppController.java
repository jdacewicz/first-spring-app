package pl.jdacewicz.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jdacewicz.socialmedia.domain.Post;
import pl.jdacewicz.socialmedia.domain.User;
import pl.jdacewicz.socialmedia.service.DBUserDetailsService;
import pl.jdacewicz.socialmedia.service.PostService;

import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    private DBUserDetailsService userDetailsService;
    private PostService postService;

    @Autowired
    public AppController(DBUserDetailsService userDetailsService, PostService postService) {
        this.userDetailsService = userDetailsService;
        this.postService = postService;
    }

    @GetMapping("/")
    public String showMainPage(Model model) {
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> loggedInUser = userDetailsService.getUser(loggedInUsername);

        if (loggedInUser.isPresent()) {
//            List<Post> postList = postService.getRandomPosts();
            model.addAttribute("user", loggedInUser.get());
//            model.addAttribute("posts", postList);
            return "main";
        }
        return "redirect:/register";
    }
}
