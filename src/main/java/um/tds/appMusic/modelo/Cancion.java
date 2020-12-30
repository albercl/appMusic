package um.tds.appMusic.modelo;

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
	public Cancion(String titulo, String ruta, String... interpretes) {
		this.titulo = titulo;
		this.ruta = ruta;
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
			String result = "";
			for(int i = 0; i < interpretes.size() - 1; i++)
				result += interpretes.get(i) + " & ";

			result += interpretes.get(interpretes.size() - 1);

			return result;
		}
	}
}
