package pl.mcekiera.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Movie implements Serializable{
    private String torrentName;
    private String title;
    private int year;
    private double rating;
    private String genre;
    private String torrentUrl;
    private Date publicationDate;
    private String imdbId;

    public Movie() {

    }

    public Movie(String title, int year, double rating, String genre, String imdbId, String torrentName, String torrentUrl, Date publicationDate) {
        this.title = title;
        this.torrentName = torrentName;
        this.year = year;
        this.rating = rating;
        this.genre = genre;
        this.torrentUrl = torrentUrl;
        this.publicationDate = publicationDate;
        this.imdbId = imdbId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "rating")
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Basic
    @Column(name = "link")
    public String getTorrentUrl() {
        return torrentUrl;
    }

    public void setTorrentUrl(String torrentUrl) {
        this.torrentUrl = torrentUrl;
    }

    @Basic
    @Column(name = "publicationDate")
    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Id
    @Column(name = "imdbId")
    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    @Basic
    @Column(name = "torrentName")
    public String getTorrentName() { return torrentName; }

    public void setTorrentName(String torrentName) {
        this.torrentName = torrentName;
    }

    @Override
    public String toString() {
        return title + "-" + year + "-" + rating + "-" + genre + "-" + publicationDate;
    }

    /**
     * Determines if given object is equal to this object. In comparison use all fields, with exception of publication
     * date, meaning date of torrent file publication, which is not relevant information in movie context.
     * @param o  object to coparison.
     * @return  tru if both objects are equal
     */
    @Override
    public boolean equals(Object o){
        boolean result;
        if((o == null) || (getClass() != o.getClass())){
            result = false;
        } else{
            Movie movie = (Movie)o;
            result = title.equals(movie.getTitle()) &&
                    year == movie.getYear() &&
                    rating == movie.getRating() &&
                    genre.equals(movie.getGenre()) &&
                    torrentUrl.equals(movie.getTorrentUrl()) &&
                    imdbId.equals(movie.getImdbId()) &&
                    torrentName.equals(movie.getTorrentName());
        }

        return result;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
