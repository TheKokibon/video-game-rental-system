package so.osoba;

import domain.Osoba;
import repository.DBBroker;
import so.AbstractSO;

public class SOPromeniOsobu extends AbstractSO {

    private final DBBroker broker = new DBBroker();

    @Override
    protected void validiraj(Object parametar) throws Exception {

        if (!(parametar instanceof Osoba)) {
            throw new Exception("Prosledjeni objekat nije osoba.");
        }

        Osoba o = (Osoba) parametar;

        if (o.getIdOsoba() == null) {
            throw new Exception("Osoba mora imati ID.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        broker.update((Osoba) parametar);
    }
}