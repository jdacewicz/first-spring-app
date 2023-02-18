package pl.jdacewicz.socialmedia.domain;

import jakarta.persistence.*;
import pl.jdacewicz.socialmedia.util.TimeUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    private LocalTime creationTime = LocalTime.now();
    private LocalDate creationDate = LocalDate.now();
    @OneToMany(cascade = {CascadeType.ALL})
    private List<ReactionCounter> reactionCounters = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Comment> comments = new ArrayList<>();

    public Post() {
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public String getElapsedCreationTimeMessage() {
        LocalDateTime creationDateTime = LocalDateTime.of(creationDate, creationTime);

        return TimeUtils.getElapsedTimeMessage(creationDateTime, LocalDateTime.now());
    }

    public String getImagePath() {
        if (image == null) return null;

        return "/uploads/user-photos/" + postCreator.getId() + "/" + image;
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

    public List<ReactionCounter> getReactionCounters() {
        return reactionCounters;
    }

    public void setReactionCounters(List<ReactionCounter> postReactionCounters) {
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
