package pl.mcekiera.service;

import pl.mcekiera.model.Movie;
import pl.mcekiera.model.RawJsonMovieData;
import pl.mcekiera.model.RawXmlMovieData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String title = xmlData.getTitle();
        String year;
        Matcher matcher = Pattern.compile("((?:.(?!(\\d{4})|HD|3D|LD|[\\[\\](){}]))*.).*(\\d{4})?.*").matcher(title);
        matcher.find();
        title = matcher.group(1).replaceAll("[\\s_.]","+");
        year = null == matcher.group(2) ? "" : "&y=" + matcher.group(2);
        System.out.println(xmlData.getTitle());
        System.out.println(title);

        if(jsonProcessor.fetchData("http://www.omdbapi.com/?t=" + title + year)) {
            RawJsonMovieData data = jsonProcessor.getRawJsonMovieData();
            Movie movie = new Movie();
            movie.setTitle(data.getTitle())
                    .setGenre(data.getGenre())
                    .setPublication(xmlData.getPublicationDate())
                    .setRating(data.getRating())
                    .setTorrentUrl(xmlData.getLink())
                    .setYear(data.getYear())
                    .setImdbId(data.getImdbId());
            movies.add(movie);
        }
    }

    public static void main(String[] args) {
        MovieDataCombiner combiner = new MovieDataCombiner();
        combiner.getMovieData("https://yourbittorrent.com/movies/rss.xml").forEach(Object::toString);

    }
}
