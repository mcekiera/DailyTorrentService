package pl.mcekiera.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Provides simpler interface for creation of Movie object. Movie class has complicated constructor
 * with many parameters, MovieBuilder ensure default values and proper order of parameters during object
 * creation. It use builder design patter. It methods corrects input if it is not in proper format for
 * Movie object creation.
 */
public class MovieBuilder {
    private String title = "";
    private String torrentName ="";
    private int year = 0;
    private double rating = 0.0;
    private String genre ="";
    private String torrentUrl ="";
    private Date publication = new Date(0);
    private String imdbId = "";
    private int dismiss = 0;
    private int approve = 0;

    /**
     * Builds Movie object using all fields in constructor.
     * @return new Movie object with provided data.
     */
    public Movie build() {
       return new Movie(title, year, rating, genre, imdbId, torrentName, torrentUrl, publication, dismiss, approve);
    }

    /**
     * @return title field value.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title field value
     * @param title value for title field
     * @return this object for methods chaining
     */
    public MovieBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * @return year field value.
     */
    public int getYear() {
        return year;
    }

    /**
     * Parse input, by extracting fragment containing digits, and set year field value. If input is
     * invalid, set field to value of 1900.
     * @param year String with value for year field
     * @return this object for methods chaining
     */
    public MovieBuilder setYear(String year) {
        String temp = year.replaceAll("[^\\d]","");
        this.year = temp.equals("") ? 1900 : Integer.valueOf(temp);
        return this;
    }

    /**
     * @return rating field value
     */
    public double getRating() {
        return rating;
    }

    /**
     * Parse input, extracting float value if present, and set rating field value. If value not
     * present, set rating to 0.0;
     * @param rating value for title field
     * @return this object for methods chaining
     */
    public MovieBuilder setRating(String rating) {
        String temp = rating.replaceAll("[^\\d.]","");
        this.rating = temp.equals("") ? 0.0 : Double.valueOf(temp);
        return this;
    }

    /**
     * @return genre field value
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Set genre field value
     * @param genre List of values in String format
     * @return this object for method chaining
     */
    public MovieBuilder setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    /**
     * @return torrentUrl field value
     */
    public String getTorrentUrl() {
        return torrentUrl;
    }

    /**
     * Set torrentUrl field value
     * @param torrentUrl String with url
     * @return this object for method chaining
     */
    public MovieBuilder setTorrentUrl(String torrentUrl) {
        this.torrentUrl = torrentUrl;
        return this;
    }

    public Date getPublication() {
        return publication;
    }

    /**
     * Parse input into proper format for further database usage.
     * @param publication String with date
     * @return this object, for method chaining
     */
    public MovieBuilder setPublication(String publication) {
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zz");
        try {
            this.publication =  format.parse(publication.trim());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * @return imdbId field value
     */
    public String getImdbId() {
        return imdbId;
    }

    /**
     * Set imdbId field value
     * @param imdbId IMDB movie id in String format
     * @return this object for method chaining
     */
    public MovieBuilder setImdbId(String imdbId) {
        this.imdbId = imdbId;
        return this;
    }

    public String getTorrentName() {
        return torrentName;
    }

    /**
     * Set torrentName field value
     * @param torrentName torrent file name
     * @return this object for method chaining
     */
    public MovieBuilder setTorrentName(String torrentName) {
        this.torrentName = torrentName;
        return this;
    }

    /**
     * @return dismiss field value.
     */
    public int getDismiss() {
        return dismiss;
    }

    /**
     * Set dismiss field value
     * @param dismiss value
     * @return this object for method chaining
     */
    public MovieBuilder setDismiss(int dismiss) {
        this.dismiss = dismiss;
        return this;
    }

    /**
     * @return approve field value;
     */
    public int getApprove() {
        return approve;
    }

    /**
     * Set approve field value
     * @param approve value
     * @return this object for method chaining
     */
    public MovieBuilder setApprove(int approve) {
        this.approve = approve;
        return this;
    }

    /**
     * @return String with all fields values delimited by semicolon (';') character.
     */
    @Override
    public String toString() {
        return title + ";" + torrentName + ";" + year + ";" + rating + ";" + genre +
                ";" + torrentUrl + ";" + publication + ";" + imdbId + ";" + dismiss +
                ";" + approve;
    }
}
