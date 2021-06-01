package tds.appMusic.modelo;

import tds.appMusic.modelo.util.Cargador;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Cancion {
	int id;
	
	//Atributos
	private String titulo;
	private List<String> interpretes;
	private String estilo;
	private String ruta;
	
	//Constructores
	public Cancion(int id, String titulo, String ruta, String estilo, String... interpretes) {
		this.id = id;
		this.titulo = titulo;
		this.ruta = ruta;
		this.estilo = estilo;
		this.interpretes = Arrays.asList(interpretes);
	}

	/*
	 * Constructor
	 * Carga de la canción de disco
	 */

	public String getTitulo() {
		return titulo;
	}

	public String getEstilo() {
		return estilo;
	}
	
	public List<String> getInterpretes() {
		return interpretes;
	}

	public String getRuta() {
		return ruta;
	}

	public String getRutaCompleta() {
		String rutaCompleta = Cargador.SONGS_PATH + ruta;
		if(Cargador.OS_TYPE.equals("Linux"))
			rutaCompleta = rutaCompleta.replace('\\', '/');

		return rutaCompleta;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getInterpretesString() {
		if(interpretes.size() == 1) {
			return interpretes.get(0);
		} else {
			StringBuilder result = new StringBuilder();
			for(int i = 0; i < interpretes.size() - 1; i++)
				result.append(interpretes.get(i)).append(" & ");

			result.append(interpretes.get(interpretes.size() - 1));

			return result.toString();
		}
	}

	@Override
	public String toString() {
		return titulo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cancion cancion = (Cancion) o;
		return id == cancion.id && Objects.equals(titulo, cancion.titulo) && Objects.equals(interpretes, cancion.interpretes) && Objects.equals(estilo, cancion.estilo) && Objects.equals(ruta, cancion.ruta);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, titulo, interpretes, estilo, ruta);
	}
}
