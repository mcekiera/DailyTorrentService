package pl.mcekiera.service;

import pl.mcekiera.model.Movie;
import pl.mcekiera.model.RawJsonMovieData;
import pl.mcekiera.model.RawXmlMovieData;

import java.util.ArrayList;
import java.util.List;

public class MovieDataCombiner {
    XmlMovieDataProcessor xmlProcessor;
    JsonMovieDataProcessor jsonProcessor;
    List<Movie> movies = new ArrayList<>();

    public MovieDataCombiner() {
        xmlProcessor = new XmlMovieDataProcessor();
        jsonProcessor = new JsonMovieDataProcessor();
    }

    public List<Movie> getMovieData(String url) {

        if(xmlProcessor.fetchData(url)) {
            xmlProcessor.getRawXmlMovieData().forEach(this::crateMovieObject);
        }

        return movies;

    }

    private void crateMovieObject(RawXmlMovieData xmlData) {
        String title = xmlData.getTitle().replaceAll("(\\d{4}|[\\W]+).*","");

        if(jsonProcessor.fetchData("http://www.omdbapi.com/?" + title)) {
            RawJsonMovieData data = jsonProcessor.getRawJsonMovieData();
            Movie movie = new Movie();
            movie.setTitle(data.getTitle()).setGenre(data.getGenre()).setPublication(xmlData.getPublicationDate())
                    .setRating(data.getRating()).setTorrentUrl(xmlData.getLink()).setYear(data.getYear());
            movies.add(movie);
        }
    }

    public static void main(String[] args) {
        MovieDataCombiner combiner = new MovieDataCombiner();
        combiner.getMovieData("https://yourbittorrent.com/music/rss.xml").forEach(Object::toString);

    }
}
