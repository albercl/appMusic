package um.tds.appMusic.gui.auxiliarPanels;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Insets;
import java.util.Iterator;
import java.util.List;
import java.awt.Dimension;

import um.tds.appMusic.modelo.Cancion;

public class SearchTable extends JPanel {
	private static final Object[] TABLE_IDENTIFIERS = {"Título", "Intérprete"};
	
	private JTable table;

	/**
	 * Create the panel.
	 */
	public SearchTable() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);
		
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(350, 400));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		table.setModel(new DefaultTableModel(new Object[][] {}, new Object[] {"Título", "Intérprete"}));
		scrollPane.setViewportView(table);

	}
	
	public void setSongs(List<Cancion> songs) {
		String[][] newTable = new String[songs.size()][2];
		Iterator<Cancion> iterator = songs.iterator();
		
		for(int i = 0; iterator.hasNext(); i++) {
			Cancion song = iterator.next();
			newTable[i][0] = song.getTitulo();
			newTable[i][1] = song.getInterpretesString();
		}
		
		table.setModel(new DefaultTableModel(newTable, TABLE_IDENTIFIERS));
	}
	
	public JTable getTable() {
		return table;
	}

}
