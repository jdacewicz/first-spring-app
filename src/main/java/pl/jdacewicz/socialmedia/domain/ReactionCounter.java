package pl.jdacewicz.socialmedia.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "reactionsCounters")
public class ReactionCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reactionCountId")
    private long id;
    @OneToOne
    private Reaction reaction;
    private int count = 0;

    public ReactionCounter() {
    }

    public ReactionCounter(long id, Reaction reaction) {
        this.id = id;
        this.reaction = reaction;
    }

    public ReactionCounter(Reaction reaction) {
        this.reaction = reaction;
    }

    public void adjustCount(int value) {
        count += value;
    }

    public void subtractCount(int value) {
        if (count >= 0) {
            count -= value;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long reactionCountId) {
        this.id = reactionCountId;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int reactionCount) {
        this.count = reactionCount;
    }
}
