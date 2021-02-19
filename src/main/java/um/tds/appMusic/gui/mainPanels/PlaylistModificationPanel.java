package um.tds.appMusic.gui.mainPanels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import um.tds.appMusic.gui.auxiliarPanels.SearchControls;
import um.tds.appMusic.gui.auxiliarPanels.SearchTable;
import um.tds.appMusic.modelo.AppMusic;
import um.tds.appMusic.modelo.Cancion;
import um.tds.appMusic.modelo.Playlist;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class PlaylistModificationPanel extends JPanel {
	private AppMusic controlador = AppMusic.getInstanciaUnica();

	private JTextField playlistNameField;

	private final JButton createButton;
	private final JButton deleteButton;
	private final JButton addSongButton;
	private final JButton removeSongButton;

	private final SearchTable playlistTable;
	private final SearchTable searchTable;

	private Playlist selectedPlaylist;
	private JList<String> playlistList;

	/**
	 * Create the panel.
	 */
	public PlaylistModificationPanel(JList<String> playlistList) {
		this.playlistList = playlistList;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {500};
		gridBagLayout.rowHeights = new int[] {0, 0, 300};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0};
		setLayout(gridBagLayout);
		
		JPanel playlistCreationPanel = new JPanel();
		GridBagConstraints gbc_playlistCreationPanel = new GridBagConstraints();
		gbc_playlistCreationPanel.insets = new Insets(0, 0, 5, 0);
		gbc_playlistCreationPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_playlistCreationPanel.gridx = 0;
		gbc_playlistCreationPanel.gridy = 0;
		add(playlistCreationPanel, gbc_playlistCreationPanel);
		
		playlistNameField = new JTextField();
		playlistNameField.setText("Playlist");
		playlistCreationPanel.add(playlistNameField);
		playlistNameField.setColumns(10);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		playlistCreationPanel.add(horizontalStrut);

		createButton = new JButton("Crear");
		createButton.setPreferredSize(new Dimension(100, 25));
		playlistCreationPanel.add(createButton);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		playlistCreationPanel.add(horizontalStrut_1);

		deleteButton = new JButton("Eliminar");
		deleteButton.setPreferredSize(new Dimension(100, 25));
		playlistCreationPanel.add(deleteButton);
		
		JPanel playlistModificationPanel = new JPanel();
		GridBagConstraints gbc_playlistModificationPanel = new GridBagConstraints();
		gbc_playlistModificationPanel.fill = GridBagConstraints.VERTICAL;
		gbc_playlistModificationPanel.gridx = 0;
		gbc_playlistModificationPanel.gridy = 2;
		add(playlistModificationPanel, gbc_playlistModificationPanel);
		GridBagLayout gbl_playlistModificationPanel = new GridBagLayout();
		gbl_playlistModificationPanel.columnWidths = new int[] {100, 20, 100};
		gbl_playlistModificationPanel.rowHeights = new int[] {0};
		gbl_playlistModificationPanel.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_playlistModificationPanel.rowWeights = new double[]{1.0};
		playlistModificationPanel.setLayout(gbl_playlistModificationPanel);

		searchTable = new SearchTable();
		searchTable.getTable().setModel(new DefaultTableModel(new Object[][] {}, new Object[] {"Título", "Intérprete"}));
		GridBagConstraints gbc_searchTable = new GridBagConstraints();
		gbc_searchTable.fill = GridBagConstraints.BOTH;
		gbc_searchTable.gridx = 0;
		gbc_searchTable.gridy = 0;
		playlistModificationPanel.add(searchTable, gbc_searchTable);

		playlistTable = new SearchTable();
		playlistTable.getTable().setModel(new DefaultTableModel(new Object[][] {}, new Object[] {"Título", "Intérprete"}));
		GridBagConstraints gbc_playlistTable = new GridBagConstraints();
		gbc_playlistTable.fill = GridBagConstraints.BOTH;
		gbc_playlistTable.gridx = 2;
		gbc_playlistTable.gridy = 0;
		playlistModificationPanel.add(playlistTable, gbc_playlistTable);
		
		JPanel modificationControlsPanel = new JPanel();
		GridBagConstraints gbc_modificationControlsPanel = new GridBagConstraints();
		gbc_modificationControlsPanel.insets = new Insets(0, 10, 0, 10);
		gbc_modificationControlsPanel.gridx = 1;
		gbc_modificationControlsPanel.gridy = 0;
		playlistModificationPanel.add(modificationControlsPanel, gbc_modificationControlsPanel);
		GridBagLayout gbl_modificationControlsPanel = new GridBagLayout();
		gbl_modificationControlsPanel.columnWidths = new int[] {0};
		gbl_modificationControlsPanel.rowHeights = new int[] {10, 10};
		gbl_modificationControlsPanel.columnWeights = new double[]{0.0};
		gbl_modificationControlsPanel.rowWeights = new double[]{0.0, 0.0, 0.0};
		modificationControlsPanel.setLayout(gbl_modificationControlsPanel);

		addSongButton = new JButton("Añadir");
		addSongButton.setPreferredSize(new Dimension(100, 25));
		GridBagConstraints gbc_addSongButton = new GridBagConstraints();
		gbc_addSongButton.fill = GridBagConstraints.BOTH;
		gbc_addSongButton.insets = new Insets(0, 0, 5, 0);
		gbc_addSongButton.gridx = 0;
		gbc_addSongButton.gridy = 1;
		modificationControlsPanel.add(addSongButton, gbc_addSongButton);

		removeSongButton = new JButton("Eliminar");
		removeSongButton.setPreferredSize(new Dimension(100, 25));
		GridBagConstraints gbc_romoveSongButton = new GridBagConstraints();
		gbc_romoveSongButton.fill = GridBagConstraints.BOTH;
		gbc_romoveSongButton.gridx = 0;
		gbc_romoveSongButton.gridy = 2;
		modificationControlsPanel.add(removeSongButton, gbc_romoveSongButton);
		
		SearchControls searchControls = new SearchControls(searchTable);
		GridBagConstraints gbc_searchControls = new GridBagConstraints();
		gbc_searchControls.fill = GridBagConstraints.VERTICAL;
		gbc_searchControls.insets = new Insets(0, 0, 5, 0);
		gbc_searchControls.gridx = 0;
		gbc_searchControls.gridy = 1;
		add(searchControls, gbc_searchControls);

		installListeners();
	}

	private void installListeners() {
		//TODO: Hacer que no se pueda reproducir la playlist
		//TODO: Evitar que se puedan hacer 2 playlist con el mismo nombre
		//TODO: Hacer que se ponga un borde con nombre al seleccionar una playlist
		addSongButton.addActionListener(e -> {
			Cancion song = searchTable.getSelectedSong();
			if(song != null && selectedPlaylist != null) {
				playlistTable.addSong(song);
				selectedPlaylist.addSong(song);
			}
		});

		removeSongButton.addActionListener(e -> {
			Cancion song = playlistTable.getSelectedSong();
			assert selectedPlaylist != null;
			if(song != null) {
				playlistTable.removeSong(song);
				selectedPlaylist.removeCancion(song);
			}
		});

		createButton.addActionListener(e -> {
			String playlistName = playlistNameField.getText();
			if(!playlistName.isEmpty() && !playlistName.equals("Playlist")) {
				selectedPlaylist = new Playlist(playlistNameField.getText());
				playlistTable.setSongs(selectedPlaylist.getCanciones());

				controlador.addPlaylist(selectedPlaylist);

				DefaultListModel<String> model = new DefaultListModel<>();
				controlador.getPlaylists()
						.forEach(p -> model.addElement(p.getNombre()));
				playlistList.clearSelection();
				playlistList.setModel(model);
			}
		});

		deleteButton.addActionListener(e -> {
			if(selectedPlaylist != null) {
				controlador.removePlaylist(selectedPlaylist);
				playlistTable.setSongs(new LinkedList<>());

				DefaultListModel<String> model = new DefaultListModel<>();
				controlador.getPlaylists()
						.forEach(p -> model.addElement(p.getNombre()));
				playlistList.clearSelection();
				playlistList.setModel(model);
			}
		});

		playlistNameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				playlistNameField.setText("");
			}
		});
	}

	public void setPlaylist(Playlist playlist) {
		selectedPlaylist = playlist;
		playlistTable.setSongs(playlistTable.getSongs());
		playlistNameField.setText(playlist.getNombre());
	}

	public List<Cancion> getSongs() {
		return selectedPlaylist.getCanciones();
	}

	public int getSelection() {
		return playlistTable.getSelection();
	}

	public JTable getTable() {
		return playlistTable.getTable();
	}
}
