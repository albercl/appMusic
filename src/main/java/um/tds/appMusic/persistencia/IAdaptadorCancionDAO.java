package um.tds.appMusic.persistencia;

import um.tds.appMusic.modelo.Cancion;

import java.util.List;

public interface IAdaptadorCancionDAO {
    void registrarCancion(Cancion cancion);
    void borrarCancion(Cancion cancion);
    void modificarCancion(Cancion cancion);
    Cancion recuperarCancion(int codigo);
    List<Cancion> recuperarTodasCanciones();
}
