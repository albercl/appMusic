package um.tds.appMusic.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.settings.ThemeSettings;
import com.github.weisj.darklaf.theme.DarculaTheme;
import com.github.weisj.darklaf.theme.IntelliJTheme;

import um.tds.appMusic.gui.mainPanels.MainPanel;
import um.tds.appMusic.gui.mainPanels.NavigationPanel;
import um.tds.appMusic.gui.mainPanels.PlayerPanel;
import um.tds.appMusic.gui.mainPanels.PlaylistsPanel;
import um.tds.appMusic.modelo.AppMusic;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private PlaylistsPanel playlistsPanel;
	private GridBagConstraints gbc_playlistsPanel;
	private MainPanel mainPanel;
	private PlayerPanel playerPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmAppmusic.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		
		
		//Panel superior
		topPanel = new JPanel();
		topPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		frmAppmusic.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		fechaLabel = new JLabel();
		fechaLabel.setText("mié. 17/02/2021");
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
		topPanel.add(upgradeButton);
		
		logoutButton = new JButton("Cerrar sesión");
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setFont(new Font("Dialog", Font.BOLD, 16));
		logoutButton.setFocusPainted(false);
		logoutButton.setBackground(new Color(178, 34, 34));
		topPanel.add(logoutButton);
		
		//Panel izquierdo (playlist y botones de navegacion)
		leftPanel = new JPanel();
		leftPanel.setBorder(new EtchedBorder());
		
		frmAppmusic.getContentPane().add(leftPanel, BorderLayout.WEST);
		gbl_leftPanel = new GridBagLayout();
		gbl_leftPanel.columnWidths = new int[] {0};
		gbl_leftPanel.rowHeights = new int[] {0};
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
		
		playlistsPanel = new PlaylistsPanel();
		gbc_playlistsPanel = new GridBagConstraints();
		gbc_playlistsPanel.anchor = GridBagConstraints.WEST;
		gbc_playlistsPanel.gridx = 0;
		gbc_playlistsPanel.gridy = 1;
		leftPanel.add(playlistsPanel, gbc_playlistsPanel);
		
		navigationPanel.addSearchActionListener(e -> 
			mainPanel.setSearchPanelView()
		);
		
		navigationPanel.addNewListActionListener(e -> 
			mainPanel.setPlaylistPanelView()
		);
		
		navigationPanel.addRecentsActionListener(e -> 
			mainPanel.setRecentsView()
		);
		
		navigationPanel.addMyListsActionListener(e -> {
			
		});
		
		navigationPanel.addFavouritesActionListener(e ->
			mainPanel.setFavouritesView()
		);
		
		//Panel central
		mainPanel = new MainPanel();
		frmAppmusic.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		//Panel inferior (reproductor)
		playerPanel = new PlayerPanel();
		frmAppmusic.getContentPane().add(playerPanel, BorderLayout.SOUTH);
	}
	
	public void setVisible(boolean value) {
		frmAppmusic.setVisible(true);
	}
}
