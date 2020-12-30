package um.tds.appMusic.modelo.util;

import um.tds.appMusic.modelo.Cancion;

public class ReproductorListener {
    public void onEmptyQueue() {}
    public void onStartedSong(Cancion c) {}
    public void onFinishedSong(Cancion c) {}
    public void onAlternatedRandom() {}
    public void onAlternatedRepeat() {}
}
