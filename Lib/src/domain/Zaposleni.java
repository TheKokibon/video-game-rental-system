package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Zaposleni extends OpstiDomenskiObjekat {

    private Long idZaposleni;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;

    public Zaposleni() {
    }

    public Zaposleni(Long idZaposleni, String ime, String prezime, String korisnickoIme, String lozinka) {
        this.idZaposleni = idZaposleni;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public Long getIdZaposleni() {
        return idZaposleni;
    }

    public void setIdZaposleni(Long idZaposleni) {
        this.idZaposleni = idZaposleni;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public String vratiNazivTabele() {
        return "zaposleni";
    }

    @Override
    public String vratiAlias() {
        return "z";
    }

    @Override
    public String vratiJoin() {
        return "";
    }

    @Override
    public String vratiWhereUslov() {
        if (korisnickoIme != null && lozinka != null) {
            return "korisnickoIme='" + korisnickoIme + "' AND lozinka='" + lozinka + "'";
        }
        return "";
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "ime, prezime, korisnickoIme, lozinka";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', '" + korisnickoIme + "', '" + lozinka + "'";
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "ime='" + ime + "', prezime='" + prezime + "', korisnickoIme='" + korisnickoIme + "', lozinka='" + lozinka + "'";
    }

    @Override
    public String vratiPrimarniKljucUslov() {
        return "idZaposleni=" + idZaposleni;
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Zaposleni z = new Zaposleni();
            z.setIdZaposleni(rs.getLong("idZaposleni"));
            z.setIme(rs.getString("ime"));
            z.setPrezime(rs.getString("prezime"));
            z.setKorisnickoIme(rs.getString("korisnickoIme"));
            z.setLozinka(rs.getString("lozinka"));
            lista.add(z);
        }
        return lista;
    }

    @Override
    public String toString() {
        if (idZaposleni == null) {
            return "Sve";
        }
        return ime + " " + prezime;
    }
}
