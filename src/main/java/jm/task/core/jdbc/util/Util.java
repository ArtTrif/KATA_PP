package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String USERNAME = "root";
    public static final String PASSWORD = "301089";
    public static final String CONNECTIONURL = "jdbc:mysql://localhost:3306/schema_test";

    public static Connection getConnection(){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(CONNECTIONURL, USERNAME, PASSWORD);
            Class.forName("com.mysql.cj.jdbc.Driver");
            // устанавливаем автоматическую фиксацию в false
            connection.setAutoCommit(false);
            // фиксируем транзакцию
            connection.commit();
            System.out.println("Мы подключились !\n");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            try {
                // отменяем возвращаем все изменения
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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
