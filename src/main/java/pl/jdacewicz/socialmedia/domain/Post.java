package pl.jdacewicz.socialmedia.domain;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "postId")
    private long id;

    //TODO Set here user who has created this post.
    private String content;
    private String image;
    private Timestamp creationTime;
    private Date creationDate;
    @OneToMany
    private List<ReactionCount> reactionsCounts;

    public Post() {
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

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
