package pl.jdacewicz.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.jdacewicz.socialmedia.domain.Reaction;
import pl.jdacewicz.socialmedia.domain.ReactionCounter;
import pl.jdacewicz.socialmedia.domain.User;
import pl.jdacewicz.socialmedia.service.DBUserDetailsService;
import pl.jdacewicz.socialmedia.service.ReactionCounterService;
import pl.jdacewicz.socialmedia.service.ReactionService;
import pl.jdacewicz.socialmedia.util.FileUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Controller
public class ReactionController {

    private ReactionCounterService reactionCounterService;
    private ReactionService reactionService;
    private DBUserDetailsService detailsService;

    @Autowired
    public ReactionController(ReactionCounterService reactionCounterService, ReactionService reactionService, DBUserDetailsService detailsService) {
        this.reactionCounterService = reactionCounterService;
        this.reactionService = reactionService;
        this.detailsService = detailsService;
    }

    @PostMapping("/react")
    public String react(@RequestParam Map<String, String> body) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        long reactionCounterId = Long.parseLong(body.get("reactionCounterId"));

        Optional<User> userLoggedIn = detailsService.getUser(currentUser);
        Optional<ReactionCounter> foundReactionCounter = reactionCounterService.getReactionCounter(reactionCounterId);

        if (userLoggedIn.isPresent() && foundReactionCounter.isPresent()) {
            ReactionCounter counter = foundReactionCounter.get();
            counter.adjustCount(1, userLoggedIn.get());

            reactionCounterService.updateReactionCounter(counter);
        }
        return "redirect:/";
    }

    @GetMapping("/admin/new-reaction")
    public String reactionCreatingForm() {
        return "/admin/panel-reaction";
    }

    @PostMapping("/admin/new-reaction")
    public String createReaction(@RequestParam Map<String, String> body, @RequestParam("image") MultipartFile file) throws IOException {
        String fileName = FileUtils.generateUniqueName(file.getOriginalFilename());

        Reaction newReaction = new Reaction();
        newReaction.setName(body.get("name"));
        newReaction.setImage(fileName);

        String uploadDir = "uploads/reactions";
        FileUtils.saveFile(uploadDir, fileName, file);

        reactionService.createReaction(newReaction);
        return "redirect:/";
    }
}
