package model;

import domain.Paket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PaketTableModel extends AbstractTableModel {
    private final String[] columns = {"ID", "Naziv", "Trajanje(d)", "Cena", "Broj dozvoljenih dolazaka"};
    private List<Paket> data = new ArrayList<>();

    public void setData(List<Paket> data) {
        this.data = data;
        fireTableDataChanged();
    }

    public Paket getAt(int row) { return data.get(row); }

    @Override public int getRowCount() { return data.size(); }
    @Override public int getColumnCount() { return columns.length; }
    @Override public String getColumnName(int col) { return columns[col]; }

    @Override
    public Object getValueAt(int row, int col) {
        Paket p = data.get(row);
        return switch (col) {
            case 0 -> p.getPaketID();
            case 1 -> p.getNaziv();
            case 2 -> p.getTrajanjeDana();
            case 3 -> p.getCena();
            case 4 -> p.getBrojDozvoljenihDolazaka();
            default -> "";
        };
    }
}