package pl.mcekiera.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Contains data about single user preferences in terms of movie recommendations.
 */
@Entity
public class Profile implements Serializable{
    /**
     * id of profile, email address
     */
    private String id;
    /**
     * Favourite user movie genres
     */
    private String whitelist;

    /**
     * Genres disliked by user
     */
    private String blacklist;
    /**
     * Minimal preferred by user IMDB rating for recommendations
     */
    private double minRating;

    public Profile() {}

    public Profile(String id, String whitelist, String blacklist, double minRating) {
        this.id = id;
        this.whitelist = whitelist == null ? "" : whitelist;
        this.blacklist = blacklist == null ? "" : blacklist;
        this.minRating = minRating;
    }

    /**
     * Return id of Profile object.
     * @return id in String format
     */
    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    /**
     * Only for hibernate use
     * @param id value
     */
    private void setId(String id) {
        this.id = id;
    }

    /**
     * @return List of favourite genres in String format like "Action, Drama".
     */
    @Basic
    @Column(name = "whitelist")
    public String getWhitelist() {
        return whitelist;
    }

    /**
     * Only for hibernate use
     * @param whitelist value
     */
    private void setWhitelist(String whitelist) {
        this.whitelist = whitelist;
    }

    /**
     * @return List of disliked genres in String format like "Action, Drama".
     */
    @Basic
    @Column(name = "blacklist")
    public String getBlacklist() {
        return blacklist;
    }

    /**
     * Only for hibernate use
     * @param blacklist value
     */
    private void setBlacklist(String blacklist) {
        this.blacklist = blacklist;
    }

    /**
     * @return minimal rating for recommendations for this user
     */
    @Basic
    @Column(name = "minRating")
    public double getMinRating() {
        return minRating;
    }

    /**
     * Only for hibernate use
     * @param minRating value
     */
    private void setMinRating(double minRating) {
        this.minRating = minRating;
    }

    /**
     * Compare two objects. Profiles are equal if are instances of Profile class and has same id.
     * @param o compared object
     * @return true if equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        return getId().equals(profile.getId());
    }

    /**
     * Basic hashcode implementation
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    /**
     * @return id of profile.
     */
    @Override
    public String toString() {
        return id;
    }
}
