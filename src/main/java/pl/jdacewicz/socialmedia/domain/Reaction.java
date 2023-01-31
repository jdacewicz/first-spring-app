package pl.jdacewicz.socialmedia.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "reactions")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reactionId")
    private int id;
    private String name;
    private String image;

    public Reaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int reactionId) {
        this.id = reactionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String reactionName) {
        this.name = reactionName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String reactionImage) {
        this.image = reactionImage;
    }
}
