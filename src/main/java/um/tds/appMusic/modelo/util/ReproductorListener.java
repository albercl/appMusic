package um.tds.appMusic.modelo.util;

import um.tds.appMusic.modelo.Cancion;

abstract public class ReproductorListener {
    public void onEmptyQueue() {}
    public void onStartedSong(Cancion c) {}
    public void onFinishedSong(Cancion c) {}
    public void onAlternatedRandom() {}
    public void onAlternatedRepeat() {}
    public void onPausedSong(Cancion c) {}
    public void onResumedSong(Cancion c) {}
}
