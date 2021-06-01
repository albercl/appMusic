package tds.appMusic.gui.mainPanels;

import java.awt.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import tds.appMusic.gui.MainWindow;
import tds.appMusic.gui.auxiliarPanels.SongTable;
import tds.appMusic.gui.auxiliarPanels.SongTableModel;
import tds.appMusic.modelo.AppMusic;
import tds.appMusic.modelo.Cancion;
import tds.appMusic.modelo.Playlist;
import tds.appMusic.modelo.util.ReproductorListener;

public class MainPanel extends JPanel {
	private AppMusic controlador = AppMusic.getInstanciaUnica();
	private final MainWindow mainWindow;
	private final JList<Playlist> playlistList;

	public static final int
			NONE = 0,
			SEARCH = 1,
			FAVOURITES = 2,
			RECENTS = 3,
			PLAYLIST_MOD = 4,
			PLAYLIST = 5;

	private int state = NONE;

	private SearchPanel searchPanel;
	private PlaylistModificationPanel playlistModificationPanel;
	private SongTable recentsPanel;
	private FavouritesPanel favouritesPanel;

	private JPanel playlistPanel;
	private SongTable playlistTable;
	private JButton generatePdfButton;
	private Playlist selectedPlaylist;
	
	public MainPanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		playlistList = mainWindow.getPlaylistsList();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {500};
		gridBagLayout.rowHeights = new int[] {0};
		gridBagLayout.columnWeights = new double[]{0.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		favouritesPanel = new FavouritesPanel();
		GridBagConstraints gbc_favouritesPanel = new GridBagConstraints();
		gbc_favouritesPanel.fill = GridBagConstraints.VERTICAL;
		gbc_favouritesPanel.gridx = 0;
		gbc_favouritesPanel.gridy = 0;
		add(favouritesPanel, gbc_favouritesPanel);
		searchPanel = new SearchPanel();
		GridBagConstraints gbc_searchPanel = new GridBagConstraints();
		gbc_searchPanel.fill = GridBagConstraints.VERTICAL;
		gbc_searchPanel.gridx = 0;
		gbc_searchPanel.gridy = 0;
		add(searchPanel, gbc_searchPanel);
		
		recentsPanel = new SongTable(SongTableModel.NORMAL_MODE);
		recentsPanel.setPreferredSize(new Dimension(500, 0));
		GridBagConstraints gbc_recentsPanel = new GridBagConstraints();
		gbc_recentsPanel.fill = GridBagConstraints.VERTICAL;
		gbc_recentsPanel.gridx = 0;
		gbc_recentsPanel.gridy = 0;
		add(recentsPanel, gbc_recentsPanel);
		
		playlistModificationPanel = new PlaylistModificationPanel(mainWindow);
		GridBagConstraints gbc_playlistModificationPanel = new GridBagConstraints();
		gbc_playlistModificationPanel.fill = GridBagConstraints.VERTICAL;
		gbc_playlistModificationPanel.gridx = 0;
		gbc_playlistModificationPanel.gridy = 0;
		add(playlistModificationPanel, gbc_playlistModificationPanel);


		//Panel de vista de playlist
		playlistPanel = new JPanel(new BorderLayout());

		GridBagConstraints gbc_playlistPanel = new GridBagConstraints();
		gbc_playlistPanel.fill = GridBagConstraints.BOTH;
		gbc_playlistPanel.gridx = 0;
		gbc_playlistPanel.gridy = 0;

		//Tabla
		playlistTable = new SongTable(SongTableModel.NORMAL_MODE);
		playlistTable.setPreferredSize(new Dimension(500, 0));
		playlistPanel.add(playlistTable, BorderLayout.CENTER);

		//Boton
		generatePdfButton = new JButton();
		generatePdfButton.setText("Generar PDF");
		playlistPanel.add(generatePdfButton, BorderLayout.SOUTH);
		generatePdfButton.addActionListener(e -> selectedPlaylist.generarPDF());

		add(playlistPanel, gbc_playlistPanel);

		generatePdfButton.setVisible(controlador.isPremium());
		
		resetView();
	}

	public void setView(int view) {
		resetView();

		switch (view) {
			case SEARCH:
				setSearchPanelView();
				break;
			case FAVOURITES:
				setFavouritesView();
				break;
			case RECENTS:
				setRecentsView();
				break;
			case PLAYLIST_MOD:
				setPlaylistModPanelView();
				break;
			case PLAYLIST:
				setSelectedPlaylistView();
				break;
		}
	}

	public List<Cancion> getSongs() {
		switch (state) {
			case SEARCH:
				return searchPanel.getSongsList();
			case RECENTS:
				return recentsPanel.getSongs();
			case FAVOURITES:
				return favouritesPanel.getSongs();
			case PLAYLIST:
				return playlistTable.getSongs();
			default:
				return null;
		}
	}

	public int getSelection() {
		switch (state) {
			case SEARCH:
				return searchPanel.getSelection();
			case RECENTS:
				return recentsPanel.getSelection();
			case FAVOURITES:
				return favouritesPanel.getSelection();
			case PLAYLIST:
				return playlistTable.getSelection();
			default:
				return -1;
		}
	}
	
	public void resetView() {
		searchPanel.setVisible(false);
		playlistModificationPanel.setVisible(false);
		recentsPanel.setVisible(false);
		favouritesPanel.setVisible(false);
		playlistPanel.setVisible(false);
		playlistList.setVisible(false);

		setBorder(null);
	}
	
	public void setSearchPanelView() {
		state = SEARCH;

		this.setBorder(new TitledBorder("Búsqueda de canciones"));
		searchPanel.setVisible(true);
	}
	
	public void setPlaylistModPanelView() {
		state = PLAYLIST_MOD;

		this.setBorder(new TitledBorder("Modificación de playlist"));
		playlistModificationPanel.setVisible(true);
		playlistList.setVisible(true);
	}
	
	public void setRecentsView() {
		state = RECENTS;

		this.setBorder(new TitledBorder("Canciones recientes"));
		recentsPanel.setSongs(controlador.getUserHistory());
		recentsPanel.setVisible(true);
	}
	
	public void setFavouritesView() {
		state = FAVOURITES;

		this.setBorder(new TitledBorder("Canciones favoritas"));
		favouritesPanel.setSongs(controlador.getUserReproductions());
		favouritesPanel.setVisible(true);
	}

	public void setSelectedPlaylistView() {
		Playlist playlist = playlistList.getSelectedValue();
		if (state == PLAYLIST_MOD) {
			setPlaylistModPanelView();
			playlistModificationPanel.setPlaylist(playlist);
		} else {
			state = PLAYLIST;
			playlistTable.setSongs(playlist.getSongs());
			playlistPanel.setVisible(true);
			playlistList.setVisible(true);
			selectedPlaylist = playlist;
		}
	}

	public void updatePremium(boolean isPremium) {
		generatePdfButton.setVisible(isPremium);
	}

	public void selectSongOnTable(Cancion c) {
		switch (state) {
			case SEARCH:
				searchPanel.getTable().selectSong(c);
			case RECENTS:
				recentsPanel.selectSong(c);
			case FAVOURITES:
				favouritesPanel.selectSong(c);
			case PLAYLIST:
				playlistTable.selectSong(c);
		}
	}
}
