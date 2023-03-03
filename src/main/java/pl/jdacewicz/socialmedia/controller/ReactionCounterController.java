package pl.jdacewicz.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.jdacewicz.socialmedia.domain.ReactionCounter;
import pl.jdacewicz.socialmedia.payroll.NotFoundException;
import pl.jdacewicz.socialmedia.service.ReactionCounterService;

@RestController
public class ReactionCounterController {

    private ReactionCounterService counterService;

    @Autowired
    public ReactionCounterController(ReactionCounterService counterService) {
        this.counterService = counterService;
    }

    @PutMapping("/reaction-counter/{id}")
    public ReactionCounter replaceReactionCounter(@RequestBody ReactionCounter newReactionCounter, @PathVariable Long id) {
        return counterService.getReactionCounter(id).map(counter -> {
            counter.setReaction(newReactionCounter.getReaction());
            counter.setCount(newReactionCounter.getCount());
            counter.setUsersReacted(newReactionCounter.getUsersReacted());
            return counterService.saveReactionCounter(newReactionCounter);

        }).orElseGet(() -> counterService.saveReactionCounter(newReactionCounter));
    }
    @GetMapping("/reaction-counter/{id}")
    public ReactionCounter getReactionsByPostId(@PathVariable Long id) {
        return counterService.getReactionCounter(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

}
