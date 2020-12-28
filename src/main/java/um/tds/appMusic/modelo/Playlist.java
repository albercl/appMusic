package um.tds.appMusic.modelo;

import java.util.LinkedList;
import java.util.List;

public class Playlist {
	
	//Atributos
	private int id;

	//Datos
	private String nombre;
	
	//Canciones
	private List<Cancion> canciones;
	
	
	//Constructor
	public Playlist() {
		canciones = new LinkedList<>();
	}
	
	/**
	 * @param cancion Añade una canción al final de la playlist
	 */
	public void addCancion(Cancion cancion) {
		canciones.add(cancion);
	}
	
	/**
	 * @param cancion Elimina la canción.
	 */
	public void removeCancion(Cancion cancion) {
		canciones.remove(cancion);
	}
	
	/**
	 * @return copia de la lista de canciones
	 */
	public List<Cancion> getCanciones() {
		return new LinkedList<>(canciones);
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
}
