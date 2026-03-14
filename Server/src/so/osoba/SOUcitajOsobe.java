package so.osoba;

import domain.OpstiDomenskiObjekat;
import domain.Osoba;
import java.util.ArrayList;
import java.util.List;
import repository.DBBroker;
import so.AbstractSO;

public class SOUcitajOsobe extends AbstractSO {

    private List<Osoba> lista;
    private final DBBroker broker = new DBBroker();

    @Override
    protected void validiraj(Object parametar) throws Exception {
        if (!(parametar instanceof Osoba)) {
            throw new Exception("Prosledjeni objekat nije osoba.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        List<OpstiDomenskiObjekat> rezultat = broker.vrati((Osoba) parametar);

        lista = new ArrayList<>();
        for (OpstiDomenskiObjekat odo : rezultat) {
            lista.add((Osoba) odo);
        }
    }

    public List<Osoba> getLista() {
        return lista;
    }
}