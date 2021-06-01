package tds.appMusic.modelo;

import tds.appMusic.modelo.util.Cargador;
import tds.appMusic.modelo.util.Filter;
import tds.appMusic.modelo.util.ReproductorListener;
import tds.appMusic.persistencia.*;
import um.tds.componente.CancionesListener;
import um.tds.componente.CargadorCanciones;
import um.tds.componente.IBuscadorCanciones;

import java.util.*;

//Controlador
public class AppMusic {
	private static AppMusic instanciaUnica;

	private FactoriaDAO factoriaDAO;
	private IAdaptadorUsuarioDAO usuarioDAO;
	private IAdaptadorPlaylistDAO playlistDAO;
	private IAdaptadorCancionDAO cancionDAO;

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

		usuarioDAO = factoriaDAO.getUsuarioDAO();
		cancionDAO = factoriaDAO.getCancionDAO();
		playlistDAO = factoriaDAO.getPlaylistDAO();

		users = new CatalogoUsuarios(usuarioDAO.recuperarTodosUsuarios());

		List<Cancion> canciones = cancionDAO.recuperarTodasCanciones();
		if (canciones.isEmpty()) {
			canciones = Cargador.cargarCancionesDisco();
			assert canciones != null;
			for (Cancion c : canciones)
				cancionDAO.registrarCancion(c);
		}
		songs = new CatalogoCanciones(canciones);

		addListenerToCargador(event -> songs.addCanciones(
				Cargador.descargarCanciones(
						event.getCanciones())));
	}

	public boolean login(String user, String password) {
		loggedUser = users.getUsuarioByUsername(user);
		if(loggedUser == null)
			loggedUser = users.getUsuarioByEmail(user);

		if(loggedUser == null)
			return false;

		if(loggedUser.checkPassword(password)) return true;

		loggedUser = null;
		return false;
	}

	public void logout() {
		loggedUser = null;
		player.stop();
	}

	public boolean register(String nombreReal, Date fechaU, String emailU, String nombreU, String passwordU) {
		Usuario usuario = new Usuario(0, nombreReal, fechaU, emailU, nombreU, passwordU, false);
		Collection<Usuario> usuarios = users.getUsuarios();

		if(usuarios.contains(usuario))
			return false;

		users.addUsuario(usuario);
		usuarioDAO.registrarUsuario(usuario);
		return true;
	}

	public boolean usernameExists(String username) {
		Collection<Usuario> usuarios = users.getUsuarios();
		for(Usuario u : usuarios) {
			if(u.getUsername().equals(username))
				return true;
		}

		return false;
	}

	public boolean emailExists(String email) {
		Collection<Usuario> usuarios = users.getUsuarios();
		for(Usuario u : usuarios) {
			if(u.getEmail().equals(email))
				return true;
		}

		return false;
	}

	// Obtención de canciones y listas
	public Collection<Playlist> getPlaylists() {
		return loggedUser.getPlaylists();
	}

	public Playlist addPlaylist(String name, List<Cancion> songs) {
		Playlist pl = loggedUser.addPlaylist(name, songs);
		playlistDAO.registrarPlaylist(pl);
		usuarioDAO.modificarUsuario(loggedUser);
		return pl;
	}

	public void removePlaylist(String name) {
		Playlist pl = loggedUser.removePlaylist(name);
		usuarioDAO.modificarUsuario(loggedUser);
		playlistDAO.borrarPlaylist(pl);
	}

	public void overwritePlaylist(String name, Collection<Cancion> songs) {
		Playlist pl = loggedUser.overwritePlaylist(name, songs);
		playlistDAO.modificarPlaylist(pl);
	}

	public Collection<Cancion> searchSongs(Filter filter) {
		Collection<Cancion> canciones = songs.getCanciones();
		List<Cancion> cancionesFiltradas = new LinkedList<>();

		for(Cancion c : canciones) {
			String titulo = filter.getTitulo();
			String interprete = filter.getInterprete();
			String estilo = filter.getEstilo();

			if((titulo == null || c.getTitulo().equals(titulo))
			&& (interprete == null || c.getInterpretes().contains(interprete))
			&& (estilo == null || c.getEstilo().equals(estilo)))
				cancionesFiltradas.add(c);
		}

		return cancionesFiltradas;
	}

	public List<Cancion> getUserHistory() {
		return loggedUser.getHistory();
	}

	public Map<Cancion, Integer> getUserReproductions() { return loggedUser.getReproductions(); }

	// Controles de reproducción
	public void play(Cancion song) {
		player.play(song);
		loggedUser.playedSong(song);
	}

	public void resume() {
		player.resume();
	}

	public void pause() {
		player.pause();
	}

	public void stop() {
		player.stop();
	}

	public void addListenerToPlayer(ReproductorListener listener) {
		player.addListener(listener);
	}

	public Cancion getPlayingSong() { return player.getCurrentSong(); }

	public Collection<String> getEstilos() {
		Set<String> estilos = new HashSet<>();

		for(Cancion c : songs.getCanciones()) {
			estilos.add(c.getEstilo());
		}

		return estilos;
	}

	// Obtener datos del usuario
	public Usuario getLoggedUser() {
		return loggedUser;
	}

	public boolean isPremium() {
		return loggedUser.isPremium();
	}

	public void setPremium(boolean premium) {
		loggedUser.setPremium(premium);
		usuarioDAO.modificarUsuario(loggedUser);
	}

	public void setVolume(float volume) { player.setVolume(volume); }

	public void addCancionesPorArchivo(String file) {
		buscadorCanciones.setArchivoCanciones(file);
	}

	public void addListenerToCargador(CancionesListener listener) {
		buscadorCanciones.addListener(listener);
	}
}
