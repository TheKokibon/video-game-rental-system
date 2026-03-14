package models;

import domain.VideoIgra;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleVideoIgre extends AbstractTableModel {

    private List<VideoIgra> lista;
    private final String[] kolone = {"Naziv", "Žanr", "Platforma", "Cena", "Opis"};

    public ModelTabeleVideoIgre() {
        lista = new ArrayList<>();
    }

    public ModelTabeleVideoIgre(List<VideoIgra> lista) {
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
        VideoIgra vi = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return vi.getNaziv();
            case 1:
                return vi.getZanr();
            case 2:
                return vi.getPlatforma();
            case 3:
                return vi.getCenaIznajmljivanja();
            case 4:
                return vi.getOpis();
            default:
                return null;
        }
    }

    public void dodajVideoIgru(VideoIgra vi) {
        lista.add(vi);
        fireTableDataChanged();
    }

    public void obrisiVideoIgru(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }

    public VideoIgra vratiVideoIgru(int red) {
        return lista.get(red);
    }

    public List<VideoIgra> getLista() {
        return lista;
    }

    public void setLista(List<VideoIgra> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public void izmeniVideoIgru(int red, VideoIgra videoIgra) {
        lista.set(red, videoIgra);
        fireTableDataChanged();
    }
}
