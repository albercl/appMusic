package tds.appMusic.modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Playlist {
	
	//Atributos
	private int id;

	//Datos
	private final String nombre;
	
	//Canciones
	private List<Cancion> canciones;
	
	
	//Constructor
	public Playlist(String name) {
		this.nombre = name;
		canciones = new LinkedList<>();
	}

	public Playlist(String name, List<Cancion> songs) {
		this.nombre = name;
		this.canciones = new LinkedList<>(songs);
	}
	
	/**
	 * @param cancion Añade una canción al final de la playlist
	 */
	public void addSong(Cancion cancion) {
		canciones.add(cancion);
	}
	
	/**
	 * @param cancion Elimina la canción.
	 */
	public void removeSong(Cancion cancion) {
		canciones.remove(cancion);
	}
	
	/**
	 * @return copia de la lista de canciones
	 */
	public List<Cancion> getSongs() {
		return new LinkedList<>(canciones);
	}

	public void setSongs(List<Cancion> songs) {
		this.canciones = songs;
	}

	/**
	 * Genera un archivo PDF de la lista de canciones de la playlist
	 * @return true si se ha generado el PDF
	 */
	public boolean generarPDF() {
		//TODO: Implementar métodos
		return false;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Playlist playlist = (Playlist) o;
		return Objects.equals(nombre, playlist.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public String toString() {
		return nombre;
	}
}
