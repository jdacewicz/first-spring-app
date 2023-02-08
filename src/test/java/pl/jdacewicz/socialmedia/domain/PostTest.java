package pl.jdacewicz.socialmedia.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    private Post post;

    @BeforeEach
    public void setUp() {
        this.post = new Post();
    }

    @Test
    void reactionCounterValueIsIncreasedIfReactionAlreadyCounted() {
        Reaction reaction = new Reaction(1, "testName", "image");
        Reaction reactionCopy = reaction;

        List<ReactionCounter> reactionCounterList = post.getReactionsCounters();
        reactionCounterList.add(new ReactionCounter(1, reaction));
        post.setReactionsCounters(reactionCounterList);

        post.react(reactionCopy);
        int count = post.getReactionsCounters().stream()
                .filter(r -> r.getReaction() == reactionCopy)
                .findFirst()
                .get()
                .getCount();
        assertEquals(2, count);
    }
    @Test
    void newReactionCounterIsCreatedIfReactionAlreadyNotCounted() {
        Reaction reaction = new Reaction(1, "testName", "image");

        post.react(reaction);
        int count = post.getReactionsCounters().stream()
                .filter(r -> r.getReaction() == reaction)
                .findFirst()
                .get()
                .getCount();
        assertEquals(1, count);
    }
}