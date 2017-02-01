package pl.mcekiera.model;

public class RawJsonMovieData {
    private String title;
    private String year;
    private String genre;
    private String rating;
    private String imdbId;

    public RawJsonMovieData(String title, String year, String genre, String rating, String imdbId) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
        this.imdbId = imdbId;
    }


    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getRating() {
        return rating;
    }

    public String getImdbId() {
        return imdbId;
    }



}
