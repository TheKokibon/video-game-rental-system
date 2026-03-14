package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    private DBConnection() throws SQLException {
        try {
            Properties properties = new Properties();

            InputStream input = getClass().getClassLoader()
                    .getResourceAsStream("db/db.properties");

            properties.load(input);

            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);

        } catch (IOException e) {
            throw new RuntimeException("Greška pri učitavanju db.properties fajla", e);
        }
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}