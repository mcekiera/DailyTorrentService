package pl.mcekiera.respository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import pl.mcekiera.model.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MovieDAO {

    public void save(Movie movie) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(movie);
        session.getTransaction().commit();
        session.close();
    }

    public Movie find(String id) {
        Movie movie = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
           movie = (Movie) session.find(Movie.class, id);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        session.close();
        return movie;
    }

    public void delete(Movie movie) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(movie);
        session.getTransaction().commit();
        session.close();
    }

    public static void main(String[] args) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();

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
        java.sql.Date d = null;

        try {
            publicationDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z").parse(date);
            d = new java.sql.Date(publicationDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Movie movie = new Movie(title,year,rating,genre,imdbId,torrenName,link,d);
//
//        session.save(movie);
//        session.getTransaction().commit();

        session.delete(movie);
        session.getTransaction().commit();
        session.close();
        HibernateUtility.getSessionFactory().close();

    }
}
