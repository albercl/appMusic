package um.tds.appMusic.gui.mainPanels;

import um.tds.appMusic.modelo.AppMusic;
import um.tds.appMusic.modelo.Cancion;
import um.tds.appMusic.modelo.util.ReproductorListener;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;

public class FavouritesPanel extends JPanel {
	private final AppMusic controlador = AppMusic.getInstanciaUnica();
	private static final String[] TABLE_IDENTIFIERS = {"Título", "Intérprete", "Nº"};

	private JTable table;
	private Map<Cancion, Integer> songs = new HashMap<>();

	/**
	 * Create the panel.
	 */
	public FavouritesPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(500, 0));
		add(scrollPane);
		
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(500, 400));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(new Object[][] {}, TABLE_IDENTIFIERS));
		configureModel();
		scrollPane.setViewportView(table);

		//Update songs while in favourites table
		controlador.addListenerToPlayer(new ReproductorListener() {
			@Override
			public void onStartedSong(Cancion c) {
				if(isVisible()) {
					Integer n = songs.get(c);
					songs.put(c, ++n);

					int selection = getSongs().indexOf(c);
					table.getModel().setValueAt(n, selection, 2);
				}
			}
		});
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

			((DefaultTableModel) table.getModel()).setDataVector(newTable, TABLE_IDENTIFIERS);
			configureModel();
			this.songs = songs;
		} else {
			((DefaultTableModel) table.getModel()).setDataVector(new Object[][] {}, TABLE_IDENTIFIERS);
			configureModel();
		}
	}

	private void configureModel() {
		table.getColumnModel().getColumn(0).setPreferredWidth(225);
		table.getColumnModel().getColumn(1).setPreferredWidth(225);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
	}

	public List<Cancion> getSongs() {
		return songs.keySet().stream()
				.sorted(Comparator.comparingInt(songs::get).reversed())
				.collect(Collectors.toList());
	}

	public JTable getTable() {
		return table;
	}

	public int getSelection() {
		return table.getSelectedRow();
	}
}
