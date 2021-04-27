package tds.appMusic.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tds.appMusic.persistencia.FactoriaDAO;
import tds.appMusic.persistencia.IAdaptadorUsuarioDAO;

public class CatalogoUsuarios {

	private static CatalogoUsuarios instanciaUnica;

	private Map<String, Usuario> usersByUsername = new HashMap<>();
	private Map<String, Usuario> usersByEmail = new HashMap<>();

	//private FactoriaDAO dao;
	private FactoriaDAO factoriaDAO;
	private IAdaptadorUsuarioDAO adaptadorUsuarios;

	private CatalogoUsuarios(FactoriaDAO factoriaDAO) {
		this.adaptadorUsuarios = factoriaDAO.getUsuarioDAO();
		cargarUsuariosPersistencia();
	}

	public static CatalogoUsuarios getUnicaInstancia(FactoriaDAO factoriaDAO) {
		if (instanciaUnica == null)
			instanciaUnica = new CatalogoUsuarios(factoriaDAO);

		return instanciaUnica;
	}

	/* Devuelve todos los usuarios */
	public List<Usuario> getUsuarios() {
		return new ArrayList<>(usersByUsername.values());
	}

	public Usuario getUsuario(int id) {
		for (Usuario u : usersByUsername.values()) {
			if (u.getId() == id)
				return u;
		}
		return null;
	}

	public Usuario getUsuario(String nombreUsuario) {
		return usersByUsername.get(nombreUsuario);
	}

	public void addUsuario(Usuario user) {
		usersByUsername.put(user.getUsername(), user);
		usersByEmail.put(user.getEmail(), user);
		adaptadorUsuarios.registrarUsuario(user);
	}

	public void removeUsuario(Usuario user) {
		usersByUsername.remove(user.getUsername());
		usersByEmail.remove(user.getEmail());
		adaptadorUsuarios.borrarUsuario(user);
	}

	/* Recupera todos los usuarios para trabajar con ellos en memoria */
	/*
	private void cargarCatalogoUsers() throws DAOException {
		List<Usuario> usuariosBD = adaptadorUsuarios.recuperarTodosUsuarios();
		for (Usuario user : usuariosBD)
			usersByUsername.put(user.getUsername(), user);
	}
	 */

	public Usuario login(String nameU, String passU) {
		Usuario logged = usersByUsername.get(nameU);
		if(logged != null && logged.checkPassword(passU))
			return usersByUsername.get(nameU);
		else
			return null;
	}

	public boolean register(String nombreReal, Date fechaU, String emailU, String nombreU, String passwordU) {
		Usuario user1 = new Usuario(0, nombreReal, fechaU, emailU, nombreU, passwordU);
		if (usersByUsername.containsKey(nombreU) || (usersByEmail.containsKey(emailU))) {
			return false;
		} else {
			addUsuario(user1);
		}
		return true;
	}

	public boolean usernameExists(String username) {
		return usersByUsername.containsKey(username);
	}

	private void cargarUsuariosPersistencia() {
		List<Usuario> usuarios = adaptadorUsuarios.recuperarTodosUsuarios();

		for(Usuario u : usuarios) {
			usersByUsername.put(u.getUsername(), u);
			usersByEmail.put(u.getEmail(), u);
		}
	}
}
