package service;

import domain.Clanarina;
import repository.ClanarinaRepository;

import java.sql.Date;
import java.time.LocalDate;
import repository.PaketRepository;

public class ClanarinaService {

    private final ClanarinaRepository repo = new ClanarinaRepository();
    private PaketRepository paketRepo = new PaketRepository();

    public void dodeliPaket(domain.Clanarina cl) throws Exception {
        if (cl == null) {
            throw new Exception("Članarina ne sme biti null.");
        }
        if (cl.getClan() == null || cl.getClan().getClanID() <= 0) {
            throw new Exception("Član nije izabran.");
        }
        if (cl.getPaket() == null || cl.getPaket().getPaketID() <= 0) {
            throw new Exception("Paket nije izabran.");
        }
        if (cl.getDatumPocetka() == null) {
            throw new Exception("Datum početka je obavezan.");
        }

        // UČITAJ PAKET IZ BAZE (izvor istine)
        domain.Paket paket = paketRepo.findById(cl.getPaket().getPaketID());
        if (paket == null) {
            throw new Exception("Paket ne postoji.");
        }

        int trajanje = paket.getTrajanjeDana();
        if (trajanje <= 0) {
            throw new Exception("Trajanje paketa nije validno.");
        }

        // IZRAČUNAJ datumIsteka = datumPocetka + trajanjeDana
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(cl.getDatumPocetka());
        cal.add(java.util.Calendar.DAY_OF_MONTH, trajanje);

        java.util.Date end = cal.getTime();

        cl.setDatumIsteka(new java.sql.Date(end.getTime()));
        cl.setStatus("AKTIVNA");

        // (opciono) stavi ceo paket u objekat
        cl.setPaket(paket);

        repo.save(cl);
    }

    public domain.Clanarina vratiAktivnuZaClana(int clanId) throws Exception {

        if (clanId <= 0) {
            throw new Exception("Neispravan ID člana.");
        }

        domain.Clanarina aktivna = repo.findActiveByClanId(clanId);

        if (aktivna == null) {
            return null;  // član nema aktivnu članarinu
        }

        // Dodatna bezbednosna provera:
        // Ako je datum isteka prošao, možemo je automatski označiti kao ISTEKLA
        java.util.Date danas = new java.util.Date();

        if (aktivna.getDatumIsteka() != null
                && aktivna.getDatumIsteka().before(danas)) {

            // automatski postavi status na ISTEKLA
            repo.updateStatus(aktivna.getClanarinaID(), "ISTEKLA");
            aktivna.setStatus("ISTEKLA");
        }

        return aktivna;
    }

    

    public void produziPaket(int clanId, java.sql.Date noviDatumIsteka) throws Exception {
        if (clanId <= 0) {
            throw new Exception("Neispravan ID člana.");
        }
        if (noviDatumIsteka == null) {
            throw new Exception("Novi datum isteka je obavezan.");
        }

        domain.Clanarina aktivna = repo.findActiveByClanId(clanId);
        if (aktivna == null) {
            throw new Exception("Član nema aktivnu članarinu.");
        }

        // Novi datum mora biti POSLE trenutnog isteka
        if (!noviDatumIsteka.after(aktivna.getDatumIsteka())) {
            throw new Exception("Novi datum isteka mora biti posle trenutnog datuma isteka.");
        }

        repo.updateDatumIsteka(aktivna.getClanarinaID(), noviDatumIsteka);
    }
}
