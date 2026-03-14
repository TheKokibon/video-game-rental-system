package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StavkaIznajmljivanja extends OpstiDomenskiObjekat {

    private Iznajmljivanje iznajmljivanje;
    private int rb;
    private int brojDana;
    private double cena;
    private VideoIgra videoIgra;

    public StavkaIznajmljivanja() {
    }

    public StavkaIznajmljivanja(Iznajmljivanje iznajmljivanje, int rb, int brojDana, double cena, VideoIgra videoIgra) {
        this.iznajmljivanje = iznajmljivanje;
        this.rb = rb;
        this.brojDana = brojDana;
        this.cena = cena;
        this.videoIgra = videoIgra;
    }

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getBrojDana() {
        return brojDana;
    }

    public void setBrojDana(int brojDana) {
        this.brojDana = brojDana;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public VideoIgra getVideoIgra() {
        return videoIgra;
    }

    public void setVideoIgra(VideoIgra videoIgra) {
        this.videoIgra = videoIgra;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavka_iznajmljivanja";
    }

    @Override
    public String vratiAlias() {
        return "si";
    }

    @Override
    public String vratiJoin() {
        return " JOIN video_igra vi ON si.idVideoIgra = vi.idVideoIgra ";
    }

    @Override
    public String vratiWhereUslov() {
        List<String> uslovi = new ArrayList<>();

        if (iznajmljivanje != null && iznajmljivanje.getIdIznajmljivanje() != null) {
            uslovi.add("si.idIznajmljivanje = " + iznajmljivanje.getIdIznajmljivanje());
        }

        return String.join(" AND ", uslovi);
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "idIznajmljivanje, rb, brojDana, cena, idVideoIgra";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return iznajmljivanje.getIdIznajmljivanje() + ", "
                + rb + ", "
                + brojDana + ", "
                + cena + ", "
                + videoIgra.getIdVideoIgra();
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "brojDana=" + brojDana + ", "
                + "cena=" + cena + ", "
                + "idVideoIgra=" + videoIgra.getIdVideoIgra();
    }

    @Override
    public String vratiPrimarniKljucUslov() {
        return "idIznajmljivanje = " + iznajmljivanje.getIdIznajmljivanje() + " AND rb = " + rb;
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

            StavkaIznajmljivanja si = new StavkaIznajmljivanja();
            si.setRb(rs.getInt("rb"));
            si.setBrojDana(rs.getInt("brojDana"));
            si.setCena(rs.getDouble("cena"));
            si.setVideoIgra(vi);

            lista.add(si);
        }

        return lista;
    }
}
