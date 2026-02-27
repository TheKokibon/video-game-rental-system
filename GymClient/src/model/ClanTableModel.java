package model;
import domain.Clan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ClanTableModel extends AbstractTableModel {

    private final String[] columns = {"ID", "Ime", "Prezime", "Email", "Telefon", "Aktivan"};
    private List<Clan> data = new ArrayList<>();

    public void setData(List<Clan> data) {
        this.data = data;
        fireTableDataChanged();
    }

    public Clan getAt(int row) {
        return data.get(row);
    }

    @Override
    public int getRowCount() { return data.size(); }

    @Override
    public int getColumnCount() { return columns.length; }

    @Override
    public String getColumnName(int column) { return columns[column]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Clan c = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> c.getClanID();
            case 1 -> c.getIme();
            case 2 -> c.getPrezime();
            case 3 -> c.getEmail();
            case 4 -> c.getTelefon();
            case 5 -> c.isAktivan();
            default -> "";
        };
    }
}