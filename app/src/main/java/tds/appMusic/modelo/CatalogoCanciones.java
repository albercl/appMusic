package tds.appMusic.modelo;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tds.appMusic.modelo.util.Filter;

public class CatalogoCanciones {
	private final static String SONGS_PATH = "C:\\tds\\canciones";

    private List<Cancion> canciones;
    
    public CatalogoCanciones() throws Exception {
    	canciones = new LinkedList<>();
    	cargarCanciones();
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
    		stream = stream.filter(c -> titulo.equalsIgnoreCase(c.getTitulo()));
    	
    	if(interprete != null)
    		stream = stream.filter(c -> c.getInterpretes()
    				.stream()
    				.anyMatch(interprete::equalsIgnoreCase));
    	
    	if(estilo != null)
    		stream = stream.filter(c -> estilo.equals(c.getEstilo()));
    	
    	return stream.collect(Collectors.toList());
    }

    public List<Cancion> getMostPlayedSongs() {
    	return canciones.stream()
				.sorted()
				.collect(Collectors.toList());
	}

	private void cargarCanciones() throws Exception {
		File rootDir = new File(SONGS_PATH);
		if(System.getProperty("os.name").equals("Linux"))
			rootDir = new File(System.getProperty("user.home") + "/tds/canciones");
		
		if(!rootDir.isDirectory())
			throw new Exception("No se ha podido abrir el directorio de canciones");

		File[] estilos = rootDir.listFiles();
		for(File f : estilos) {
			File[] canciones = f.listFiles();
			for(File c : canciones) {
				String ruta = f.getName() + "\\" + c.getName();

				String[] parts = c.getName().split("-");

				String interpretesString = parts[0];
				String[] interpretes = interpretesString.split("&");
				for(int i = 0; i < interpretes.length; i++)
					interpretes[i] = interpretes[i].trim();

				String name = parts[1].substring(0, parts[1].length() - 4).trim();

				Cancion song = new Cancion(name, ruta, interpretes);
				song.setEstilo(f.getName());
				this.canciones.add(song);
			}
		}
	}

	public Set<String> getEstilos() {
		Set<String> estilos = new HashSet<>();
		canciones.forEach(cancion -> estilos.add(cancion.getEstilo()));
		return estilos;
	}
}
