package um.tds.appMusic.persistencia.tds;

import um.tds.appMusic.modelo.Cancion;
import um.tds.appMusic.persistencia.IAdaptadorCancionDAO;

import java.util.List;

import tds.driver.*;

public class AdaptadorCancionTDS implements IAdaptadorCancionDAO {
    private static ServicioPersistencia servicioPersistencia;
    private static AdaptadorCancionTDS instanciaUnica;

    @Override
    public void registrarCancion(Cancion cancion) {

    }

    @Override
    public void borrarCancion(Cancion cancion) {

    }

    @Override
    public void modificarCancion(Cancion cancion) {

    }

    @Override
    public Cancion recuperarCancion(int codigo) {
        return null;
    }

    @Override
    public List<Cancion> recuperarTodasCanciones() {
        return null;
    }
}

