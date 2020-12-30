package um.tds.appMusic.modelo;

import um.tds.appMusic.modelo.util.Filter;

import java.util.Date;
import java.util.List;

//Controlador
public class AppMusic {
	private static AppMusic instanciaUnica;


	private CatalogoCanciones songs;
	private CatalogoUsuarios users;
	private Reproductor player;

	private Usuario loggedUser;

	public static AppMusic getInstanciaUnica() {
		if (instanciaUnica == null)
			instanciaUnica = new AppMusic();

		return instanciaUnica;
	}

	private AppMusic() {
		users = CatalogoUsuarios.getUnicaInstancia();
		player = new Reproductor();

		//TODO: Aplicar singleton
		try {
			songs = new CatalogoCanciones();
		} catch (Exception e) {
			System.err.println("Algo ha ocurrido al intentar cargar las canciones");
			e.printStackTrace();
			System.exit(1);
		}
	}

	public boolean login(String user, String password) {
		// if logged update logged user
		loggedUser = users.login(user, password);
		if (loggedUser != null) {
			player.setCurrentUser(loggedUser);
			return true;
		} else
			return false;
	}

	public void logout() {
		loggedUser = null;
		player.setCurrentUser(null);
	}

	public boolean register(String nombreReal, Date fechaU, String emailU, String nombreU, String passwordU) {
		// a catalogo de usuarios
		return users.register(nombreReal, fechaU, emailU, nombreU, passwordU);
		// falla cuando nombre o correo ya registrados
	}

	public boolean checkUsername(String username) {
		return users.checkUsername(username);
	}

	public void addPlaylist(Playlist playlist) {
		loggedUser.addPlaylist(playlist);
	}

	public void addSongToPlaylist(Playlist playlist, Cancion song) {
		playlist.addSong(song);
	}

	// Obtención de canciones y listas
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
		return songs.getCancionesFiltradas(filter);
	}

	public List<Cancion> getMostPlayedSongs() {
		return songs.getMostPlayedSongs();
	}

	public List<Cancion> getUserHistory() {
		return loggedUser.getHistory();
	}

	// Controles de reproducción
	// TODO: Añadir controles reproductor
	public void play(Cancion song) {
		player.play(song);
		loggedUser.playedSong(song);
	}

	public void pause() {
		player.pause();
	}

	public void goNext() {
		player.goNext();
	}

	public void goBack() {
		player.goBack();
	}

	public void randomize() {
		player.randomize();
	}

	// Obtener datos del usuario
	public String getUsername() {
		return loggedUser.getUsername();
	}

	public boolean isPremium() {
		return loggedUser.isPremium();
	}
}
