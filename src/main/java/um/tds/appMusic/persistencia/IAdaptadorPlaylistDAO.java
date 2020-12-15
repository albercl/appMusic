package um.tds.appMusic.persistencia;

import um.tds.appMusic.modelo.Playlist;

import java.util.List;

public interface IAdaptadorPlaylistDAO {
    void registrarPlaylist(Playlist playlist);
    void borrarPlaylist(Playlist playlist);
    void modificarPlaylist(Playlist playlist);
    Playlist recuperarPlaylist(int codigo);
    List<Playlist> recuperarTodasPlaylistes();
}