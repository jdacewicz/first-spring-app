package pl.jdacewicz.socialmedia.domain;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "commentId")
    private long id;

    //TODO Set here user who has created this comment.

    private String content;
    private String image;
    private Timestamp creationTime;
    private Date creationDate;
    @OneToMany
    private List<ReactionCounter> reactionsCounters;

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public List<ReactionCounter> getReactionsCounters() {
        return reactionsCounters;
    }

    public void setReactionsCounters(List<ReactionCounter> reactionsCounters) {
        this.reactionsCounters = reactionsCounters;
    }
}
