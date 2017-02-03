package pl.mcekiera.model;

import java.util.Date;

public class Movie {
    private final String torrentName;
    private final String title;
    private final int year;
    private final double rating;
    private final String genre;
    private final String torrentUrl;
    private final Date publicationDate;
    private final String imdbId;

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

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    public String getGenre() {
        return genre;
    }

    public String getTorrentUrl() {
        return torrentUrl;
    }

    public Date getPublication() {
        return publicationDate;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getTorrentName() { return torrentName; }

    @Override
    public String toString() {
        return title + "-" + year + "-" + rating + "-" + genre + "-" + publicationDate;
    }

}
