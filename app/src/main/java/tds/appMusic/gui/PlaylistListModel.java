package tds.appMusic.gui;

import tds.appMusic.modelo.Playlist;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class PlaylistListModel implements ListModel<Playlist> {
    private List<Playlist> playlists = new LinkedList<>();
    private final List<ListDataListener> listeners = new LinkedList<>();

    @Override
    public int getSize() {
        return playlists.size();
    }

    @Override
    public Playlist getElementAt(int i) {
        return playlists.get(i);
    }

    @Override
    public void addListDataListener(ListDataListener listDataListener) {
        listeners.add(listDataListener);
    }

    @Override
    public void removeListDataListener(ListDataListener listDataListener) {
        listeners.remove(listDataListener);
    }

    public void setPlaylists(Collection<Playlist> playlists) {
        this.playlists = new LinkedList<>(playlists);
        notifyListChanges();
    }

    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
        notifyListChanges();
    }

    public void removePlaylist(Playlist playlist) {
        playlists.remove(playlist);
        notifyListChanges();
    }

    private void notifyListChanges() {
        ListDataEvent event = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, playlists.size());
        listeners.forEach(l -> l.contentsChanged(event));
    }
}
