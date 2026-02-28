package repository;

import db.DBConnection;
import domain.Administrator;
import domain.Paket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaketRepository {

    public List<Paket> getAll() throws Exception {
        String sql = "SELECT paketID, naziv, trajanjeDana, cena, brojDozvoljenihDolazaka, administratorID FROM paket";
        Connection conn = DBConnection.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            List<Paket> list = new ArrayList<>();
            while (rs.next()) {
                Paket p = new Paket();
                p.setPaketID(rs.getInt("paketID"));
                p.setNaziv(rs.getString("naziv"));
                p.setTrajanjeDana(rs.getInt("trajanjeDana"));
                p.setCena(rs.getDouble("cena"));
                p.setBrojDozvoljenihDolazaka(rs.getInt("brojDozvoljenihDolazaka"));

                Administrator a = new Administrator();
                a.setAdministratorID(rs.getInt("administratorID"));
                p.setAdministrator(a);

                list.add(p);
            }
            return list;
        }
    }

    public void save(Paket p) throws Exception {
        String sql = "INSERT INTO paket (naziv, trajanjeDana, cena, brojDozvoljenihDolazaka, administratorID) VALUES (?,?,?,?,?)";
        Connection conn = DBConnection.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNaziv());
            ps.setInt(2, p.getTrajanjeDana());
            ps.setDouble(3, p.getCena());
            ps.setInt(4, p.getBrojDozvoljenihDolazaka());
            ps.setInt(5, p.getAdministrator().getAdministratorID());
            ps.executeUpdate();
        }
    }

    public void update(Paket p) throws Exception {
        String sql = "UPDATE paket SET naziv=?, trajanjeDana=?, cena=?, brojDozvoljenihDolazaka =?, administratorID=? WHERE paketID=?";
        Connection conn = DBConnection.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNaziv());
            ps.setInt(2, p.getTrajanjeDana());
            ps.setDouble(3, p.getCena());
            ps.setInt(4, p.getBrojDozvoljenihDolazaka());
            ps.setInt(5, p.getAdministrator().getAdministratorID());
            ps.setInt(6, p.getPaketID());

            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new Exception("Paket nije pronađen.");
            }
        }
    }

    public void delete(int paketID) throws Exception {
        String sql = "DELETE FROM paket WHERE paketID=?";
        Connection conn = DBConnection.getInstance().getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, paketID);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new Exception("Paket nije pronađen.");
            }
        }
    }

    public List<Paket> search(String q) throws Exception {
        String sql = "SELECT paketID, naziv, trajanjeDana, cena, brojDozvoljenihDolazaka, administratorID "
                + "FROM paket WHERE paketID LIKE ? OR naziv LIKE ?";

        Connection conn = DBConnection.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            String like = "%" + q + "%";
            ps.setString(1, like);
            ps.setString(2, like);

            try (ResultSet rs = ps.executeQuery()) {
                List<Paket> list = new ArrayList<>();
                while (rs.next()) {
                    Paket p = new Paket();
                    p.setPaketID(rs.getInt("paketID"));
                    p.setNaziv(rs.getString("naziv"));
                    p.setTrajanjeDana(rs.getInt("trajanjeDana"));
                    p.setCena(rs.getDouble("cena"));
                    p.setBrojDozvoljenihDolazaka(rs.getInt("brojDozvoljenihDolazaka"));

                    Administrator a = new Administrator();
                    a.setAdministratorID(rs.getInt("administratorID"));
                    p.setAdministrator(a);

                    list.add(p);
                }
                return list;
            }
        }
    }

    public domain.Paket findById(int paketID) throws Exception {
        String sql = "SELECT paketID, naziv, trajanjeDana, cena, brojDozvoljenihDolazaka, administratorID "
                + "FROM paket WHERE paketID = ?";

        java.sql.Connection conn = db.DBConnection.getInstance().getConnection();

        try (java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, paketID);

            try (java.sql.ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {
                    return null; // nije pronađen paket
                }

                domain.Paket p = new domain.Paket();
                p.setPaketID(rs.getInt("paketID"));
                p.setNaziv(rs.getString("naziv"));
                p.setTrajanjeDana(rs.getInt("trajanjeDana"));
                p.setCena(rs.getDouble("cena"));
                p.setBrojDozvoljenihDolazaka(rs.getInt("brojDozvoljenihDolazaka"));

                domain.Administrator a = new domain.Administrator();
                a.setAdministratorID(rs.getInt("administratorID"));
                p.setAdministrator(a);

                return p;
            }
        }
    }
}
