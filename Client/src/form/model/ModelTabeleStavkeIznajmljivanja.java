package form.model;

import domain.StavkaIznajmljivanja;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleStavkeIznajmljivanja extends AbstractTableModel {

    private List<StavkaIznajmljivanja> lista;
    private final String[] kolone = {"Rb", "Video igra", "Broj dana", "Cena"};

    public ModelTabeleStavkeIznajmljivanja() {
        lista = new ArrayList<>();
    }

    public ModelTabeleStavkeIznajmljivanja(List<StavkaIznajmljivanja> lista) {
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
        StavkaIznajmljivanja s = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return s.getRb();
            case 1:
                return s.getVideoIgra() != null ? s.getVideoIgra().getNaziv() : "";
            case 2:
                return s.getBrojDana();
            case 3:
                return s.getCena();
            default:
                return null;
        }
    }

    public void dodajStavku(StavkaIznajmljivanja stavka) {
        if (stavka.getRb() == 0) {
            stavka.setRb(vratiSledeciRb());
        }
        lista.add(stavka);
        fireTableDataChanged();
    }

    public void obrisiStavku(int red) {
    lista.remove(red);
    fireTableDataChanged();
}

    private int vratiSledeciRb() {
        int max = 0;
        for (StavkaIznajmljivanja s : lista) {
            if (s.getRb() > max) {
                max = s.getRb();
            }
        }
        return max + 1;
    }

    public List<StavkaIznajmljivanja> getLista() {
        return lista;
    }

    public void setLista(List<StavkaIznajmljivanja> lista) {
        this.lista = lista;
        vratiSledeciRb();
        fireTableDataChanged();
    }

    public StavkaIznajmljivanja vratiStavku(int red) {
        return lista.get(red);
    }
}
