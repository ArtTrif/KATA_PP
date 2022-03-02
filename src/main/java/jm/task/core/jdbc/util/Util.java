package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String USERNAME = "root";
    public static final String PASSWORD = "301089";
    public static final String CONNECTIONURL = "jdbc:mysql://localhost:3306/schema_test";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(CONNECTIONURL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
            System.out.println("Мы подключились !\n");
        } catch (SQLException e) {
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


}
