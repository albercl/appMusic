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

	private Usuario currentUser;

	private List<Cancion> queue;
	private List<Cancion> backupQueue;

	private Runnable backupEndOfMedia;
	private boolean isRepeating;
    private boolean isRandomized;
    private float volume = 1f;

    private List<ReproductorListener> listeners;
	
	public Reproductor() {
        com.sun.javafx.application.PlatformImpl.startup(()->{});

		queue = new LinkedList<>();
		listeners = new LinkedList<>();
	}

	public void addListener(ReproductorListener listener) {
	    listeners.add(listener);
    }

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }

    public void play(Playlist playlist, int index) {
        play(playlist.getSongs(), index);
	}

    public void play(List<Cancion> songs, int index) {
        queue.clear();
        queue = new LinkedList<>(songs);

        currentSong = queue.get(index);
        currentPlayer = createPlayer(currentSong);

        setRepeatToFalse();
        isRandomized = false;
    }
	
    public void play(Cancion song) {
	    if(currentSong == song) {
	        currentPlayer.play();
        } else {
            queue.clear();
            queue.add(song);

            currentSong = song;
            currentPlayer = createPlayer(currentSong);

            setRepeatToFalse();
            isRandomized = false;
        }
    }
    
    public void pause() {
	    if(currentSong != null) {
            currentPlayer.pause();
            listeners.forEach(l -> l.onPausedSong(currentSong));
        }
    }
    
    public void resume() {
        if(currentSong != null) {
            currentPlayer.play();
            listeners.forEach(l -> l.onResumedSong(currentSong));
        }
    }

    public Cancion goNext() {
	    if(currentSong != null) {
            int position = queue.indexOf(currentSong);
            if (position + 1 < queue.size()) {
                currentSong = queue.get(position + 1);

                currentPlayer.stop();
                currentPlayer = createPlayer(currentSong);

                setRepeatToFalse();
            }
        }

        return currentSong;
    }

    public Cancion goBack() {
        if (currentSong != null){
            int position = queue.indexOf(currentSong);
            if (position - 1 >= 0) {
                currentSong = queue.get(position - 1);

                currentPlayer.stop();
                currentPlayer = createPlayer(currentSong);

                setRepeatToFalse();
            }
        }

        return currentSong;
    }

    public void alternateRandom() {
	    if(currentSong != null) {
            if (!isRandomized) {
                backupQueue = new LinkedList<>(queue);
                Collections.shuffle(queue);
                queue.remove(currentSong);
                queue.add(0, currentSong);

                isRandomized = true;
            } else {
                queue = backupQueue;
                isRandomized = false;
            }

            listeners.forEach(ReproductorListener::onAlternatedRandom);
        }
    }

    public void alternateRepeat() {
	    if(currentSong != null) {
            if (isRepeating) {
                currentPlayer.setOnEndOfMedia(backupEndOfMedia);
                isRepeating = false;
            } else {
                backupEndOfMedia = currentPlayer.getOnEndOfMedia();
                currentPlayer.setOnEndOfMedia(() -> currentPlayer.seek(Duration.ZERO));

                isRepeating = true;
            }

            listeners.forEach(ReproductorListener::onAlternatedRepeat);
        }
    }

    public Cancion getCurrentSong() {
	    return currentSong;
    }

    private MediaPlayer createPlayer(Cancion song) {
        String fileName = song.getRuta();
        String route;
        if(System.getProperty("os.name").equals("Linux"))
        	route = System.getProperty("user.home") + "/tds/canciones/" + fileName.replace("\\", "/");
        else
        	route = "C:\\tds\\canciones\\" + fileName;
        
        File f = new File(route);
        Media hit = new Media(f.toURI().toString());
        MediaPlayer player = new MediaPlayer(hit);
        player.setVolume(volume);
        
        player.setOnEndOfMedia(() -> {
            int position = queue.indexOf(song);
            listeners.forEach(l -> l.onFinishedSong(currentSong));

            if(position + 1 < queue.size()) {
                Cancion nextSong = queue.get(position + 1);
                currentSong = nextSong;

                currentPlayer = createPlayer(nextSong);
                currentPlayer.play();

                currentUser.playedSong(nextSong);
            } else {
                listeners.forEach(ReproductorListener::onEmptyQueue);
                currentPlayer = null;
                currentSong = null;
            }
        });

        player.play();
        listeners.forEach(l -> l.onStartedSong(currentSong));
        currentUser.playedSong(song);

        return player;
    }

    private void setRepeatToFalse() {
	    if(isRepeating) {
	        isRepeating = false;
	        listeners.forEach(ReproductorListener::onAlternatedRepeat);
        }
    }

    public void setVolume(float volume) {
	    this.volume = volume;
	    if(currentPlayer != null)
	        currentPlayer.setVolume(volume);
    }
}
