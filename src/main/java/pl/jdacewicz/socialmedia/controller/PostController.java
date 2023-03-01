package pl.jdacewicz.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.jdacewicz.socialmedia.domain.Post;
import pl.jdacewicz.socialmedia.payroll.PostNotFoundException;
import pl.jdacewicz.socialmedia.service.PostService;

import java.util.List;

@RestController
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    List<Post> getRandomPosts() {
        return postService.getRandomPosts();
    }

    @PostMapping("/post")
    Post newPost(@RequestBody Post newPost) {
        return postService.createPost(newPost);
    }

    @GetMapping("/post/{id}")
    Post getSinglePost(@PathVariable Long id) {
        return postService.getPost(id)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    @DeleteMapping("/post/{id}")
    void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
