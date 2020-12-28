package um.tds.appMusic.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import um.tds.appMusic.persistencia.DAOException;
import um.tds.appMusic.persistencia.FactoriaDAO;
import um.tds.appMusic.persistencia.IAdaptadorUsuarioDAO;


public class CatalogoUsuarios {
	
	private Map<String,Usuario> usuarios; 
	private static CatalogoUsuarios unicaInstancia = new CatalogoUsuarios();
	private FactoriaDAO dao;
	private IAdaptadorUsuarioDAO adaptadorUsuarios;
	
	private CatalogoUsuarios() {
		try {
  			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
  			adaptadorUsuarios = dao.getUsuarioDAO();
  			usuarios = new HashMap<String,Usuario>();
  			this.cargarCatalogo();
  		} catch (DAOException eDAO) {
  			eDAO.printStackTrace();
  		}
	}
	
	public static CatalogoUsuarios getUnicaInstancia(){
		return unicaInstancia;
	}
	
	/*Devuelve todos los usuarios*/
	public List<Usuario> getUsuarios(){
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		for (Usuario c:usuarios.values()) 
			lista.add(c);
		return lista;
	}
	
	public Usuario getUsuario(int id) {
		for (Usuario u:usuarios.values()) {
			if (u.getId()==id) return u;
		}
		return null;
	}
	
	public Usuario getUsuario(String nombreUsuario) {
		return usuarios.get(nombreUsuario); 
	}
	
	public void addCliente(Usuario user) {
		usuarios.put(user.getNombre(), user);
	}
	public void removeCliente (Usuario user) {
		usuarios.remove(user.getNombre());
	}
	
	/*Recupera todos los usuarios para trabajar con ellos en memoria*/
	private void cargarCatalogo() throws DAOException {
		 List<Usuario> usuariosBD = adaptadorUsuarios.recuperarTodosUsuarios();
		 for (Usuario user: usuariosBD) 
			     usuarios.put(user.getNombre(), user);
	}
}
