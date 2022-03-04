package jm.task.core.jdbc.dao;

import com.mysql.cj.protocol.Resultset;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void closeConnection() {
        Util.closeConnection(connection);
    }

    public void createUsersTable() {
        try {
            // устанавливаем автоматическую фиксацию в false
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS table_users" +
                    "( id int not null auto_increment primary key, name VARCHAR(50), lastName VARCHAR(50), age MEDIUMINT );");
            preparedStatement.executeUpdate();
            // фиксируем транзакцию
            connection.commit();
        } catch (SQLException e) {
            // отменяем возвращаем все изменения
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS table_users");
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO table_users(name, lastName, age) VALUES (?,?,?);")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }


    }

    public void removeUserById(long id) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM table_users WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User " + id + " удален");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<User> userList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM table_users;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM table_users");
            preparedStatement.executeUpdate();
            System.out.println("Таблица очищена");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
