package tds.appMusic.modelo;

import tds.appMusic.modelo.util.Filter;
import tds.appMusic.modelo.util.PlaylistListener;
import tds.appMusic.modelo.util.PremiumListener;
import tds.appMusic.modelo.util.ReproductorListener;
import tds.appMusic.persistencia.DAOException;
import tds.appMusic.persistencia.FactoriaDAO;
import um.tds.componente.CancionesEvent;
import um.tds.componente.CancionesListener;
import um.tds.componente.CargadorCanciones;
import um.tds.componente.IBuscadorCanciones;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

//Controlador
public class AppMusic {
	private static AppMusic instanciaUnica;

	private FactoriaDAO factoriaDAO;

	private final CatalogoCanciones songs;
	private final CatalogoUsuarios users;
	private final Reproductor player;
	private final IBuscadorCanciones buscadorCanciones = new CargadorCanciones();

	private Usuario loggedUser;

	public static AppMusic getInstanciaUnica() {
		if (instanciaUnica == null)
			instanciaUnica = new AppMusic();

		return instanciaUnica;
	}

	private AppMusic() {
		player = new Reproductor();

		try {
			factoriaDAO = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		users = CatalogoUsuarios.getUnicaInstancia(factoriaDAO);
		songs = CatalogoCanciones.getInstanciaUnica(factoriaDAO);

		addListenerToCargador(this::nuevasCanciones);
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

	public Usuario getLoggedUser() {
		return loggedUser;
	}

	public boolean usernameExists(String username) {
		return users.usernameExists(username);
	}

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

	public Playlist addPlaylist(String name, List<Cancion> songs) {
		return loggedUser.addPlaylist(name, songs);
	}

	public void removePlaylist(String name) {
		loggedUser.removePlaylist(name);
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

	public Set<String> getEstilos() { return songs.getEstilos(); }

	// Obtener datos del usuario
	public String getUsername() {
		return loggedUser.getUsername();
	}

	public boolean isPremium() {
		return loggedUser.isPremium();
	}

	public void setPremium(boolean premium) {
		loggedUser.setPremium(premium);
	}

	public void addPremiumListener(PremiumListener listener) {
		loggedUser.addPremiumListener(listener);
	}

	public void removePremiumListener(PremiumListener listener) {
		loggedUser.removePremiumListener(listener);
	}

	public void setVolume(float volume) { player.setVolume(volume); }

	public void loadSongsFromFile(String fileUrl) {
		buscadorCanciones.setArchivoCanciones(fileUrl);
	}

	public void addListenerToCargador(CancionesListener listener) {
		buscadorCanciones.addListener(listener);
	}

	public void addPlaylistListenerToUser(PlaylistListener listener) {
		loggedUser.addPlaylistListener(listener);
	}

	private void nuevasCanciones(CancionesEvent event) {
		songs.cargarCancionesNuevas(event.getCanciones());
	}
}
