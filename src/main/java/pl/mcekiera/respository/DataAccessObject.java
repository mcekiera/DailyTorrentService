package pl.mcekiera.respository;

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

        try {
            session.saveOrUpdate(object);
        } catch (HibernateException ex) {
            ex.printStackTrace();
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

        try {
            session.delete(object);
        } catch (HibernateException ex) {
            ex.printStackTrace();
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

        try {
            object = session.find(typeParameterClass, id);
        } catch (HibernateException ex) {
            ex.printStackTrace();
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
        Session session = factory.openSession();
        TypedQuery<T> query = session.createQuery(sqlQuery,typeParameterClass);
        List<T> result = query.getResultList();
        session.close();
        return result;
    }
}
