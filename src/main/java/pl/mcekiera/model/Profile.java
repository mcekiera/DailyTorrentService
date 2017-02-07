package pl.mcekiera.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Profile implements Serializable{
    private String id;
    private String whitelist;
    private String blacklist;
    private double minRating;

    public Profile() {}

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "whitelist")
    public String getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(String whitelist) {
        this.whitelist = whitelist;
    }

    @Basic
    @Column(name = "blacklist")
    public String getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(String blacklist) {
        this.blacklist = blacklist;
    }


    @Basic
    @Column(name = "minRating")
    public double getMinRating() {
        return minRating;
    }

    public void setMinRating(double minRating) {
        this.minRating = minRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        if (!getId().equals(profile.getId())) return false;
        if (getWhitelist() != null ? !getWhitelist().equals(profile.getWhitelist()) : profile.getWhitelist() != null)
            return false;
        return getBlacklist() != null ? getBlacklist().equals(profile.getBlacklist()) : profile.getBlacklist() == null;

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getWhitelist() != null ? getWhitelist().hashCode() : 0);
        result = 31 * result + (getBlacklist() != null ? getBlacklist().hashCode() : 0);
        return result;
    }
}
