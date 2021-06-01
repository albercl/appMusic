package tds.appMusic.modelo;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import tds.appMusic.modelo.util.ReproductorListener;

public class Reproductor {
	private MediaPlayer currentPlayer;
	private Cancion currentSong;

    private float volume = 1f;

    private final List<ReproductorListener> listeners;
	
	public Reproductor() {
        com.sun.javafx.application.PlatformImpl.startup(()->{});

		listeners = new LinkedList<>();
	}

	public void addListener(ReproductorListener listener) {
	    listeners.add(listener);
    }
	
    public void play(Cancion song) {
	    if(currentPlayer != null) {
            if(!currentSong.equals(song)){
                currentPlayer.stop();
                currentSong = song;
                currentPlayer = createPlayer(song);
            } else {
                currentPlayer.play();
            }
        } else {
            currentSong = song;
            currentPlayer = createPlayer(currentSong);
        }
    }
    
    public void pause() {
	    if(currentSong != null) {
            currentPlayer.pause();
        }
    }
    
    public void resume() {
        if(currentSong != null) {
            currentPlayer.play();
        }
    }

    public Cancion getCurrentSong() {
	    return currentSong;
    }

    private MediaPlayer createPlayer(Cancion song) {
        String route = song.getRutaCompleta();
        
        File f = new File(route);
        Media hit = new Media(f.toURI().toString());
        MediaPlayer player = new MediaPlayer(hit);
        player.setVolume(volume);

        player.setOnEndOfMedia(() -> listeners.forEach(l -> l.onFinishedSong(song)));

        player.play();

        return player;
    }

    public void setVolume(float volume) {
	    this.volume = volume;
	    if(currentPlayer != null)
	        currentPlayer.setVolume(volume);
    }
}
