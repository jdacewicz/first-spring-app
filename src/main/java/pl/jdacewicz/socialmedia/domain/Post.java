package pl.jdacewicz.socialmedia.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
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
    @OneToMany
    private List<ReactionCount> reactionsCounts;

    public Post() {
        this.creationTime = LocalTime.now();
        this.creationDate = LocalDate.now();
    }

    public void react(Reaction reaction) {
        Optional<ReactionCount> reactionCount = reactionsCounts.stream()
                .filter(c -> c.getReaction() == reaction)
                .findFirst();

        if (reactionCount.isPresent()) {
            ReactionCount rC = reactionCount.get();
            rC.adjustCount(1);
        } else {
            ReactionCount newReactionCount = new ReactionCount();
            newReactionCount.setReaction(reaction);
            reactionsCounts.add(newReactionCount);
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

    public List<ReactionCount> getReactionsCounts() {
        return reactionsCounts;
    }

    public void setReactionsCounts(List<ReactionCount> postReactionsCount) {
        this.reactionsCounts = postReactionsCount;
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
