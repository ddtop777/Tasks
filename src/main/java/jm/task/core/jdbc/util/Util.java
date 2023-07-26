package jm.task.core.jdbc.util;

import java.sql.Statement;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String PASSWORD_KEY = "db.password";
    private static final String USER_NAME_KEY = "db.username";
    private static final String URL_KEY = "db.url";
    private static SessionFactory sessionFactory;



    public static Connection open() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USER_NAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(User.class);

        try {
            sessionFactory = configuration.buildSessionFactory();
            System.out.println("OK");

        } catch (Exception e) {
            System.out.println("Something went wrong:\n" + e);
        }
        return sessionFactory;
    }
}
