package so.osoba;

import domain.Osoba;
import repository.DBBroker;
import so.AbstractSO;

public class SOKreirajOsobu extends AbstractSO {

    private final DBBroker broker = new DBBroker();
    private Osoba kreiranaOsoba;        // ← polje za pamćenje rezultata

    @Override
    protected void validiraj(Object parametar) throws Exception {
        if (!(parametar instanceof Osoba)) {
            throw new Exception("Prosledjeni objekat nije osoba.");
        }
        Osoba o = (Osoba) parametar;
        if (o.getIme() == null || o.getIme().isEmpty()) {
            throw new Exception("Ime je obavezno.");
        }
        if (o.getPrezime() == null || o.getPrezime().isEmpty()) {
            throw new Exception("Prezime je obavezno.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        Osoba o = (Osoba) parametar;
        broker.insert(o);
        kreiranaOsoba = o;               // ← sačuvaj referencu
    }

    public Osoba getOsoba() {
        return kreiranaOsoba;             // ← vraća sačuvani objekat
    }
}