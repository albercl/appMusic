package tds.appMusic.gui.auxiliarPanels;

import tds.appMusic.modelo.AppMusic;
import tds.appMusic.modelo.Cancion;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SongTableModel implements TableModel {
    private final AppMusic controlador = AppMusic.getInstanciaUnica();

    public static final int NORMAL_MODE = 0;
    public static final int REPRODUCTION_MODE = 1;

    private List<Cancion> canciones = new LinkedList<>();
    private final Map<Cancion, Integer> reproducciones = controlador.getUserReproductions();

    private final List<TableModelListener> listeners = new LinkedList<>();

    private int mode = NORMAL_MODE;

    public SongTableModel() {

    }

    public SongTableModel(int mode) {
        this.mode = mode;
    }

    @Override
    public int getRowCount() {
        return canciones.size();
    }

    @Override
    public int getColumnCount() {
        switch (mode) {
            case NORMAL_MODE:
                return 2;
            case REPRODUCTION_MODE:
                return 3;
        }
        return 0;
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "Título";
            case 1:
                return "Intérprete";
            case 2:
                return "Nº";
        }

        return null;
    }

    @Override
    public Class<?> getColumnClass(int i) {
        switch (i) {
            case 0:
            case 1:
                return String.class;
            case 2:
                return Integer.class;
        }

        return null;
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Cancion cancion = canciones.get(i);

        switch (i1) {
            case 0:
                return cancion.getTitulo();
            case 1:
                return cancion.getInterpretesString();
            case 2:
                if(mode == REPRODUCTION_MODE)
                    return reproducciones.get(cancion);
                else
                    return null;
        }

        return null;
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {

    }

    @Override
    public void addTableModelListener(TableModelListener tableModelListener) {
        listeners.add(tableModelListener);
    }

    @Override
    public void removeTableModelListener(TableModelListener tableModelListener) {
        listeners.remove(tableModelListener);
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
        TableModelEvent event = new TableModelEvent(this);
        for (TableModelListener l : listeners) {
            l.tableChanged(event);
        }
    }

    public List<Cancion> getCanciones() {
        return new LinkedList<>(canciones);
    }

    public void addSong(Cancion cancion) {
        canciones.add(cancion);
        TableModelEvent event = new TableModelEvent(this);
        listeners.forEach(l -> l.tableChanged(event));
    }

    public void removeSong(Cancion cancion) {
        canciones.remove(cancion);
        TableModelEvent event = new TableModelEvent(this);
        listeners.forEach(l -> l.tableChanged(event));
    }

    public void clearTable() {
        canciones.clear();
        TableModelEvent event = new TableModelEvent(this);
        listeners.forEach(l -> l.tableChanged(event));
    }
}
