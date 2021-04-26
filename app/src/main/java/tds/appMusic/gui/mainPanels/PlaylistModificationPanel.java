package tds.appMusic.gui.mainPanels;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import tds.appMusic.gui.auxiliarPanels.SearchControls;
import tds.appMusic.gui.auxiliarPanels.SongTable;
import tds.appMusic.modelo.AppMusic;
import tds.appMusic.modelo.Cancion;
import tds.appMusic.modelo.Playlist;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PlaylistModificationPanel extends JPanel {
	private AppMusic controlador = AppMusic.getInstanciaUnica();

	private JTextField playlistNameField;

	private final JButton createButton;
	private final JButton deleteButton;
	private final JButton addSongButton;
	private final JButton removeSongButton;

	private final SongTable playlistTable;
	private final SongTable searchTable;

	private Playlist selectedPlaylist = null;
	private final JList<Playlist> playlistList;
	private final SearchControls searchControls;

	/**
	 * Create the panel.
	 */
	public PlaylistModificationPanel(JList<Playlist> playlistList) {
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
		playlistNameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				super.mouseClicked(mouseEvent);
				playlistNameField.setText("");
			}
		});
		
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

		searchTable = new SongTable();
		GridBagConstraints gbc_searchTable = new GridBagConstraints();
		gbc_searchTable.fill = GridBagConstraints.BOTH;
		gbc_searchTable.gridx = 0;
		gbc_searchTable.gridy = 0;
		playlistModificationPanel.add(searchTable, gbc_searchTable);

		playlistTable = new SongTable();
		playlistTable.setBorder(new TitledBorder("Playlist"));
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

		searchControls = new SearchControls(searchTable);
		GridBagConstraints gbc_searchControls = new GridBagConstraints();
		gbc_searchControls.fill = GridBagConstraints.VERTICAL;
		gbc_searchControls.insets = new Insets(0, 0, 5, 0);
		gbc_searchControls.gridx = 0;
		gbc_searchControls.gridy = 1;
		add(searchControls, gbc_searchControls);

		installListeners();

		setPlaylist(null);
	}

	private void installListeners() {
		addSongButton.addActionListener(e -> {
			//Mover canciones de una tabla a otra
			Cancion song = searchTable.getSelectedSong();
			playlistTable.addSong(song);
		});

		removeSongButton.addActionListener(e -> {
			//Eliminar canción seleccionada de la tabla de playlist
			playlistTable.removeSelectedSong();
		});

		createButton.addActionListener(e -> {
			String name = playlistNameField.getText();
			List<Cancion> songs = playlistTable.getSongs();
			int response;

			if(selectedPlaylist == null) {
				response = JOptionPane.showConfirmDialog(this,
						"Se va a crear la playlist con nombre: '" + name + "'",
						"Creación de playlist",
						JOptionPane.YES_NO_OPTION);

				//Comprobar si hay canciones en la tabla de nueva playlist y intentar crear playlist en controlador
				if(response == JOptionPane.YES_OPTION) {
					Playlist newPlaylist = controlador.addPlaylist(name, songs);
					if(!name.isEmpty() && newPlaylist != null) {
						//ok
						JOptionPane.showMessageDialog(this, "Se ha creado la playlist: '" + name + "'");
						setPlaylist(newPlaylist);
					} else {
						//error, playlist ya existe o nombre en blanco
						JOptionPane.showMessageDialog(this, "No se ha podido crear la playlist. Ya existe o el nombre está en blanco");
					}
				}
			} else {
				//Sobreescribir playlist existente
				JOptionPane.showConfirmDialog(this,
						"Se va a sobreescribir la playlist con nombre: '" + name + "'",
						"Creación de playlist",
						JOptionPane.DEFAULT_OPTION);

				controlador.removePlaylist(name);
				controlador.addPlaylist(name, songs);
			}
		});

		deleteButton.addActionListener(e -> {
			if(selectedPlaylist == null) {
				//Limpiar tabla
				playlistTable.clear();
			} else {
				//Eliminar playlist existente
				controlador.removePlaylist(playlistNameField.getText());
				setPlaylist(null);
			}
		});

		searchControls.getCancelButton().addActionListener(e -> setPlaylist(null));
	}

	public void setPlaylist(Playlist playlist) {
		if(playlist == null) {
			selectedPlaylist = null;
			playlistTable.clear();
			playlistNameField.setEnabled(true);
			playlistNameField.setText("Playlist");
			createButton.setText("Crear");
		} else {
			selectedPlaylist = playlist;
			playlistTable.setSongs(playlist.getSongs());
			playlistNameField.setText(playlist.getNombre());
			playlistNameField.setEnabled(false);
			createButton.setText("Sobreescribir");
		}
	}
}
