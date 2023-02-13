package pl.jdacewicz.socialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jdacewicz.socialmedia.domain.ReactionCounter;
import pl.jdacewicz.socialmedia.repository.ReactionCounterRepository;

import java.util.Optional;

@Service
public class ReactionCounterService {

    private ReactionCounterRepository reactionCounterRepository;

    @Autowired
    public ReactionCounterService(ReactionCounterRepository reactionCounterRepository) {
        this.reactionCounterRepository = reactionCounterRepository;
    }

    public Optional<ReactionCounter> getReactionCounter(long id) {
        return reactionCounterRepository.findById(id);
    }

    public void updateReactionCounter(ReactionCounter reactionCounter) {
        reactionCounterRepository.saveAndFlush(reactionCounter);
    }
}
