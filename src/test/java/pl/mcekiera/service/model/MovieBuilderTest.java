package pl.mcekiera.service.model;

import org.junit.Test;
import pl.mcekiera.model.Movie;
import pl.mcekiera.model.MovieBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class MovieBuilderTest {


    @Test
    public void build() {
        Locale.setDefault(Locale.ENGLISH);
        String title = "Title";
        int year = 2000;
        double rating = 1.00;
        String genre = "Genre";
        String imdbId = "imdbId";
        String torrenName = "TorrentName";
        String link = "Link";

        String date = "Sun, 05 Feb 2017 21:41:02 +0000";
        Date publicationDate = null;

        try {
            publicationDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Movie movie = new Movie(title,year,rating,genre,imdbId,torrenName,link,publicationDate);

        MovieBuilder builder = new MovieBuilder();
        builder.setTitle(title).setYear(Integer.toString(year)).setRating(Double.toString(rating))
                .setGenre(genre).setImdbId(imdbId).setTorrentName(torrenName).setTorrentUrl(link).setPublication(date);

        assertEquals(movie,builder.build());
    }
}
