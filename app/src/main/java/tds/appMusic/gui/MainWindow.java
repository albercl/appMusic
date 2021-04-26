package tds.appMusic.gui;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;

import pulsador.Luz;
import tds.appMusic.gui.mainPanels.MainPanel;
import tds.appMusic.gui.mainPanels.NavigationPanel;
import tds.appMusic.gui.mainPanels.PlayerPanel;
import tds.appMusic.modelo.AppMusic;
import tds.appMusic.modelo.Cancion;
import tds.appMusic.modelo.Playlist;
import tds.appMusic.modelo.util.ReproductorListener;
import um.tds.componente.CargadorCanciones;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MainWindow {
	private AppMusic controlador = AppMusic.getInstanciaUnica();

	private JFrame frmAppmusic;
	private JPanel topPanel;
	private JLabel fechaLabel;
	private JLabel horaLabel;
	private JLabel welcomeMessageLabel;
	private JButton upgradeButton;
	private JButton logoutButton;
	private JPanel leftPanel;
	private GridBagLayout gbl_leftPanel;
	private NavigationPanel navigationPanel;
	private GridBagConstraints gbc_navigationPanel;
	private JList<Playlist> playlistsPanel;
	private PlaylistListModel playlistsModel;
	private GridBagConstraints gbc_playlistsPanel;
	private MainPanel mainPanel;
	private PlayerPanel playerPanel;

	//Player control
	private boolean isPlaying = false;

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAppmusic = new JFrame();
		frmAppmusic.setTitle("AppMusic");
		frmAppmusic.setBounds(100, 100, 996, 850);
		frmAppmusic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAppmusic.getContentPane().setLayout(new BorderLayout(0, 0));

		ImageIcon iconoAppMusic = new ImageIcon(GuiUtils.loadAppIcon("icons/iconoAppMusic.png"));
		frmAppmusic.setIconImage(iconoAppMusic.getImage());

		ImageIcon iconoPremium = new ImageIcon(GuiUtils.loadAppIcon("icons/iconoAppMusicPremium.png"));
		
		//Panel superior
		topPanel = new JPanel();
		topPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		frmAppmusic.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JPanel pulsadorPanel = new JPanel();
		pulsadorPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		Luz pulsadorBusqueda = new Luz();
		pulsadorBusqueda.addEncendidoListener(e -> {
			pulsadorBusqueda.repaint();

			JFileChooser ventana = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int returnValue = ventana.showOpenDialog(frmAppmusic);

			if(returnValue == JFileChooser.APPROVE_OPTION) {
				File selected = ventana.getSelectedFile();

				controlador.loadSongsFromFile(selected.getAbsolutePath());
			}
		});

		pulsadorPanel.add(pulsadorBusqueda);
		topPanel.add(pulsadorPanel);
		
		fechaLabel = new JLabel();
		Date date = new Date();
		String strDateFormat = "E dd/MM/yyyy"; // El formato de fecha está especificado
		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
		fechaLabel.setText(objSDF.format(date));
		fechaLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		topPanel.add(fechaLabel);
		
		horaLabel = new JLabel("Hora");
		horaLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		GuiUtils.showTime(horaLabel);
		topPanel.add(horaLabel);
		
		welcomeMessageLabel = new JLabel("<html><p><b><span style=\"color: rgb(178, 34, 34)\">Bienvenido, </span>" + controlador.getUsername() + "<dynamic></b> </p></html>");
		welcomeMessageLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		topPanel.add(welcomeMessageLabel);
		
		upgradeButton = new JButton("<html><p><b><span style=\"color: rgb(239, 184, 16)\">Mejora tu cuenta</b></span></p></html>");
		upgradeButton.setForeground(Color.YELLOW);
		upgradeButton.setFont(new Font("Dialog", Font.PLAIN, 16));
		upgradeButton.setFocusPainted(false);
		upgradeButton.setBackground(Color.BLACK);
		controlador.addPremiumListener((user, isPremium) -> {
			upgradeButton.setEnabled(!isPremium);

			if(isPremium) {
				upgradeButton.setEnabled(false);
				upgradeButton.setText("<html><p><b><span style=\"color: rgb(239, 184, 16)\">Ya eres premium!</b></span></p></html>");
			} else {
				upgradeButton.setEnabled(true);
				upgradeButton.setText("<html><p><b><span style=\"color: rgb(239, 184, 16)\">Mejora tu cuenta</b></span></p></html>");
			}
		});

		upgradeButton.addActionListener(e -> {
			//Abrir la ventana
			PremiumWindow premiumWindow = new PremiumWindow();
			premiumWindow.setVisible(true);
		});
		topPanel.add(upgradeButton);
		
		logoutButton = new JButton("Cerrar sesión");
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setFont(new Font("Dialog", Font.BOLD, 16));
		logoutButton.setFocusPainted(false);
		logoutButton.setBackground(new Color(178, 34, 34));
		logoutButton.addActionListener(arg0 -> {
			frmAppmusic.setVisible(false);
			LoginWindow loginWindow = new LoginWindow();
			loginWindow.setVisible(true);

			controlador.logout();
		});
		topPanel.add(logoutButton);
		
		//Panel izquierdo (playlists y botones de navegacion)
		leftPanel = new JPanel();
		leftPanel.setBorder(new EtchedBorder());
		
		frmAppmusic.getContentPane().add(leftPanel, BorderLayout.WEST);
		gbl_leftPanel = new GridBagLayout();
		gbl_leftPanel.columnWidths = new int[] {0};
		gbl_leftPanel.rowHeights = new int[] {0, 0};
		gbl_leftPanel.columnWeights = new double[]{0.0};
		gbl_leftPanel.rowWeights = new double[]{0.0, 1.0};
		leftPanel.setLayout(gbl_leftPanel);
		
		navigationPanel = new NavigationPanel();
		gbc_navigationPanel = new GridBagConstraints();
		gbc_navigationPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_navigationPanel.insets = new Insets(0, 0, 0, 5);
		gbc_navigationPanel.gridx = 0;
		gbc_navigationPanel.gridy = 0;
		leftPanel.add(navigationPanel, gbc_navigationPanel);
		
		playlistsPanel = new JList<>();
		playlistsPanel.setBorder(new TitledBorder("Mis listas"));
		playlistsModel = new PlaylistListModel();
		playlistsModel.setPlaylists(controlador.getPlaylists());
		playlistsPanel.setModel(playlistsModel);
		gbc_playlistsPanel = new GridBagConstraints();
		gbc_playlistsPanel.anchor = GridBagConstraints.NORTHWEST;
		gbc_playlistsPanel.gridx = 0;
		gbc_playlistsPanel.gridy = 1;
		gbc_playlistsPanel.fill = GridBagConstraints.BOTH;
		playlistsPanel.setVisible(false);
		leftPanel.add(playlistsPanel, gbc_playlistsPanel);

		controlador.addPlaylistListenerToUser(l -> playlistsModel.setPlaylists(l));

		controlador.addPremiumListener((user, isPremium) -> {
			if(isPremium) {
				frmAppmusic.setIconImage(iconoPremium.getImage());
				frmAppmusic.setTitle("AppMusic Premium");
			} else {
				frmAppmusic.setIconImage(iconoAppMusic.getImage());
				frmAppmusic.setTitle("AppMusic");
			}
		});

		playlistsPanel.addListSelectionListener(e -> {
			Playlist selected = playlistsPanel.getSelectedValue();
			if(selected != null) {
				mainPanel.setSelectedPlaylistView(selected);
				playlistsPanel.clearSelection();
			}
		});
		
		navigationPanel.addSearchActionListener(e -> mainPanel.setSearchPanelView());
		
		navigationPanel.addNewListActionListener(e -> mainPanel.setPlaylistModPanelView());
		
		navigationPanel.addRecentsActionListener(e -> mainPanel.setRecentsView());
		
		navigationPanel.addMyListsActionListener(e -> playlistsPanel.setVisible(true));
		
		navigationPanel.addFavouritesActionListener(e -> mainPanel.setFavouritesView());
		
		//Panel central
		mainPanel = new MainPanel(playlistsPanel);
		frmAppmusic.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		//Panel inferior (reproductor)
		playerPanel = new PlayerPanel();
		frmAppmusic.getContentPane().add(playerPanel, BorderLayout.SOUTH);

		playerPanel.getPlayButton().addActionListener(a -> {
			int selection = mainPanel.getSelection();
			List<Cancion> songs = mainPanel.getSongs();

			if(controlador.getPlayingSong() == null) {
				if(selection != -1) {
					controlador.play(songs, selection);
					isPlaying = true;
				}
			} else {
				if(isPlaying) {
					controlador.pause();
					isPlaying = false;
				} else {
					if(selection == -1) {
						controlador.resume();
					} else {
						if(controlador.getPlayingSong().equals(songs.get(selection)))
							controlador.resume();
						else
							controlador.play(songs, selection);
					}

					isPlaying = true;
				}
			}
		});

		playerPanel.getBackButton().addActionListener(a -> controlador.goBack());

		playerPanel.getForwardButton().addActionListener(a -> controlador.goNext());

		playerPanel.getRandomButton().addActionListener(a -> controlador.alternateRandom());

		playerPanel.getReplayButton().addActionListener(a -> controlador.alternateRepeat());

		controlador.addListenerToPlayer(new ReproductorListener() {
			@Override
			public void onEmptyQueue() {
				isPlaying = false;
				playerPanel.setPlaying(false);
			}

			@Override
			public void onStartedSong(Cancion c) {
				isPlaying = true;
				playerPanel.setPlaying(true);
			}

			@Override
			public void onFinishedSong(Cancion c) {
				isPlaying = false;
				playerPanel.setPlaying(false);
			}

			@Override
			public void onPausedSong(Cancion c) {
				isPlaying = false;
				playerPanel.setPlaying(false);
			}

			@Override
			public void onResumedSong(Cancion c) {
				isPlaying = true;
				playerPanel.setPlaying(true);
			}

			@Override
			public void onAlternatedRandom() {
				playerPanel.alternateRandom();
			}

			@Override
			public void onAlternatedRepeat() {
				playerPanel.alternateReplay();
			}
		});
	}
	
	public void setVisible(boolean value) {
		frmAppmusic.setVisible(true);
	}
}
