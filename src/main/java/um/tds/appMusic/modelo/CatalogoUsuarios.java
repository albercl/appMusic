package um.tds.appMusic.modelo;

import java.util.HashMap;
import java.util.Map;

public class CatalogoUsuarios {
    private Map<String, Usuario> usuarios;

    public CatalogoUsuarios() {
        usuarios = new HashMap<>();
    }

    public Usuario getUsuario(String usuario) {
        return usuarios.get(usuario);
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }
}
