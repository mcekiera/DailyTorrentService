package pl.mcekiera.model;

public class Movie {
    private String title;
    private int year;
    private double rating;
    private String genre;
    private String torrentUrl;
    private String publication;

    public Movie setTitle(String title) {
        this.title = title;
        return this;
    }

    public Movie setYear(int year) {
        this.year = year;
        return this;
    }

    public Movie setRating(double rating) {
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


    
    

}
