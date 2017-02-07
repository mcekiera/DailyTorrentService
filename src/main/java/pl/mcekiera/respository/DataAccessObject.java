package pl.mcekiera.respository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.mcekiera.model.Movie;
import pl.mcekiera.model.Profile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Provide interface for Hibernate common actions. Parametrized class,
 * @param <T> Class for which DataAccessObject provide Hibernate services.
 */
public class DataAccessObject<T> {

    /**
     * Instance of shared SessionFactory object, necessary for Hibernate integration.
     */
    private static SessionFactory factory = HibernateUtility.getSessionFactory();
    private final Class<T> typeParameterClass;

    public DataAccessObject(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }
    /**
     * Saves or update record of given entity represented by given object.
     * @param object
     */
    public void saveOrUpdate(T object) {
        Session session = factory.openSession();
        session.beginTransaction();

        try {
            session.saveOrUpdate(object);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        session.getTransaction().commit();
        session.close();
    };

    /**
     * Removes records of entity, represented by given object class,  from database
     * @param object of T class
     */
    public void delete(T object) {
        Session session = factory.openSession();
        session.beginTransaction();

        try {
            session.delete(object);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        session.getTransaction().commit();
        session.close();
    };

    /**
     * Retrieve object of given id from database.
     * @param id of object
     * @return T object
     */
    public T find(String id) {
        T object = null;
        Session session = factory.openSession();

        try {
            object = (T) session.find(typeParameterClass, id);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }

        session.close();
        return object;
    }

    public static void main(String[] args) {
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


        point.delete(movie);
        same = point.find(movie.getImdbId());

        DataAccessObject<Profile> profileDataAccessObject = new DataAccessObject<>(Profile.class);
        Profile profile = new Profile("test@test.pl","Action","Romance",6.0);

       profileDataAccessObject.delete(profileDataAccessObject.find("test@test.pl"));
    }

}
