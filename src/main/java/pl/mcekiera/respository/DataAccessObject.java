package pl.mcekiera.respository;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Provide interface for Hibernate common actions. Parametrized class,
 * @param <T> Class for which DataAccessObject provide Hibernate services.
 */
public class DataAccessObject<T> {

    /**
     * Instance of shared SessionFactory object, necessary for Hibernate integration.
     */
    private static SessionFactory factory = HibernateUtility.getSessionFactory();
    private static Logger log = Logger.getLogger(DataAccessObject.class);
    private final Class<T> typeParameterClass;

    public DataAccessObject(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }
    /**
     * Saves or update record of given entity represented by given object.
     * @param object to save
     */
    public void saveOrUpdate(T object) {
        Session session = factory.openSession();
        session.beginTransaction();

        log.info("Save or update object: " + object.toString());
        try {
            session.saveOrUpdate(object);
        } catch (HibernateException ex) {
            log.error(ex.getMessage());
        }

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Removes records of entity, represented by given object class,  from database
     * @param object of T class
     */
    public void delete(T object) {
        Session session = factory.openSession();
        session.beginTransaction();

        log.info("Delete object: " + object.toString());
        try {
            session.delete(object);
        } catch (HibernateException ex) {
            log.error(ex.getMessage());
        }

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Retrieve object of given id from database.
     * @param id of object
     * @return T object
     */
    public T find(String id) {
        T object = null;
        Session session = factory.openSession();

        log.info("Find object: " + id);
        try {
            object = session.find(typeParameterClass, id);
        } catch (HibernateException ex) {
            log.error(ex.getMessage());
        }

        session.close();
        return object;
    }

    /**
     * Execute a query on database and return a list of objects.
     * @param sqlQuery query to execute.
     * @return List of objects, if query id properly designed, should return object of DataAccesObject's
     * generic type, in other situation return just Objects list.
     */
    public List<T> query(String sqlQuery) {
        log.info("Query processed: " + sqlQuery);
        Session session = factory.openSession();
        TypedQuery<T> query = session.createQuery(sqlQuery,typeParameterClass);
        List<T> result = query.getResultList();
        session.close();
        return result;
    }
}
