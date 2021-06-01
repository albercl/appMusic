package tds.appMusic.modelo;

import java.util.*;

import tds.appMusic.persistencia.FactoriaDAO;
import tds.appMusic.persistencia.IAdaptadorUsuarioDAO;

public class CatalogoUsuarios {

	private Map<String, Usuario> usersByUsername = new HashMap<>();
	private Map<String, Usuario> usersByEmail = new HashMap<>();

	public CatalogoUsuarios(List<Usuario> usuarios) {
		for(Usuario u : usuarios) {
			usersByUsername.put(u.getUsername(), u);
			usersByEmail.put(u.getEmail(), u);
		}
	}

	/* Devuelve todos los usuarios */

	public Usuario getUsuarioByUsername(String nombreUsuario) {
		return usersByUsername.get(nombreUsuario);
	}

	public Usuario getUsuarioByEmail(String email) {
		return usersByEmail.get(email);
	}

	public Collection<Usuario> getUsuarios() {
		return usersByEmail.values();
	}

	public void addUsuario(Usuario user) {
		usersByUsername.put(user.getUsername(), user);
		usersByEmail.put(user.getEmail(), user);
	}
}
