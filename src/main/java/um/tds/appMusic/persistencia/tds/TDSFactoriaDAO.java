package um.tds.appMusic.persistencia.tds;

import um.tds.appMusic.persistencia.FactoriaDAO;
import um.tds.appMusic.persistencia.IAdaptadorCancionDAO;
import um.tds.appMusic.persistencia.IAdaptadorPlaylistDAO;
import um.tds.appMusic.persistencia.IAdaptadorUsuarioDAO;

public class TDSFactoriaDAO extends FactoriaDAO {
    public TDSFactoriaDAO () {}

    @Override
    public IAdaptadorUsuarioDAO getUsuarioDAO() {
        return null;
    }

    @Override
    public IAdaptadorPlaylistDAO getPlaylistDAO() {
        return null;
    }

    @Override
    public IAdaptadorCancionDAO getCancionDAO() {
        return null;
    }


}
