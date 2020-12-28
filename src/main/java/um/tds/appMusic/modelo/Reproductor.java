package um.tds.appMusic.modelo;

import java.io.File;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Reproductor {
	private MediaPlayer mediaPlayer;
	
	public Reproductor() {
		try {
            com.sun.javafx.application.PlatformImpl.startup(()->{});
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
	}
	
	public void play(List<Cancion> canciones) {
		
	}
	
    public void play(Cancion cancion) {     
        String fileName = cancion.getRuta();
        File f = new File("C:\\tds\\canciones\\"+fileName);
        Media hit = new Media(f.toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }
    
    public void pause() {
    	mediaPlayer.pause();
    }
    
    public void resume() {
    	mediaPlayer.play();
    }
}
