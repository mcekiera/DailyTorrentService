package pl.mcekiera.model;

public class MovieBuilder {
    private String title;
    private String torrentName;
    private String year;
    private String rating;
    private String genre;
    private String torrentUrl;
    private String publication;
    private String imdbId;

    public MovieBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public MovieBuilder setTorrentName(String torrentName) {
        this.torrentName = torrentName;
        return this;
    }

    public MovieBuilder setYear(String year) {
        this.year = year;
        return this;
    }

    public MovieBuilder setRating(String rating) {
        this.rating = rating;
        return this;
    }

    public MovieBuilder setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public MovieBuilder setTorrentUrl(String torrentUrl) {
        this.torrentUrl = torrentUrl;
        return this;
    }

    public MovieBuilder setPublication(String publication) {
        this.publication = publication;
        return this;
    }

    public MovieBuilder setImdbId(String imdbId) {
        this.imdbId = imdbId;
        return this;
    }

    public Movie build() {

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

    public String getTorrentName() {
        return torrentName;
    }
}
