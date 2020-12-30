package um.tds.appMusic.gui;

import um.tds.appMusic.modelo.AppMusic;
import um.tds.appMusic.modelo.Cancion;
import um.tds.appMusic.modelo.util.Filter;
import um.tds.appMusic.modelo.util.ReproductorListener;

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
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Rectangle;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.TitledBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.stream.Collectors;
import javax.swing.border.SoftBevelBorder;

public class Main {
	private AppMusic controlador;

	private DefaultTableCellRenderer centerRenderer;

	private JFrame MainFrame;

	//Paneles a cambiar en la ventana para cada funcion
	private JPanel playerPanel;
	private JPanel songsListPanel;
	
	private JTextField txtNewList;
	private JTextField txtInterprete;
	private JTextField txtTitulo;
	
	private JScrollPane tableScrollPane;
	private JTable recentSongsTable;
	private JTable favouritesSongsTable;

	private JList<String> list;
	private JTable modSearchTable;
	private JTable modPlaylistTable;
	private JPanel searchPanel;
	private JPanel searchPanel2;
	private JPanel modifyPlaylistPanel;
	private JPanel newListPanel;
	private JScrollPane scrollPane;
	private JScrollPane tableFavouritesScrollPane;

	private JScrollPane searchScrollPanel;

	private List<Cancion> shownSongs;
	private boolean isPlaying = false;
	private boolean isRandom = false;
	private boolean isRepeating = false;

	private JButton replayButton;

	private ImageIcon imageIcon12;
	private ImageIcon imageIcon13;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Main window = new Main();
				window.MainFrame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
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
	


	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		controlador = AppMusic.getInstanciaUnica();

		centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		MainFrame = new JFrame();
		MainFrame.setTitle("AppMusic");
		MainFrame.setBounds(375, 75, 1050, 950);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon iconoAppMusic = new ImageIcon(GuiUtils.loadAppIcon("icons/iconoAppMusic.png"));
		MainFrame.setIconImage(iconoAppMusic.getImage());

		JPanel topPanel = new JPanel();
		topPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		topPanel.setBackground(SystemColor.activeCaption);
		MainFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		Date date1 = new Date();
        String strDateFormat = "E dd/MM/yyyy"; // El formato de fecha está especificado  
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
		JLabel fechaLabel = new JLabel();
		fechaLabel.setText(objSDF.format(date1));
		fechaLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		topPanel.add(fechaLabel);
		
		JLabel horaLabel = new JLabel("Hora");
		topPanel.add(horaLabel);
		horaLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		GuiUtils.showTime(horaLabel);

		JLabel welcomeMessageLabel = new JLabel(
				"<html><p><b><span style=\"color: rgb(178, 34, 34)\">Bienvenido, </span>" + controlador.getUsername() + "</b> </p></html>");
		welcomeMessageLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		topPanel.add(welcomeMessageLabel);

