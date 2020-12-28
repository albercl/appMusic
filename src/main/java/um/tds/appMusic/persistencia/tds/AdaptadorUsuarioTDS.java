package um.tds.appMusic.persistencia.tds;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import um.tds.appMusic.modelo.Playlist;
import um.tds.appMusic.modelo.Usuario;
import um.tds.appMusic.persistencia.IAdaptadorUsuarioDAO;
import um.tds.appMusic.persistencia.PoolDAO;

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

    @Override
    public void registrarUsuario(Usuario usuario) {
        Entidad entidadUsuario;
        boolean existe = true;

        //Se comprueba si el usuario está registrado en el servicio de persistencia
        try {
            servicioPersistencia.recuperarEntidad(usuario.getId());
        } catch (NullPointerException e) {
            existe = false;
        }

        if(existe) return;

        //Se registran las playlists del usuario
        AdaptadorPlaylistTDS adaptadorPlaylistTDS = AdaptadorPlaylistTDS.getInstanciaUnica();
        for(Playlist pl : usuario.getPlaylists()) {
            adaptadorPlaylistTDS.registrarPlaylist(pl);
        }

        //Crear la entidad usuario
        entidadUsuario = new Entidad();
        entidadUsuario.setNombre("usuario");
        entidadUsuario.setPropiedades(new ArrayList<>(
                Arrays.asList(new Propiedad("nombre", usuario.getNombre()),
                        new Propiedad("fechaNacimiento", dateFormat.format(usuario.getFechaNacimiento())),
                        new Propiedad("email", usuario.getEmail()),
                        new Propiedad("usuario", usuario.getUsuario()),
                        new Propiedad("contrasena", usuario.getContrasena()),
                        new Propiedad("premium", String.valueOf(usuario.isPremium())),
                        new Propiedad("playlists", obtenerCodigosPlaylists(usuario.getPlaylists()))
        )));

        //Registrar la entidad creada
        servicioPersistencia.registrarEntidad(entidadUsuario);

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
        Entidad entidadUsuario;

        entidadUsuario = servicioPersistencia.recuperarEntidad(usuario.getId());

        //Actualizar todas las propiedades del usuario
        servicioPersistencia.eliminarPropiedadEntidad(entidadUsuario, "nombre");
        servicioPersistencia.anadirPropiedadEntidad(entidadUsuario, "nombre",
                                                    usuario.getNombre());

        servicioPersistencia.eliminarPropiedadEntidad(entidadUsuario, "fechaNacimiento");
        servicioPersistencia.anadirPropiedadEntidad(entidadUsuario, "fechaNacimiento",
                                                    dateFormat.format(usuario.getFechaNacimiento()));

        servicioPersistencia.eliminarPropiedadEntidad(entidadUsuario, "email");
        servicioPersistencia.anadirPropiedadEntidad(entidadUsuario, "email",
                                                    usuario.getEmail());

        servicioPersistencia.eliminarPropiedadEntidad(entidadUsuario, "usuario");
        servicioPersistencia.anadirPropiedadEntidad(entidadUsuario, "usuario",
                                                    usuario.getUsuario());

        servicioPersistencia.eliminarPropiedadEntidad(entidadUsuario, "contrasena");
        servicioPersistencia.anadirPropiedadEntidad(entidadUsuario, "contrasena",
                                                    usuario.getContrasena());

        servicioPersistencia.eliminarPropiedadEntidad(entidadUsuario, "premium");
        servicioPersistencia.anadirPropiedadEntidad(entidadUsuario, "premium",
                                                    String.valueOf(usuario.isPremium()));

        servicioPersistencia.eliminarPropiedadEntidad(entidadUsuario, "playlists");
        servicioPersistencia.anadirPropiedadEntidad(entidadUsuario, "playlists",
                                                    obtenerCodigosPlaylists(usuario.getPlaylists()));
    }

    @Override
    public Usuario recuperarUsuario(int codigo) {
        //Comprobar si la entidad esta en el pool
        if (PoolDAO.getInstanciaUnica().contiene(codigo))
            return (Usuario) PoolDAO.getInstanciaUnica().getObjeto(codigo);

        //Si no recuperar de la base de datos
        Entidad entidadUsuario = servicioPersistencia.recuperarEntidad(codigo);
        String nombreReal;
        Date fechaU = new Date();
        String emailU;
        String nombreU;
        String passwordU;

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
        
        
        Usuario usuario = new Usuario(nombreReal, fechaU, emailU, nombreU, passwordU);
        PoolDAO.getInstanciaUnica().addObjeto(codigo, usuario);

        //Recuperar entidades que son objetos
        //Playlists
        usuario.setPlaylists(
                recuperarPlaylistsDesdeCodigos(
                        servicioPersistencia.recuperarPropiedadEntidad(entidadUsuario, "playlists")));

        
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

    private String obtenerCodigosPlaylists(List<Playlist> playlists) {
        StringBuilder codigos = new StringBuilder();

        for(Playlist pl : playlists) {
            codigos.append(pl.getId()).append(' ');
        }

        return codigos.toString().trim();
    }

    private List<Playlist> recuperarPlaylistsDesdeCodigos(String codigos) {
        List<Playlist> playlists = new LinkedList<>();

        AdaptadorPlaylistTDS adaptadorPlaylistTDS = AdaptadorPlaylistTDS.getInstanciaUnica();
        Arrays.stream(codigos.split(" ")).forEach(c -> {
            int cInt = Integer.parseInt(c);
            playlists.add(adaptadorPlaylistTDS.recuperarPlaylist(cInt));
        });

        return playlists;
    }
}
