package um.tds.componente;

public class CancionesEvent {
    private Canciones canciones;

    public CancionesEvent(Canciones canciones) {
        this.canciones = canciones;
    }

    public Canciones getCanciones() {
        return canciones;
    }
}
