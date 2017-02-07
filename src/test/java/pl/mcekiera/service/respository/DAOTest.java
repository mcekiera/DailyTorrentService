package pl.mcekiera.service.respository;

import org.junit.Before;
import org.junit.Test;
import pl.mcekiera.model.Movie;
import pl.mcekiera.model.Profile;
import pl.mcekiera.respository.DataAccessObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class DAOTest {

    @Before
    public void setUp() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    public void profileDAOTest() {
        Profile profile = new Profile("test@test.pl","Action","Romance",6.0);

        DataAccessObject<Profile> point = new DataAccessObject<>(Profile.class);

        point.saveOrUpdate(profile);
        Profile same = point.find("test@test.pl");
        assertEquals("Profile should be equal to provided",profile,same);

        point.delete(profile);
        same = point.find("test@test.pl");
        assertEquals("Profile should be null after removing from DB",null,same);
    }
    @Test
    public void MovieDaoTest() {

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

        Movie movie = new Movie(title,year,rating,genre,imdbId,torrenName,link,publicationDate);

        DataAccessObject<Movie> point = new DataAccessObject<>(Movie.class);

        point.saveOrUpdate(movie);
        Movie same = point.find(movie.getImdbId());
        assertEquals("Should be same as retrieved from DB",movie,same);

        point.delete(movie);
        same = point.find(movie.getImdbId());
        assertEquals("Should be null after delete from DB",null,same);

    }

}

