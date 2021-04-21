package tds.appMusic.gui.mainPanels;

import javax.swing.*;

import tds.appMusic.gui.auxiliarPanels.SearchControls;
import tds.appMusic.gui.auxiliarPanels.SongTable;
import tds.appMusic.modelo.Cancion;

import java.awt.*;
import java.util.List;

public class SearchPanel extends JPanel {

	private SearchControls searchControls;
	private SongTable searchTable;
	
	/**
	 * Create the panel.
	 */
	public SearchPanel() {
		setLayout(new BorderLayout(0, 10));
		setPreferredSize(new Dimension(500, 400));
		
		searchTable = new SongTable();
		add(searchTable, BorderLayout.CENTER);
		
		searchControls = new SearchControls(searchTable);
		add(searchControls, BorderLayout.NORTH);
	}

	public List<Cancion> getSongsList() {
		return searchTable.getSongs();
	}

	public int getSelection() {
		return searchTable.getSelection();
	}

	public JTable getTable() {
		return searchTable.getTable();
	}
}
