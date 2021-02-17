package um.tds.appMusic.modelo;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import um.tds.appMusic.modelo.util.ReproductorListener;

public class Reproductor {
	private MediaPlayer currentPlayer;
	private Cancion currentSong;

	private Usuario currentUser;

	private List<Cancion> queue;
	private List<Cancion> backupQueue;

	private Runnable backupEndOfMedia;
	private boolean isRepeating;
    private boolean isRandomized;

    private List<ReproductorListener> listeners;
	
	public Reproductor() {
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
        play(playlist.getCanciones(), index);
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
	    if(currentSong != null)
    	    currentPlayer.pause();
    }
    
    public void resume() {
        if(currentSong != null)
    	    currentPlayer.play();
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

            listeners.stream().forEach(l -> l.onAlternatedRandom());
        }
    }

    public void alternateRepeat() {
	    if(currentSong != null) {
            if (isRepeating) {
                currentPlayer.setOnEndOfMedia(backupEndOfMedia);
                isRepeating = false;
            } else {
                backupEndOfMedia = currentPlayer.getOnEndOfMedia();
                currentPlayer.setOnEndOfMedia(() -> {
                    currentPlayer.seek(Duration.ZERO);
                });

                isRepeating = true;
            }

            listeners.stream().forEach(l -> l.onAlternatedRepeat());
        }
    }

    public Cancion getCurrentSong() {
	    return currentSong;
    }

    private MediaPlayer createPlayer(Cancion song) {
        String fileName = song.getRuta();
        File f = new File("C:\\tds\\canciones\\"+fileName);
        Media hit = new Media(f.toURI().toString());
        MediaPlayer player = new MediaPlayer(hit);
        
        player.setOnEndOfMedia(() -> {
            int position = queue.indexOf(song);
            listeners.stream().forEach(l -> l.onFinishedSong(currentSong));

            if(position + 1 < queue.size()) {
                Cancion nextSong = queue.get(position + 1);
                currentSong = nextSong;

                MediaPlayer nextPlayer = createPlayer(nextSong);
                currentPlayer = nextPlayer;
                currentPlayer.play();

                currentUser.playedSong(nextSong);
            } else {
                listeners.stream().forEach(l -> l.onEmptyQueue());
                currentPlayer = null;
                currentSong = null;
            }
        });

        player.setVolume(0.35);
        player.play();
        listeners.stream().forEach(l -> l.onStartedSong(currentSong));
        currentUser.playedSong(song);

        return player;
    }

    private void setRepeatToFalse() {
	    if(isRepeating) {
	        isRepeating = false;
	        listeners.stream().forEach(l -> l.onAlternatedRepeat());
        }
    }
}
