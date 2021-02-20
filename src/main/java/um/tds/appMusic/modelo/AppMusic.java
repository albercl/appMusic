package um.tds.appMusic.modelo;

import um.tds.appMusic.modelo.util.Filter;
import um.tds.appMusic.modelo.util.ReproductorListener;

import java.util.Date;
import java.util.List;
import java.util.Map;

//Controlador
public class AppMusic {
	private static AppMusic instanciaUnica;


	private CatalogoCanciones songs;
	private final CatalogoUsuarios users;
	private final Reproductor player;

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

	public void removePlaylist(Playlist playlist) { loggedUser.removePlaylist(playlist); }

	public void addSongToPlaylist(Playlist playlist, Cancion song) {
		playlist.addSong(song);
	}

	// Obtención de canciones y listas
	public List<Playlist> getPlaylists() {
		return loggedUser.getPlaylists();
	}

	public Playlist getPlaylist(String name) {
		return loggedUser.getPlaylist(name);
	}

	public List<Cancion> searchSongs(Filter filter) {
		return songs.getCancionesFiltradas(filter);
	}

	public List<Cancion> getUserHistory() {
		return loggedUser.getHistory();
	}

	public Map<Cancion, Integer> getUserReproductions() { return loggedUser.getReproductions(); }

	// Controles de reproducción
	public void play(Cancion song) {
		player.play(song);
	}

	public void play(Playlist playlist, int index) {
		player.play(playlist, index);
	}

	public void play(List<Cancion> songs, int index) {
		player.play(songs, index);
	}

	public void resume() {
		player.resume();
	}

	public void pause() {
		player.pause();
	}

	public Cancion goNext() {
		return player.goNext();
	}

	public Cancion goBack() {
		return player.goBack();
	}

	public void alternateRandom() {
		player.alternateRandom();
	}

	public void alternateRepeat() {
		player.alternateRepeat();
	}

	public void addListenerToPlayer(ReproductorListener listener) {
		player.addListener(listener);
	}

	public Cancion getPlayingSong() { return player.getCurrentSong(); }

	// Obtener datos del usuario
	public String getUsername() {
		return loggedUser.getUsername();
	}

	public boolean isPremium() {
		return loggedUser.isPremium();
	}

	public void setVolume(float volume) { player.setVolume(volume); }
}
