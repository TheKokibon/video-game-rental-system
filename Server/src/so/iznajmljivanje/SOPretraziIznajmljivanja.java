package so.iznajmljivanje;

import domain.Iznajmljivanje;
import domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import repository.DBBroker;
import so.AbstractSO;

public class SOPretraziIznajmljivanja extends AbstractSO {

    private final DBBroker broker = new DBBroker();
    private List<Iznajmljivanje> lista;

    @Override
    protected void validiraj(Object parametar) throws Exception {
        if (!(parametar instanceof Iznajmljivanje)) {
            throw new Exception("Prosledjeni objekat nije Iznajmljivanje.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        List<OpstiDomenskiObjekat> rezultat = broker.vrati((OpstiDomenskiObjekat) parametar);
        lista = new ArrayList<>();

        for (OpstiDomenskiObjekat obj : rezultat) {
            lista.add((Iznajmljivanje) obj);
        }
    }

    public List<Iznajmljivanje> getLista() {
        return lista;
    }
}