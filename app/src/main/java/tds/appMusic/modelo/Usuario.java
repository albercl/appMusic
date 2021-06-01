package tds.appMusic.modelo;

import java.util.*;

public class Usuario {

	private static final int ELDER_MIN_AGE = 65;
	private static final int YOUNG_MAX_AGE = 25;
	
	//Atributos
	private int id;

	//Datos personales
	private String name;
	private final Date birthdate;
	private final String email;
	
	//Credenciales
	private final String username;
	private final String password;
	
	//Datos de la cuenta
	private boolean premium;

	private final Map<String, Playlist> playlists;

	private final Map<Cancion, Integer> reproductions;
	private List<Cancion> history;

	private final List<IDescuento> descuentos;
	
	//Constructores
	public Usuario(int id, String nombreReal, Date fechaU, String emailU, String nombreU, String passwordU, boolean premium) {
		this.id = id;
		this.name = nombreReal;
		this.birthdate = fechaU;
		this.email = emailU;
		this.username = nombreU;
		this.password = passwordU;
		this.premium = premium;

		playlists = new HashMap<>();
		history = new LinkedList<>();
		reproductions = new HashMap<>();
		descuentos = new LinkedList<>();

		if(isYoung())
			descuentos.add(new DescuentoJoven());

		if(isElder())
			descuentos.add(new DescuentoJubilado());
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
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public List<Playlist> getPlaylists() {
		return new ArrayList<>(playlists.values());
	}

	public Playlist getPlaylist(String name) {
		return playlists.get(name);
	}

	public Playlist addPlaylist(String name, Collection<Cancion> songs) {
		Playlist pl = new Playlist(name, new LinkedList<>(songs));
		if(playlists.containsKey(name))
			return null;

		playlists.put(name, pl);
		return pl;
	}

	public Playlist overwritePlaylist(String name, Collection<Cancion> songs) {
		Playlist pl = playlists.get(name);
		pl.setSongs(new LinkedList<>(songs));

		return pl;
	}

	public Playlist removePlaylist(String name) {
		return playlists.remove(name);
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public List<Cancion> getHistory() {
		return new LinkedList<>(history);
	}

	public void setHistory(List<Cancion> history) {this.history = history; }

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

	public IDescuento getMejorDescuento() {
		if(descuentos.isEmpty())
			return null;

		IDescuento mejorDescuento = descuentos.get(0);

		for(IDescuento descuento : descuentos) {
			if(descuento.getDescuento() < mejorDescuento.getDescuento())
				mejorDescuento = descuento;
		}

		return mejorDescuento;
	}

	private boolean isElder() {
		Calendar c = Calendar.getInstance();
		c.setTime(birthdate);
		c.add(Calendar.YEAR, ELDER_MIN_AGE);
		Date d = c.getTime();

		return d.before(new Date(System.currentTimeMillis()));
	}

	private boolean isYoung() {
		Calendar c = Calendar.getInstance();
		c.setTime(birthdate);
		c.add(Calendar.YEAR, YOUNG_MAX_AGE);
		Date d = c.getTime();

		return d.after(new Date(System.currentTimeMillis()));
	}

	@Override
	public String toString() {
		return username;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Usuario usuario = (Usuario) o;
		return email.equals(usuario.email) && username.equals(usuario.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, birthdate, email, username, password, premium, playlists, reproductions, history, descuentos);
	}
}
