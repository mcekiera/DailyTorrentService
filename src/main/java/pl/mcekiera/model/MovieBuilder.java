package pl.mcekiera.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieBuilder {
    private String title = "";
    private String torrentName ="";
    private int year = 0;
    private double rating = 0.0;
    private String genre ="";
    private String torrentUrl ="";
    private Date publication = new Date(0);
    private String imdbId = "";

    public Movie build() {
       return new Movie(title, torrentName, year, rating, genre,torrentUrl, publication, imdbId);
    }

    public String getTitle() {
        return title;
    }

    public MovieBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getYear() {
        return year;
    }

    public MovieBuilder setYear(String year) {
        this.year = Integer.valueOf(year.replaceAll("[^\\d]",""));
        return this;
    }

    public double getRating() {
        return rating;
    }

    public MovieBuilder setRating(String rating) {
        this.rating = Double.valueOf(rating.replaceAll("[^\\d.]",""));
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public MovieBuilder setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public String getTorrentUrl() {
        return torrentUrl;
    }

    public MovieBuilder setTorrentUrl(String torrentUrl) {
        this.torrentUrl = torrentUrl;
        return this;
    }

    public Date getPublication() {
        return publication;
    }

    public MovieBuilder setPublication(String publication) {
        System.out.println(">" + publication + "<");
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        try {
            this.publication =  format.parse(publication.trim());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getImdbId() {
        return imdbId;
    }

    public MovieBuilder setImdbId(String imdbId) {
        this.imdbId = imdbId;
        return this;
    }

    public String getTorrentName() {
        return torrentName;
    }

    public MovieBuilder setTorrentName(String torrentName) {
        this.torrentName = torrentName;
        return this;
    }
}
