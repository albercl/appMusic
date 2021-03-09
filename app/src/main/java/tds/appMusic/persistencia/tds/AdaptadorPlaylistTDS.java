package tds.appMusic.persistencia.tds;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import tds.appMusic.modelo.Playlist;
import tds.appMusic.persistencia.IAdaptadorPlaylistDAO;

import java.util.List;

@SuppressWarnings("unused")
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
    public List<Playlist> recuperarTodasPlaylists() {
        return null;
    }
}
