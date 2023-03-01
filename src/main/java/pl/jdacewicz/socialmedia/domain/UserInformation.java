package pl.jdacewicz.socialmedia.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class UserInformation {

    @Transient
    public final static String PROFILE_PICTURES_DIRECTORY_PATH = "uploads/user-profile-pictures";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstname;
    private String lastname;
    private String profilePicture;
    private String gender;
    @OneToOne(mappedBy = "userInformation")
    @JsonBackReference
    private User user;

    public UserInformation() {
    }

    public String getProfilePicturePath() {
        if (profilePicture == null) return null;

        return "/" + PROFILE_PICTURES_DIRECTORY_PATH + "/" + user.getId() + "/" + profilePicture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
