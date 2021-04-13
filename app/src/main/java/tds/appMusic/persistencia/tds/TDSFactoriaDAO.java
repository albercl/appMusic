package tds.appMusic.persistencia.tds;

import tds.appMusic.persistencia.FactoriaDAO;
import tds.appMusic.persistencia.IAdaptadorCancionDAO;
import tds.appMusic.persistencia.IAdaptadorPlaylistDAO;
import tds.appMusic.persistencia.IAdaptadorUsuarioDAO;

public class TDSFactoriaDAO extends FactoriaDAO {
    public TDSFactoriaDAO () {}

    @Override
    public IAdaptadorUsuarioDAO getUsuarioDAO() {
        return AdaptadorUsuarioTDS.getInstanciaUnica();
    }

    @Override
    public IAdaptadorPlaylistDAO getPlaylistDAO() {
        return AdaptadorPlaylistTDS.getInstanciaUnica();
    }

    @Override
    public IAdaptadorCancionDAO getCancionDAO() {
        return AdaptadorCancionTDS.getInstanciaUnica();
    }


}
