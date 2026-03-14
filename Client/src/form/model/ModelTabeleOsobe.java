package form.model;

import domain.Osoba;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleOsobe extends AbstractTableModel {

    private List<Osoba> lista;
    private final String[] kolone = {
        "ID", "Ime", "Prezime", "Email", "Telefon", "Kategorija"
    };

    public ModelTabeleOsobe() {
        lista = new ArrayList<>();
    }

    public ModelTabeleOsobe(List<Osoba> lista) {
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

        Osoba o = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return o.getIdOsoba();

            case 1:
                return o.getIme();

            case 2:
                return o.getPrezime();

            case 3:
                return o.getEmail();

            case 4:
                return o.getTelefon();

            case 5:
                if (o.getKategorijaOsobe() != null) {
                    return o.getKategorijaOsobe().getNaziv();
                }
                return "";

            default:
                return "";
        }
    }

    public void setLista(List<Osoba> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public Osoba getOsoba(int red) {
        return lista.get(red);
    }

    public List<Osoba> getLista() {
        return lista;
    }

    public void obrisiOsobu(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }
}