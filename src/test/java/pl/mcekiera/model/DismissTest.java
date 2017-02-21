package pl.mcekiera.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class DismissTest {

    @Test
    public void equal() {
        Dismiss d1 = new Dismiss("test@test.pl","tt000001");
        Dismiss d2 = new Dismiss("test@test.pl","tt000001");
        assertEquals("Should return treu if objects has same field values", d1,d2);
    }

    @Test
    public void notEqual() {
        Dismiss d1 = new Dismiss("test@test.pl","tt000001");
        Dismiss d2 = new Dismiss("test2@test.pl","tt000001");
        assertNotEquals("Should return false if objects has different field values",d1,d2);
    }

    @Test
    public void equalHashcode() {
        Dismiss d1 = new Dismiss("test@test.pl","tt000001");
        Dismiss d2 = new Dismiss("test@test.pl","tt000001");
        assertEquals("Equal objects should return same hashcode", d1.hashCode(),d2.hashCode());
    }

    @Test
    public void notEqualHashcode() {
        Dismiss d1 = new Dismiss("test@test.pl","tt000001");
        Dismiss d2 = new Dismiss("test2@test.pl","tt000001");
        assertNotEquals("Not equal objects should return different hashcodes", d1.hashCode(),d2.hashCode());
    }
}
