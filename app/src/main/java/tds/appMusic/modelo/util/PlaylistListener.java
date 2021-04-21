package tds.appMusic.modelo.util;

import tds.appMusic.modelo.Playlist;

import java.util.List;

public interface PlaylistListener {
    void playlistListChanged(List<Playlist> playlists);
}
