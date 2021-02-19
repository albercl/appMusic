package um.tds.appMusic.gui.mainPanels;

import java.awt.*;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import um.tds.appMusic.gui.auxiliarPanels.SearchTable;
import um.tds.appMusic.modelo.AppMusic;
import um.tds.appMusic.modelo.Cancion;
import um.tds.appMusic.modelo.util.ReproductorListener;

public class MainPanel extends JPanel {
	private AppMusic controlador = AppMusic.getInstanciaUnica();

	public static final int
			NONE = 0,
			SEARCH = 1,
			FAVOURITES = 2,
			RECENTS = 3,
			PLAYLIST = 4;

	private int state = NONE;
	

	private SearchPanel searchPanel;
	private PlaylistModificationPanel playlistPanel;
	private SearchTable recentsPanel;
	private FavouritesPanel favouritesPanel;

	
	public MainPanel() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {300};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		favouritesPanel = new FavouritesPanel();
		GridBagConstraints gbc_favouritesPanel = new GridBagConstraints();
		gbc_favouritesPanel.fill = GridBagConstraints.BOTH;
		gbc_favouritesPanel.gridx = 0;
		gbc_favouritesPanel.gridy = 0;
		add(favouritesPanel, gbc_favouritesPanel);
		searchPanel = new SearchPanel();
		GridBagConstraints gbc_searchPanel = new GridBagConstraints();
		gbc_searchPanel.fill = GridBagConstraints.BOTH;
		gbc_searchPanel.gridx = 1;
		gbc_searchPanel.gridy = 0;
		add(searchPanel, gbc_searchPanel);
		
		recentsPanel = new SearchTable();
		recentsPanel.setPreferredSize(new Dimension(500, 0));
		GridBagConstraints gbc_recentsPanel = new GridBagConstraints();
		gbc_recentsPanel.fill = GridBagConstraints.BOTH;
		gbc_recentsPanel.gridx = 2;
		gbc_recentsPanel.gridy = 0;
		add(recentsPanel, gbc_recentsPanel);
		
		playlistPanel = new PlaylistModificationPanel();
		GridBagConstraints gbc_playlistPanel = new GridBagConstraints();
		gbc_playlistPanel.fill = GridBagConstraints.BOTH;
		gbc_playlistPanel.gridx = 3;
		gbc_playlistPanel.gridy = 0;
		add(playlistPanel, gbc_playlistPanel);

		controlador.addListenerToPlayer(new ReproductorListener() {
			@Override
			public void onStartedSong(Cancion c) {
				JTable table = getTable();
				List<Cancion> songs = getSongs();

				int index = songs.indexOf(c);
				if(index != -1)
					table.setRowSelectionInterval(index, index);
			}
		});
		
		resetView();
	}
	
	public void resetView() {
		searchPanel.setVisible(false);
		playlistPanel.setVisible(false);
		recentsPanel.setVisible(false);
		favouritesPanel.setVisible(false);
		setBorder(null);
	}
	
	public void setSearchPanelView() {
		state = SEARCH;

		resetView();
		this.setBorder(new TitledBorder("Búsqueda de canciones"));
		searchPanel.setVisible(true);
	}
	
	public void setPlaylistPanelView() {
		state = PLAYLIST;

		resetView();
		this.setBorder(new TitledBorder("Modificación de playlist"));
		playlistPanel.setVisible(true);
	}
	
	public void setRecentsView() {
		state = RECENTS;

		resetView();
		this.setBorder(new TitledBorder("Canciones recientes"));
		recentsPanel.setSongs(controlador.getUserHistory());
		recentsPanel.setVisible(true);
	}
	
	public void setFavouritesView() {
		state = FAVOURITES;

		resetView();
		this.setBorder(new TitledBorder("Canciones favoritas"));
		favouritesPanel.setSongs(controlador.getUserReproductions());
		favouritesPanel.setVisible(true);
	}

	public List<Cancion> getSongs() {
		switch (state) {
		case SEARCH:
			return searchPanel.getSongsList();
		case PLAYLIST:
			return null;
		case RECENTS:
			return recentsPanel.getSongs();
		case FAVOURITES:

			default:
				return searchPanel.getSongsList();
		}
	}

	public int getSelection() {
		switch (state) {
		case SEARCH:
			return searchPanel.getSelection();
		case PLAYLIST:
			return -1;
		case RECENTS:
			return recentsPanel.getSelection();
		case FAVOURITES:
			default:
				return searchPanel.getSelection();
		}
	}

	public JTable getTable() {
		switch (state) {
		case SEARCH:
			return searchPanel.getTable();
		case PLAYLIST:
			return null;
		case RECENTS:
			return recentsPanel.getTable();
		case FAVOURITES:
			default:
				return searchPanel.getTable();
		}
	}
}