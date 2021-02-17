package um.tds.appMusic.gui.mainPanels;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import um.tds.appMusic.oldgui.GuiUtils;

import java.awt.Insets;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class PlayerPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public PlayerPanel() {
		setBorder(new EtchedBorder());
		
		JButton randomButton = new JButton("");
		randomButton.setOpaque(false);
		randomButton.setMargin(new Insets(2, 0, 2, 8));
		randomButton.setHorizontalAlignment(SwingConstants.LEFT);
		randomButton.setFocusPainted(false);
		randomButton.setContentAreaFilled(false);
		randomButton.setBorderPainted(false);
		ImageIcon randomOffIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoRandom.png"));
		randomButton.setIcon(randomOffIcon);
		ImageIcon randomOnIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoDefault.png"));
		add(randomButton);
		
		JButton backButton = new JButton("");
		backButton.setOpaque(false);
		backButton.setMargin(new Insets(2, 0, 2, 0));
		backButton.setHorizontalAlignment(SwingConstants.LEFT);
		backButton.setFocusPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		ImageIcon backIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoBack.png"));
		backButton.setIcon(backIcon);
		add(backButton);
		
		JButton playButton = new JButton("");
		playButton.setOpaque(false);
		playButton.setMargin(new Insets(2, 0, 2, 0));
		playButton.setHorizontalAlignment(SwingConstants.LEFT);
		playButton.setFocusPainted(false);
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);
		ImageIcon playIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoPlay.png", 45, 45));
		playButton.setIcon(playIcon);
		ImageIcon pauseIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoPause.png", 45, 45));
		add(playButton);
		
		JButton forwardButton = new JButton("");
		forwardButton.setOpaque(false);
		forwardButton.setMargin(new Insets(2, 0, 2, 8));
		forwardButton.setHorizontalAlignment(SwingConstants.LEFT);
		forwardButton.setFocusPainted(false);
		forwardButton.setContentAreaFilled(false);
		forwardButton.setBorderPainted(false);
		ImageIcon forwardIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoForward.png"));
		forwardButton.setIcon(forwardIcon);
		add(forwardButton);
		
		JButton replayButton = new JButton("");
		replayButton.setOpaque(false);
		replayButton.setMargin(new Insets(2, 0, 2, 0));
		replayButton.setIconTextGap(0);
		replayButton.setHorizontalTextPosition(SwingConstants.LEADING);
		replayButton.setHorizontalAlignment(SwingConstants.LEFT);
		replayButton.setFocusPainted(false);
		replayButton.setContentAreaFilled(false);
		replayButton.setBorderPainted(false);
		ImageIcon replayOffIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoReplay.png"));
		replayButton.setIcon(replayOffIcon);
		ImageIcon replayOnIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoReplay2.png"));
		add(replayButton);

	}
}
