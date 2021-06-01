package tds.appMusic.persistencia.tds;

import beans.Entidad;
import beans.Propiedad;
import com.sun.jndi.ldap.pool.Pool;
import tds.appMusic.modelo.AppMusic;
import tds.appMusic.modelo.Cancion;
import tds.appMusic.persistencia.PoolDAO;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import tds.appMusic.modelo.Playlist;
import tds.appMusic.persistencia.IAdaptadorPlaylistDAO;

import java.time.LocalDate;
import java.util.*;

@SuppressWarnings("unused")
public class AdaptadorPlaylistTDS implements IAdaptadorPlaylistDAO {
	private static ServicioPersistencia servicioPersistencia;
    private static AdaptadorPlaylistTDS instanciaUnica;

    public static AdaptadorPlaylistTDS getInstanciaUnica() {
        if (instanciaUnica == null)
            instanciaUnica = new AdaptadorPlaylistTDS();

        return instanciaUnica;
    }

    private AdaptadorPlaylistTDS() {
        servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }

    private final PoolDAO pool = PoolDAO.getInstanciaUnica();

    @Override
    public void registrarPlaylist(Playlist playlist) {
        Entidad entidadPlaylist;

        entidadPlaylist = servicioPersistencia.recuperarEntidad(playlist.getId());

        if(entidadPlaylist != null) return;

        entidadPlaylist = new Entidad();
        entidadPlaylist.setNombre("playlist");

        entidadPlaylist.setPropiedades(Arrays.asList(
                new Propiedad("nombre", playlist.getNombre()),
                new Propiedad("canciones", songListToIdString(playlist.getSongs()))
        ));

        entidadPlaylist = servicioPersistencia.registrarEntidad(entidadPlaylist);

        playlist.setId(entidadPlaylist.getId());
    }

    @Override
    public void borrarPlaylist(Playlist playlist) {
        Entidad entidadPlaylist;

        //TODO: Borrar playlist de usuario
        AdaptadorUsuarioTDS adaptadorUsuarioTDS = AdaptadorUsuarioTDS.getInstanciaUnica();

        entidadPlaylist = servicioPersistencia.recuperarEntidad(playlist.getId());
        servicioPersistencia.borrarEntidad(entidadPlaylist);
    }

    @Override
    public void modificarPlaylist(Playlist playlist) {
        Entidad entidadPlaylist = servicioPersistencia.recuperarEntidad(playlist.getId());

        //Actualizar todas las propiedades de la cancion
        servicioPersistencia.eliminarPropiedadEntidad(entidadPlaylist, "nombre");
        servicioPersistencia.anadirPropiedadEntidad(entidadPlaylist, "nombre", playlist.getNombre());

        servicioPersistencia.eliminarPropiedadEntidad(entidadPlaylist, "canciones");
        servicioPersistencia.anadirPropiedadEntidad(entidadPlaylist, "canciones", songListToIdString(playlist.getSongs()));

    }

    @Override
    public Playlist recuperarPlaylist(int codigo) {
        if (pool.contiene(codigo))
            return (Playlist) pool.getObjeto(codigo);

        Entidad entidadPlaylist = servicioPersistencia.recuperarEntidad(codigo);

        String nombre = servicioPersistencia.recuperarPropiedadEntidad(entidadPlaylist, "nombre");
        String songs = servicioPersistencia.recuperarPropiedadEntidad(entidadPlaylist, "canciones");

        Playlist pl = new Playlist(entidadPlaylist.getId(), nombre, idsToSongList(songs));

        pool.addObjeto(entidadPlaylist.getId(), pl);

        return pl;
    }

    @Override
    public List<Playlist> recuperarTodasPlaylists() {
        List<Playlist> playlists = new LinkedList<>();
        List<Entidad> entidadesPlaylist = servicioPersistencia.recuperarEntidades("playlist");

        for(Entidad e : entidadesPlaylist)
            playlists.add(recuperarPlaylist(e.getId()));

        return playlists;
    }

    private String songListToIdString(List<Cancion> songs) {
        StringBuilder songsIds = new StringBuilder();
        for(Cancion cancion : songs)
            songsIds.append(cancion.getId()).append(" ");

        return songsIds.toString().trim();
    }

    private List<Cancion> idsToSongList(String ids) {
        List<Cancion> canciones = new LinkedList<>();
        StringTokenizer tokenizer = new StringTokenizer(ids, " ");

        AdaptadorCancionTDS adaptadorCancionTDS = AdaptadorCancionTDS.getInstanciaUnica();

        while(tokenizer.hasMoreTokens()) {
            canciones.add(adaptadorCancionTDS.recuperarCancion(Integer.parseInt((String) tokenizer.nextElement())));
        }

        return canciones;
    }
}
