package tds.appMusic.gui.mainPanels;

import tds.appMusic.gui.auxiliarPanels.SongTable;
import tds.appMusic.gui.auxiliarPanels.SongTableModel;
import tds.appMusic.modelo.AppMusic;
import tds.appMusic.modelo.Cancion;
import tds.appMusic.modelo.Reproduccion;
import tds.appMusic.modelo.util.ReproductorListener;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;

public class FavouritesPanel extends JPanel {
	private final AppMusic controlador = AppMusic.getInstanciaUnica();
	private static final String[] TABLE_IDENTIFIERS = {"Título", "Intérprete", "Nº"};

	private SongTable table;
	private Map<Cancion, Integer> songs = new HashMap<>();

	/**
	 * Create the panel.
	 */
	public FavouritesPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(500, 0));
		add(scrollPane);
		
		table = new SongTable(SongTableModel.REPRODUCTION_MODE);

		scrollPane.setViewportView(table);
	}

	public void setSongs(List<Reproduccion> reps) {
		List<Cancion> canciones = new ArrayList<>();
		canciones = reps.stream()
			.map(rep -> rep.getCancion())
			.distinct()
			.sorted((c1, c2) -> Long.compare(controlador.getUserNumReproductions(c2), controlador.getUserNumReproductions(c1)))
			.collect(Collectors.toList());
		table.getModel().setCanciones(canciones);
	}

	public List<Cancion> getSongs() {
		return table.getModel().getCanciones();
	}

	public SongTable getTable() {
		return table;
	}

	public int getSelection() {
		return table.getSelection();
	}

	public void selectSong(Cancion c) {
		table.selectSong(c);
	}
}
