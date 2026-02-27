package service;

import domain.Clan;
import repository.ClanRepository;

import java.util.List;

public class ClanService {

    private final ClanRepository repo = new ClanRepository();

    public List<Clan> getAll() throws Exception {
        return repo.getAll();
    }

    public void save(domain.Clan c) throws Exception {
        if (c == null) {
            throw new Exception("Clan ne sme biti null.");
        }
        if (c.getIme() == null || c.getIme().trim().isEmpty()) {
            throw new Exception("Ime je obavezno.");
        }
        if (c.getPrezime() == null || c.getPrezime().trim().isEmpty()) {
            throw new Exception("Prezime je obavezno.");
        }
        if (c.getEmail() == null || c.getEmail().trim().isEmpty()) {
            throw new Exception("Email je obavezan.");
        }
        if (c.getTelefon() == null || c.getTelefon().trim().isEmpty()) {
            throw new Exception("Telefon je obavezan.");
        }
        if (c.getAdministrator() == null) {
            throw new Exception("Administrator mora biti postavljen.");
        }

        repo.save(c);
    }

    public void delete(int clanID) throws Exception {
        if (clanID <= 0) {
            throw new Exception("Neispravan ID člana.");
        }
        repo.delete(clanID);
    }

    public void update(domain.Clan c) throws Exception {
        if (c == null) {
            throw new Exception("Clan ne sme biti null.");
        }
        if (c.getClanID() <= 0) {
            throw new Exception("ClanID nije validan.");
        }

        String ime = c.getIme() == null ? "" : c.getIme().trim();
        String prezime = c.getPrezime() == null ? "" : c.getPrezime().trim();
        String email = c.getEmail() == null ? "" : c.getEmail().trim();
        String telefon = c.getTelefon() == null ? "" : c.getTelefon().trim();

        if (ime.isEmpty()) {
            throw new Exception("Ime je obavezno.");
        }
        if (prezime.isEmpty()) {
            throw new Exception("Prezime je obavezno.");
        }
        if (email.isEmpty()) {
            throw new Exception("Email je obavezan.");
        }
        if (telefon.isEmpty()) {
            throw new Exception("Telefon je obavezan.");
        }

        if (c.getAdministrator() == null || c.getAdministrator().getAdministratorID() <= 0) {
            throw new Exception("Administrator mora biti postavljen.");
        }

        repo.update(c);
    }

    public java.util.List<domain.Clan> search(String q) throws Exception {
        if (q == null) {
            q = "";
        }
        q = q.trim();
        if (q.isEmpty()) {
            return repo.getAll(); // ako prazno, vrati sve
        }
        return repo.search(q);
    }
}
