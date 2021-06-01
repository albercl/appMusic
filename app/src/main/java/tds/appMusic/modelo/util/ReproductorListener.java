package tds.appMusic.modelo.util;

import tds.appMusic.modelo.Cancion;

public interface ReproductorListener {
    void onFinishedSong(Cancion c);
}
