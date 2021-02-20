package um.tds.appMusic.modelo;

import java.util.*;

public class Usuario {
	
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
	private List<Playlist> playlists;
	private Map<Cancion, Integer> reproductions;
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

		this.playlists = new LinkedList<>();
		history = new LinkedList<>();
		reproductions = new HashMap<>();
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
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public Playlist getPlaylist(String name) {
		return playlists.stream().filter(p -> p.getNombre().equals(name)).findFirst().get();
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
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
	
	public void addPlaylist(Playlist pl) {
		playlists.add(pl);
	}

	public void removePlaylist(Playlist playlist) { playlists.remove(playlist); }

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
}
