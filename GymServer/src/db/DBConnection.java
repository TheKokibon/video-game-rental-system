package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    private static final String URL =
            "jdbc:mysql://localhost:3306/gymdb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASS);
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null) instance = new DBConnection();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}