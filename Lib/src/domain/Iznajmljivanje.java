package domain;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Iznajmljivanje extends OpstiDomenskiObjekat {

    private Long idIznajmljivanje;
    private Date datumIznajmljivanja;
    private double ukupanIznos;
    private Zaposleni zaposleni;
    private Osoba osoba;
    private List<StavkaIznajmljivanja> stavke;

    public Iznajmljivanje() {
        stavke = new ArrayList<>();
    }

    public Iznajmljivanje(Long idIznajmljivanje, Date datumIznajmljivanja, double ukupanIznos, Zaposleni zaposleni, Osoba osoba, List<StavkaIznajmljivanja> stavke) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.datumIznajmljivanja = datumIznajmljivanja;
        this.ukupanIznos = ukupanIznos;
        this.zaposleni = zaposleni;
        this.osoba = osoba;
        this.stavke = stavke;
    }

    public Long getIdIznajmljivanje() {
        return idIznajmljivanje;
    }

    public void setIdIznajmljivanje(Long idIznajmljivanje) {
        this.idIznajmljivanje = idIznajmljivanje;
    }

    public Date getDatumIznajmljivanja() {
        return datumIznajmljivanja;
    }

    public void setDatumIznajmljivanja(Date datumIznajmljivanja) {
        this.datumIznajmljivanja = datumIznajmljivanja;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public List<StavkaIznajmljivanja> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaIznajmljivanja> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String vratiNazivTabele() {
        return "iznajmljivanje";
    }

    @Override
    public String vratiAlias() {
        return "i";
    }

    @Override
    public String vratiJoin() {
        return " JOIN zaposleni z ON i.idZaposleni = z.idZaposleni "
                + "JOIN osoba o ON i.idOsoba = o.idOsoba "
                + "LEFT JOIN stavka_iznajmljivanja si ON i.idIznajmljivanje = si.idIznajmljivanje "
                + "LEFT JOIN video_igra vi ON si.idVideoIgra = vi.idVideoIgra ";
    }

    @Override
    public String vratiWhereUslov() {
        List<String> uslovi = new ArrayList<>();

        if (idIznajmljivanje != null) {
            uslovi.add("i.idIznajmljivanje = " + idIznajmljivanje);
        }

        if (zaposleni != null && zaposleni.getIdZaposleni() != null) {
            uslovi.add("i.idZaposleni = " + zaposleni.getIdZaposleni());
        }

        if (osoba != null && osoba.getIdOsoba() != null) {
            uslovi.add("i.idOsoba = " + osoba.getIdOsoba());
        }

        return String.join(" AND ", uslovi);
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "datumIznajmljivanja, ukupanIznos, idZaposleni, idOsoba";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + datumIznajmljivanja + "', "
                + ukupanIznos + ", "
                + zaposleni.getIdZaposleni() + ", "
                + osoba.getIdOsoba();
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "datumIznajmljivanja='" + datumIznajmljivanja + "', "
                + "ukupanIznos=" + ukupanIznos + ", "
                + "idZaposleni=" + zaposleni.getIdZaposleni() + ", "
                + "idOsoba=" + osoba.getIdOsoba();
    }

    @Override
    public String vratiPrimarniKljucUslov() {
        return "idIznajmljivanje = " + idIznajmljivanje;
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Zaposleni z = new Zaposleni();
            z.setIdZaposleni(rs.getLong("idZaposleni"));

            Osoba o = new Osoba();
            o.setIdOsoba(rs.getLong("idOsoba"));

            Iznajmljivanje i = new Iznajmljivanje();
            i.setIdIznajmljivanje(rs.getLong("idIznajmljivanje"));
            i.setDatumIznajmljivanja(rs.getDate("datumIznajmljivanja"));
            i.setUkupanIznos(rs.getDouble("ukupanIznos"));
            i.setZaposleni(z);
            i.setOsoba(o);

            lista.add(i);
        }

        return lista;
    }
}
