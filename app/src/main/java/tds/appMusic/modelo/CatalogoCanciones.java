package tds.appMusic.modelo;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.*;
import java.util.*;
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

    private final Set<Cancion> canciones;

	public CatalogoCanciones(Collection<Cancion> canciones) {
		this.canciones = new HashSet<>(canciones);
	}

	public void addCancion(Cancion cancion) {
		canciones.add(cancion);
	}

	public void addCanciones(Collection<Cancion> canciones) {
		this.canciones.addAll(canciones);
	}

    public List<Cancion> getCanciones() {
        return new LinkedList<>(canciones);
    }
}
