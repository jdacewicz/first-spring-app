package pl.jdacewicz.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.jdacewicz.socialmedia.domain.Reaction;
import pl.jdacewicz.socialmedia.domain.ReactionCounter;
import pl.jdacewicz.socialmedia.service.ReactionCounterService;
import pl.jdacewicz.socialmedia.service.ReactionService;

import java.util.Map;
import java.util.Optional;

@Controller
public class ReactionController {

    private ReactionCounterService reactionCounterService;
    private ReactionService reactionService;

    @Autowired
    public ReactionController(ReactionCounterService reactionCounterService, ReactionService reactionService) {
        this.reactionCounterService = reactionCounterService;
        this.reactionService = reactionService;
    }

    @PostMapping("/react")
    public String react(@RequestParam Map<String, String> body) {
        long id = Long.parseLong(body.get("reactionCounterId"));
        Optional<ReactionCounter> foundReactionCounter = reactionCounterService.getReactionCounter(id);

        if (foundReactionCounter.isPresent()) {
            ReactionCounter counter = foundReactionCounter.get();
            counter.adjustCount(1);
            reactionCounterService.updateReactionCounter(counter);
        }
        return "redirect:/";
    }

    @GetMapping("/admin/new-reaction")
    public String reactionCreatingForm() {
        return "/admin/panel-reaction";
    }

    @PostMapping("/admin/new-reaction")
    public String createReaction(@RequestParam Map<String, String> body) {
        Reaction newReaction = new Reaction();
        newReaction.setName(body.get("name"));
        newReaction.setImage(body.get("image"));

        reactionService.createReaction(newReaction);
        return "redirect:/";
    }
}
