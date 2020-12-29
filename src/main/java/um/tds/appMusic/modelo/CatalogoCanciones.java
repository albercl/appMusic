package um.tds.appMusic.modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import um.tds.appMusic.modelo.util.Filter;

public class CatalogoCanciones {
    private List<Cancion> canciones;
    
    public CatalogoCanciones() {
    	canciones = new LinkedList<>();
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }
    
    public List<Cancion> getCancionesFiltradas(Filter filtro) {
    	String titulo = filtro.getTitulo();
    	String interprete = filtro.getInterprete();
    	String estilo = filtro.getEstilo();
    	
    	Stream<Cancion> stream = canciones.stream();
    	
    	if(titulo != null)
    		stream.filter(c -> titulo.equalsIgnoreCase(c.getTitulo()));
    	
    	if(interprete != null)
    		stream.filter(c -> c.getInterpretes()
    				.stream()
    				.anyMatch(i -> interprete.equalsIgnoreCase(i)));
    	
    	if(estilo != null)
    		stream.filter(c -> estilo.equals(c.getEstilo()));
    	
    	return stream.collect(Collectors.toList());
    }

    public List<Cancion> getMostPlayedSongs() {
    	return canciones.stream()
				.sorted()
				.collect(Collectors.toList());
	}
}
