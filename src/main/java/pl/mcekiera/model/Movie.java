package pl.mcekiera.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Object storing basic data about movie and torrent file with this movie.
 */
@Entity
public class Movie implements Serializable{
    /**
     * Name of a torrent file.
     */
    private String torrentName;
    /**
     * Title of movie.
     */
    private String title;
    /**
     * Year of movie release.
     */
    private int year;
    /**
     * Rating of movie in Internet Movie Databese (IMDB).
     */
    private double rating;
    /**
     * Genre of the movie.
     */
    private String genre;
    /**
     * URL to torrent file with this movie.
     */
    private String torrentUrl;
    /**
     * Date of publication of torrent file.
     */
    private Date publicationDate;
    /**
     * ID of movie in IMDB.
     */
    private String imdbId;
    /**
     * Number of dismiss recommendation requests for this movie
     */
    private int dismiss;
    /**
     * Number of approve recommendation requests for this movie
     */
    private int approve;

    public Movie() {}

    public Movie(String title, int year, double rating, String genre, String imdbId, String torrentName, String torrentUrl, Date publicationDate, int dismiss, int approve) {
        this.title = title;
        this.torrentName = torrentName;
        this.year = year;
        this.rating = rating;
        this.genre = genre;
        this.torrentUrl = torrentUrl;
        this.publicationDate = publicationDate;
        this.imdbId = imdbId;
        this.dismiss = dismiss;
        this.approve = approve;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    private void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "rating")
    public double getRating() {
        return rating;
    }

    private void setRating(double rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "genre")
    public String getGenre() {
        return genre;
    }

    private void setGenre(String genre) {
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

    private void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    @Basic
    @Column(name = "torrentName")
    public String getTorrentName() { return torrentName; }

    public void setTorrentName(String torrentName) {
        this.torrentName = torrentName;
    }

    @Basic
    @Column(name = "dismiss")
    public int getDismiss() {
        return dismiss;
    }

    private void setDismiss(int dismiss) {
        this.dismiss = dismiss;
    }

    @Basic
    @Column(name = "approve")
    public int getApprove() {
        return approve;
    }

    private void setApprove(int approve) {
        this.approve = approve;
    }

    /**
     * Increment the dismiss value by 1
     */
    public void dismiss() {
        dismiss += 1;
    }

    /**
     * Increment the approve value by 1.
     */
    public void approve() {
        approve += 1;
    }

    /**
     * @return title and IMDB id of the movie in format title(imdbId)
     */
    @Override
    public String toString() {
        return title + "(" + imdbId + ")";
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

    /**
     * Basic hashCode implementation.
     * @return
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
