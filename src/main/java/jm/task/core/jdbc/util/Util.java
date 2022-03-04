package jm.task.core.jdbc.util;

import com.fasterxml.classmate.AnnotationConfiguration;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public static final String USERNAME = "root";
    public static final String PASSWORD = "301089";
    public static final String CONNECTIONURL = "jdbc:mysql://localhost:3306/schema_test";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(CONNECTIONURL, USERNAME, PASSWORD);
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Мы подключились !\n");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ///// ********* Hibernate ********** /////
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                //Конфигурации hibernate которые эквивалентны hibernate.cfg.xml
                Properties prop = new Properties();
                prop.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                prop.put(Environment.URL, "jdbc:mysql://localhost:3306/schema_test?useSSL=false");
                prop.put(Environment.USER, "root");
                prop.put(Environment.PASS, "301089");
                prop.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                prop.put(Environment.SHOW_SQL, "true");
                prop.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                prop.put(Environment.HBM2DDL_AUTO, "create-drop");
                configuration.setProperties(prop);
                //добавляем Класс(сущность)
                configuration.addAnnotatedClass(User.class);
                /*ServiceRegistry содержит службы, которые понадобятся Hibernate во время начальной загрузки и во время выполнения.
                StandardServiceRegistryBuilder — это Конструктор для стандартных ServiceRegistry экземпляров.*/
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void closeSessionFactory(SessionFactory sessionFactory) {
        try {
            sessionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
