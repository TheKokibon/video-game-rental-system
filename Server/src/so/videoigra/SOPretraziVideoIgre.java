package so.videoigra;

import domain.OpstiDomenskiObjekat;
import domain.VideoIgra;
import java.util.ArrayList;
import java.util.List;
import repository.DBBroker;
import so.AbstractSO;

public class SOPretraziVideoIgre extends AbstractSO {

    private final DBBroker broker = new DBBroker();
    private List<VideoIgra> lista;

    @Override
    protected void validiraj(Object parametar) throws Exception {
        if (!(parametar instanceof VideoIgra)) {
            throw new Exception("Prosleđeni objekat nije VideoIgra.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        List<OpstiDomenskiObjekat> rezultat = broker.vrati((OpstiDomenskiObjekat) parametar);
        lista = new ArrayList<>();

        for (OpstiDomenskiObjekat obj : rezultat) {
            lista.add((VideoIgra) obj);
        }
    }

    public List<VideoIgra> getLista() {
        return lista;
    }
}