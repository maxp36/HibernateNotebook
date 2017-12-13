package notebook;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    //Annotation based configuration
    private static SessionFactory sessionAnnotationFactory;

    private static SessionFactory buildSessionAnnotationFactory() {
        try {
            // Create the SessionFactory by setting property
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/notebook");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "root");
            configuration.setProperty("hibernate.hbm2ddl.auto", "create");
            configuration.addAnnotatedClass(Contact.class);

            System.out.println("Hibernate Annotation Configuration loaded");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Annotation serviceRegistry created");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionAnnotationFactory() {
        if(sessionAnnotationFactory == null) sessionAnnotationFactory = buildSessionAnnotationFactory();
        return sessionAnnotationFactory;
    }
}
