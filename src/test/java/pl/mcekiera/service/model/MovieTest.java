package pl.mcekiera.service.model;

import org.junit.Before;
import org.junit.Test;
import pl.mcekiera.model.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class MovieTest {
    private Movie m1;
    private Movie m2;
    private Movie m3;


    @Before
    public void setUp() {
        Locale.setDefault(Locale.ENGLISH);
        String title = "Title";
        String title3 = "Title2";

        int year = 2000;
        int year3 = 1970;

        double rating = 1.00;
        double rating2 = 3.0;

        String genre = "Genre";

        String imdbId = "imdbId";
        String imdbId3 = "imdbId3";

        String torrenName = "TorrentName";
        String torrenName2 = "TorrentName2";

        String link = "Link";

        String date = "Sun, 05 Feb 2017 21:41:02 +0000";
        Date publicationDate = null;

        try {
            publicationDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        m1 = new Movie(title,year,rating,genre,imdbId,torrenName,link,publicationDate,0,0);
        m2 = new Movie(title,year,rating2,genre,imdbId,torrenName2,link,publicationDate,0,0);
        m3 = new Movie(title3,year3,rating,genre,imdbId3,torrenName,link,publicationDate,0,0);
    }

    @Test
    public void equalToSame() {
        assertEquals("Should return true if it is same object", m1,m1);
    }


    @Test
    public void equalToMixed() {
        assertEquals("Should return true, even if some irrelevant fields are different", m1,m2);
    }

    @Test
    public void notEqual() {
        assertNotEquals("Should return false, if relevant fields are different", m1,m3);
    }

    @Test
    public void equalHashcode() {
        assertEquals("Equal objects should return same hashcode", m1.hashCode(),m2.hashCode());
    }

    @Test
    public void notEqualHashcode() {
        assertNotEquals("Not equal objects should return different hashcodes", m1.hashCode(),m3.hashCode());
    }
}
