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

    public Reaction(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
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
