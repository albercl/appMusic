package um.tds.appMusic.gui.mainPanels;

import um.tds.appMusic.modelo.Cancion;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.util.*;
import java.util.stream.Collectors;

public class FavouritesPanel extends JPanel {
	private static final String[] TABLE_IDENTIFIERS = {"Título", "Intérprete", "Reproducciones"};

	private JTable table;
	private Map<Cancion, Integer> songs = new HashMap<>();

	/**
	 * Create the panel.
	 */
	public FavouritesPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new Object[] {"Título", "Intérprete", "Reproducciones"}));
		scrollPane.setViewportView(table);
	}

	public void setSongs(Map<Cancion, Integer> songs) {
		if(songs != null) {
			String[][] newTable = new String[songs.size()][3];
			List<Cancion> songsListOrdered = songs.keySet().stream()
					.sorted(Comparator.comparingInt(songs::get).reversed())
					.collect(Collectors.toList());

			Iterator<Cancion> it = songsListOrdered.iterator();
			for(int i = 0; it.hasNext(); i++) {
				Cancion song = it.next();
				newTable[i][0] = song.getTitulo();
				newTable[i][1] = song.getInterpretesString();
				newTable[i][2] = String.valueOf(songs.get(song));
			}

			table.setModel(new DefaultTableModel(newTable, TABLE_IDENTIFIERS));
			this.songs = songs;
		} else {
			table.setModel(new DefaultTableModel(new Object[][] {}, TABLE_IDENTIFIERS));
		}
	}

}
