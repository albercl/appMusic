package tds.appMusic.modelo;

import tds.appMusic.modelo.util.PlaylistListener;
import tds.appMusic.modelo.util.PremiumListener;
import tds.appMusic.persistencia.DAOException;
import tds.appMusic.persistencia.FactoriaDAO;
import tds.appMusic.persistencia.IAdaptadorPlaylistDAO;
import tds.appMusic.persistencia.IAdaptadorUsuarioDAO;

import java.util.*;

public class Usuario {

	private static final int ELDER_MIN_AGE = 65;
	private static final int YOUNG_MAX_AGE = 25;
	
	//Atributos
	private int id;

	//Datos personales
	private String name;
	private Date birthdate;
	private String email;
	
	//Credenciales
	private String username;
	private String password;
	
	//Datos de la cuenta
	private boolean premium;
	private final List<PremiumListener> premiumListeners;

	private final Map<String, Playlist> playlists;
	private final List<PlaylistListener> playlistListeners;

	private final Map<Cancion, Integer> reproductions;
	private List<Cancion> history;

	private IAdaptadorPlaylistDAO adaptadorPlaylistDAO;
	private IAdaptadorUsuarioDAO adaptadorUsuarioDAO;
	
	//Constructores
	public Usuario(int id, String nombreReal, Date fechaU, String emailU, String nombreU, String passwordU) {
		this.id = id;
		this.name = nombreReal;
		this.birthdate = fechaU;
		this.email = emailU;
		this.username = nombreU;
		this.password = passwordU;
		this.premium = false;

		playlists = new HashMap<>();
		history = new LinkedList<>();
		reproductions = new HashMap<>();
		playlistListeners = new LinkedList<>();
		premiumListeners = new LinkedList<>();

		try {
			adaptadorPlaylistDAO = FactoriaDAO.getInstancia().getPlaylistDAO();
			adaptadorUsuarioDAO = FactoriaDAO.getInstancia().getUsuarioDAO();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	//Funciones

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;

		for (PremiumListener l : premiumListeners) {
			l.premiumChanged(this, premium);
		}
	}

	public void addPremiumListener(PremiumListener listener) {
		premiumListeners.add(listener);
	}

	public void removePremiumListener(PremiumListener listener) {
		premiumListeners.remove(listener);
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public List<Playlist> getPlaylists() {
		return new ArrayList<>(playlists.values());
	}

	public Playlist getPlaylist(String name) {
		return playlists.get(name);
	}

	public Playlist addPlaylist(String name, List<Cancion> songs) {
		Playlist pl = new Playlist(name, songs);
		if(playlists.containsKey(name))
			return null;

		playlists.put(name, pl);

		adaptadorPlaylistDAO.registrarPlaylist(pl);
		adaptadorUsuarioDAO.modificarUsuario(this);

		notifyListeners();
		return pl;
	}

	public void removePlaylist(String name) {
		adaptadorPlaylistDAO.borrarPlaylist(playlists.get(name));
		playlists.remove(name);
		adaptadorUsuarioDAO.modificarUsuario(this);

		notifyListeners();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Cancion> getHistory() {
		return new LinkedList<>(history);
	}

	public void setHistory(List<Cancion> history) {
		this.history = history;
	}

	public Map<Cancion, Integer> getReproductions() {
		return new HashMap<>(reproductions);
	}

	public void playedSong(Cancion song) {
		history.add(song);
		int timesPlayed = reproductions.computeIfAbsent(song, k -> 0);
		timesPlayed = timesPlayed + 1;
		reproductions.put(song, timesPlayed);
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

    public void setPlaylists(List<Playlist> playlists) {
		this.playlists.clear();
		playlists.forEach(p -> this.playlists.put(p.getNombre(), p));
    }

    public void addPlaylistListener(PlaylistListener listener) {
		playlistListeners.add(listener);
	}

	public void removePlaylistListener(PlaylistListener listener) {
		playlistListeners.remove(listener);
	}

	private void notifyListeners() {
		for (PlaylistListener l : playlistListeners) {
			l.playlistListChanged(new ArrayList<>(playlists.values()));
		}
	}

	public boolean isElder() {
		Calendar c = Calendar.getInstance();
		c.setTime(birthdate);
		c.add(Calendar.YEAR, ELDER_MIN_AGE);
		Date d = c.getTime();

		return d.before(new Date(System.currentTimeMillis()));
	}

	public boolean isYoung() {
		Calendar c = Calendar.getInstance();
		c.setTime(birthdate);
		c.add(Calendar.YEAR, YOUNG_MAX_AGE);
		Date d = c.getTime();

		return d.after(new Date(System.currentTimeMillis()));
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"id='" + id + '\'' +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", premium=" + premium +
				", playlists=" + playlists +
				'}';
	}
}
