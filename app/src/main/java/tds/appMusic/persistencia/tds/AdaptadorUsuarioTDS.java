package tds.appMusic.persistencia.tds;

import beans.Entidad;
import beans.Propiedad;
import tds.appMusic.modelo.Cancion;
import tds.appMusic.persistencia.PoolDAO;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import tds.appMusic.modelo.Playlist;
import tds.appMusic.modelo.Usuario;
import tds.appMusic.persistencia.IAdaptadorUsuarioDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {
    private static ServicioPersistencia servicioPersistencia;
    private final SimpleDateFormat dateFormat;
    private static AdaptadorUsuarioTDS instanciaUnica;

    public static AdaptadorUsuarioTDS getInstanciaUnica() {
        if(instanciaUnica == null)
            instanciaUnica = new AdaptadorUsuarioTDS();

        return instanciaUnica;
    }

    private AdaptadorUsuarioTDS() {
        servicioPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    private final PoolDAO pool = PoolDAO.getInstanciaUnica();

    @Override
    public void registrarUsuario(Usuario usuario) {
        Entidad entidadUsuario;

        //Se comprueba si el usuario está registrado en el servicio de persistencia

        entidadUsuario = servicioPersistencia.recuperarEntidad(usuario.getId());
        if(entidadUsuario != null) return;

        List<Cancion> recientes = usuario.getHistory();

        //Crear la entidad usuario
        entidadUsuario = new Entidad();
        entidadUsuario.setNombre("usuario");
        entidadUsuario.setPropiedades(new ArrayList<>(
                Arrays.asList(new Propiedad("nombre", usuario.getName()),
                        new Propiedad("fechaNacimiento", dateFormat.format(usuario.getBirthdate())),
                        new Propiedad("email", usuario.getEmail()),
                        new Propiedad("usuario", usuario.getUsername()),
                        new Propiedad("contrasena", usuario.getPassword()),
                        new Propiedad("premium", String.valueOf(usuario.isPremium())),
                        new Propiedad("playlists", listToIdString(usuario.getPlaylists())),
                        new Propiedad("recientes", listToIdString(recientes.subList(0, Math.min(recientes.size(), 10))
        )))));

        //Registrar la entidad creada
        entidadUsuario = servicioPersistencia.registrarEntidad(entidadUsuario);

        //Actualizar el id del usuario
        usuario.setId(entidadUsuario.getId());
    }

    @Override
    public void borrarUsuario(Usuario usuario) {
        Entidad entidadUsuario;
        AdaptadorPlaylistTDS adaptadorPlaylistTDS = AdaptadorPlaylistTDS.getInstanciaUnica();

        for(Playlist pl : usuario.getPlaylists()) {
            adaptadorPlaylistTDS.borrarPlaylist(pl);
        }

        entidadUsuario = servicioPersistencia.recuperarEntidad(usuario.getId());
        servicioPersistencia.borrarEntidad(entidadUsuario);
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        Entidad entidadUsuario = servicioPersistencia.recuperarEntidad(usuario.getId());

        //Actualizar todas las propiedades del usuario
        for(Propiedad p : entidadUsuario.getPropiedades()) {
            String name = p.getNombre();
            switch (name) {
                case "nombre":
                    p.setValor(usuario.getName());
                    break;
                case "fechaNacimiento":
                    p.setValor(dateFormat.format(usuario.getBirthdate()));
                    break;
                case "email":
                    p.setValor(usuario.getEmail());
                    break;
                case "usuario":
                    p.setValor(usuario.getUsername());
                    break;
                case "contrasena":
                    p.setValor(usuario.getPassword());
                    break;
                case "premium":
                    p.setValor(String.valueOf(usuario.isPremium()));
                    break;
                case "playlists":
                    p.setValor(listToIdString(usuario.getPlaylists()));
                    break;
                case "recientes":
                    List<Cancion> recientes = usuario.getHistory();
                    p.setValor(listToIdString(recientes.subList(0, Math.min(recientes.size(), 10))));
                    break;
            }

            servicioPersistencia.modificarPropiedad(p);
        }
    }

    @Override
    public Usuario recuperarUsuario(int codigo) {
        if(pool.contiene(codigo)) return (Usuario) pool.getObjeto(codigo);

        //Si no recuperar de la base de datos
        Entidad entidadUsuario = servicioPersistencia.recuperarEntidad(codigo);
        String nombreReal;
        Date fechaU = new Date();
        String emailU;
        String nombreU;
        String passwordU;
        boolean premium;

        //Recuperar entidades que no son objetos
        //Fecha
        try {
            fechaU = dateFormat.parse(
                    servicioPersistencia
                            .recuperarPropiedadEntidad(entidadUsuario, "fechaNacimiento"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Nombre
        nombreReal = servicioPersistencia.recuperarPropiedadEntidad(entidadUsuario, "nombre");

        //Email
        emailU = servicioPersistencia.recuperarPropiedadEntidad(entidadUsuario, "email");

        //Usuario
        nombreU = servicioPersistencia.recuperarPropiedadEntidad(entidadUsuario, "usuario");

        //Contraseña
        passwordU = servicioPersistencia.recuperarPropiedadEntidad(entidadUsuario, "contrasena");

        premium = Boolean.parseBoolean(servicioPersistencia.recuperarPropiedadEntidad(entidadUsuario, "premium"));
        
        
        Usuario usuario = new Usuario(entidadUsuario.getId(), nombreReal, fechaU, emailU, nombreU, passwordU, premium);

        //Recuperar entidades que son objetos
        //Playlists
        usuario.setPlaylists(
                idStringToPlaylistList(
                        servicioPersistencia.recuperarPropiedadEntidad(entidadUsuario, "playlists"))
        );

        usuario.setHistory(
                idStringToSongList(
                        servicioPersistencia.recuperarPropiedadEntidad(entidadUsuario, "recientes"))
        );

        pool.addObjeto(entidadUsuario.getId(), usuario);

        return usuario;
    }

    @Override
    public List<Usuario> recuperarTodosUsuarios() {
        List<Usuario> usuarios = new LinkedList<>();
        List<Entidad> entidadesUsuario = servicioPersistencia.recuperarEntidades("usuario");

        for(Entidad e : entidadesUsuario)
            usuarios.add(recuperarUsuario(e.getId()));

        return usuarios;
    }

    private String listToIdString(List<?> list) {
        StringBuilder ids = new StringBuilder();
        for(Object o : list) {
            if (o instanceof Playlist) {
                Playlist pl = (Playlist) o;
                ids.append(pl.getId()).append(" ");
            } else if (o instanceof Cancion) {
                Cancion c = (Cancion) o;
                ids.append(c.getId()).append(" ");
            }
        }

        return ids.toString().trim();
    }

    private List<Playlist> idStringToPlaylistList(String ids) {
        List<Playlist> playlists = new LinkedList<>();
        StringTokenizer tokenizer = new StringTokenizer(ids, " ");

        AdaptadorPlaylistTDS adaptadorPlaylistTDS = AdaptadorPlaylistTDS.getInstanciaUnica();

        while(tokenizer.hasMoreTokens()) {
            playlists.add(adaptadorPlaylistTDS.recuperarPlaylist(Integer.parseInt((String) tokenizer.nextElement())));
        }

        return playlists;
    }

    private List<Cancion> idStringToSongList(String ids) {
        List<Cancion> canciones = new LinkedList<>();
        if(ids != null) {
            StringTokenizer tokenizer = new StringTokenizer(ids, " ");

            AdaptadorCancionTDS adaptadorCancionTDS = AdaptadorCancionTDS.getInstanciaUnica();

            while (tokenizer.hasMoreTokens()) {
                canciones.add(adaptadorCancionTDS.recuperarCancion(Integer.parseInt((String) tokenizer.nextElement())));
            }
        }

        return canciones;
    }
}
