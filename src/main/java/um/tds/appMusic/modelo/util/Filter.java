package um.tds.appMusic.modelo.util;

public class Filter {
	private String titulo;
	private String interprete;
	private String estilo;
	
	public Filter(String titulo, String interprete, String estilo) {
		this.titulo = titulo;
		this.interprete = interprete;
		this.estilo = estilo;
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
