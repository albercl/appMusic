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

	private Usuario loggedUser;

	public static AppMusic getInstanciaUnica() {
		if(instanciaUnica == null)
			instanciaUnica = new AppMusic();

		return instanciaUnica;
	}

	public boolean login(String user, String password) {
		//if logged update logged user
		loggedUser = null;
		return false;
	}

	public boolean register(String nombreReal, Date fechaU, String emailU, String nombreU, String passwordU) {
		//a catalogo de usuarios

		//falla cuando nombre o correo ya registrados
		return false;
	}

	public void addPlaylist(Playlist playlist) {

	}

	public void addSongToPlaylist(Playlist playlist, Cancion song) {

	}

	//Obtención de canciones y listas
	public List<Playlist> getPlaylists() {
		return loggedUser.getPlaylists();
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
		return null;
	}

	public List<Cancion> getLast10PlayedSongs() {
		return null;
	}

	//Controles de reproducción
	public void playSong(Cancion song) {
		reproductor.play(song);
	}

	//Obtener datos del usuario
	public String getName() {
		return loggedUser.getNombre();
	}

	public boolean isPremium() {
		return loggedUser.isPremium();
	}
}
