package um.tds.appMusic.modelo;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Reproductor {
	private MediaPlayer currentPlayer;
	private Cancion currentSong;

	private Usuario applicant;
	private List<Cancion> queue;

	private Runnable backupEndOfMedia;
	private boolean isRepeating;
	
	public Reproductor() {
		try {
            com.sun.javafx.application.PlatformImpl.startup(()->{});
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

		queue = new LinkedList<>();
	}

    public void setApplicant(Usuario applicant) {
        this.applicant = applicant;
    }

    public void play(Playlist playlist) {
        queue.clear();
        queue = new LinkedList<>(playlist.getCanciones());

        currentSong = queue.get(0);
        currentPlayer = createPlayer(currentSong);

        isRepeating = false;
	}
	
    public void play(Cancion song) {
	    queue.clear();
	    queue.add(song);

	    currentSong = song;
        currentPlayer = createPlayer(currentSong);

        isRepeating = false;
    }
    
    public void pause() {
    	currentPlayer.pause();
    }
    
    public void resume() {
    	currentPlayer.play();
    }

    public void goNext() {
        int position = queue.indexOf(currentSong);
        if(position + 1 < queue.size()) {
            currentSong = queue.get(position + 1);

            currentPlayer.stop();
            currentPlayer = createPlayer(currentSong);

            isRepeating = false;
        }
    }

    public void goBack() {
        int position = queue.indexOf(currentSong);
        if(position - 1 > 0) {
            currentSong = queue.get(position - 1);

            currentPlayer.stop();
            currentPlayer = createPlayer(currentSong);

            isRepeating = false;
        }
    }

    public void randomize() {
        queue = queue.subList(queue.indexOf(currentSong), queue.size() - 1);
        Collections.shuffle(queue);
    }

    public void alternateRepeat() {
	    if(isRepeating) {
            currentPlayer.setOnEndOfMedia(backupEndOfMedia);
            isRepeating = false;
        } else {
            backupEndOfMedia = currentPlayer.getOnEndOfMedia();
            currentPlayer.setOnEndOfMedia(() -> {
                currentPlayer.seek(Duration.ZERO);
            });

            isRepeating = true;
        }
    }

    private MediaPlayer createPlayer(Cancion song) {
        String fileName = song.getRuta();
        File f = new File("C:\\tds\\canciones\\"+fileName);
        Media hit = new Media(f.toURI().toString());
        MediaPlayer player = new MediaPlayer(hit);

        player.setOnEndOfMedia(() -> {
            int position = queue.indexOf(player);

            if(position + 1 < queue.size()) {
                Cancion nextSong = queue.get(position + 1);
                MediaPlayer nextPlayer = createPlayer(nextSong);

                currentSong = nextSong;
                currentPlayer = nextPlayer;
                currentPlayer.play();
                applicant.playedSong(nextSong);
            } else
                currentPlayer = null;
        });

        applicant.playedSong(song);

        return player;
    }

    public boolean isRepeating() {
        return isRepeating;
    }
}
