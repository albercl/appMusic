package um.tds.appMusic.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import um.tds.appMusic.persistencia.DAOException;
import um.tds.appMusic.persistencia.FactoriaDAO;
import um.tds.appMusic.persistencia.IAdaptadorUsuarioDAO;

public class CatalogoUsuarios {

	private Map<String, Usuario> usersByUsername;
	private Map<String, Usuario> usersByEmail;
	private static CatalogoUsuarios unicaInstancia = new CatalogoUsuarios();
	private FactoriaDAO dao;
	private IAdaptadorUsuarioDAO adaptadorUsuarios;

	private CatalogoUsuarios() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
			adaptadorUsuarios = dao.getUsuarioDAO();
			usersByUsername = new HashMap<String, Usuario>();
			usersByEmail = new HashMap<String, Usuario>();
			this.cargarCatalogoUsers();
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}

	public static CatalogoUsuarios getUnicaInstancia() {
		return unicaInstancia;
	}

	/* Devuelve todos los usuarios */
	public List<Usuario> getUsuarios() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		for (Usuario c : usersByUsername.values())
			lista.add(c);
		return lista;
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
		usersByUsername.put(user.getName(), user);
	}

	public void removeUsuario(Usuario user) {
		usersByUsername.remove(user.getName());
	}

	/* Recupera todos los usuarios para trabajar con ellos en memoria */
	private void cargarCatalogoUsers() throws DAOException {
		List<Usuario> usuariosBD = adaptadorUsuarios.recuperarTodosUsuarios();
		for (Usuario user : usuariosBD)
			usersByEmail.put(user.getName(), user);
	}

	public Usuario login(String nameU, String passU) {
		if (usersByUsername.get(nameU).getContrasena().equals(passU))
			return usersByUsername.get(nameU);
		else
			return null;
	}

	public boolean register(String nombreReal, Date fechaU, String emailU, String nombreU, String passwordU) {
		Usuario user1 = new Usuario(nombreReal, fechaU, emailU, nombreU, passwordU);
		if (usersByUsername.containsKey(nombreU) || (usersByEmail.containsKey(emailU))) {
			return false;
		} else
			addUsuario(user1);
		return true;
	}

}
