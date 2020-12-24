package um.tds.appMusic.modelo;

//Controlador
public class AppMusic {
	private static AppMusic instanciaUnica;

	@SuppressWarnings("unused")
	private CatalogoCanciones canciones;
	@SuppressWarnings("unused")
	private CatalogoUsuarios usuarios;
	@SuppressWarnings("unused")
	private Reproductor reproductor;

	public static AppMusic getInstanciaUnica() {
		if(instanciaUnica == null)
			instanciaUnica = new AppMusic();

		return instanciaUnica;
	}
}
