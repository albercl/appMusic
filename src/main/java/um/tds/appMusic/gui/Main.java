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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Rectangle;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.AbstractListModel;

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
	 * Launch the application.
	 */
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		MainFrame.setVisible(b);
	}
	
	void showTime(JLabel label) {
		new Timer(0, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Date d = new Date();
		        String strDateFormat = "kk:mm:ss zz"; // El formato de fecha está especificado  
		        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
		        label.setText("- "+objSDF.format(d)+" -");
			}
		}) .start();
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
		MainFrame.setBounds(400, 100, 650, 650);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon iconoAppMusic = new ImageIcon(GuiUtils.loadAppIcon("icons/iconoAppMusic.png"));
		MainFrame.setIconImage(iconoAppMusic.getImage());

		JPanel topPanel = new JPanel();
		topPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		topPanel.setBackground(SystemColor.inactiveCaption);
		MainFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		Date date1 = new Date();
        String strDateFormat = "E dd/MM/yyyy"; // El formato de fecha está especificado  
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
		JLabel fechaLabel = new JLabel();
		fechaLabel.setText(objSDF.format(date1) + " -");
		fechaLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		topPanel.add(fechaLabel);

		JLabel welcomeMessageLabel = new JLabel(
				"<html><p><b><span style=\"color: rgb(178, 34, 34)\">Bienvenido, </span>Luis_Gregorio</b> -</p></html>");
		welcomeMessageLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		topPanel.add(welcomeMessageLabel);

		JButton upgradeButton = new JButton(
				"<html><p><b><span style=\"color: rgb(239, 184, 16)\">Mejora tu cuenta</b></span></p></html>");
		upgradeButton.setFocusPainted(false);
		upgradeButton.setForeground(Color.YELLOW);
		upgradeButton.setBackground(new Color(0, 0, 0));
		upgradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		upgradeButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		topPanel.add(upgradeButton);

		JButton logoutButton = new JButton("Cerrar sesión");
		logoutButton.setFocusPainted(false);
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.setVisible(false);
				Login LoginFrame = new Login();
				LoginFrame.setVisible(true);
			}
		});
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setBackground(new Color(178, 34, 34));
		logoutButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		topPanel.add(logoutButton);

		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setBackground(SystemColor.inactiveCaption);
		MainFrame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));

		// Panel de canciones recientes
		songsListPanel = new JPanel();
		songsListPanel.setBorder(
				new TitledBorder(null, "Canciones Recientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		centerPanel.add(songsListPanel);
		GridBagLayout gbl_recentsSongsPanel_1 = new GridBagLayout();
		gbl_recentsSongsPanel_1.rowHeights = new int[] { 10, 300, 50 };
		gbl_recentsSongsPanel_1.columnWidths = new int[] { 10, 300, 10 };
		gbl_recentsSongsPanel_1.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gbl_recentsSongsPanel_1.rowWeights = new double[] { 0.0, 1.0, 0.0 };
		songsListPanel.setLayout(gbl_recentsSongsPanel_1);
		
		JLabel horaLabel = new JLabel("Hora");
		GridBagConstraints gbc_horaLabel = new GridBagConstraints();
		gbc_horaLabel.fill = GridBagConstraints.VERTICAL;
		gbc_horaLabel.anchor = GridBagConstraints.EAST;
		gbc_horaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_horaLabel.gridx = 1;
		gbc_horaLabel.gridy = 0;
		songsListPanel.add(horaLabel, gbc_horaLabel);
		horaLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		showTime(horaLabel);

		JScrollPane tableScrollPane = new JScrollPane();
		tableScrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_tableScrollPane = new GridBagConstraints();
		gbc_tableScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_tableScrollPane.fill = GridBagConstraints.BOTH;
		gbc_tableScrollPane.gridx = 1;
		gbc_tableScrollPane.gridy = 1;
		songsListPanel.add(tableScrollPane, gbc_tableScrollPane);

		recentSongsTable = new JTable();
		recentSongsTable.setBorder(new EmptyBorder(0, 0, 0, 0));
		recentSongsTable.setEnabled(false);
		recentSongsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		recentSongsTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"Cara al Sol.mp3", "Francisco Franco & Adolf Hitler"},
				{"Doble Excavadora.mp3", "Elminion & Xixauxas"},
				{"Play Hard.mp3", "David Guetta & Akon & Ne-Yo"},
				{"Soy Peor.mp3", "Bad Bunny"},
				{"We dont talk anymore.mp3", "Charlie Puth & Selena G\u00F3mez"},
				{"Vodovorot.mp3", "Vladimir Putin & Donald Trump"},
				{"MORE.mp3", "K/DA"},
				{"Somebody that I used to know.mp3", "Goyte & Kimbra"},
				{"Blinding Lights.mp3", "The Weekend"},
				{"Watermelon sugar.mp3", "Harry Styles"},
			},
			new String[] {
				"Canci\u00F3n", "Artista/s"
			}
		));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < recentSongsTable.getColumnCount() ; ++i) {
			recentSongsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		tableScrollPane.setViewportView(recentSongsTable);

		playerPanel = new JPanel();
		GridBagConstraints gbc_playerPanel = new GridBagConstraints();
		gbc_playerPanel.anchor = GridBagConstraints.WEST;
		gbc_playerPanel.insets = new Insets(0, 0, 0, 5);
		gbc_playerPanel.gridx = 1;
		gbc_playerPanel.gridy = 2;
		songsListPanel.add(playerPanel, gbc_playerPanel);

		JButton randomButton = new JButton("");
		randomButton.setFocusPainted(false);
		randomButton.setHorizontalTextPosition(SwingConstants.LEADING);
		randomButton.setMargin(new Insets(2, 0, 2, 0));
		randomButton.setIconTextGap(0);
		randomButton.setHorizontalAlignment(SwingConstants.LEFT);
		randomButton.setOpaque(false);
		randomButton.setContentAreaFilled(false);
		randomButton.setBorderPainted(false);

		ImageIcon imageIcon5 = new ImageIcon(GuiUtils.loadImage("icons/iconoRandom.png"));
		randomButton.setIcon(imageIcon5);
		ImageIcon imageIcon10 = new ImageIcon(GuiUtils.loadImage("icons/iconoDefault.png"));
		randomButton.addActionListener(new ActionListener() {
			private boolean flag = true;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				randomButton.setIcon(flag ? imageIcon10 : imageIcon5);
				flag = !flag;
			}
		});

		JButton invisibleButton = new JButton("");
		invisibleButton.setMargin(new Insets(2, 14, 2, 86));
		invisibleButton.setOpaque(false);
		invisibleButton.setContentAreaFilled(false);
		invisibleButton.setBorderPainted(false);
		playerPanel.add(invisibleButton);
		playerPanel.add(randomButton);

		JButton backButton = new JButton("");
		backButton.setFocusPainted(false);
		backButton.setMargin(new Insets(2, 0, 2, 0));
		backButton.setHorizontalAlignment(SwingConstants.LEFT);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);

		ImageIcon imageIcon6 = new ImageIcon(GuiUtils.loadImage("icons/iconoBack.png"));
		backButton.setIcon(imageIcon6);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		playerPanel.add(backButton);

		JButton playButton = new JButton("");
		playButton.setFocusPainted(false);
		playButton.setMargin(new Insets(2, 0, 2, 0));
		playButton.setHorizontalAlignment(SwingConstants.LEFT);
		playButton.setOpaque(false);
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);

		ImageIcon imageIcon7 = new ImageIcon(GuiUtils.loadImage("icons/iconoPlay.png", 45, 45));
		playButton.setIcon(imageIcon7);
		ImageIcon imageIcon9 = new ImageIcon(GuiUtils.loadImage("icons/iconoPause.png", 45, 45));
		playButton.addActionListener(new ActionListener() {
			private boolean flag = true;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playButton.setIcon(flag ? imageIcon9 : imageIcon7);
				flag = !flag;
			}
		});

		playerPanel.add(playButton);

		JButton forwardButton = new JButton("");
		forwardButton.setFocusPainted(false);
		forwardButton.setHorizontalAlignment(SwingConstants.LEFT);
		forwardButton.setMargin(new Insets(2, 0, 2, 0));
		forwardButton.setOpaque(false);
		forwardButton.setContentAreaFilled(false);
		forwardButton.setBorderPainted(false);
		JScrollPane tableFavouritesScrollPane = new JScrollPane();
		
		ImageIcon imageIcon8 = new ImageIcon(GuiUtils.loadImage("icons/iconoForward.png"));
		forwardButton.setIcon(imageIcon8);
		forwardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		playerPanel.add(forwardButton);
		
		JScrollPane scrollPane = new JScrollPane();
		JPanel navigationPanel = new JPanel();
		navigationPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		navigationPanel.setBackground(SystemColor.activeCaption);
		MainFrame.getContentPane().add(navigationPanel, BorderLayout.WEST);
		GridBagLayout gbl_navigationPanel = new GridBagLayout();
		gbl_navigationPanel.columnWidths = new int[] { 110, 0 };
		gbl_navigationPanel.rowHeights = new int[] { 25, 25, 25, 25, 0, 0, 0 };
		gbl_navigationPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_navigationPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		navigationPanel.setLayout(gbl_navigationPanel);

		JButton searchButton = new JButton("Buscar");
		searchButton.setFocusPainted(false);
		searchButton.setIconTextGap(5);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				songsListPanel.setBorder(
						new TitledBorder(null, "Buscar canciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				tableScrollPane.setVisible(false);
				playerPanel.setVisible(false);
				scrollPane.setVisible(false);
				tableFavouritesScrollPane.setVisible(false);
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
		newListButton.setFocusPainted(false);
		newListButton.setMargin(new Insets(2, 14, 5, 2));
		newListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				songsListPanel.setBorder(
						new TitledBorder(null, "Crear playlist", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				scrollPane.setVisible(true);
				tableScrollPane.setVisible(false);
				playerPanel.setVisible(false);
				tableFavouritesScrollPane.setVisible(false);
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
		ImageIcon imageIcon2 = new ImageIcon(GuiUtils.loadImage("icons/iconoNewList.png")); // load the image to a
																							// imageIcon
		newListButton.setIcon(imageIcon2);
		navigationPanel.add(newListButton, gbc_newListButton);
		

		
		JButton recentsButton = new JButton("Recientes");
		recentsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tableScrollPane.setVisible(true);
				songsListPanel.setBorder(
					new TitledBorder(null, "Canciones recientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				scrollPane.setVisible(false);
				playerPanel.setVisible(true);
			}
		});
		recentsButton.setFocusPainted(false);
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
		ImageIcon imageIcon3 = new ImageIcon(GuiUtils.loadImage("icons/iconoRecents.png")); // load the image to a
																							// imageIcon
		recentsButton.setIcon(imageIcon3);
		navigationPanel.add(recentsButton, gbc_recentsButton);

		
		JButton myListsButton = new JButton("Mis listas");
		myListsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				songsListPanel.setBorder(
						new TitledBorder(null, "Mis listas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				scrollPane.setVisible(true);
				tableScrollPane.setVisible(true);
				playerPanel.setVisible(true);
			}
		});
		myListsButton.setFocusPainted(false);
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
		ImageIcon imageIcon4 = new ImageIcon(GuiUtils.loadImage("icons/iconoMyLists.png")); // load the image to a
																							// imageIcon
		myListsButton.setIcon(imageIcon4);
		navigationPanel.add(myListsButton, gbc_myListsButton);
		
		tableFavouritesScrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tableFavouritesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_tableFavouritesScrollPane = new GridBagConstraints();
		gbc_tableFavouritesScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_tableFavouritesScrollPane.fill = GridBagConstraints.BOTH;
		gbc_tableFavouritesScrollPane.gridx = 1;
		gbc_tableFavouritesScrollPane.gridy = 1;
		songsListPanel.add(tableFavouritesScrollPane, gbc_tableFavouritesScrollPane);

		JTable favouritesSongsTable = new JTable();
		favouritesSongsTable.setVisible(false);
		favouritesSongsTable.setBorder(new EmptyBorder(0, 0, 0, 0));
		favouritesSongsTable.setEnabled(false);
		favouritesSongsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		favouritesSongsTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"Soy Peor.mp3", "Bad Bunny", "300"},
				{"Cara al Sol.mp3", "Francisco Franco & Adolf Hitler", "200"},
				{"Vodovorot.mp3", "Vladimir Putin & Donald Trump", "150"},
				{"Doble Excavadora.mp3", "Elminion & Xixauxas", "100"},
				{"We dont talk anymore.mp3", "Charlie Puth & Selena G\u00F3mez", "50"},
				{"Play Hard.mp3", "David Guetta & Akon & Ne-Yo", "40"},
				{"MORE.mp3", "K/DA", "30"},
				{"Somebody that I used to know.mp3", "Goyte & Kimbra", "20"},
				{"Blinding Lights.mp3", "The Weekend", "10"},
				{"Watermelon sugar.mp3", "Harry Styles", "5"},
			},
			new String[] {
				"Canci\u00F3n", "Artista/s", "N\u00BA veces"
			}));

		favouritesSongsTable.getColumnModel().getColumn(0).setPreferredWidth(180);
		favouritesSongsTable.getColumnModel().getColumn(1).setPreferredWidth(180);
		favouritesSongsTable.getColumnModel().getColumn(2).setPreferredWidth(54);
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < favouritesSongsTable.getColumnCount() ; ++i) {
			favouritesSongsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		tableFavouritesScrollPane.setViewportView(favouritesSongsTable);
		
		JButton favouritesButton = new JButton("Favoritas");
		favouritesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tableScrollPane.setVisible(false);
				songsListPanel.setBorder(
					new TitledBorder(null, "Canciones más escuchadas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				tableFavouritesScrollPane.setVisible(true);
				favouritesSongsTable.setVisible(true);
				scrollPane.setVisible(false);
				playerPanel.setVisible(true);
			}
		});
		favouritesButton.setFocusPainted(false);
		favouritesButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_favouritesButton = new GridBagConstraints();
		gbc_favouritesButton.anchor = GridBagConstraints.WEST;
		gbc_favouritesButton.fill = GridBagConstraints.VERTICAL;
		gbc_favouritesButton.insets = new Insets(0, 0, 5, 0);
		gbc_favouritesButton.gridx = 0;
		gbc_favouritesButton.gridy = 4;
		favouritesButton.setOpaque(false);
		favouritesButton.setContentAreaFilled(false);
		favouritesButton.setBorderPainted(false);
		ImageIcon imageIcon11 = new ImageIcon(GuiUtils.loadImage("icons/iconoMasEscuchadas.png")); // load the image to a																					// imageIcon
		favouritesButton.setIcon(imageIcon11);
		navigationPanel.add(favouritesButton, gbc_favouritesButton);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(100, 100));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(SystemColor.inactiveCaption);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 5;
		navigationPanel.add(panel, gbc_panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		

		scrollPane.setVisible(false);
		panel.add(scrollPane);
		
		JList<String> list = new JList<>();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Ahora soy peor", "New playlist", "Requiem", "Fifa Playlist", "Pepote", "Non-heterosexual playlist", "Pain", "Anele", "Musicote", "Boruto's Dad", "MMM", "DC", "Muzi"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		/*list.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		}); */
		scrollPane.setViewportView(list);
	}


}
