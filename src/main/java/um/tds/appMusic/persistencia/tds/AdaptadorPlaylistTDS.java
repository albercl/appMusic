package um.tds.appMusic.persistencia.tds;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import um.tds.appMusic.modelo.Playlist;
import um.tds.appMusic.persistencia.IAdaptadorPlaylistDAO;

import java.util.List;

public class AdaptadorPlaylistTDS implements IAdaptadorPlaylistDAO {
    private static ServicioPersistencia servicioPersistencia;
    private static AdaptadorPlaylistTDS instanciaUnica;

    public static AdaptadorPlaylistTDS getInstanciaUnica() {
        if (instanciaUnica == null)
            instanciaUnica = new AdaptadorPlaylistTDS();

        return instanciaUnica;
    }

    private AdaptadorPlaylistTDS() {
        servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }

    @Override
    public void registrarPlaylist(Playlist playlist) {

    }

    @Override
    public void borrarPlaylist(Playlist playlist) {

    }

    @Override
    public void modificarPlaylist(Playlist playlist) {

    }

    @Override
    public Playlist recuperarPlaylist(int codigo) {
        return null;
    }

    @Override
    public List<Playlist> recuperarTodasPlaylistes() {
        return null;
    }
}
