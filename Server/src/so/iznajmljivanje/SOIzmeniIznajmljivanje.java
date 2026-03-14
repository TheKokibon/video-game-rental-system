package so.iznajmljivanje;

import domain.Iznajmljivanje;
import domain.OpstiDomenskiObjekat;
import domain.StavkaIznajmljivanja;
import java.util.ArrayList;
import java.util.List;
import repository.DBBroker;
import so.AbstractSO;

public class SOIzmeniIznajmljivanje extends AbstractSO {

    private final DBBroker broker = new DBBroker();

    @Override
    protected void validiraj(Object parametar) throws Exception {
        if (!(parametar instanceof Iznajmljivanje)) {
            throw new Exception("Prosledjeni objekat nije Iznajmljivanje.");
        }

        Iznajmljivanje i = (Iznajmljivanje) parametar;

        if (i.getIdIznajmljivanje() == null) {
            throw new Exception("ID iznajmljivanja je obavezan.");
        }

        if (i.getDatumIznajmljivanja() == null) {
            throw new Exception("Datum iznajmljivanja je obavezan.");
        }

        if (i.getOsoba() == null || i.getOsoba().getIdOsoba() == null) {
            throw new Exception("Osoba je obavezna.");
        }

        if (i.getZaposleni() == null || i.getZaposleni().getIdZaposleni() == null) {
            throw new Exception("Zaposleni je obavezan.");
        }

        if (i.getStavke() == null || i.getStavke().isEmpty()) {
            throw new Exception("Iznajmljivanje mora imati bar jednu stavku.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        Iznajmljivanje novoIznajmljivanje = (Iznajmljivanje) parametar;

        broker.update(novoIznajmljivanje);

        // 1. Učitaj stare stavke iz baze
        StavkaIznajmljivanja kriterijum = new StavkaIznajmljivanja();
        kriterijum.setIznajmljivanje(novoIznajmljivanje);

        List<OpstiDomenskiObjekat> rezultat = broker.vrati(kriterijum);
        List<StavkaIznajmljivanja> stareStavke = new ArrayList<>();

        for (OpstiDomenskiObjekat odo : rezultat) {
            stareStavke.add((StavkaIznajmljivanja) odo);
        }

        List<StavkaIznajmljivanja> noveStavke = novoIznajmljivanje.getStavke();

        // 2. Obrisi stare koje vise ne postoje
        for (StavkaIznajmljivanja stara : stareStavke) {
            StavkaIznajmljivanja pronadjena = nadjiPoRb(noveStavke, stara.getRb());

            if (pronadjena == null) {
                stara.setIznajmljivanje(novoIznajmljivanje);
                broker.delete(stara);
            }
        }

        // 3. Update postojecih i insert novih
        for (StavkaIznajmljivanja nova : noveStavke) {
            nova.setIznajmljivanje(novoIznajmljivanje);

            StavkaIznajmljivanja stara = nadjiPoRb(stareStavke, nova.getRb());

            if (stara == null) {
                broker.insert(nova);
            } else {
                broker.update(nova);
            }
        }
    }

    private StavkaIznajmljivanja nadjiPoRb(List<StavkaIznajmljivanja> lista, int rb) {
        for (StavkaIznajmljivanja s : lista) {
            if (s.getRb() == rb) {
                return s;
            }
        }
        return null;
    }
}
