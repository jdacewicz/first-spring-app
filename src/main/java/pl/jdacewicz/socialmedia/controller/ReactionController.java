package pl.jdacewicz.socialmedia.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jdacewicz.socialmedia.domain.Post;
import pl.jdacewicz.socialmedia.domain.Reaction;
import pl.jdacewicz.socialmedia.service.PostService;
import pl.jdacewicz.socialmedia.service.ReactionService;

import java.util.Map;
import java.util.Optional;

@Controller
public class ReactionController {

    private PostService postService;
    private ReactionService reactionService;

    @Autowired
    public ReactionController(PostService postService, ReactionService reactionService) {
        this.postService = postService;
        this.reactionService = reactionService;
    }

    @PostMapping("/react")
    public String reactToPost(@RequestParam Map<String, String> body) {
        Optional<Post> searchedPost = postService.getPost(Long.parseLong(body.get("postId")));
        Optional<Reaction> searchedReaction = reactionService.getReaction(Long.parseLong(body.get("reactionId")));

        if (searchedPost.isPresent() && searchedReaction.isPresent()) {
            Post post = searchedPost.get();
            Reaction reaction = searchedReaction.get();

            post.react(reaction);
            postService.updatePost(post);
        }
        return "redirect:/";
    }
}
