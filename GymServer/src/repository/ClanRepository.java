package repository;

import db.DBConnection;
import domain.Administrator;
import domain.Clan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClanRepository {

    public List<Clan> getAll() throws Exception {
        String sql = "SELECT clanID, ime, prezime, email, telefon, aktivan, administratorID FROM clan";

        Connection conn = DBConnection.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            List<Clan> list = new ArrayList<>();

            while (rs.next()) {
                Clan c = new Clan();
                c.setClanID(rs.getInt("clanID"));
                c.setIme(rs.getString("ime"));
                c.setPrezime(rs.getString("prezime"));
                c.setEmail(rs.getString("email"));
                c.setTelefon(rs.getString("telefon"));
                c.setAktivan(rs.getBoolean("aktivan"));

                Administrator a = new Administrator();
                a.setAdministratorID(rs.getInt("administratorID"));
                c.setAdministrator(a);

                list.add(c);
            }

            return list;
        }

    }

    public void save(domain.Clan c) throws Exception {
        String sql = "INSERT INTO clan (ime, prezime, email, telefon, aktivan, administratorID) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        java.sql.Connection conn = db.DBConnection.getInstance().getConnection();

        try (java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getIme());
            ps.setString(2, c.getPrezime());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getTelefon());
            ps.setBoolean(5, c.isAktivan());
            ps.setInt(6, c.getAdministrator().getAdministratorID());

            ps.executeUpdate();
        }
    }
    
    public void delete(int clanID) throws Exception {
    String sql = "DELETE FROM clan WHERE clanID=?";

    java.sql.Connection conn = db.DBConnection.getInstance().getConnection();

    try (java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, clanID);

        int affected = ps.executeUpdate();
        if (affected == 0) {
            throw new Exception("Član nije pronađen (nije obrisan nijedan red).");
        }
    }
}
    
    public void update(domain.Clan c) throws Exception {
    String sql = "UPDATE clan SET ime=?, prezime=?, email=?, telefon=?, aktivan=?, administratorID=? " +
                 "WHERE clanID=?";

    java.sql.Connection conn = db.DBConnection.getInstance().getConnection();

    try (java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, c.getIme());
        ps.setString(2, c.getPrezime());
        ps.setString(3, c.getEmail());
        ps.setString(4, c.getTelefon());
        ps.setBoolean(5, c.isAktivan());
        ps.setInt(6, c.getAdministrator().getAdministratorID());
        ps.setInt(7, c.getClanID());

        int affected = ps.executeUpdate();
        if (affected == 0) {
            throw new Exception("Član nije pronađen (nije izmenjen nijedan red).");
        }
    }
    
}
    
    public java.util.List<domain.Clan> search(String q) throws Exception {
    String sql = "SELECT clanID, ime, prezime, email, telefon, aktivan, administratorID " +
                 "FROM clan " +
                 "WHERE clanID LIKE ? OR ime LIKE ? ";

    java.sql.Connection conn = db.DBConnection.getInstance().getConnection();

    try (java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
        String like = "%" + q + "%";
        ps.setString(1, like);
        ps.setString(2, like);
        
        try (java.sql.ResultSet rs = ps.executeQuery()) {
            java.util.List<domain.Clan> list = new java.util.ArrayList<>();

            while (rs.next()) {
                domain.Clan c = new domain.Clan();
                c.setClanID(rs.getInt("clanID"));
                c.setIme(rs.getString("ime"));
                c.setPrezime(rs.getString("prezime"));
                c.setEmail(rs.getString("email"));
                c.setTelefon(rs.getString("telefon"));
                c.setAktivan(rs.getBoolean("aktivan"));

                domain.Administrator a = new domain.Administrator();
                a.setAdministratorID(rs.getInt("administratorID"));
                c.setAdministrator(a);

                list.add(c);
            }
            return list;
        }
    }
}
}
