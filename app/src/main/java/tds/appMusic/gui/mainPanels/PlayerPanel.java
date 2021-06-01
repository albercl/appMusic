package tds.appMusic.gui.mainPanels;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import tds.appMusic.gui.GuiUtils;
import tds.appMusic.gui.MainWindow;
import tds.appMusic.modelo.AppMusic;
import tds.appMusic.modelo.Cancion;
import tds.appMusic.modelo.util.ReproductorListener;

import java.awt.Insets;

import javax.swing.border.EtchedBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PlayerPanel extends JPanel {
	private final AppMusic controlador = AppMusic.getInstanciaUnica();
	private final MainWindow mainWindow;

	private final ImageIcon randomOffIcon;
	private final ImageIcon randomOnIcon;
	private final ImageIcon backIcon;
	private final ImageIcon playIcon;
	private final ImageIcon pauseIcon;
	private final ImageIcon forwardIcon;
	private final ImageIcon replayOffIcon;
	private final ImageIcon replayOnIcon;

	private JPanel buttonsPanel;
	private JButton randomButton;
	private JButton backButton;
	private JButton playButton;
	private JButton forwardButton;
	private JButton replayButton;
	private JPanel volumePanel;
	private JSlider slider;
	private JPanel songInfoPanel;
	private JScrollPane scrollPane;
	private JLabel songTitleLabel;
	private JLabel songArtistLabel;
	private JLabel songGenreLabel;

	//Queue control
	private int currentSongIndex;
	private Cancion currentSong;
	private List<Cancion> queue;
	//Copia de la cola para cuando se randomize la original
	private List<Cancion> backupQueue;
	private boolean isPaused = false;
	private boolean isRepeating = false;
	private boolean isRandom = false;

	/**
	 * Create the panel.
	 */
	public PlayerPanel(MainWindow mainWindow) {
		this.mainWindow = mainWindow;

		setBorder(new EtchedBorder());
		backIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoBack.png"));
		playIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoPlay.png", 45, 45));
		pauseIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoPause.png", 45, 45));
		forwardIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoForward.png"));
		replayOffIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoReplay.png"));
		replayOnIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoReplay2.png"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {200, 200, 200};
		gridBagLayout.rowHeights = new int[]{20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(200, 80));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.anchor = GridBagConstraints.WEST;
		gbc_scrollPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_scrollPane.insets = new Insets(10, 10, 10, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		songInfoPanel = new JPanel();
		scrollPane.setViewportView(songInfoPanel);
		songInfoPanel.setLayout(new GridLayout(3, 1, 0, 0));
		
		songTitleLabel = new JLabel("Esperando canción...");
		songTitleLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		songInfoPanel.add(songTitleLabel);
		
		songArtistLabel = new JLabel("");
		songInfoPanel.add(songArtistLabel);
		
		songGenreLabel = new JLabel("");
		songGenreLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		songInfoPanel.add(songGenreLabel);
		
		buttonsPanel = new JPanel();
		GridBagConstraints gbc_buttonsPanel = new GridBagConstraints();
		gbc_buttonsPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonsPanel.insets = new Insets(0, 0, 0, 5);
		gbc_buttonsPanel.gridx = 1;
		gbc_buttonsPanel.gridy = 0;
		add(buttonsPanel, gbc_buttonsPanel);
		
		randomButton = new JButton("");
		randomButton.setOpaque(false);
		randomButton.setMargin(new Insets(2, 0, 2, 8));
		randomButton.setHorizontalAlignment(SwingConstants.LEFT);
		randomButton.setFocusPainted(false);
		randomButton.setContentAreaFilled(false);
		randomButton.setBorderPainted(false);
		randomOffIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoRandom.png"));
		randomOnIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoDefault.png"));
		randomButton.setIcon(randomOffIcon);
		buttonsPanel.add(randomButton);
		
		backButton = new JButton("");
		backButton.setOpaque(false);
		backButton.setMargin(new Insets(2, 0, 2, 0));
		backButton.setHorizontalAlignment(SwingConstants.LEFT);
		backButton.setFocusPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.setIcon(backIcon);
		buttonsPanel.add(backButton);
		
		playButton = new JButton("");
		playButton.setOpaque(false);
		playButton.setMargin(new Insets(2, 0, 2, 0));
		playButton.setHorizontalAlignment(SwingConstants.LEFT);
		playButton.setFocusPainted(false);
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);
		playButton.setIcon(playIcon);
		buttonsPanel.add(playButton);
		
		forwardButton = new JButton("");
		forwardButton.setOpaque(false);
		forwardButton.setMargin(new Insets(2, 0, 2, 8));
		forwardButton.setHorizontalAlignment(SwingConstants.LEFT);
		forwardButton.setFocusPainted(false);
		forwardButton.setContentAreaFilled(false);
		forwardButton.setBorderPainted(false);
		forwardButton.setIcon(forwardIcon);
		buttonsPanel.add(forwardButton);
		
		replayButton = new JButton("");
		replayButton.setOpaque(false);
		replayButton.setMargin(new Insets(2, 0, 2, 0));
		replayButton.setIconTextGap(0);
		replayButton.setHorizontalTextPosition(SwingConstants.LEADING);
		replayButton.setHorizontalAlignment(SwingConstants.LEFT);
		replayButton.setFocusPainted(false);
		replayButton.setContentAreaFilled(false);
		replayButton.setBorderPainted(false);
		replayButton.setIcon(replayOffIcon);
		buttonsPanel.add(replayButton);
		
		volumePanel = new JPanel();
		GridBagConstraints gbc_volumePanel = new GridBagConstraints();
		gbc_volumePanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_volumePanel.anchor = GridBagConstraints.EAST;
		gbc_volumePanel.gridx = 2;
		gbc_volumePanel.gridy = 0;
		add(volumePanel, gbc_volumePanel);
		
		slider = new JSlider();
		slider.setValue(50);
		slider.setMaximum(50);


		volumePanel.add(slider);

		installListeners();
	}

	private void installListeners() {
		slider.addChangeListener(e -> controlador.setVolume(slider.getValue() / 100f));

		controlador.addListenerToPlayer(new ReproductorListener() {
			@Override
			public void onFinishedSong(Cancion c) {
				if(isRepeating) {
					controlador.play(currentSong);
				} else if (!nextSong()) {
					currentSong = null;
				} else {
					mainWindow.selectSongOnTable(currentSong);
				}

				setSongInfo(currentSong);

			}
		});

		playButton.addActionListener(e -> {
			int selection = mainWindow.getSelection();
			List<Cancion> songs = mainWindow.getSongs();

			if(currentSong == null) {
				if(selection >= 0) {
					currentSong = songs.get(selection);
					playSong();
				} else {
					alternatePause();
				}
			} else {
				if(selection >= 0) {
					if(!songs.get(selection).equals(currentSong) && isPaused) {
						currentSong = songs.get(selection);
						playSong();
					} else {
						alternatePause();
					}
				}
			}
		});

		backButton.addActionListener(e -> {
			if(currentSongIndex - 1 >= 0) {
				currentSongIndex -= 1;
				currentSong = queue.get(currentSongIndex);
				setSongInfo(currentSong);

				isPaused = false;
				playButton.setIcon(pauseIcon);
				mainWindow.selectSongOnTable(currentSong);
				controlador.play(currentSong);
			}
		});

		forwardButton.addActionListener(e -> nextSong());

		randomButton.addActionListener(e -> {
			if(isRandom) {
				isRandom = false;
				queue = new LinkedList<>(backupQueue);
				currentSongIndex = queue.indexOf(currentSong);

				randomButton.setIcon(randomOffIcon);

			} else {
				isRandom = true;
				Collections.shuffle(queue);

				queue.add(0, currentSong);
				currentSongIndex = 0;

				randomButton.setIcon(randomOnIcon);
			}
		});

		replayButton.addActionListener(e -> {
			if(isRepeating) {
				isRepeating = false;
				replayButton.setIcon(replayOffIcon);
			} else {
				isRepeating = true;
				replayButton.setIcon(replayOnIcon);
			}
		});
	}

	private boolean	 nextSong() {
		if(currentSongIndex + 1 < queue.size()) {
			currentSongIndex += 1;
			currentSong = queue.get(currentSongIndex);
			setSongInfo(currentSong);
			isPaused = false;
			playButton.setIcon(pauseIcon);
			mainWindow.selectSongOnTable(currentSong);
			controlador.play(currentSong);
			return true;
		}

		return false;
	}

	private void setSongInfo(Cancion c) {
		if(c != null) {
			songTitleLabel.setText(c.getTitulo());
			songArtistLabel.setText(c.getInterpretesString());
			songGenreLabel.setText(c.getEstilo());
		} else {
			songTitleLabel.setText("Esperando canción...");
			songArtistLabel.setText("");
			songGenreLabel.setText("");
		}
	}

	private void playSong() {
		currentSongIndex = mainWindow.getSelection();
		queue = new LinkedList<>(mainWindow.getSongs());
		backupQueue = new LinkedList<>(queue);

		currentSong = queue.get(currentSongIndex);

		if (isRandom) {
			Collections.shuffle(queue);

			queue.add(0, currentSong);
			currentSongIndex = 0;
		}

		setSongInfo(currentSong);
		mainWindow.selectSongOnTable(currentSong);
		isPaused = false;
		controlador.play(currentSong);
		playButton.setIcon(pauseIcon);
	}

	private void alternatePause() {
		if(isPaused) {
			isPaused = false;
			controlador.resume();
			playButton.setIcon(pauseIcon);
		} else {
			isPaused = true;
			controlador.pause();
			playButton.setIcon(playIcon);
		}
	}
}
