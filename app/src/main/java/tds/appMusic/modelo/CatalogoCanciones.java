package tds.appMusic.modelo;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tds.appMusic.modelo.util.Filter;
import tds.appMusic.persistencia.DAOException;
import tds.appMusic.persistencia.FactoriaDAO;
import tds.appMusic.persistencia.IAdaptadorCancionDAO;
import tds.appMusic.persistencia.tds.AdaptadorCancionTDS;
import tds.appMusic.persistencia.tds.TDSFactoriaDAO;
import um.tds.componente.Canciones;

public class CatalogoCanciones {
	private static final String SONGS_PATH = "C:\\tds\\canciones";

	private static CatalogoCanciones instancia;
	private static final Object lock = new Object();

	public static CatalogoCanciones getInstanciaUnica(FactoriaDAO factoria) {
		if(instancia == null) {
			synchronized (lock) {
					if(instancia == null)
						try {
							instancia = new CatalogoCanciones(factoria);
						} catch (Exception e) {
							System.out.println("No se han podido cargar las canciones...");
							e.printStackTrace();

							System.exit(1);
						}
			}
		}

		return instancia;
	}

	private final IAdaptadorCancionDAO adaptador;

    private List<Cancion> canciones;

	private CatalogoCanciones(FactoriaDAO factoria) throws Exception {
		this.adaptador = factoria.getCancionDAO();

		String binPath = CatalogoCanciones.class.getClassLoader().getResource(".").getPath().replaceFirst("/", "");

		// quitar "/" añadida al inicio del path en plataforma Windows

		canciones = adaptador.recuperarTodasCanciones();

		//Si no hay canciones en la persistencia, se cargan de disco
		if(canciones.isEmpty()) {
			cargarCanciones();

			for(Cancion cancion : canciones)
				adaptador.registrarCancion(cancion);
		}
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

				Cancion song = new Cancion(0, name, ruta, f.getName(), interpretes);
				this.canciones.add(song);
			}
		}
	}

	public Set<String> getEstilos() {
		Set<String> estilos = new HashSet<>();
		canciones.forEach(cancion -> estilos.add(cancion.getEstilo()));
		return estilos;
	}

	public void cargarCancionesNuevas(Canciones canciones) {
		File rootDir = new File(SONGS_PATH);
		if(System.getProperty("os.name").equals("Linux"))
			rootDir = new File(System.getProperty("user.home") + "/tds/canciones");

		for(um.tds.componente.Cancion cancion : canciones.getCancion()) {
			try {
				//Comprobar estilo
				String estilo = cancion.getEstilo().toUpperCase();
				String stylePath = Paths.get(rootDir.getPath(), estilo).toString();

				File targetDirectory = null;
				File[] directories = rootDir.listFiles();
				assert directories != null;
				for (int i = 0; i < directories.length && targetDirectory == null; i++) {
					File directory = directories[i];
					if (directory.isDirectory() && directory.getName().equals(estilo)) {
						targetDirectory = directory;
					}
				}

				//Si no existe carpeta de estilo, crear una
				if (targetDirectory == null) {
					Files.createDirectory(Paths.get(stylePath));
				}

				//Descargar canción
				URL uri = new URL(cancion.getURL());

				Path song = Paths.get(stylePath, cancion.getInterprete() + " - " + cancion.getTitulo() + ".mp3");
				System.out.println("Descargando: " + cancion.getTitulo());
				Files.write(song, new byte[0], StandardOpenOption.CREATE_NEW);
				System.out.println("Descarga terminada!");

				try (InputStream stream = uri.openStream()) {
					Files.copy(stream, song);
				}

				String builder =
						cancion.getEstilo().toUpperCase() + '/' +
						cancion.getInterprete() + " - " + cancion.getTitulo() +
						".mp3";

				Cancion c = new Cancion(0,
						cancion.getTitulo(),
						builder,
						cancion.getEstilo().toUpperCase(),
						cancion.getInterprete());

				this.canciones.add(c);
				adaptador.registrarCancion(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