		JButton upgradeButton = new JButton(
				"<html><p><b><span style=\"color: rgb(239, 184, 16)\">Mejora tu cuenta</b></span></p></html>");
		upgradeButton.setFocusPainted(false);
		upgradeButton.setForeground(Color.YELLOW);
		upgradeButton.setBackground(new Color(0, 0, 0));
		upgradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {}
		});
		upgradeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		topPanel.add(upgradeButton);

		JButton logoutButton = new JButton("Cerrar sesión");
		logoutButton.setFocusPainted(false);
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.setVisible(false);
				Login LoginFrame = new Login();
				LoginFrame.setVisible(true);

				controlador.logout();
			}
		});
		
		DefaultListModel<String> modelList = new DefaultListModel<>();
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setBackground(new Color(178, 34, 34));
		logoutButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		topPanel.add(logoutButton);

		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		centerPanel.setBackground(SystemColor.activeCaptionBorder);
		MainFrame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		// Panel de canciones recientes
		songsListPanel = new JPanel();
		songsListPanel.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Canciones recientes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		centerPanel.add(songsListPanel);
		GridBagLayout gbl_recentsSongsPanel_1 = new GridBagLayout();
		gbl_recentsSongsPanel_1.rowHeights = new int[] { 10, 0, 0, 300, 50 };
		gbl_recentsSongsPanel_1.columnWidths = new int[] {30, 500, 30};
		gbl_recentsSongsPanel_1.columnWeights = new double[] { 1.0, 0.0, 1.0 };
		gbl_recentsSongsPanel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0 };
		songsListPanel.setLayout(gbl_recentsSongsPanel_1);
		
		//Panel de nombre de nueva lista
		newListPanel = new JPanel();
		newListPanel.setVisible(false);
		GridBagConstraints gbc_newListPanel = new GridBagConstraints();
		gbc_newListPanel.fill = GridBagConstraints.BOTH;
		gbc_newListPanel.insets = new Insets(0, 0, 5, 5);
		gbc_newListPanel.gridx = 1;
		gbc_newListPanel.gridy = 0;
		songsListPanel.add(newListPanel, gbc_newListPanel);
		
		txtNewList = new JTextField();
		txtNewList.setPreferredSize(new Dimension(7, 20));
		txtNewList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		newListPanel.add(txtNewList);
		txtNewList.setColumns(10);
		
		//Boton de nueva lista
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setForeground(new Color(0, 128, 128));
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean ok = true;

				String nombreLista = txtNewList.getText();

				String[] options = {"Si", "No"};
				int reply = JOptionPane.showOptionDialog(null,
						"¿Deseas crear una nueva lista?",
						"Nueva lista",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null, options, options[0]);

				if (reply == JOptionPane.YES_OPTION) {
					if(nombreLista.isEmpty())
						ok = false;

					for(int i = 0; i < list.getModel().getSize() && ok; i++) {
						if(list.getModel().getElementAt(i).equals(nombreLista)) {
							String[] opt1 = {"Aceptar"};
							JOptionPane.showOptionDialog(null,
								"Ya existe una lista con ese nombre.",
								"Lista existente",
								JOptionPane.OK_OPTION,
								JOptionPane.WARNING_MESSAGE,
								null, opt1, opt1[0]);

							ok = false;
						}

					}
					String[] opt1 = {"Aceptar"};
					if(ok) {
						JOptionPane.showOptionDialog(null, "Creada lista: " + nombreLista,
								"Éxito",
								JOptionPane.OK_OPTION,
								JOptionPane.INFORMATION_MESSAGE,
								null, opt1, opt1[0]);
						modelList.addElement(nombreLista);
						enableModListPanel();
					}
				}
			}
		});
		
		JButton invisibleButton2 = new JButton("");
		invisibleButton2.setOpaque(false);
		invisibleButton2.setMargin(new Insets(2, 15, 2, 15));
		invisibleButton2.setContentAreaFilled(false);
		invisibleButton2.setBorderPainted(false);
		newListPanel.add(invisibleButton2);
		newListPanel.add(btnNewButton);
		
		//Panel de busqueda de canciones
		searchPanel = new JPanel();
		searchPanel.setVisible(false);
		GridBagConstraints gbc_searchPanel = new GridBagConstraints();
		gbc_searchPanel.insets = new Insets(0, 0, 5, 5);
		gbc_searchPanel.fill = GridBagConstraints.BOTH;
		gbc_searchPanel.gridx = 1;
		gbc_searchPanel.gridy = 1;
		songsListPanel.add(searchPanel, gbc_searchPanel);
		
		txtTitulo = new JTextField();
		txtTitulo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtTitulo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtTitulo.setText("");
			}
		});
		txtTitulo.setText("Título");
		searchPanel.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JButton invisibleButton2_1 = new JButton("");
		invisibleButton2_1.setOpaque(false);
		invisibleButton2_1.setMargin(new Insets(2, 15, 2, 15));
		invisibleButton2_1.setContentAreaFilled(false);
		invisibleButton2_1.setBorderPainted(false);
		searchPanel.add(invisibleButton2_1);
		
		txtInterprete = new JTextField();
		txtInterprete.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtInterprete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtInterprete.setText("");
			}
		});
		txtInterprete.setText("Intérprete");
		searchPanel.add(txtInterprete);
		txtInterprete.setColumns(10);
		
		JButton invisibleButton2_2 = new JButton("");
		invisibleButton2_2.setOpaque(false);
		invisibleButton2_2.setMargin(new Insets(2, 15, 2, 15));
		invisibleButton2_2.setContentAreaFilled(false);
		invisibleButton2_2.setBorderPainted(false);
		searchPanel.add(invisibleButton2_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Cualquiera","POP", "ROCK", "ROMANTICA", "OPERA", "JAZZ", "FLAMENCO", "CLASICA", "CANTAUTOR", "BOLERO"}));
		searchPanel.add(comboBox);
		
		searchPanel2 = new JPanel();
		searchPanel2.setVisible(false);
		GridBagConstraints gbc_searchPanel2 = new GridBagConstraints();
		gbc_searchPanel2.insets = new Insets(0, 0, 5, 5);
		gbc_searchPanel2.fill = GridBagConstraints.BOTH;
		gbc_searchPanel2.gridx = 1;
		gbc_searchPanel2.gridy = 2;
		songsListPanel.add(searchPanel2, gbc_searchPanel2);
		
		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String titulo = txtTitulo.getText();
				String interprete = txtInterprete.getText();
				String estilo = String.valueOf(comboBox.getSelectedItem());

				titulo = titulo.equals("Título") ? "" : titulo;
				interprete = interprete.equals("Intérprete") ? "" : interprete;
				estilo = estilo.equals("Cualquiera") ? "" : estilo;

				Filter filtro = new Filter(titulo,interprete,estilo);

				if(!newListPanel.isVisible()) {
					recentSongsTable.setModel(createSongsModel(filtro));
					GuiUtils.centerTable(recentSongsTable, centerRenderer);
					enableSearchResultPanel();
				} else {
					modSearchTable.setModel(createSongsModel(filtro));
					GuiUtils.centerTable(modSearchTable, centerRenderer);
				}
			}
		});
		searchPanel2.add(btnNewButton_1);
		btnNewButton_1.setForeground(new Color(0, 128, 128));
		btnNewButton_1.setBackground(SystemColor.inactiveCaption);
		
		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (modifyPlaylistPanel.isVisible() == false) {
					tableScrollPane.setVisible(false);
					playerPanel.setVisible(false);
					txtInterprete.setText("Intérprete");
					txtTitulo.setText("Título");
					comboBox.setSelectedIndex(0);
				}
				else {
					txtInterprete.setText("Intérprete");
					txtTitulo.setText("Título");
					comboBox.setSelectedIndex(0);
				}
			}
		});
		btnNewButton_2.setFocusPainted(false);
		searchPanel2.add(btnNewButton_2);
		btnNewButton_2.setForeground(new Color(0, 128, 128));
		btnNewButton_2.setBackground(SystemColor.inactiveCaption);
		
		modifyPlaylistPanel = new JPanel();
		GridBagConstraints gbc_modifyPlaylistPanel = new GridBagConstraints();
		gbc_modifyPlaylistPanel.gridwidth = 3;
		gbc_modifyPlaylistPanel.fill = GridBagConstraints.BOTH;
		gbc_modifyPlaylistPanel.gridx = 0;
		gbc_modifyPlaylistPanel.gridy = 3;
		songsListPanel.add(modifyPlaylistPanel, gbc_modifyPlaylistPanel);
		GridBagLayout gbl_modifyPlaylistPanel = new GridBagLayout();
		gbl_modifyPlaylistPanel.columnWidths = new int[] {300, 20, 300};
		gbl_modifyPlaylistPanel.rowHeights = new int[] {0};
		gbl_modifyPlaylistPanel.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_modifyPlaylistPanel.rowWeights = new double[]{1.0};
		modifyPlaylistPanel.setLayout(gbl_modifyPlaylistPanel);
		modifyPlaylistPanel.setVisible(false);
		
		searchScrollPanel = new JScrollPane();
		searchScrollPanel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		searchScrollPanel.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Búsqueda", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		searchScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_searchScrollPanel = new GridBagConstraints();
		gbc_searchScrollPanel.insets = new Insets(0, 0, 0, 5);
		gbc_searchScrollPanel.fill = GridBagConstraints.BOTH;
		gbc_searchScrollPanel.gridx = 0;
		gbc_searchScrollPanel.gridy = 0;
		modifyPlaylistPanel.add(searchScrollPanel, gbc_searchScrollPanel);
		
		JScrollPane playlistPanel = new JScrollPane();
		playlistPanel.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Playlist", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		playlistPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_playlistPanel = new GridBagConstraints();
		gbc_playlistPanel.fill = GridBagConstraints.BOTH;
		gbc_playlistPanel.gridx = 2;
		gbc_playlistPanel.gridy = 0;
		modifyPlaylistPanel.add(playlistPanel, gbc_playlistPanel);
		
		modSearchTable = new JTable() {       
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
				}
		};
		modSearchTable.setBackground(Color.WHITE);
		modSearchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modSearchTable.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"T\u00EDtulo", "Int\u00E9rpretes"
			}
		));

		GuiUtils.centerTable(modSearchTable, centerRenderer);
		
	    JTableHeader headerSearchTable = modSearchTable.getTableHeader();
	    headerSearchTable.setForeground(new Color(0, 128, 128));
	    headerSearchTable.setBackground(SystemColor.inactiveCaption);
	    headerSearchTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		searchScrollPanel.setViewportView(modSearchTable);
		
		modPlaylistTable = new JTable() {       
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
				}
		};
		modPlaylistTable.setBackground(Color.WHITE);
		modPlaylistTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modPlaylistTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"Cara al Sol.mp3", "Francsico Franco & Adolf Hitler"},
				{"Russian Hardbass.mp3", "Vladimir Putin"},
				{"Toxicidad fuera.mp3", "Ibai Llanos"},
			},
			new String[] {
				"T\u00EDtulo", "Int\u00E9rpretes"
			}
		));

		GuiUtils.centerTable(modPlaylistTable, centerRenderer);

	    JTableHeader headerPlaylistTable = modPlaylistTable.getTableHeader();
	    headerPlaylistTable.setForeground(new Color(0, 128, 128));
	    headerPlaylistTable.setBackground(SystemColor.inactiveCaption);
	    headerPlaylistTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		playlistPanel.setViewportView(modPlaylistTable);
		
		JPanel modButtonPanel = new JPanel();
		GridBagConstraints gbc_modButtonPanel = new GridBagConstraints();
		gbc_modButtonPanel.fill = GridBagConstraints.VERTICAL;
		gbc_modButtonPanel.insets = new Insets(0, 0, 0, 5);
		gbc_modButtonPanel.gridx = 1;
		gbc_modButtonPanel.gridy = 0;
		modifyPlaylistPanel.add(modButtonPanel, gbc_modButtonPanel);
		GridBagLayout gbl_modButtonPanel = new GridBagLayout();
		gbl_modButtonPanel.columnWidths = new int[] {0};
		gbl_modButtonPanel.rowHeights = new int[] {0, 0, 30, 30};
		gbl_modButtonPanel.columnWeights = new double[]{1.0};
		gbl_modButtonPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		modButtonPanel.setLayout(gbl_modButtonPanel);
		
		JButton btnNewButton_1_1 = new JButton("Añadir");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        // check for selected row first
		        if (modSearchTable.getSelectedRow() != -1) {
		            // remove selected row from the model
		        	Vector<String> selectedRow = (Vector<String>) ((DefaultTableModel) modSearchTable.getModel()).getDataVector().elementAt(modSearchTable.getSelectedRow());
		        	DefaultTableModel modelTable = (DefaultTableModel)modPlaylistTable.getModel(); 
		        	modelTable.addRow(selectedRow);
		        }    
			
			}
		});
		btnNewButton_1_1.setFocusPainted(false);
		btnNewButton_1_1.setForeground(new Color(0, 128, 128));
		btnNewButton_1_1.setBackground(SystemColor.inactiveCaption);
		GridBagConstraints gbc_btnNewButton_1_1 = new GridBagConstraints();
		gbc_btnNewButton_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1_1.gridx = 0;
		gbc_btnNewButton_1_1.gridy = 0;
		modButtonPanel.add(btnNewButton_1_1, gbc_btnNewButton_1_1);
		
		JButton btnNewButton_2_1 = new JButton("Eliminar");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        // check for selected row first
		        if (modPlaylistTable.getSelectedRow() != -1) {
		            // remove selected row from the model
		        	DefaultTableModel modelTable = (DefaultTableModel)modPlaylistTable.getModel(); 
		        	modelTable.removeRow(modPlaylistTable.getSelectedRow());
		        }    
			}
		});
		btnNewButton_2_1.setFocusPainted(false);
		btnNewButton_2_1.setForeground(new Color(0, 128, 128));
		btnNewButton_2_1.setBackground(SystemColor.inactiveCaption);
		GridBagConstraints gbc_btnNewButton_2_1 = new GridBagConstraints();
		gbc_btnNewButton_2_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2_1.gridx = 0;
		gbc_btnNewButton_2_1.gridy = 1;
		modButtonPanel.add(btnNewButton_2_1, gbc_btnNewButton_2_1);

		tableScrollPane = new JScrollPane();
		tableScrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_tableScrollPane = new GridBagConstraints();
		gbc_tableScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_tableScrollPane.fill = GridBagConstraints.BOTH;
		gbc_tableScrollPane.gridx = 1;
		gbc_tableScrollPane.gridy = 3;
		songsListPanel.add(tableScrollPane, gbc_tableScrollPane);

		recentSongsTable = new JTable() {       
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
            return false;
			}
		};
		recentSongsTable.setEnabled(true);
		recentSongsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		shownSongs = controlador.getUserHistory();
		Collections.reverse(shownSongs);
		recentSongsTable.setModel(createSongsModel(shownSongs));

		GuiUtils.centerTable(recentSongsTable, centerRenderer);

	    JTableHeader headerRecentsSongsTable = recentSongsTable.getTableHeader();
	    headerRecentsSongsTable.setForeground(new Color(0, 128, 128));
	    headerRecentsSongsTable.setBackground(SystemColor.inactiveCaption);
	    headerRecentsSongsTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		tableScrollPane.setViewportView(recentSongsTable);

		playerPanel = new JPanel();
		GridBagConstraints gbc_playerPanel = new GridBagConstraints();
		gbc_playerPanel.fill = GridBagConstraints.BOTH;
		gbc_playerPanel.insets = new Insets(0, 0, 0, 5);
		gbc_playerPanel.gridx = 1;
		gbc_playerPanel.gridy = 4;
		songsListPanel.add(playerPanel, gbc_playerPanel);

		JButton randomButton = new JButton("");
		randomButton.setFocusPainted(false);
		randomButton.setHorizontalTextPosition(SwingConstants.LEADING);
		randomButton.setMargin(new Insets(2, 0, 2, 8));
		randomButton.setIconTextGap(0);
		randomButton.setHorizontalAlignment(SwingConstants.LEFT);
		randomButton.setOpaque(false);
		randomButton.setContentAreaFilled(false);
		randomButton.setBorderPainted(false);

		ImageIcon imageIcon5 = new ImageIcon(GuiUtils.loadImage("icons/iconoRandom.png"));
		randomButton.setIcon(imageIcon5);
		ImageIcon imageIcon10 = new ImageIcon(GuiUtils.loadImage("icons/iconoDefault.png"));
		randomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controlador.alternateRandom();
			}
		});
		
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

				Cancion previousSong = controlador.goBack();
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

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = recentSongsTable.getSelectedRow();

				if(controlador.getPlayingSong() == null) {
					if(row != -1) {
						playButton.setIcon(imageIcon9);
						controlador.play(shownSongs, row);
					}
				} else {
					if(isPlaying) {
						controlador.pause();
						playButton.setIcon(imageIcon7);
						isPlaying = false;
					} else {
						if(row == -1) {
							controlador.resume();
							playButton.setIcon(imageIcon9);
							isPlaying = true;
						} else {
							playButton.setIcon(imageIcon9);
							controlador.play(shownSongs, row);
						}
					}
				}
			}
		});

		controlador.addListenerToPlayer(new ReproductorListener() {
			@Override
			public void onEmptyQueue() {
				isPlaying = false;
				playButton.setIcon(imageIcon7);
			}

			@Override
			public void onStartedSong(Cancion c) {
				int index = shownSongs.indexOf(c);
				if(index != -1) {
					recentSongsTable.changeSelection(index, 0, false, false);
				}

				isPlaying = true;
				playButton.setIcon(imageIcon9);
			}

			@Override
			public void onAlternatedRandom() {
				if(isRandom) {
					isRandom = false;
					randomButton.setIcon(imageIcon5);
				} else {
					isRandom = true;
					randomButton.setIcon(imageIcon10);
				}
			}

			@Override
			public void onAlternatedRepeat() {
				if(isRepeating) {
					isRepeating = false;
					replayButton.setIcon(imageIcon12);
				} else {
					isRepeating = true;
					replayButton.setIcon(imageIcon13);
				}
			}
		});

		playerPanel.add(playButton);

		JButton forwardButton = new JButton("");
		forwardButton.setMargin(new Insets(2, 0, 2, 8));
		forwardButton.setFocusPainted(false);
		forwardButton.setHorizontalAlignment(SwingConstants.LEFT);
		forwardButton.setOpaque(false);
		forwardButton.setContentAreaFilled(false);
		forwardButton.setBorderPainted(false);
		tableFavouritesScrollPane = new JScrollPane();
		
		ImageIcon imageIcon8 = new ImageIcon(GuiUtils.loadImage("icons/iconoForward.png"));
		forwardButton.setIcon(imageIcon8);
		forwardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cancion nextSong = controlador.goNext();
			}
		});
		playerPanel.add(forwardButton);
		
		replayButton = new JButton("");
		replayButton.setOpaque(false);
		replayButton.setMargin(new Insets(2, 0, 2, 0));
		replayButton.setIconTextGap(0);
		replayButton.setHorizontalTextPosition(SwingConstants.LEADING);
		replayButton.setHorizontalAlignment(SwingConstants.LEFT);
		replayButton.setFocusPainted(false);
		replayButton.setContentAreaFilled(false);
		replayButton.setBorderPainted(false);
		
		imageIcon12 = new ImageIcon(GuiUtils.loadImage("icons/iconoReplay.png"));
		replayButton.setIcon(imageIcon12);
		imageIcon13 = new ImageIcon(GuiUtils.loadImage("icons/iconoReplay2.png"));
		replayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controlador.alternateRepeat();
			}
		});
		
		playerPanel.add(replayButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVisible(false);
		JPanel navigationPanel = new JPanel();
		navigationPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
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
				txtInterprete.setText("Intérprete");
				txtTitulo.setText("Título");

				setMainPanelBorderTitle("Buscar canciones");
				setViewSearch();
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
				txtInterprete.setText("Intérprete");
				txtTitulo.setText("Título");
				txtNewList.setText("");
				setMainPanelBorderTitle("Crear playlist");
				setViewNewList();
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
				setMainPanelBorderTitle("Canciones recientes");
				shownSongs = controlador.getUserHistory();
				Collections.reverse(shownSongs);
				recentSongsTable.setModel(createSongsModel(shownSongs));
				GuiUtils.centerTable(recentSongsTable, centerRenderer);
				setViewRecent();
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
				setMainPanelBorderTitle("Mis listas");
				setViewMyLists();
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
		gbc_tableFavouritesScrollPane.gridy = 3;
		songsListPanel.add(tableFavouritesScrollPane, gbc_tableFavouritesScrollPane);

		favouritesSongsTable = new JTable() {
	         public boolean editCellAt(int row, int column, java.util.EventObject e) {
	             return false;
	          }
	       };
		favouritesSongsTable.setEnabled(true);
		
		favouritesSongsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    JTableHeader headerFavouritesSongsTable = favouritesSongsTable.getTableHeader();
	    headerFavouritesSongsTable.setForeground(new Color(0, 128, 128));
	    headerFavouritesSongsTable.setBackground(SystemColor.inactiveCaption);
	    headerFavouritesSongsTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		tableFavouritesScrollPane.setViewportView(favouritesSongsTable);
		
		JButton favouritesButton = new JButton("Favoritas");
		favouritesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setMainPanelBorderTitle("Canciones más escuchadas");

				favouritesSongsTable.setModel(createFavouriteSongsModel(controlador.getUserReproductions()));
				favouritesSongsTable.getColumnModel().getColumn(0).setPreferredWidth(180);
				favouritesSongsTable.getColumnModel().getColumn(1).setPreferredWidth(180);
				favouritesSongsTable.getColumnModel().getColumn(2).setPreferredWidth(54);
				GuiUtils.centerTable(favouritesSongsTable, centerRenderer);
				setViewFavourites();
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
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setPreferredSize(new Dimension(135, 300));
		panel.setBackground(SystemColor.inactiveCaption);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 5;
		navigationPanel.add(panel, gbc_panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		panel.add(scrollPane);
		
		list = new JList<>(modelList);
		list.setBorder(null);
		list.setBackground(SystemColor.inactiveCaption);

		scrollPane.setViewportView(list);
	}

	
	private void setViewNewList() {
		resetView();
		scrollPane.setVisible(true);
		newListPanel.setVisible(true);
	}

	private void enableModListPanel() {
		searchPanel.setVisible(true);
		searchPanel2.setVisible(true);
		modifyPlaylistPanel.setVisible(true);
	}

	private void setViewSearch() {
		resetView();

		searchPanel.setVisible(true);
		searchPanel2.setVisible(true);
	}

	private void enableSearchResultPanel() {
		tableScrollPane.setVisible(true);
		playerPanel.setVisible(true);
	}

	private void setViewRecent() {
		resetView();

		tableScrollPane.setVisible(true);
		playerPanel.setVisible(true);
	}

	private void setViewMyLists() {
		resetView();

		scrollPane.setVisible(true);
	}

	private void enableListTable() {
		tableScrollPane.setVisible(true);
		playerPanel.setVisible(true);
	}

	private void setViewFavourites() {
		resetView();

		tableFavouritesScrollPane.setVisible(true);
	}

	private void resetView() {
		searchPanel.setVisible(false);
		searchPanel2.setVisible(false);
		playerPanel.setVisible(false);
		tableScrollPane.setVisible(false);
		newListPanel.setVisible(false);
		modifyPlaylistPanel.setVisible(false);
		scrollPane.setVisible(false);
		tableFavouritesScrollPane.setVisible(false);
	}

	private void setMainPanelBorderTitle(String title) {
		songsListPanel.setBorder(
				new TitledBorder(
						new EtchedBorder(
								EtchedBorder.LOWERED,
								new Color(255, 255, 255),
								new Color(160, 160, 160)),
						title,
						TitledBorder.LEADING,
						TitledBorder.TOP,
						null,
						null));

	}

	private DefaultTableModel createSongsModel(Filter filter) {
		return createSongsModel(controlador.searchSongs(filter));
	}

	private DefaultTableModel createSongsModel(List<Cancion> songs) {
		shownSongs = songs;
		Object[][] matrix = new Object[shownSongs.size()][2];
		for(int i = 0; i < shownSongs.size(); i++) {
			Cancion s = shownSongs.get(i);

			matrix[i][0] = s.getTitulo();
			matrix[i][1] = s.getInterpretesString();
		}

		return new DefaultTableModel(
				matrix,
				new String[] {"Canci\u00F3n", "Intérpretes"}
		);
	}

	private DefaultTableModel createFavouriteSongsModel(Map<Cancion, Integer> songs) {
		Object[][] matrix = new Object[songs.size()][3];
		Comparator<Cancion> comparator = (s1, s2) -> {
			int r1 = songs.get(s1);
			int r2 = songs.get(s2);

			if(r1 > r2)
				return 1;
			else if(r1 == r2)
				return 0;
			else
				return -1;
		};

		List<Cancion> songsList = songs.keySet().stream()
				.sorted(comparator.reversed())
				.collect(Collectors.toList());

		for(int i = 0; i < songsList.size(); i++) {
			Cancion s = songsList.get(i);

			matrix[i][0] = s.getTitulo();
			matrix[i][1] = s.getInterpretesString();
			matrix[i][2] = songs.get(s);
		}

		return new DefaultTableModel(
				matrix,
				new String[] {"Canci\u00F3n", "Intérpretes", "N\u00BA veces"}
		);
	}
}
