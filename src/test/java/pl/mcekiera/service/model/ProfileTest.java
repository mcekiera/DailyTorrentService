package pl.mcekiera.service.model;

import org.junit.Before;
import org.junit.Test;
import pl.mcekiera.model.MovieBuilder;
import pl.mcekiera.model.Profile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ProfileTest {
    private MovieBuilder builder;

    @Before
    public void setup() {
        builder = new MovieBuilder();
    }

    @Test
    public void equalsMixed() {
        Profile p1 = new Profile("test@test.pl","Action","Romance",8.0);
        Profile p2 = new Profile("test@test.pl","Romance","Action",6.0);
        assertEquals("Should be equal if other fields are different", p1,p2);
    }

    @Test
    public void equalsSimple() {
        Profile p1 = new Profile("test@test.pl","Action","Romance",8.0);
        Profile p2 = new Profile("test@test.pl","Action","Romance",8.0);
        assertEquals("Should be equal all is same", p1,p2);
    }

    @Test
    public void notEquals() {
        Profile p1 = new Profile("test@test.pl","Action","Romance",8.0);
        Profile p2 = new Profile("test2@test.pl","Action","Romance",8.0);
        assertNotEquals("Should not be equal if id is different", p1,p2);
    }

    @Test
    public void equalHashCode() {
        Profile p1 = new Profile("test@test.pl","Action","Romance",8.0);
        Profile p2 = new Profile("test@test.pl","Romance","Action",6.0);
        assertEquals("Should return same hashcode if id is equal", p1.hashCode(),p2.hashCode());
    }

    @Test
    public void customToString() {
        Profile p1 = new Profile("test@test.pl","Action","Romance",8.0);
        assertEquals("Should return id","test@test.pl",p1.toString());
    }



}
