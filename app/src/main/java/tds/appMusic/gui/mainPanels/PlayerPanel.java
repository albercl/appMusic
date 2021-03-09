package tds.appMusic.gui.mainPanels;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import tds.appMusic.modelo.AppMusic;
import tds.appMusic.modelo.Cancion;
import tds.appMusic.modelo.util.ReproductorListener;
import tds.appMusic.oldgui.GuiUtils;

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

public class PlayerPanel extends JPanel {
	private final AppMusic controlador = AppMusic.getInstanciaUnica();

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

	/**
	 * Create the panel.
	 */
	public PlayerPanel() {
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
		slider.addChangeListener(e -> {
			controlador.setVolume(slider.getValue() / 100f);
		});

		volumePanel.add(slider);

		controlador.addListenerToPlayer(new ReproductorListener() {
			@Override
			public void onStartedSong(Cancion c) {
				songTitleLabel.setText(c.getTitulo());
				songArtistLabel.setText(c.getInterpretesString());
				songGenreLabel.setText(c.getEstilo());
			}

			@Override
			public void onEmptyQueue() {
				songTitleLabel.setText("Esperando canción...");
				songArtistLabel.setText("");
				songGenreLabel.setText("");
			}
		});
	}

	public JButton getRandomButton() {
		return randomButton;
	}

	public void setRandom(boolean value) {
		if(value)
			randomButton.setIcon(randomOnIcon);
		else
			randomButton.setIcon(randomOffIcon);
	}

	public void alternateRandom() {
		setRandom(!randomButton.getIcon().equals(randomOnIcon));
	}

	public JButton getBackButton() {
		return backButton;
	}

	public JButton getPlayButton() {
		return playButton;
	}

	public void setPlaying(boolean value) {
		if(value)
			playButton.setIcon(pauseIcon);
		else
			playButton.setIcon(playIcon);

	}

	public void alternatePlayingPause() {
		setPlaying(!playButton.getIcon().equals(pauseIcon));
	}

	public JButton getForwardButton() {
		return forwardButton;
	}

	public JButton getReplayButton() {
		return replayButton;
	}

	public void setReplay(boolean value) {
		if(value)
			replayButton.setIcon(replayOnIcon);
		else
			replayButton.setIcon(replayOffIcon);
	}

	public void alternateReplay() {
		setReplay(!replayButton.getIcon().equals(replayOnIcon));
	}
}
