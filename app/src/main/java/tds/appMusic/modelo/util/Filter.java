package tds.appMusic.modelo.util;

public class Filter {
	private String titulo;
	private String interprete;
	private String estilo;

	public Filter() {}

	public Filter(String titulo, String interprete, String estilo) {
		this.titulo = titulo.isEmpty() ? null : titulo;
		this.interprete = interprete.isEmpty() ? null : interprete;
		this.estilo = estilo.isEmpty() ? null : estilo;
	}
	
	public String getEstilo() {
		return estilo;
	}
	
	public String getInterprete() {
		return interprete;
	}
	
	public String getTitulo() {
		return titulo;
	}
}
