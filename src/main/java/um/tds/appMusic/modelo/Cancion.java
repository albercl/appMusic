package um.tds.appMusic.modelo;

import java.io.Serializable;

public class Cancion {
	int id;
	
	//Atributos
	private String titulo;
	private String interprete;
	private String estilo;
	private String ruta;    
	
	//Constructores
	public Cancion() {

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

	public String getInterprete() {
		return interprete;
	}

	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
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
}
