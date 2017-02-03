package pl.mcekiera.service;

import pl.mcekiera.model.Movie;
import pl.mcekiera.model.MovieBuilder;
import pl.mcekiera.model.RawJsonMovieData;
import pl.mcekiera.model.RawXmlMovieData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
        try {
            System.out.println(title);
            if (jsonProcessor.fetchData("http://www.omdbapi.com/?t=" + title)) {
                RawJsonMovieData data = jsonProcessor.getRawJsonMovieData();
                MovieBuilder movie = new MovieBuilder();
                movie.setTitle(data.getTitle())
                        .setGenre(data.getGenre())
                        .setPublication(xmlData.getPublicationDate())
                        .setRating(data.getRating())
                        .setTorrentUrl(xmlData.getLink())
                        .setYear(data.getYear())
                        .setImdbId(data.getImdbId());
                movies.add(movie.build());
                System.out.println(movie.toString());
            }
        } catch (Exception ex) {

        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        try {
            System.out.print(format.parse("Fri, 03 Feb 2017 20:07:07 +0000"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        MovieDataCombiner combiner = new MovieDataCombiner();
        combiner.getMovieData("https://yourbittorrent.com/movies/rss.xml").forEach(System.out::println);

    }
}
