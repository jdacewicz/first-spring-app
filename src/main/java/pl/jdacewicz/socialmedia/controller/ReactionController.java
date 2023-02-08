package pl.jdacewicz.socialmedia.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jdacewicz.socialmedia.domain.Post;
import pl.jdacewicz.socialmedia.domain.Reaction;
import pl.jdacewicz.socialmedia.service.PostService;
import pl.jdacewicz.socialmedia.service.ReactionService;

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

    @PostMapping("/post/{postId}/react/{reactionId}")
    public void reactToPost(@PathVariable Long postId, @PathVariable Long reactionId) {
        Optional<Post> searchedPost = postService.getPost(postId);
        Optional<Reaction> searchedReaction = reactionService.getReaction(reactionId);

        if (searchedPost.isPresent() && searchedReaction.isPresent()) {
            Post post = searchedPost.get();
            Reaction reaction = searchedReaction.get();

            post.react(reaction);
            postService.updatePost(post);
        }
    }
}
