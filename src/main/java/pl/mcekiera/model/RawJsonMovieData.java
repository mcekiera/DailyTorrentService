package pl.mcekiera.model;

public class RawJsonMovieData {
    private String title;
    private int year;
    private String genre;
    private double rating;

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    public RawJsonMovieData(String title, int year, String genre, double rating) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }



}
