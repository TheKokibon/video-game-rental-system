package repository;

import db.DBConnection;
import domain.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdministratorRepository {

    public Administrator login(String username, String password) throws Exception {
        String sql = "SELECT administratorID, username, password " +
                     "FROM administrator WHERE username=? AND password=?";

        Connection conn = DBConnection.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Administrator a = new Administrator();
                    a.setAdministratorID(rs.getInt("administratorID"));
                    a.setUsername(rs.getString("username"));
                    a.setPassword(rs.getString("password"));
                    return a;
                }
                return null;
            }
        }
    }
}