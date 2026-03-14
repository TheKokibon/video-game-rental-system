package form.model;

import domain.Zaposleni;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleUlogovani extends AbstractTableModel {

    private final List<Zaposleni> lista = new ArrayList<>();
    private final String[] kolone = {
        "ID", "Ime", "Prezime", "Korisnicko ime"
    };

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

        Zaposleni z = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return z.getIdZaposleni();
            case 1:
                return z.getIme();
            case 2:
                return z.getPrezime();
            case 3:
                return z.getKorisnickoIme();
            default:
                return "N/A";
        }
    }

    public void osvezi(List<Zaposleni> ulogovani) {
        lista.clear();
        lista.addAll(ulogovani);
        fireTableDataChanged();
    }
}