package so.videoigra;

import domain.OpstiDomenskiObjekat;
import domain.VideoIgra;
import repository.DBBroker;
import so.AbstractSO;

public class SOPromeniVideoIgru extends AbstractSO {

    private final DBBroker broker = new DBBroker();

    @Override
    protected void validiraj(Object parametar) throws Exception {
        if (!(parametar instanceof VideoIgra)) {
            throw new Exception("Prosleđeni objekat nije VideoIgra.");
        }

        VideoIgra vi = (VideoIgra) parametar;

        if (vi.getIdVideoIgra() == null) {
            throw new Exception("ID video igre je obavezan.");
        }

        if (vi.getNaziv() == null || vi.getNaziv().trim().isEmpty()) {
            throw new Exception("Naziv je obavezan.");
        }

        if (vi.getZanr() == null || vi.getZanr().trim().isEmpty()) {
            throw new Exception("Žanr je obavezan.");
        }

        if (vi.getPlatforma() == null || vi.getPlatforma().trim().isEmpty()) {
            throw new Exception("Platforma je obavezna.");
        }

        if (vi.getCenaIznajmljivanja() <= 0) {
            throw new Exception("Cena mora biti veća od 0.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        broker.update((OpstiDomenskiObjekat) parametar);
    }
}