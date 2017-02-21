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

    /**
     * @return title value
     */
    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    /**
     * Only for hibernate use
     * @param title value
     */
    private void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return year value
     */
    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    /**
     * Only for hibernate use
     * @param year value
     */
    private void setYear(int year) {
        this.year = year;
    }

    /**
     * @return rating value
     */
    @Basic
    @Column(name = "rating")
    public double getRating() {
        return rating;
    }

    /**
     * Only for hibernate use
     * @param rating value
     */
    private void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * @return genre value
     */
    @Basic
    @Column(name = "genre")
    public String getGenre() {
        return genre;
    }

    /**
     * Only for hibernate use
     * @param genre value
     */
    private void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return torrent url value
     */
    @Basic
    @Column(name = "link")
    public String getTorrentUrl() {
        return torrentUrl;
    }

    /**
     * @param torrentUrl value for torrentUrl field
     */
    public void setTorrentUrl(String torrentUrl) {
        this.torrentUrl = torrentUrl;
    }

    /**
     * @return publication date of torrent value
     */
    @Basic
    @Column(name = "publicationDate")
    public Date getPublicationDate() {
        return publicationDate;
    }

    /**
     * @param publicationDate value for publicationDate field
     */
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * @return imdbId value
     */
    @Id
    @Column(name = "imdbId")
    public String getImdbId() {
        return imdbId;
    }

    /**
     * Only for hibernate use
     * @param imdbId value
     */
    private void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    /**
     * @return torrent file name value
     */
    @Basic
    @Column(name = "torrentName")
    public String getTorrentName() { return torrentName; }

    public void setTorrentName(String torrentName) {
        this.torrentName = torrentName;
    }

    /**
     * @return dismiss requests number
     */
    @Basic
    @Column(name = "dismiss")
    public int getDismiss() {
        return dismiss;
    }

    /**
     * Only for hibernate use
     * @param dismiss value
     */
    private void setDismiss(int dismiss) {
        this.dismiss = dismiss;
    }

    /**
     * @return approve requests number
     */
    @Basic
    @Column(name = "approve")
    public int getApprove() {
        return approve;
    }

    /**
     * Only for hibernate use
     * @param approve value
     */
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
     * Determines if given object is equal to this object. In comparison use title, imdbId and year filds, as rest could
     * change and are not relevant for movie identification.
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
                    imdbId.equals(movie.getImdbId());
        }

        return result;
    }

    /**
     * Basic hashCode implementation, uses title, year and imdbId fields for generating hashcode.
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        int result = getTitle().hashCode();
        result = 31 * result + getYear();
        result = 31 * result + getImdbId().hashCode();
        return result;
    }

}
