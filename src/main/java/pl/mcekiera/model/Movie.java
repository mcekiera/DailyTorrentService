package pl.mcekiera.model;

public class Movie {
    private String title;
    private String year;
    private String rating;
    private String genre;
    private String torrentUrl;
    private String publication;
    private String imdbId;

    public Movie setTitle(String title) {
        this.title = title;
        return this;
    }

    public Movie setYear(String year) {
        this.year = year;
        return this;
    }

    public Movie setRating(String rating) {
        this.rating = rating;
        return this;
    }

    public Movie setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public Movie setTorrentUrl(String torrentUrl) {
        this.torrentUrl = torrentUrl;
        return this;
    }

    public Movie setPublication(String publication) {
        this.publication = publication;
        return this;
    }

    public Movie setImdbId(String imdbId) {
        this.imdbId = imdbId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }

    public String getGenre() {
        return genre;
    }

    public String getTorrentUrl() {
        return torrentUrl;
    }

    public String getPublication() {
        return publication;
    }

    public String getImdbId() {
        return imdbId;
    }

}
