package um.tds.appMusic.modelo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Usuario {
	//Datos personales
	private String nombre;
	private Date fechaNacimiento;
	private String email;
	
	//Credenciales
	private String usuario;
	private String contraseña;
	
	//Datos de la cuenta
	private boolean premium;
	private List<Playlist> playlists;      
	
	//Constructores
	/**
	 * Constructor de la clase para el registro
	 * @param nombre Nombre completo del usuario
	 * @param fechaNacimiento Fecha de nacimiento del usuario
	 * @param email Correo electrónico del usuario
	 * @param usuario Nombre de usuario para el inicio de sesión
	 * @param contraseña Contraseña para el inicio de sesión
	 */
	public Usuario(String nombre, Date fechaNacimiento, String email, String usuario, String contraseña) {
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		
		this.usuario = usuario;
		this.contraseña = contraseña;
		
		premium = false;
		playlists = new LinkedList<>();
	}
	
	//Funciones
	/**
	 * Funcionalidad de usuario premium. Genera un PDF de la lista dada si el usuario es premium.
	 * Para generar el archivo el usuario debe ser premium.
	 * @param pl Playlist de la que generar el PDF
	 * @return true si se ha generado el PDF, false si no se ha generado
	 */
	public boolean generarPDFPlaylist(Playlist pl) {
		if(premium)
			return pl.generarPDF();
		
		return false;
	}
}
