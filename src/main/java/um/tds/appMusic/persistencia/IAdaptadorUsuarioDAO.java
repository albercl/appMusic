package um.tds.appMusic.persistencia;

import um.tds.appMusic.modelo.Usuario;

import java.util.List;

public interface IAdaptadorUsuarioDAO {
    void registrarUsuario(Usuario usuario);
    void borrarUsuario(Usuario usuario);
    void modificarUsuario(Usuario usuario);
    Usuario recuperarUsuario(int codigo);
    List<Usuario> recuperarTodasUsuarios();
}
