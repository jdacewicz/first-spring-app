package pl.jdacewicz.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.jdacewicz.socialmedia.domain.ReactionCounter;
import pl.jdacewicz.socialmedia.service.ReactionCounterService;

import java.util.Map;
import java.util.Optional;

@Controller
public class ReactionController {

    private ReactionCounterService reactionCounterService;

    @Autowired
    public ReactionController(ReactionCounterService reactionCounterService) {
        this.reactionCounterService = reactionCounterService;
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
}
