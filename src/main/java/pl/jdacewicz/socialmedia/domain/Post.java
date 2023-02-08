package pl.jdacewicz.socialmedia.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "postId")
    private long id;
    @OneToOne
    private User postCreator;
    private String content;
    private String image;
    private LocalTime creationTime;
    private LocalDate creationDate;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<ReactionCounter> reactionCounters = new ArrayList<>();

    public Post() {
        this.creationTime = LocalTime.now();
        this.creationDate = LocalDate.now();
    }

    public void react(Reaction reaction) {
        Optional<ReactionCounter> reactionCounter = reactionCounters.stream()
                .filter(c -> c.getReaction() == reaction)
                .findFirst();

        if (reactionCounter.isPresent()) {
            ReactionCounter rC = reactionCounter.get();
            rC.adjustCount(1);
        } else {
            ReactionCounter newReactionCounter = new ReactionCounter();
            newReactionCounter.setReaction(reaction);
            reactionCounters.add(newReactionCounter);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long postId) {
        this.id = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String postContent) {
        this.content = postContent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String postImage) {
        this.image = postImage;
    }

    public List<ReactionCounter> getReactionsCounters() {
        return reactionCounters;
    }

    public void setReactionsCounters(List<ReactionCounter> postReactionCounters) {
        this.reactionCounters = postReactionCounters;
    }

    public LocalTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public User getPostCreator() {
        return postCreator;
    }

    public void setPostCreator(User postCreator) {
        this.postCreator = postCreator;
    }
}
