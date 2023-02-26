package pl.jdacewicz.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmedia.domain.Post;
import pl.jdacewicz.socialmedia.domain.User;
import pl.jdacewicz.socialmedia.service.DBUserDetailsService;
import pl.jdacewicz.socialmedia.service.PostService;
import pl.jdacewicz.socialmedia.util.FileUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Controller
public class PostController {

    private PostService postService;
    private DBUserDetailsService detailsService;

    @Autowired
    public PostController(PostService postService, DBUserDetailsService detailsService) {
        this.postService = postService;
        this.detailsService = detailsService;
    }

    @GetMapping("/new-post")
    public String createPostForm() {
        return "new-post";
    }

    @PostMapping("/new-post")
    public String createPost(@RequestParam Map<String, String> body, @RequestParam("image") MultipartFile file) throws IOException {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userLoggedIn = detailsService.getUser(currentUser);

        Post post = new Post();
        post.setContent(body.get("content"));
        post.setPostCreator(userLoggedIn.get());
        if (!file.isEmpty()) {
            String fileName = FileUtils.generateUniqueName(file.getOriginalFilename());
            post.setImage(fileName);

            String uploadDir = "uploads/user-photos/" + userLoggedIn.get().getId();
            FileUtils.saveFile(uploadDir, fileName, file);
        }
        postService.createPost(post);

        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String showPost(@PathVariable Long id, Model model) {
        Optional<Post> post = postService.getPost(id);

        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "post";
        }
        return "error";
    }
}
