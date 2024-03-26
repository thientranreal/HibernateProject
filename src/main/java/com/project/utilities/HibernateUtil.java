package com.project.utilities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory factory;

    // Get session factory
    public static SessionFactory getInstance() {
        try {
            if (factory == null) {
                factory = new Configuration().configure().buildSessionFactory();
            }
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return factory;
    }

    // Close SessionFactory
    public static void close() {
        if (factory != null) {
            factory.close();
        }
    }
}
