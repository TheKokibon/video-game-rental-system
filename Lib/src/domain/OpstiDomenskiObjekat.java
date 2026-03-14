package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class OpstiDomenskiObjekat implements Serializable {

    public abstract String vratiNazivTabele();

    public abstract String vratiAlias();

    public abstract String vratiJoin();

    public abstract String vratiWhereUslov();


    public abstract String vratiKoloneZaInsert();

    public abstract String vratiVrednostiZaInsert();

    public abstract String vratiVrednostiZaUpdate();

    public abstract String vratiPrimarniKljucUslov();

    public abstract List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws SQLException;
}