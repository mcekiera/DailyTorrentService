//package pl.mcekiera.respository;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import pl.mcekiera.model.Movie;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//
///**
// * Movie class Data Access Object.
// */
//public class MovieDAO extends DataAccessObject<Movie>{
//    private SessionFactory factory = HibernateUtility.getSessionFactory();
//
//    /**
//     * Saves or update record of given movie represented by given Movie object.
//     * @param movie
//     */
//    public void saveOrUpdate(Movie movie) {
//        Session session = factory.openSession();
//        session.beginTransaction();
//
//        try {
//            session.saveOrUpdate(movie);
//        } catch (HibernateException ex) {
//            ex.printStackTrace();
//        }
//
//        session.getTransaction().commit();
//        session.close();
//    }
//
//    /**
//     * Retrieve movie of given imdbId from database.
//     * @param id of movie from IMDB
//     * @return Movie object.
//     */
//    public Movie find(String id) {
//        Movie movie = null;
//        Session session = factory.openSession();
//
//        try {
//           movie = (Movie) session.find(Movie.class, id);
//        } catch (HibernateException ex) {
//            ex.printStackTrace();
//        }
//
//        session.close();
//        return movie;
//    }
//
//    /**
//     * Removes records of movie represented by given Movie object from database
//     * @param movie
//     */
//    public void delete(Movie movie) {
//        Session session = factory.openSession();
//        session.beginTransaction();
//
//        try {
//            session.delete(movie);
//        } catch (HibernateException ex) {
//            ex.printStackTrace();
//        }
//
//        session.getTransaction().commit();
//        session.close();
//    }
//
//    public static void main(String[] args) {
//        Session session = HibernateUtility.getSessionFactory().openSession();
//        session.beginTransaction();
//
//        Locale.setDefault(Locale.ENGLISH);
//        String title = "Title";
//        int year = 2000;
//        double rating = 1.00;
//        String genre = "Genre";
//        String imdbId = "imdbId";
//        String torrenName = "TorrentName";
//        String link = "Link";
//
//        String date = "Sun, 05 Feb 2017 00:00:00 +0000";
//        Date publicationDate = null;
//        java.sql.Date d = null;
//
//        try {
//            publicationDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z").parse(date);
//            d = new java.sql.Date(publicationDate.getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        Movie movie = new Movie(title,year,rating,genre,imdbId,torrenName,link,d);
////
////        session.saveOrUpdate(movie);
////        session.getTransaction().commit();
//
//        session.delete(movie);
//        session.getTransaction().commit();
//        session.close();
//        HibernateUtility.getSessionFactory().close();
//
//    }
//}
