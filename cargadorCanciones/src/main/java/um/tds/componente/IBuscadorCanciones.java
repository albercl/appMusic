package um.tds.componente;

public interface IBuscadorCanciones {
    void setArchivoCanciones(String archivo);
    void addListener(CancionesListener listener);
}
