package tds.appMusic.modelo.util;

import tds.appMusic.modelo.Cancion;

public interface ReproductorListener {
    default void onFinishedSong(Cancion c) {}
}
