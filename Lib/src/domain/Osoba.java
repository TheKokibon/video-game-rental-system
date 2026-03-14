package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Osoba extends OpstiDomenskiObjekat {

    private Long idOsoba;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private KategorijaOsobe kategorijaOsobe;

    public Osoba() {
    }

    public Osoba(Long idOsoba, String ime, String prezime, String email, String telefon, KategorijaOsobe kategorijaOsobe) {
        this.idOsoba = idOsoba;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.kategorijaOsobe = kategorijaOsobe;
    }

    public Long getIdOsoba() {
        return idOsoba;
    }

    public void setIdOsoba(Long idOsoba) {
        this.idOsoba = idOsoba;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public KategorijaOsobe getKategorijaOsobe() {
        return kategorijaOsobe;
    }

    public void setKategorijaOsobe(KategorijaOsobe kategorijaOsobe) {
        this.kategorijaOsobe = kategorijaOsobe;
    }

    @Override
    public String vratiNazivTabele() {
        return "osoba";
    }

    @Override
    public String vratiAlias() {
        return "o";
    }

    @Override
    public String vratiJoin() {
        return " JOIN kategorija_osobe ko ON o.idKategorijaOsobe = ko.idKategorijaOsobe ";
    }

    @Override
    public String vratiWhereUslov() {
        List<String> uslovi = new ArrayList<>();

        if (idOsoba != null) {
            uslovi.add("o.idOsoba = " + idOsoba);
        }

        if (ime != null && !ime.trim().isEmpty()) {
            String kriterijum = ime.trim();
            uslovi.add("(o.ime LIKE '%" + kriterijum + "%' "
                    + "OR o.prezime LIKE '%" + kriterijum + "%' "
                    + "OR o.email LIKE '%" + kriterijum + "%' "
                    + "OR o.telefon LIKE '%" + kriterijum + "%')");
        }

        if (prezime != null && !prezime.trim().isEmpty()) {
            uslovi.add("o.prezime LIKE '" + prezime.trim() + "%'");
        }

        if (email != null && !email.trim().isEmpty()) {
            uslovi.add("o.email LIKE '" + email.trim() + "%'");
        }

        if (telefon != null && !telefon.trim().isEmpty()) {
            uslovi.add("o.telefon LIKE '" + telefon.trim() + "%'");
        }

        if (kategorijaOsobe != null && kategorijaOsobe.getIdKategorijaOsobe() != null) {
            uslovi.add("o.idKategorijaOsobe = " + kategorijaOsobe.getIdKategorijaOsobe());
        }

        return String.join(" AND ", uslovi);
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "ime, prezime, email, telefon, idKategorijaOsobe";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', '" + email + "', '" + telefon + "', "
                + kategorijaOsobe.getIdKategorijaOsobe();
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "ime='" + ime + "', "
                + "prezime='" + prezime + "', "
                + "email='" + email + "', "
                + "telefon='" + telefon + "', "
                + "idKategorijaOsobe=" + kategorijaOsobe.getIdKategorijaOsobe();
    }

    @Override
    public String vratiPrimarniKljucUslov() {
        return "idOsoba = " + idOsoba;
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            KategorijaOsobe ko = new KategorijaOsobe();
            ko.setIdKategorijaOsobe(rs.getLong("idKategorijaOsobe"));
            ko.setNaziv(rs.getString("naziv"));

            Osoba o = new Osoba();
            o.setIdOsoba(rs.getLong("idOsoba"));
            o.setIme(rs.getString("ime"));
            o.setPrezime(rs.getString("prezime"));
            o.setEmail(rs.getString("email"));
            o.setTelefon(rs.getString("telefon"));
            o.setKategorijaOsobe(ko);

            lista.add(o);
        }

        return lista;
    }

    @Override
    public String toString() {
        if (idOsoba == null) {
            return "Sve";
        }
        return ime + " " + prezime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Osoba)) {
            return false;
        }

        Osoba other = (Osoba) obj;
        return idOsoba != null && idOsoba.equals(other.getIdOsoba());
    }

    @Override
    public int hashCode() {
        return idOsoba != null ? idOsoba.hashCode() : 0;
    }

}
