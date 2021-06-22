package tds.appMusic.persistencia.tds;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.appMusic.modelo.Cancion;
import tds.appMusic.modelo.Playlist;
import tds.appMusic.modelo.Reproduccion;
import tds.appMusic.persistencia.IAdaptadorReproduccionDAO;
import tds.appMusic.persistencia.PoolDAO;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

@SuppressWarnings("unused")
public class AdaptadorReproduccionTDS implements IAdaptadorReproduccionDAO {
    private static ServicioPersistencia servicioPersistencia;
    private static AdaptadorReproduccionTDS instanciaUnica;

    public static AdaptadorReproduccionTDS getInstanciaUnica() {
        if(instanciaUnica == null)
            instanciaUnica = new AdaptadorReproduccionTDS();

        return instanciaUnica;
    }
    
    private AdaptadorReproduccionTDS() {
        servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }
    
    private final PoolDAO pool = PoolDAO.getInstanciaUnica();
    
	@Override
	public void registrarReproduccion(Reproduccion reproduccion) {
		Entidad entidadReproduccion;

        entidadReproduccion = servicioPersistencia.recuperarEntidad(reproduccion.getId());

        if(entidadReproduccion != null) return;

        entidadReproduccion = new Entidad();
        entidadReproduccion.setNombre("reproduccion");

        entidadReproduccion.setPropiedades(Arrays.asList(
                new Propiedad("cancion", Integer.toString(reproduccion.getCancion().getId()))
        ));

        entidadReproduccion = servicioPersistencia.registrarEntidad(entidadReproduccion);
        reproduccion.setId(entidadReproduccion.getId());
		
	}

	@Override
	public void borrarReproduccion(Reproduccion reproduccion) {
    	Entidad entidadReproduccion;

        AdaptadorReproduccionTDS adaptadorReproduccionTDS = AdaptadorReproduccionTDS.getInstanciaUnica();
        
        entidadReproduccion = servicioPersistencia.recuperarEntidad(reproduccion.getId());
        servicioPersistencia.borrarEntidad(entidadReproduccion);	
		
	}

	@Override
	public void modificarReproduccion(Reproduccion reproduccion) {
		Entidad entidadReproduccion = servicioPersistencia.recuperarEntidad(reproduccion.getId());

        servicioPersistencia.eliminarPropiedadEntidad(entidadReproduccion, "cancion");
        servicioPersistencia.anadirPropiedadEntidad(entidadReproduccion, "cancion",
        							Integer.toString(reproduccion.getCancion().getId()));		
	}

	@Override
	public Reproduccion recuperarReproduccion(int codigo) {

        Entidad entidadReproduccion = servicioPersistencia.recuperarEntidad(codigo);

        String cancion = servicioPersistencia.recuperarPropiedadEntidad(entidadReproduccion, "cancion");
        
        Reproduccion rep = new Reproduccion(entidadReproduccion.getId(), idsToSongList(cancion));

        pool.addObjeto(entidadReproduccion.getId(), rep);

        return rep;
	}

	@Override
	public List<Reproduccion> recuperarTodasReproducciones() {
        List<Reproduccion> reproducciones = new LinkedList<>();
        List<Entidad> entidadesReproduccion = servicioPersistencia.recuperarEntidades("reproduccion");

        for(Entidad e : entidadesReproduccion)
        	reproducciones.add(recuperarReproduccion(e.getId()));

        return reproducciones;
	}
	
    private Cancion idsToSongList(String id) {
    	
        AdaptadorCancionTDS adaptadorCancionTDS = AdaptadorCancionTDS.getInstanciaUnica();
        return adaptadorCancionTDS.recuperarCancion(Integer.parseInt(id));
    }

}
