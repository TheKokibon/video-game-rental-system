package so.videoigra;

import domain.OpstiDomenskiObjekat;
import domain.VideoIgra;
import repository.DBBroker;
import so.AbstractSO;

public class SOObrisiVideoIgru extends AbstractSO {

    private final DBBroker broker = new DBBroker();

    @Override
    protected void validiraj(Object parametar) throws Exception {
        if (!(parametar instanceof VideoIgra)) {
            throw new Exception("Prosleđeni objekat nije VideoIgra.");
        }

        VideoIgra vi = (VideoIgra) parametar;
        if (vi.getIdVideoIgra() == null) {
            throw new Exception("ID video igre je obavezan za brisanje.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        broker.delete((OpstiDomenskiObjekat) parametar);
    }
}