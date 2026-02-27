package domain;

import java.io.Serializable;

public class Paket implements Serializable {

    private static final long serialVersionUID = 1L;

    private int paketID;
    private String naziv;
    private int trajanjeDana;
    private double cena;
    private int brojDozvoljenihDolazaka;

    private Administrator administrator; // ako čuvaš ko je kreirao

    public Paket() {
    }

    public Paket(int paketID, String naziv, int trajanjeDana, double cena, int brojDozvoljenihDolazaka, Administrator administrator) {
        this.paketID = paketID;
        this.naziv = naziv;
        this.trajanjeDana = trajanjeDana;
        this.cena = cena;
        this.brojDozvoljenihDolazaka = brojDozvoljenihDolazaka;

        this.administrator = administrator;
    }

    public int getBrojDozvoljenihDolazaka() {
        return brojDozvoljenihDolazaka;
    }

    public void setBrojDozvoljenihDolazaka(int brojDozvoljenihDolazaka) {
        this.brojDozvoljenihDolazaka = brojDozvoljenihDolazaka;
    }

    public int getPaketID() {
        return paketID;
    }

    public void setPaketID(int paketID) {
        this.paketID = paketID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getTrajanjeDana() {
        return trajanjeDana;
    }

    public void setTrajanjeDana(int trajanjeDana) {
        this.trajanjeDana = trajanjeDana;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
