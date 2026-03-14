package so.iznajmljivanje;

import domain.Iznajmljivanje;
import domain.OpstiDomenskiObjekat;
import domain.StavkaIznajmljivanja;
import repository.DBBroker;
import so.AbstractSO;

public class SOKreirajIznajmljivanje extends AbstractSO {

    private final DBBroker broker = new DBBroker();

    @Override
    protected void validiraj(Object parametar) throws Exception {
        if (!(parametar instanceof Iznajmljivanje)) {
            throw new Exception("Prosleđeni objekat nije Iznajmljivanje.");
        }

        Iznajmljivanje i = (Iznajmljivanje) parametar;

        if (i.getDatumIznajmljivanja() == null) {
            throw new Exception("Datum iznajmljivanja je obavezan.");
        }

        if (i.getZaposleni() == null || i.getZaposleni().getIdZaposleni() == null) {
            throw new Exception("Zaposleni je obavezan.");
        }

        if (i.getOsoba() == null || i.getOsoba().getIdOsoba() == null) {
            throw new Exception("Osoba je obavezna.");
        }

        if (i.getStavke() == null || i.getStavke().isEmpty()) {
            throw new Exception("Iznajmljivanje mora imati bar jednu stavku.");
        }

        for (StavkaIznajmljivanja si : i.getStavke()) {
            if (si.getVideoIgra() == null || si.getVideoIgra().getIdVideoIgra() == null) {
                throw new Exception("Svaka stavka mora imati video igru.");
            }

            if (si.getBrojDana() <= 0) {
                throw new Exception("Broj dana mora biti veći od 0.");
            }

            if (si.getCena() <= 0) {
                throw new Exception("Cena stavke mora biti veća od 0.");
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        Iznajmljivanje i = (Iznajmljivanje) parametar;

        Long id = broker.insertVratiID(i);
        i.setIdIznajmljivanje(id);

        int rb = 1;
        for (StavkaIznajmljivanja si : i.getStavke()) {
            si.setIznajmljivanje(i);
            si.setRb(rb++);
            broker.insert(si);
        }
    }
    
    
}