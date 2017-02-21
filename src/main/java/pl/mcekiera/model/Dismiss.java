package pl.mcekiera.model;

import javax.persistence.*;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "imdbId","profileId"}))
@Entity
public class Dismiss {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String imdbId;
    private String profileId;

    public Dismiss(){}

    public Dismiss(String profileId, String movieId) {
        this.profileId = profileId;
        this.imdbId = movieId;

    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "imdbId")
    public String getImdbId() {
        return imdbId;
    }

    private void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    @Basic
    @Column(name = "profileId")
    public String getProfileId() {
        return profileId;
    }

    private void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    @Override
    public String toString() {
        return "Dissmissed: " + profileId + "-" + imdbId;
    }
}
