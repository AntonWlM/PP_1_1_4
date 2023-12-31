package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection;

    //todo: ...IF NOT EXISTS - написал сам в запросе.
    private static final String createUsersQuery = "CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR (50), lastName VARCHAR (50), age INT(3))";


    public UserDaoJDBCImpl() {
        connection = new Util().getConnection();
    }

    @Override
    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(createUsersQuery);
        } catch (SQLException e) {
            System.out.println("Ошибка создания TABLE users (таблица уже имеется)");;
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE users");
        } catch (SQLException e) {
            System.out.println("TABLE users отсутствует");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, lastName, age) values (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Invalid saveUser: " + e.getMessage());//todo: так нужно сделать везде
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE from users WHERE id=?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Invalid removeUserById: " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
//        try (ResultSet resultSet = connection.createStatement().executeQuery("select * FROM users")) {//todo: .executeQuery("select * FROM users") - запрос в качестве ресурса ..это перебор точно
          try (Statement statement = connection.createStatement()) {
              ResultSet resultSet = statement.executeQuery("select * FROM users");//todo: выносим SQL-переменную из тела метода
              while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Данные не считаны");
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM users");
        } catch (SQLException e) {
            throw new IllegalStateException("Invalid cleanUsersTable: " + e.getMessage());
        }
    }
}
