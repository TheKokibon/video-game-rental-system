package so;

import db.DBConnection;

public abstract class AbstractSO {

    public final void izvrsi(Object parametar) throws Exception {
        try {
            validiraj(parametar);
            izvrsiOperaciju(parametar);
            DBConnection.getInstance().getConnection().commit();
        } catch (Exception e) {
            DBConnection.getInstance().getConnection().rollback();
            throw e;
        }
    }

    protected void validiraj(Object parametar) throws Exception {
    }

    protected abstract void izvrsiOperaciju(Object parametar) throws Exception;
}