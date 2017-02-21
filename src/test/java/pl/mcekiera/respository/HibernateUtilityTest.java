package pl.mcekiera.respository;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HibernateUtilityTest {

    @Test
    public void hasOnlyOneInstanceOfSessionFacotory() {
        assertEquals("Should return true, only one SessionFactory should exist", HibernateUtility.getSessionFactory(),HibernateUtility.getSessionFactory());
    }
}
