package service;

import domain.Paket;
import repository.PaketRepository;

import java.util.List;

public class PaketService {
    private final PaketRepository repo = new PaketRepository();

    public List<Paket> getAll() throws Exception {
        return repo.getAll();
    }

    public void save(Paket p) throws Exception {
        validate(p, false);
        repo.save(p);
    }

    public void update(Paket p) throws Exception {
        validate(p, true);
        repo.update(p);
    }

    public void delete(int paketID) throws Exception {
        if (paketID <= 0) throw new Exception("Neispravan ID paketa.");
        repo.delete(paketID);
    }

    public List<Paket> search(String q) throws Exception {
        if (q == null || q.trim().isEmpty()) return repo.getAll();
        return repo.search(q.trim());
    }

    private void validate(Paket p, boolean update) throws Exception {
        if (p == null) throw new Exception("Paket ne sme biti null.");
        if (update && p.getPaketID() <= 0) throw new Exception("PaketID nije validan.");

        String naziv = p.getNaziv() == null ? "" : p.getNaziv().trim();
        if (naziv.isEmpty()) throw new Exception("Naziv je obavezan.");
        if (p.getTrajanjeDana() <= 0) throw new Exception("Trajanje (dani) mora biti > 0.");
        if (p.getCena() <= 0) throw new Exception("Cena mora biti > 0.");

        if (p.getAdministrator() == null || p.getAdministrator().getAdministratorID() <= 0)
            throw new Exception("Administrator mora biti postavljen.");
    }
}