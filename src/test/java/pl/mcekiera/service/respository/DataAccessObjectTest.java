package pl.mcekiera.service.respository;

import org.junit.Before;
import org.junit.Test;
import pl.mcekiera.model.Movie;
import pl.mcekiera.respository.DataAccessObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class DataAccessObjectTest {
    Movie movie;

    @Before
    public void setUp() {
        Locale.setDefault(Locale.ENGLISH);
        String title = "Title";
        int year = 2000;
        double rating = 1.00;
        String genre = "Genre";
        String imdbId = "imdbId";
        String torrenName = "TorrentName";
        String link = "Link";

        String date = "Sun, 05 Feb 2017 00:00:00 +0000";
        Date publicationDate = null;
        java.sql.Date sqlDate = null;

        try {
            publicationDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z").parse(date);
            sqlDate = new java.sql.Date(publicationDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        movie = new Movie(title,year,rating,genre,imdbId,torrenName,link,publicationDate);
    }

    @Test
    public void saveFindDelete() {

        DataAccessObject<Movie> point = new DataAccessObject<>();
        point.saveOrUpdate(movie);
        Movie same = point.find(movie.getImdbId());
        assertEquals("Should be same as retrieved from DB",movie,same);

        point.delete(movie);
        same = point.find(movie.getImdbId());
        assertEquals("Should be null after delete from DB",null,same);

    }
}
