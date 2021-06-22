package tds.appMusic.persistencia;

import tds.appMusic.modelo.Reproduccion;

import java.util.List;

public interface IAdaptadorReproduccionDAO {
    void registrarReproduccion(Reproduccion reproduccion);
    void borrarReproduccion(Reproduccion reproduccion);
    void modificarReproduccion(Reproduccion reproduccion);
    Reproduccion recuperarReproduccion(int codigo);
    List<Reproduccion> recuperarTodasReproducciones();
}