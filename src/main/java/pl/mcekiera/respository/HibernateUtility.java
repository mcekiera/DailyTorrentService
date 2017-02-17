package pl.mcekiera.respository;

import org.apache.log4j.Logger;
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
    private static Logger log = Logger.getLogger(HibernateUtility.class);
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    static {
        try {
            log.info("Initializing session factory");
            serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure( "hibernate.cfg.xml" )
                    .build();

            Metadata metadata = new MetadataSources( serviceRegistry )
                    .getMetadataBuilder()
                    .build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (HibernateException ex) {
            log.error(ex.getMessage());
        }
    }

    /**
     * @return  SessionFactory object necessary for database connection by Hibernate.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
