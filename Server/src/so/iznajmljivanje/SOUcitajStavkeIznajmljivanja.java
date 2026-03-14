package so.iznajmljivanje;

import domain.OpstiDomenskiObjekat;
import domain.StavkaIznajmljivanja;
import java.util.ArrayList;
import java.util.List;
import repository.DBBroker;
import so.AbstractSO;

public class SOUcitajStavkeIznajmljivanja extends AbstractSO {

    private final DBBroker broker = new DBBroker();
    private List<StavkaIznajmljivanja> lista;

    @Override
    protected void validiraj(Object parametar) throws Exception {
        if (!(parametar instanceof StavkaIznajmljivanja)) {
            throw new Exception("Prosleđeni objekat nije StavkaIznajmljivanja.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        List<OpstiDomenskiObjekat> rezultat = broker.vrati((OpstiDomenskiObjekat) parametar);
        lista = new ArrayList<>();

        for (OpstiDomenskiObjekat obj : rezultat) {
            lista.add((StavkaIznajmljivanja) obj);
        }
    }

    public List<StavkaIznajmljivanja> getLista() {
        return lista;
    }
}