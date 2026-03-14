package controller;

import domain.Iznajmljivanje;
import domain.Osoba;
import domain.StavkaIznajmljivanja;
import domain.VideoIgra;
import domain.Zaposleni;
import java.util.ArrayList;
import java.util.List;
import so.iznajmljivanje.SOIzmeniIznajmljivanje;
import so.iznajmljivanje.SOKreirajIznajmljivanje;
import so.osoba.SOKreirajOsobu;
import so.videoigra.SOKreirajVideoIgru;
import so.SOLogin;
import so.osoba.SOObrisiOsobu;
import so.videoigra.SOObrisiVideoIgru;
import so.iznajmljivanje.SOPretraziIznajmljivanja;
import so.osoba.SOPretraziOsobe;
import so.videoigra.SOPretraziVideoIgre;
import so.osoba.SOPromeniOsobu;
import so.videoigra.SOPromeniVideoIgru;
import so.iznajmljivanje.SOUcitajIznajmljivanje;
import so.osoba.SOUcitajOsobe;
import so.iznajmljivanje.SOUcitajStavkeIznajmljivanja;
import so.videoigra.SOUcitajVideoIgre;

public class Controller {

    private static Controller instance;
    private final List<Zaposleni> ulogovani = new ArrayList<>();

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public synchronized Zaposleni login(Zaposleni zaposleni) throws Exception {
        for (Zaposleni z : ulogovani) {
            if (z.getKorisnickoIme().equals(zaposleni.getKorisnickoIme())) {
                throw new Exception("Zaposleni je vec ulogovan.");
            }
        }

        SOLogin so = new SOLogin();
        so.izvrsi(zaposleni);
        Zaposleni ulogovaniZaposleni = so.getUlogovani();

        ulogovani.add(ulogovaniZaposleni);
        return ulogovaniZaposleni;
    }

    public synchronized void logout(Zaposleni zaposleni) {
        ulogovani.removeIf(z -> z.getKorisnickoIme().equals(zaposleni.getKorisnickoIme()));
    }

    public synchronized List<Zaposleni> getUlogovani() {
        return new ArrayList<>(ulogovani);
    }

    public Osoba kreirajOsobu(Osoba osoba) throws Exception {
        SOKreirajOsobu so = new SOKreirajOsobu();
        so.izvrsi(osoba);
        return so.getOsoba();
    }

    public List<Osoba> ucitajOsobe() throws Exception {
        SOUcitajOsobe so = new SOUcitajOsobe();
        so.izvrsi(new Osoba());
        return so.getLista();
    }

    public List<Osoba> pretraziOsobe(Osoba kriterijum) throws Exception {
        SOPretraziOsobe so = new SOPretraziOsobe();
        so.izvrsi(kriterijum);
        return so.getLista();
    }

    public void promeniOsobu(Osoba osoba) throws Exception {
        SOPromeniOsobu so = new SOPromeniOsobu();
        so.izvrsi(osoba);
    }

    public void obrisiOsobu(Osoba osoba) throws Exception {
        SOObrisiOsobu so = new SOObrisiOsobu();
        so.izvrsi(osoba);
    }

    public void kreirajVideoIgru(VideoIgra videoIgra) throws Exception {
        SOKreirajVideoIgru so = new SOKreirajVideoIgru();
        so.izvrsi(videoIgra);
    }

    public List<VideoIgra> ucitajVideoIgre() throws Exception {
        SOUcitajVideoIgre so = new SOUcitajVideoIgre();
        so.izvrsi(new VideoIgra());
        return so.getLista();
    }

    public List<VideoIgra> pretraziVideoIgre(VideoIgra videoIgra) throws Exception {
        SOPretraziVideoIgre so = new SOPretraziVideoIgre();
        so.izvrsi(videoIgra);
        return so.getLista();
    }

    public void obrisiVideoIgru(VideoIgra videoIgra) throws Exception {
        SOObrisiVideoIgru so = new SOObrisiVideoIgru();
        so.izvrsi(videoIgra);
    }

    public void promeniVideoIgru(VideoIgra videoIgra) throws Exception {
        SOPromeniVideoIgru so = new SOPromeniVideoIgru();
        so.izvrsi(videoIgra);
    }

    public void kreirajIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        SOKreirajIznajmljivanje so = new SOKreirajIznajmljivanje();
        so.izvrsi(iznajmljivanje);
    }

    public void izmeniIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        SOIzmeniIznajmljivanje so = new SOIzmeniIznajmljivanje();
        so.izvrsi(iznajmljivanje);
    }

    public List<Iznajmljivanje> ucitajIznajmljivanja() throws Exception {
        SOUcitajIznajmljivanje so = new SOUcitajIznajmljivanje();
        so.izvrsi(new Iznajmljivanje());
        return so.getLista();
    }

    public List<StavkaIznajmljivanja> ucitajStavkeIznajmljivanja(Iznajmljivanje iznajmljivanje) throws Exception {
        StavkaIznajmljivanja s = new StavkaIznajmljivanja();
        s.setIznajmljivanje(iznajmljivanje);

        SOUcitajStavkeIznajmljivanja so = new SOUcitajStavkeIznajmljivanja();
        so.izvrsi(s);

        return so.getLista();
    }

    public List<Iznajmljivanje> pretraziIznajmljivanja(Iznajmljivanje kriterijum) throws Exception {
        SOPretraziIznajmljivanja so = new SOPretraziIznajmljivanja();
        so.izvrsi(kriterijum);
        return so.getLista();
    }
}
