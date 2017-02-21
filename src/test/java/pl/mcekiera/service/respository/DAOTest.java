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
        Profile profile2 = new Profile("test@test2.pl","Drama","Romance",6.0);
        Profile profile3 = new Profile("test@test3.pl","Comedy","Romance",6.0);

        DataAccessObject<Profile> point = new DataAccessObject<>(Profile.class);

        point.saveOrUpdate(profile);
        point.saveOrUpdate(profile2);
        point.saveOrUpdate(profile3);

        Profile same = point.find("test@test.pl");
        Profile same2 = point.find("test@test2.pl");
        Profile same3 = point.find("test@test3.pl");

        assertEquals("Profile should be equal to provided",profile,same);
        assertEquals("Profile should be equal to provided",profile2,same2);
        assertEquals("Profile should be equal to provided",profile3,same3);

        point.delete(profile);
        point.delete(profile2);
        point.delete(profile3);

        same = point.find("test@test.pl");
        same2 = point.find("test@test2.pl");
        same3 = point.find("test@test3.pl");

        assertEquals("Profile should be null after removing from DB",null,same);
        assertEquals("Profile should be null after removing from DB",null,same2);
        assertEquals("Profile should be null after removing from DB",null,same3);
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

        Movie movie = new Movie(title,year,rating,genre,imdbId,torrenName,link,publicationDate,0,0);

        DataAccessObject<Movie> point = new DataAccessObject<>(Movie.class);

        point.saveOrUpdate(movie);
        Movie same = point.find(movie.getImdbId());
        assertEquals("Should be same as retrieved from DB",movie,same);

        point.delete(movie);
        same = point.find(movie.getImdbId());
        assertEquals("Should be null after delete from DB",null,same);

    }

}

