package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String dbUrl = "jdbc:mysql://localhost:3306/userbase";//todo: static - зачем?
    private static final String dbUserName = "root";
    private static final String dbPass = "root";

    private Connection connection;

    public Connection getConnection() {
        connection = null;
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbPass);
        } catch (SQLException e) {
            System.out.println("Соединение не установлено или ошибка соединения");
        }
        return connection;
    }
}
