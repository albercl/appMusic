package um.tds.componente;

import java.util.LinkedList;
import java.util.List;

public class CargadorCanciones implements IBuscadorCanciones {
    List<CancionesListener> listeners = new LinkedList<>();

    public void setArchivoCanciones(String archivoCanciones) {
        Canciones canciones = MapperCancionesXMLtoJava.cargarCanciones(archivoCanciones);
        CancionesEvent event = new CancionesEvent(canciones);
        listeners.forEach(listeners -> listeners.nuevasCanciones(event));
    }

    public void addListener(CancionesListener listener) {
        listeners.add(listener);
    }
}
