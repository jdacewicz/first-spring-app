package pl.jdacewicz.socialmedia.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReactionCounterTest {

    Reaction reaction = new Reaction();
    ReactionCounter counter;

    @BeforeEach
    void setUp() {
        counter = new ReactionCounter(reaction);
    }

    @Test
    void ProperCountChangeOnNotStoredUserProvided() {
        User user = new User();
        user.setId(1);

        counter.changeCount(1, user);

        assertEquals(1, counter.getCount());
    }

    @Test
    void ProperCountChangeOnStoredUserProvided() {
        User user = new User();
        user.setId(1);
        User userCopy = user;

        counter.changeCount(1, user);
        counter.changeCount(1, userCopy);

        assertEquals(0, counter.getCount());
    }
}