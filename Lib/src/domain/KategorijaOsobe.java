package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KategorijaOsobe extends OpstiDomenskiObjekat {

    private Long idKategorijaOsobe;
    private String naziv;

    public KategorijaOsobe() {
    }

    public KategorijaOsobe(Long idKategorijaOsobe, String naziv) {
        this.idKategorijaOsobe = idKategorijaOsobe;
        this.naziv = naziv;
    }

    public Long getIdKategorijaOsobe() {
        return idKategorijaOsobe;
    }

    public void setIdKategorijaOsobe(Long idKategorijaOsobe) {
        this.idKategorijaOsobe = idKategorijaOsobe;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "kategorija_osobe";
    }

    @Override
    public String vratiAlias() {
        return "ko";
    }

    @Override
    public String vratiJoin() {
        return "";
    }

    @Override
    public String vratiWhereUslov() {
        if (idKategorijaOsobe == 0) {
            return "idKategorijaOsobe = " + idKategorijaOsobe;
        }
        if (naziv != null && !naziv.trim().isEmpty()) {
            return "naziv LIKE '" + naziv.trim() + "%'";
        }
        return "";
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "naziv";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + naziv + "'";
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "naziv='" + naziv + "'";
    }

    @Override
    public String vratiPrimarniKljucUslov() {
        return "idKategorijaOsobe = " + idKategorijaOsobe;
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            KategorijaOsobe ko = new KategorijaOsobe();
            ko.setIdKategorijaOsobe(rs.getLong("idKategorijaOsobe"));
            ko.setNaziv(rs.getString("naziv"));
            lista.add(ko);
        }

        return lista;
    }

    @Override
    public String toString() {
        return naziv;
    }

    
}