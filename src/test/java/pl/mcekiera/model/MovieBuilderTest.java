package pl.mcekiera.model;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class MovieBuilderTest {
    private MovieBuilder builder;

    @Before
    public void setup() {
        builder = new MovieBuilder();
    }

    @Test
    public void setYearWithMixedInput() {
        builder.setYear("aaa2000");
        assertEquals("Should return only integer, if input contains other char", 2000, builder.getYear());
    }

    @Test
    public void setYearWithProperInput() {
        builder.setYear("1234");
        assertEquals("Should return only integer", 1234, builder.getYear());
    }

    @Test
    public void setYearWithInvalidInput() {
        builder.setYear("aaa");
        assertEquals("Should return only 1900, if input is invalid", 1900, builder.getYear());
    }

    @Test
    public void setRatingWithMixedInput() {
        builder.setRating("aaa2.0");
        assertEquals("Should return only double value if input has other chars", 2.0, builder.getRating(),0.1);
    }

    @Test
    public void setRatingWithIntegerInput() {
        builder.setRating("2");
        assertEquals("Should return double value input is integer", 2.0, builder.getRating(),0.1);
    }

    @Test
    public void setRatingWithProperInput() {
        builder.setRating("2.0");
        assertEquals("Should return double value", 2.0, builder.getRating(),0.1);
    }

    @Test
    public void setRatingWithInvalidInput() {
        builder.setRating("aaa");
        assertEquals("Should return 0.0 if input is invalid", 0.0, builder.getRating(),0.1);
    }

    @Test
    public void setPublication() {
        String date = "Sun, 05 Feb 2017 21:41:02 +0100";
        LocalDateTime ldt = LocalDateTime.of(2017, 2, 5, 21, 41, 2);
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        builder.setPublication(date);
        assertEquals("Should return proper formatted date", zdt.toInstant(), builder.getPublication().toInstant());
    }

    @Test
    public void setInvalidPublication() {
        builder.setPublication("aaa");
        assertEquals("Should return Thu Jan 01 01:00:00 CET 1970 on invalid input", new Date(0), builder.getPublication());
    }

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

        Movie movie = new Movie(title,year,rating,genre,imdbId,torrenName,link,publicationDate,0,0);

        builder.setTitle(title).setYear(Integer.toString(year)).setRating(Double.toString(rating))
                .setGenre(genre).setImdbId(imdbId).setTorrentName(torrenName).setTorrentUrl(link).setPublication(date);

        assertEquals("Should return equal Movie object", movie,builder.build());
    }

    @Test
    public void customToString() {
        String result = ";;0;0.0;;;Thu Jan 01 01:00:00 CET 1970;;0;0";
        assertEquals("Should returns string with all fields values delimited by semicolon", builder.toString(),result);
    }
}
