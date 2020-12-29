package um.tds.appMusic.modelo;

import java.util.*;

public class Usuario {
	
	//Atributos
	private int id;

	//Datos personales
	private String nombre;
	private Date fechaNacimiento;
	private String email;
	
	//Credenciales
	private String usuario;
	private String contrasena;
	
	//Datos de la cuenta
	private boolean premium;
	private List<Playlist> playlists;
	private Map<Cancion, Integer> reproducciones;
	private List<Cancion> historial;
	
	//Constructores
	public Usuario(String nombreReal, Date fechaU, String emailU, String nombreU, String passwordU) {
		this.id = 0;
		this.nombre = nombreReal;
		this.fechaNacimiento = fechaU;
		this.email = emailU;
		this.usuario = nombreU;
		this.contrasena = passwordU;
		this.premium = false;

		this.playlists = new LinkedList<>();
		historial = new LinkedList<>();
		reproducciones = new HashMap<>();
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
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public void addPlaylist(Playlist pl) {
		playlists.add(pl);
	}

	public List<Cancion> getHistorial() {
		return historial;
	}

	public void setHistorial(List<Cancion> historial) {
		this.historial = historial;
	}

	public int getSongReproductions(Cancion song) {
		return reproducciones.get(song);
	}

	public void playedSong(Cancion song) {
		historial.add(song);
		//TODO: Comprobar suma objeto integer
		Integer timesPlayed = reproducciones.get(song);
		timesPlayed += 1;
		reproducciones.put(song, timesPlayed);
	}
}
