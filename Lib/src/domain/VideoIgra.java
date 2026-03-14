package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoIgra extends OpstiDomenskiObjekat {

    private Long idVideoIgra;
    private String naziv;
    private String zanr;
    private String platforma;
    private double cenaIznajmljivanja;
    private String opis;

    public VideoIgra() {
    }

    public VideoIgra(Long idVideoIgra, String naziv, String zanr, String platforma, double cenaIznajmljivanja, String opis) {
        this.idVideoIgra = idVideoIgra;
        this.naziv = naziv;
        this.zanr = zanr;
        this.platforma = platforma;
        this.cenaIznajmljivanja = cenaIznajmljivanja;
        this.opis = opis;
    }

    public Long getIdVideoIgra() {
        return idVideoIgra;
    }

    public void setIdVideoIgra(Long idVideoIgra) {
        this.idVideoIgra = idVideoIgra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public String getPlatforma() {
        return platforma;
    }

    public void setPlatforma(String platforma) {
        this.platforma = platforma;
    }

    public double getCenaIznajmljivanja() {
        return cenaIznajmljivanja;
    }

    public void setCenaIznajmljivanja(double cenaIznajmljivanja) {
        this.cenaIznajmljivanja = cenaIznajmljivanja;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String vratiNazivTabele() {
        return "video_igra";
    }

    @Override
    public String vratiAlias() {
        return "vi";
    }

    @Override
    public String vratiJoin() {
        return "";
    }

    @Override
    public String vratiWhereUslov() {
        List<String> uslovi = new ArrayList<>();

        if (idVideoIgra != null) {
            uslovi.add("idVideoIgra = " + idVideoIgra);
        }

        if (naziv != null && !naziv.trim().isEmpty()) {
            String kriterijum = naziv.trim();
            uslovi.add("(naziv LIKE '%" + kriterijum + "%' "
                    + "OR zanr LIKE '%" + kriterijum + "%' "
                    + "OR platforma LIKE '%" + kriterijum + "%' "
                    + "OR opis LIKE '%" + kriterijum + "%')");
        }

        return String.join(" AND ", uslovi);
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "naziv, zanr, platforma, cenaIznajmljivanja, opis";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + naziv + "', '" + zanr + "', '" + platforma + "', " + cenaIznajmljivanja + ", '" + opis + "'";
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "naziv='" + naziv + "', "
                + "zanr='" + zanr + "', "
                + "platforma='" + platforma + "', "
                + "cenaIznajmljivanja=" + cenaIznajmljivanja + ", "
                + "opis='" + opis + "'";
    }

    @Override
    public String vratiPrimarniKljucUslov() {
        return "idVideoIgra = " + idVideoIgra;
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            VideoIgra vi = new VideoIgra();
            vi.setIdVideoIgra(rs.getLong("idVideoIgra"));
            vi.setNaziv(rs.getString("naziv"));
            vi.setZanr(rs.getString("zanr"));
            vi.setPlatforma(rs.getString("platforma"));
            vi.setCenaIznajmljivanja(rs.getDouble("cenaIznajmljivanja"));
            vi.setOpis(rs.getString("opis"));

            lista.add(vi);
        }

        return lista;
    }

    @Override
    public String toString() {
        if (idVideoIgra == null) {
            return "Sve";
        }
        return naziv;
    }
}
