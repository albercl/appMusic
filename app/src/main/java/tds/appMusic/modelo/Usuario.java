package tds.appMusic.modelo;

import tds.appMusic.modelo.util.PlaylistListener;
import tds.appMusic.modelo.util.PremiumListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
	
	//Constructores
	public Usuario(String nombreReal, Date fechaU, String emailU, String nombreU, String passwordU) {
		this.id = 0;
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
	}
	
	//Funciones
	/**
	 * Funcionalidad de usuario premium. Genera un PDF de la lista dada si el usuario es premium.
	 * Para generar el archivo el usuario debe ser premium.
	 * @param pl Playlist de la que generar el PDF
	 * @return true si se ha generado el PDF, false si no se ha generado
	 */
	public boolean generarPDFPlaylist(Playlist pl) {
		if(premium)
			return pl.generarPDF();
		
		return false;
	}

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
		notifyListeners();
		return pl;
	}

	public void removePlaylist(String name) {
		playlists.remove(name);
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
		playlists.forEach(p -> addPlaylist(p.getNombre(), p.getSongs()));
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
}
