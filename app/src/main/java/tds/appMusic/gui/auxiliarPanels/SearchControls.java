package tds.appMusic.gui.auxiliarPanels;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListDataListener;

import tds.appMusic.modelo.AppMusic;
import tds.appMusic.modelo.util.Filter;
import tds.appMusic.modelo.Cancion;

import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.util.stream.Collectors;

public class SearchControls extends JPanel {
	private AppMusic controlador = AppMusic.getInstanciaUnica();
	
	private JTextField titleField;
	private JTextField artistField;
	private SearchTable tablePanel;

	private JButton cancelButton;
	private JButton searchButton;

	public JButton getCancelButton() {
		return cancelButton;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	/**
	 * Create the panel.
	 */
	public SearchControls(SearchTable tablePanel) {
		this.tablePanel = tablePanel;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[] {0, 0};
		gridBagLayout.columnWeights = new double[]{0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0};
		setLayout(gridBagLayout);
		
		JPanel searchParamsPanel = new JPanel();
		GridBagConstraints gbc_searchParamsPanel = new GridBagConstraints();
		gbc_searchParamsPanel.fill = GridBagConstraints.VERTICAL;
		gbc_searchParamsPanel.insets = new Insets(0, 0, 10, 0);
		gbc_searchParamsPanel.gridx = 0;
		gbc_searchParamsPanel.gridy = 0;
		add(searchParamsPanel, gbc_searchParamsPanel);
		GridBagLayout gbl_searchParamsPanel = new GridBagLayout();
		gbl_searchParamsPanel.columnWidths = new int[] {100, 0, 100, 0, 100};
		gbl_searchParamsPanel.rowHeights = new int[] {0};
		gbl_searchParamsPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_searchParamsPanel.rowWeights = new double[]{0.0};
		searchParamsPanel.setLayout(gbl_searchParamsPanel);
		
		titleField = new JTextField();
		titleField.setText("Título");
		titleField.setColumns(10);
		titleField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		titleField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				super.mouseClicked(mouseEvent);
				titleField.setText("");
			}
		});
		GridBagConstraints gbc_titleField = new GridBagConstraints();
		gbc_titleField.anchor = GridBagConstraints.WEST;
		gbc_titleField.insets = new Insets(0, 0, 0, 5);
		gbc_titleField.gridx = 0;
		gbc_titleField.gridy = 0;
		searchParamsPanel.add(titleField, gbc_titleField);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.anchor = GridBagConstraints.WEST;
		gbc_horizontalStrut.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalStrut.gridx = 1;
		gbc_horizontalStrut.gridy = 0;
		searchParamsPanel.add(horizontalStrut, gbc_horizontalStrut);
		
		artistField = new JTextField();
		artistField.setText("Intérprete");
		artistField.setColumns(10);
		artistField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		artistField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				super.mouseClicked(mouseEvent);
				artistField.setText("");
			}
		});
		GridBagConstraints gbc_artistField = new GridBagConstraints();
		gbc_artistField.anchor = GridBagConstraints.WEST;
		gbc_artistField.insets = new Insets(0, 0, 0, 5);
		gbc_artistField.gridx = 2;
		gbc_artistField.gridy = 0;
		searchParamsPanel.add(artistField, gbc_artistField);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.anchor = GridBagConstraints.WEST;
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalStrut_1.gridx = 3;
		gbc_horizontalStrut_1.gridy = 0;
		searchParamsPanel.add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		JComboBox genreComboBox = new JComboBox();
		genreComboBox.setModel(new ComboBox());
		GridBagConstraints gbc_genreComboBox = new GridBagConstraints();
		gbc_genreComboBox.anchor = GridBagConstraints.NORTHWEST;
		gbc_genreComboBox.gridx = 4;
		gbc_genreComboBox.gridy = 0;
		searchParamsPanel.add(genreComboBox, gbc_genreComboBox);
		
		JPanel searchButtonsPanel = new JPanel();
		GridBagConstraints gbc_searchButtonsPanel = new GridBagConstraints();
		gbc_searchButtonsPanel.fill = GridBagConstraints.VERTICAL;
		gbc_searchButtonsPanel.gridx = 0;
		gbc_searchButtonsPanel.gridy = 1;
		add(searchButtonsPanel, gbc_searchButtonsPanel);
		GridBagLayout gbl_searchButtonsPanel = new GridBagLayout();
		gbl_searchButtonsPanel.columnWidths = new int[] {100, 0, 100};
		gbl_searchButtonsPanel.rowHeights = new int[] {20};
		gbl_searchButtonsPanel.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_searchButtonsPanel.rowWeights = new double[]{0.0};
		searchButtonsPanel.setLayout(gbl_searchButtonsPanel);

		searchButton = new JButton("Buscar");
		searchButton.setBackground(UIManager.getColor("Button.shadow"));
		searchButton.setPreferredSize(new Dimension(100, 25));
		searchButton.setFocusPainted(false);
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.fill = GridBagConstraints.BOTH;
		gbc_searchButton.insets = new Insets(0, 0, 0, 5);
		gbc_searchButton.gridx = 0;
		gbc_searchButton.gridy = 0;
		searchButtonsPanel.add(searchButton, gbc_searchButton);
		
		Component separator = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 0, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 0;
		searchButtonsPanel.add(separator, gbc_separator);

		cancelButton = new JButton("Cancelar");
		cancelButton.setPreferredSize(new Dimension(100, 25));
		cancelButton.setFocusPainted(false);
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.fill = GridBagConstraints.BOTH;
		gbc_cancelButton.gridx = 2;
		gbc_cancelButton.gridy = 0;
		searchButtonsPanel.add(cancelButton, gbc_cancelButton);

		searchButton.addActionListener(e -> {
			String text;
			JTable table = tablePanel.getTable();
			
			String song = "";
			text = titleField.getText();
			if(!text.equals("Título"))
				song = text;
			
			String artist = "";
			text = artistField.getText();
			if(!text.equals("Intérprete"))
				artist = text;
			
			String genre = "";
			text = (String) genreComboBox.getSelectedItem();
			if(!text.equals("TODOS"))
				genre = text;
				
			List<Cancion> canciones = controlador.searchSongs(new Filter(song, artist, genre));
			tablePanel.setSongs(canciones);
		});

		cancelButton.addActionListener(e -> {
			titleField.setText("Título");
			artistField.setText("Intérprete");
			tablePanel.setSongs(null);
		});
	}
}

class ComboBox implements MutableComboBoxModel<String> {

	private final AppMusic controlador = AppMusic.getInstanciaUnica();

	private List<String> estilos;
	private String selected = null;

	public ComboBox() {
		estilos = new ArrayList<>(controlador.getEstilos());
		estilos.add(0, "TODOS");
	}

	@Override
	public void addElement(String s) {
		estilos.add(s);
	}

	@Override
	public void removeElement(Object o) {
		estilos.remove(o);
	}

	@Override
	public void insertElementAt(String s, int i) {
		estilos.add(i, s);
	}

	@Override
	public void removeElementAt(int i) {
		estilos.remove(i);
	}

	@Override
	public void setSelectedItem(Object o) {
		selected = (String) o;
	}

	@Override
	public Object getSelectedItem() {
		return selected;
	}

	@Override
	public int getSize() {
		return estilos.size();
	}

	@Override
	public String getElementAt(int i) {
		return estilos.get(i);
	}

	@Override
	public void addListDataListener(ListDataListener listDataListener) {

	}

	@Override
	public void removeListDataListener(ListDataListener listDataListener) {

	}
}
