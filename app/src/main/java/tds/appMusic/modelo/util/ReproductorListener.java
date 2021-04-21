package tds.appMusic.modelo.util;

import tds.appMusic.modelo.Cancion;

public interface ReproductorListener {
    default void onEmptyQueue() {}
    default void onStartedSong(Cancion c) {}
    default void onFinishedSong(Cancion c) {}
    default void onAlternatedRandom() {}
    default void onAlternatedRepeat() {}
    default void onPausedSong(Cancion c) {}
    default void onResumedSong(Cancion c) {}
}
