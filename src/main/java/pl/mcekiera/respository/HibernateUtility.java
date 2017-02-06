package pl.mcekiera.respository;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Helper class for Hibernate service.
 */
public class HibernateUtility {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    static {
        try {

            serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure( "hibernate.cfg.xml" )
                    .build();

            Metadata metadata = new MetadataSources( serviceRegistry )
                    .getMetadataBuilder()
                    .build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * @return  SessionFactory object necessary for database connection by Hibernate.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
