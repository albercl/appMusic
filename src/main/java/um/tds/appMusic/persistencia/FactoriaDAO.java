package um.tds.appMusic.persistencia;

public abstract class FactoriaDAO {
    private static FactoriaDAO instanciaUnica;

    public static final String DAO_TDS = "persistencia.TDSFactoriaDAO";

    public static FactoriaDAO getInstancia(String tipo) throws DAOException {
        if(instanciaUnica == null) {
            try {
                instanciaUnica = (FactoriaDAO) Class.forName(tipo).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new DAOException(e.getMessage());
            }
        }

        return instanciaUnica;
    }

    public static FactoriaDAO getInstancia() throws DAOException {
        return getInstancia(DAO_TDS);
    }

    protected FactoriaDAO() {}

    public abstract IAdaptadorUsuarioDAO getUsuarioDAO();
    public abstract IAdaptadorPlaylistDAO getPlaylistDAO();
    public abstract IAdaptadorCancionDAO getCancionDAO();
}
