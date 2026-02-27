package repository;

import db.DBConnection;
import domain.Clanarina;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ClanarinaRepository {

    public void save(domain.Clanarina cl) throws Exception {
        String sql = "INSERT INTO clanarina (datumPocetka, datumIsteka, status, clanID, paketID) "
                + "VALUES (?, ?, ?, ?, ?)";

        java.sql.Connection conn = db.DBConnection.getInstance().getConnection();

        try (java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, cl.getDatumPocetka());
            ps.setDate(2, cl.getDatumIsteka());
            ps.setString(3, cl.getStatus());
            ps.setInt(4, cl.getClan().getClanID());
            ps.setInt(5, cl.getPaket().getPaketID());
            ps.executeUpdate();
        }
    }

    public domain.Clanarina findActiveByClanId(int clanID) throws Exception {
        String sql
                = "SELECT c.clanarinaID, c.datumPocetka, c.datumIsteka, c.status, c.clanID, "
                + "p.paketID, p.naziv, p.trajanjeDana "
                + "FROM clanarina c "
                + "JOIN paket p ON c.paketID = p.paketID "
                + "WHERE c.clanID=? AND c.status='AKTIVNA' "
                + "ORDER BY c.datumIsteka DESC LIMIT 1";

        java.sql.Connection conn = db.DBConnection.getInstance().getConnection();

        try (java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clanID);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                domain.Clanarina cl = new domain.Clanarina();
                cl.setClanarinaID(rs.getInt("clanarinaID"));
                cl.setDatumPocetka(rs.getDate("datumPocetka"));
                cl.setDatumIsteka(rs.getDate("datumIsteka"));
                cl.setStatus(rs.getString("status"));

                domain.Clan c = new domain.Clan();
                c.setClanID(rs.getInt("clanID"));
                cl.setClan(c);

                domain.Paket p = new domain.Paket();
                p.setPaketID(rs.getInt("paketID"));
                p.setNaziv(rs.getString("naziv"));
                p.setTrajanjeDana(rs.getInt("trajanjeDana"));
                cl.setPaket(p);

                return cl;
            }
        }
    }

    public void updateStatus(int clanarinaID, String status) throws Exception {
        String sql = "UPDATE clanarina SET status=? WHERE clanarinaID=?";

        java.sql.Connection conn = db.DBConnection.getInstance().getConnection();

        try (java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, clanarinaID);
            ps.executeUpdate();
        }
    }

    public void updateDatumIsteka(int clanarinaID, java.sql.Date noviDatumIsteka) throws Exception {
        String sql = "UPDATE clanarina SET datumIsteka=?, status='AKTIVNA' WHERE clanarinaID=?";

        java.sql.Connection conn = db.DBConnection.getInstance().getConnection();

        try (java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, noviDatumIsteka);
            ps.setInt(2, clanarinaID);

            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new Exception("Članarina nije pronađena.");
            }
        }
    }
}
