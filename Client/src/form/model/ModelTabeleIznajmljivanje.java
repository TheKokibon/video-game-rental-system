package models;

import domain.Iznajmljivanje;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleIznajmljivanje extends AbstractTableModel {

    private List<Iznajmljivanje> lista;
    private final String[] kolone = {"ID", "Datum", "Ukupan iznos", "Zaposleni", "Osoba"};

    public ModelTabeleIznajmljivanje() {
        lista = new ArrayList<>();
    }

    public ModelTabeleIznajmljivanje(List<Iznajmljivanje> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Iznajmljivanje i = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return i.getIdIznajmljivanje();
            case 1:
                return i.getDatumIznajmljivanja() == null
                        ? ""
                        : new SimpleDateFormat("dd.MM.yyyy").format(i.getDatumIznajmljivanja());
            case 2:
                return i.getUkupanIznos();
            case 3:
                return i.getZaposleni() != null ? i.getZaposleni().getIdZaposleni() : "";
            case 4:
                return i.getOsoba() != null ? i.getOsoba().getIdOsoba() : "";
            default:
                return null;
        }
    }

    public void setLista(List<Iznajmljivanje> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public List<Iznajmljivanje> getLista() {
        return lista;
    }

    public Iznajmljivanje vratiIznajmljivanje(int red) {
        return lista.get(red);
    }
}