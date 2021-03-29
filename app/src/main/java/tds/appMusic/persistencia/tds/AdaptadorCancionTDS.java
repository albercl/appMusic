package tds.appMusic.persistencia.tds;

import tds.appMusic.modelo.Cancion;
import tds.appMusic.persistencia.IAdaptadorCancionDAO;
import tds.appMusic.persistencia.PoolDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.*;

@SuppressWarnings("unused")
public class AdaptadorCancionTDS implements IAdaptadorCancionDAO {
    private static ServicioPersistencia servicioPersistencia;
    private static AdaptadorCancionTDS instanciaUnica;

    public static AdaptadorCancionTDS getInstanciaUnica() {
        if(instanciaUnica == null)
            instanciaUnica = new AdaptadorCancionTDS();

        return instanciaUnica;
    }

    private AdaptadorCancionTDS() {
        servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }

    @Override
    public void registrarCancion(Cancion cancion) {
    	Entidad entidadCancion;
        boolean existe = true;

        //Se comprueba si la cancion está registrada en el servicio de persistencia
        try {
            servicioPersistencia.recuperarEntidad(cancion.getId());
        } catch (NullPointerException e) {
            existe = false;
        }

        if(existe) return;

        //Crear la entidad cancion
        entidadCancion = new Entidad();
        entidadCancion.setNombre("cancion");
        entidadCancion.setPropiedades(new ArrayList<>(
                Arrays.asList(new Propiedad("titulo", cancion.getTitulo()),
                		//TODO: Actualizar para varios interpretes
                		new Propiedad("interprete", cancion.getInterpretes().get(0)),
                		new Propiedad("estilo", cancion.getEstilo()),
                		new Propiedad("ruta", cancion.getRuta())
                		)));

        //Registrar la entidad creada
        servicioPersistencia.registrarEntidad(entidadCancion);

        //Actualizar el id del usuario
        cancion.setId(entidadCancion.getId());
    }

    @Override
    public void borrarCancion(Cancion cancion) {
    	Entidad entidadCancion;
        AdaptadorPlaylistTDS adaptadorPlaylistTDS = AdaptadorPlaylistTDS.getInstanciaUnica();

        entidadCancion = servicioPersistencia.recuperarEntidad(cancion.getId());
        servicioPersistencia.borrarEntidad(entidadCancion);	
    }

    @Override
    public void modificarCancion(Cancion cancion) {
        Entidad entidadCancion;

        entidadCancion = servicioPersistencia.recuperarEntidad(cancion.getId());

        //Actualizar todas las propiedades de la cancion
        servicioPersistencia.eliminarPropiedadEntidad(entidadCancion, "titulo");
        servicioPersistencia.anadirPropiedadEntidad(entidadCancion, "titulo",
                                                    cancion.getTitulo());

        servicioPersistencia.eliminarPropiedadEntidad(entidadCancion, "interprete");
        servicioPersistencia.anadirPropiedadEntidad(entidadCancion, "interprete",
                                                    //TODO: Actualizar para varios interpretes
                                                    cancion.getInterpretes().get(0));

        servicioPersistencia.eliminarPropiedadEntidad(entidadCancion, "estilo");
        servicioPersistencia.anadirPropiedadEntidad(entidadCancion, "estilo",
                                                    cancion.getEstilo());

        servicioPersistencia.eliminarPropiedadEntidad(entidadCancion, "ruta");
        servicioPersistencia.anadirPropiedadEntidad(entidadCancion, "ruta",
                                                    cancion.getRuta());
    }

    @Override
    public Cancion recuperarCancion(int codigo) {
        //Comprobar si la entidad esta en el pool
        if (PoolDAO.getInstanciaUnica().contiene(codigo))
            return (Cancion) PoolDAO.getInstanciaUnica().getObjeto(codigo);

        //Si no recuperar de la base de datos
        Entidad entidadCancion = servicioPersistencia.recuperarEntidad(codigo);
        
        //Nombre
        String titulo =
                servicioPersistencia.recuperarPropiedadEntidad(entidadCancion, "titulo");

        //Email
        //TODO: Actualizar para varios interpretes
        String interprete =
                servicioPersistencia.recuperarPropiedadEntidad(entidadCancion, "interprete");

        //Usuario
        String estilo =
                servicioPersistencia.recuperarPropiedadEntidad(entidadCancion, "estilo");

        //Contraseña
        String ruta =
                servicioPersistencia.recuperarPropiedadEntidad(entidadCancion, "ruta");

        //TODO: Actualizar para soporte de varios interpretes
        Cancion cancion = new Cancion(titulo, ruta, interprete);

        PoolDAO.getInstanciaUnica().addObjeto(codigo, cancion);

        return cancion;
    }

    @Override
    public List<Cancion> recuperarTodasCanciones() {
        List<Cancion> canciones = new LinkedList<>();
        List<Entidad> entidadesCancion = servicioPersistencia.recuperarEntidades("cancion");

        for(Entidad e : entidadesCancion)
            canciones.add(recuperarCancion(e.getId()));

        return canciones;
    }
}
