package tds.appMusic.modelo;

import java.util.Arrays;
import java.util.List;

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
	
	/* TODO: implementar clase
	 * Constructor
	 * Carga de la canción de disco
	 */

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	
	public List<String> getInterpretes() {
		return interpretes;
	}
	
	public void addInterprete(String interprete) {
		interpretes.add(interprete);
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
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
		return "Cancion{" +
				"id=" + id +
				", titulo='" + titulo + '\'' +
				", interpretes=" + interpretes +
				", estilo='" + estilo + '\'' +
				", ruta='" + ruta + '\'' +
				'}';
	}
}
