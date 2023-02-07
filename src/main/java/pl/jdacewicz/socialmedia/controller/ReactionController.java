package pl.jdacewicz.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/post/{id}/react/{reactionId}")
    public void reactToPost(@PathVariable Long id, @PathVariable Long reactionId) {
        Optional<Post> searchedPost = postService.getPost(id);
        Optional<Reaction> searchedReaction = reactionService.getReaction(reactionId);

        if (searchedPost.isPresent() && searchedReaction.isPresent()) {
            Post post = searchedPost.get();
            Reaction reaction = searchedReaction.get();

            post.react(reaction);
            postService.updatePost(post);
        }
    }
}
