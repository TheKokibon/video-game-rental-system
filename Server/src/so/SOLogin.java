package so;

import domain.OpstiDomenskiObjekat;
import domain.Zaposleni;
import java.util.List;
import repository.DBBroker;

public class SOLogin extends AbstractSO {

    private Zaposleni ulogovani;
    private final DBBroker broker = new DBBroker();

    @Override
    protected void validiraj(Object parametar) throws Exception {
        if (!(parametar instanceof Zaposleni)) {
            throw new Exception("Prosledjeni objekat nije zaposleni.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        List<OpstiDomenskiObjekat> lista = broker.vrati((Zaposleni) parametar);

        if (lista.isEmpty()) {
            throw new Exception("Korisničko ime i šifra nisu ispravni.");
        }

        ulogovani = (Zaposleni) lista.get(0);
    }

    public Zaposleni getUlogovani() {
        return ulogovani;
    }
}