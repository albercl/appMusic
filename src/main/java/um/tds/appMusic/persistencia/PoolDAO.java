package um.tds.appMusic.persistencia;

import java.util.HashMap;
import java.util.Map;

public class PoolDAO {
    private static PoolDAO instanciaUnica;

    //TODO: consultar diferencias entre Map y Hashtable
    private Map<Integer, Object> pool;

    private PoolDAO() {
        pool = new HashMap<>();
    }

    public static PoolDAO getInstanciaUnica() {
        if(instanciaUnica == null)
            instanciaUnica = new PoolDAO();

        return instanciaUnica;
    }

    public Object getObjeto(int id) {
        return pool.get(id);
    }

    public void addObjeto(int id, Object objeto) {
        pool.put(id, objeto);
    }

    public boolean contiene(int id) {
        return pool.containsKey(id);
    }
}
