package pl.jdacewicz.socialmedia.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "reactionsCounts")
public class ReactionCount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reactionCountId")
    private long id;
    @OneToOne
    private Reaction reaction;
    private int count;

    public ReactionCount() {
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
