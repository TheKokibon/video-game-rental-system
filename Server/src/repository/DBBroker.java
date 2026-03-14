package repository;

import db.DBConnection;
import domain.Iznajmljivanje;
import domain.OpstiDomenskiObjekat;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.sql.*;

public class DBBroker {

    public List<OpstiDomenskiObjekat> vrati(OpstiDomenskiObjekat odo) throws Exception {
        String sql = "SELECT * FROM "
                + odo.vratiNazivTabele() + " "
                + odo.vratiAlias() + " "
                + odo.vratiJoin();

        if (odo.vratiWhereUslov() != null && !odo.vratiWhereUslov().trim().isEmpty()) {
            sql += " WHERE " + odo.vratiWhereUslov();
        }

        System.out.println(sql);

        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sql);

        return odo.vratiListu(rs);
    }

    public void insert(OpstiDomenskiObjekat odo) throws Exception {
        String sql = "INSERT INTO "
                + odo.vratiNazivTabele()
                + " (" + odo.vratiKoloneZaInsert() + ") "
                + "VALUES (" + odo.vratiVrednostiZaInsert() + ")";

        System.out.println("SQL INSERT: " + sql);

        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        statement.executeUpdate(sql);
        statement.close();
    }

    public void update(OpstiDomenskiObjekat odo) throws Exception {
        String sql = "UPDATE "
                + odo.vratiNazivTabele()
                + " SET " + odo.vratiVrednostiZaUpdate()
                + " WHERE " + odo.vratiPrimarniKljucUslov();

        System.out.println(sql);

        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        int brojIzmenjenih = statement.executeUpdate(sql);
        statement.close();

        if (brojIzmenjenih == 0) {
            throw new Exception("Sistem ne moze da zapamti objekat.");
        }
    }

    public void delete(OpstiDomenskiObjekat odo) throws Exception {
        String sql = "DELETE FROM "
                + odo.vratiNazivTabele()
                + " WHERE " + odo.vratiPrimarniKljucUslov();

        System.out.println(sql);

        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        int brojObrisanih = statement.executeUpdate(sql);
        statement.close();

        if (brojObrisanih == 0) {
            throw new Exception("Sistem ne moze da obrise objekat.");
        }
    }

    public Long insertVratiID(OpstiDomenskiObjekat odo) throws Exception {
        String upit = "INSERT INTO " + odo.vratiNazivTabele()
                + " (" + odo.vratiKoloneZaInsert() + ") VALUES (" + odo.vratiVrednostiZaInsert() + ")";

        PreparedStatement ps = DBConnection.getInstance().getConnection()
                .prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getLong(1);
        }
        throw new Exception("Sistem ne može da vrati ID nakon insert-a.");
    }

    public void obrisiStavkeIznajmljivanja(Long idIznajmljivanje) throws Exception {
        String upit = "DELETE FROM stavka_iznajmljivanja WHERE idIznajmljivanje = " + idIznajmljivanje;
        Statement st = DBConnection.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }
}
