package pl.jdacewicz.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jdacewicz.socialmedia.domain.Comment;
import pl.jdacewicz.socialmedia.domain.Post;
import pl.jdacewicz.socialmedia.domain.User;
import pl.jdacewicz.socialmedia.service.CommentService;
import pl.jdacewicz.socialmedia.service.DBUserDetailsService;
import pl.jdacewicz.socialmedia.service.PostService;

import java.util.Map;
import java.util.Optional;

@Controller
public class CommentController {

    private DBUserDetailsService detailsService;
    private PostService postService;

    @Autowired
    public CommentController(DBUserDetailsService detailsService, PostService postService) {
        this.detailsService = detailsService;
        this.postService = postService;
    }

    @PostMapping("/comment")
    public String createComment(@RequestParam Map<String, String> body) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userLoggedIn = detailsService.getUser(currentUser);

        long postId = Long.parseLong(body.get("postId"));
        Optional<Post> foundPost = postService.getPost(postId);

        if (foundPost.isPresent()) {
            Post post = foundPost.get();

            Comment comment = new Comment();
            comment.setContent(body.get("commentContent"));
            comment.setCreator(userLoggedIn.get());

            post.addComment(comment);
            postService.updatePost(post);
        }
        return "redirect:/";
    }
}
