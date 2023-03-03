package pl.jdacewicz.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.jdacewicz.socialmedia.domain.Reaction;
import pl.jdacewicz.socialmedia.payroll.NotFoundException;
import pl.jdacewicz.socialmedia.service.ReactionService;

import java.util.List;

@RestController
public class ReactionController {

    private ReactionService reactionService;

    @Autowired
    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @GetMapping("/reactions")
    public List<Reaction> getAll() {
        return reactionService.getAllReactions();
    }

    @GetMapping("/reaction/{id}")
    public Reaction get(@PathVariable Long id) {
        return reactionService.getReaction(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/reaction/{id}")
    public Reaction replace(@RequestBody Reaction newReaction, @PathVariable Long id) {
        return reactionService.getReaction(id).map(reaction -> {
            reaction.setName(newReaction.getName());
            reaction.setImage(newReaction.getImage());
            return reactionService.saveReaction(reaction);

        }).orElseGet(() -> reactionService.saveReaction(newReaction));
    }

    @PostMapping("/reaction")
    public Reaction create(@RequestBody Reaction newReaction) {
        return reactionService.saveReaction(newReaction);
    }

    @DeleteMapping("/reaction/{id}")
    public void delete(@PathVariable Long id){
        reactionService.deleteReaction(id);
    }
}
