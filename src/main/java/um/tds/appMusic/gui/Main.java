package um.tds.appMusic.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Rectangle;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Main {

	private JFrame MainFrame;
	private JTable recentSongsTable;
	
	private JPanel playerPanel;
	private JPanel songsListPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.MainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MainFrame = new JFrame();
		MainFrame.setTitle("AppMusic");
		MainFrame.setBounds(500, 200, 500, 505);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon iconoAppMusic = new ImageIcon(GuiUtils.loadAppIcon("icons/iconoAppMusic.png"));
		MainFrame.setIconImage(iconoAppMusic.getImage());
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		topPanel.setBackground(SystemColor.inactiveCaption);
		MainFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel welcomeMessageLabel = new JLabel("<html><p><b><span style=\"color: rgb(178, 34, 34)\">Bienvenido, </b></span>%user%</p></html>");
		welcomeMessageLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		topPanel.add(welcomeMessageLabel);
		
		JButton upgradeButton = new JButton("<html><p><b><span style=\"color: rgb(239, 184, 16)\">Mejora tu cuenta</b></span></p></html>");
		upgradeButton.setForeground(Color.YELLOW);
		upgradeButton.setBackground(new Color(0, 0, 0));
		upgradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		upgradeButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		topPanel.add(upgradeButton);
		
		JButton logoutButton = new JButton("Cerrar sesión");
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setBackground(new Color(178, 34, 34));
		logoutButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		topPanel.add(logoutButton);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setBackground(SystemColor.inactiveCaption);
		MainFrame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		//Panel de canciones recientes
		songsListPanel = new JPanel();
		songsListPanel.setBorder(new TitledBorder(null, "Canciones Recientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		centerPanel.add(songsListPanel);
		GridBagLayout gbl_recentsSongsPanel_1 = new GridBagLayout();
		gbl_recentsSongsPanel_1.rowHeights = new int[] {10, 300, 50};
		gbl_recentsSongsPanel_1.columnWidths = new int[] {10, 300, 10};
		gbl_recentsSongsPanel_1.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_recentsSongsPanel_1.rowWeights = new double[]{0.0, 1.0, 0.0};
		songsListPanel.setLayout(gbl_recentsSongsPanel_1);
		
		JScrollPane tableScrollPane = new JScrollPane();
		tableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_tableScrollPane = new GridBagConstraints();
		gbc_tableScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_tableScrollPane.fill = GridBagConstraints.BOTH;
		gbc_tableScrollPane.gridx = 1;
		gbc_tableScrollPane.gridy = 1;
		songsListPanel.add(tableScrollPane, gbc_tableScrollPane);
		
		recentSongsTable = new JTable();
		recentSongsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		recentSongsTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Canci\u00F3n", "Artista/s"
			}
		));
		tableScrollPane.setViewportView(recentSongsTable);
		
		playerPanel = new JPanel();
		GridBagConstraints gbc_playerPanel = new GridBagConstraints();
		gbc_playerPanel.insets = new Insets(0, 0, 0, 5);
		gbc_playerPanel.fill = GridBagConstraints.BOTH;
		gbc_playerPanel.gridx = 1;
		gbc_playerPanel.gridy = 2;
		songsListPanel.add(playerPanel, gbc_playerPanel);
		
		JPanel navigationPanel = new JPanel();
		navigationPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		navigationPanel.setBackground(SystemColor.activeCaption);
		MainFrame.getContentPane().add(navigationPanel, BorderLayout.WEST);
		GridBagLayout gbl_navigationPanel = new GridBagLayout();
		gbl_navigationPanel.columnWidths = new int[] {110, 0};
		gbl_navigationPanel.rowHeights = new int[]{25, 25, 25, 25, 0, 0};
		gbl_navigationPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_navigationPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		navigationPanel.setLayout(gbl_navigationPanel);
		
		JButton searchButton = new JButton("Buscar");
		searchButton.setIconTextGap(5);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		searchButton.setOpaque(false);
		searchButton.setContentAreaFilled(false);
		searchButton.setBorderPainted(false);

		ImageIcon imageIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoSearch.png"));
		searchButton.setIcon(imageIcon);
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.fill = GridBagConstraints.VERTICAL;
		gbc_searchButton.anchor = GridBagConstraints.WEST;
		gbc_searchButton.insets = new Insets(0, 0, 5, 0);
		gbc_searchButton.gridx = 0;
		gbc_searchButton.gridy = 0;
		navigationPanel.add(searchButton, gbc_searchButton);
		
		JButton newListButton = new JButton("Nueva lista");
		newListButton.setMargin(new Insets(2, 14, 5, 2));
		newListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		newListButton.setIconTextGap(5);
		newListButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		newListButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_newListButton = new GridBagConstraints();
		gbc_newListButton.anchor = GridBagConstraints.WEST;
		gbc_newListButton.fill = GridBagConstraints.VERTICAL;
		gbc_newListButton.insets = new Insets(0, 0, 5, 0);
		gbc_newListButton.gridx = 0;
		gbc_newListButton.gridy = 1;
		newListButton.setOpaque(false);
		newListButton.setContentAreaFilled(false);
		newListButton.setBorderPainted(false);
		ImageIcon imageIcon2 = new ImageIcon(GuiUtils.loadImage("icons/iconoNewList.png")); // load the image to a imageIcon
		newListButton.setIcon(imageIcon2);
		navigationPanel.add(newListButton, gbc_newListButton);
		
		
			
		JButton recentsButton = new JButton("Recientes");
		recentsButton.setBackground(SystemColor.activeCaption);
		recentsButton.setMargin(new Insets(2, 14, 2, 7));
		recentsButton.setIconTextGap(5);
		recentsButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_recentsButton = new GridBagConstraints();
		gbc_recentsButton.fill = GridBagConstraints.VERTICAL;
		gbc_recentsButton.anchor = GridBagConstraints.WEST;
		gbc_recentsButton.insets = new Insets(0, 0, 5, 0);
		gbc_recentsButton.gridx = 0;
		gbc_recentsButton.gridy = 2;
		recentsButton.setOpaque(false);
		recentsButton.setContentAreaFilled(false);
		recentsButton.setBorderPainted(false);
		ImageIcon imageIcon3 = new ImageIcon(GuiUtils.loadImage("icons/iconoRecents.png")); // load the image to a imageIcon
		recentsButton.setIcon(imageIcon3);
		navigationPanel.add(recentsButton, gbc_recentsButton);
		
		JButton myListsButton = new JButton("Mis listas");
		myListsButton.setBounds(new Rectangle(3, 0, 0, 0));
		myListsButton.setIconTextGap(5);
		myListsButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_myListsButton = new GridBagConstraints();
		gbc_myListsButton.fill = GridBagConstraints.VERTICAL;
		gbc_myListsButton.anchor = GridBagConstraints.WEST;
		gbc_myListsButton.insets = new Insets(0, 0, 5, 0);
		gbc_myListsButton.gridx = 0;
		gbc_myListsButton.gridy = 3;
		myListsButton.setOpaque(false);
		myListsButton.setContentAreaFilled(false);
		myListsButton.setBorderPainted(false);
		ImageIcon imageIcon4 = new ImageIcon(GuiUtils.loadImage("icons/iconoMyLists.png")); // load the image to a imageIcon
		myListsButton.setIcon(imageIcon4);
		navigationPanel.add(myListsButton, gbc_myListsButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(SystemColor.inactiveCaption);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		navigationPanel.add(panel, gbc_panel);
	}
}
