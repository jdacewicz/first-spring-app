package pl.jdacewicz.socialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jdacewicz.socialmedia.domain.Reaction;
import pl.jdacewicz.socialmedia.repository.ReactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReactionService {

    private ReactionRepository reactionRepository;

    @Autowired
    public ReactionService(ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }

    public Optional<Reaction> getReaction(Long id) {
        return reactionRepository.findById(id);
    }

    public List<Reaction> getAllReactions() {
        return reactionRepository.findAll();
    }

    public Reaction saveReaction(Reaction reaction) {
        return reactionRepository.saveAndFlush(reaction);
    }

    public void deleteReaction(Long id) {
        reactionRepository.deleteById(id);
    }
}
