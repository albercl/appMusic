package um.tds.appMusic.modelo;

import um.tds.appMusic.modelo.util.Filter;

import java.util.Date;
import java.util.List;

//Controlador
public class AppMusic {
	private static AppMusic instanciaUnica;

	@SuppressWarnings("unused")
	private CatalogoCanciones canciones;
	@SuppressWarnings("unused")
	private CatalogoUsuarios usuarios;
	@SuppressWarnings("unused")
	private Reproductor reproductor;

	private Usuario usuarioLogged;

	public static AppMusic getInstanciaUnica() {
		if (instanciaUnica == null)
			instanciaUnica = new AppMusic();

		return instanciaUnica;
	}

	public boolean login(String user, String password) {
		// if logged update logged user
		Usuario loggedU = usuarios.login(user, password);
		if (loggedU != null) {
			return true;
		} else
			return false;
	}

	public boolean register(String nombreReal, Date fechaU, String emailU, String nombreU, String passwordU) {
		// a catalogo de usuarios
		return usuarios.register(nombreReal, fechaU, emailU, nombreU, passwordU);
		// falla cuando nombre o correo ya registrados
	}

	public void addPlaylist(Playlist playlist) {
		usuarioLogged.addPlaylist(playlist);
	}

	public void addSongToPlaylist(Playlist playlist, Cancion song) {
		playlist.addSong(song);
	}

	// Obtención de canciones y listas
	public List<Playlist> getPlaylists() {
		return usuarioLogged.getPlaylists();
	}

	public Playlist getPlaylist(String name) {
		return null;
	}

	public Cancion getSong(String name) {
		return null;
	}

	public List<Cancion> searchSongs(Filter filter) {
		return canciones.getCancionesFiltradas(filter);
	}

	public List<Cancion> getMostPlayedSongs() {
		return canciones.getMostPlayedSongs();
	}

	public List<Cancion> getUserHistory() {
		return usuarioLogged.getHistorial();
	}

	// Controles de reproducción
	public void playSong(Cancion song) {
		reproductor.play(song);
		usuarioLogged.playedSong(song);
	}

	// Obtener datos del usuario
	public String getName() {
		return usuarioLogged.getName();
	}

	public boolean isPremium() {
		return usuarioLogged.isPremium();
	}
}
