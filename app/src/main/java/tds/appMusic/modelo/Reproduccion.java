package tds.appMusic.modelo;

import java.util.Objects;

public class Reproduccion {
	
	private int id = 200;
	
	//Atributos
	private Cancion cancion;
	
	//Constructores
	public Reproduccion(int id, Cancion cancion) {
		this.id = id;
		this.cancion = cancion;
	}
	
	public Reproduccion(Cancion cancion) {
		this.cancion = cancion;
	}

	public Cancion getCancion() {
		return cancion;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Reproduccion rep = (Reproduccion) o;
		return id == rep.id && Objects.equals(cancion, rep.cancion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, cancion);
	}
}
