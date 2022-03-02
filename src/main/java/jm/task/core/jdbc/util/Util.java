package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String USERNAME = "root";
    public static final String PASSWORD = "301089";
    public static final String CONNECTIONURL = "jdbc:mysql://localhost:3306/schema_test";

    public static Connection getConnection  () {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(CONNECTIONURL, USERNAME, PASSWORD);
            if(connection != null)
                System.out.println("Мы подключились !\n");
            else
                System.exit(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
