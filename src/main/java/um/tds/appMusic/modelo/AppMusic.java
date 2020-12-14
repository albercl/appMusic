package um.tds.appMusic.modelo;

//Controlador
public class AppMusic {
	private static AppMusic instanciaUnica;

	private CatalogoCanciones canciones;
	private CatalogoUsuarios usuarios;
	private Reproductor reproductor;

	public static AppMusic getInstanciaUnica() {
		if(instanciaUnica == null)
			instanciaUnica = new AppMusic();

		return instanciaUnica;
	}
}
