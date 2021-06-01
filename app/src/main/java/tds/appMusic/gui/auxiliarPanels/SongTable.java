package tds.appMusic.gui.auxiliarPanels;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.awt.Dimension;

import tds.appMusic.modelo.Cancion;

public class SongTable extends JPanel {
	
	private final JTable table;
	private final SongTableModel model;

	/**
	 * Create the panel.
	 */
	public SongTable(int mode) {
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(300, 400));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);
		
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(450, 400));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		model = new SongTableModel(mode);
		table.setModel(model);

		if(mode == SongTableModel.REPRODUCTION_MODE) {
			table.getColumnModel().getColumn(0).setPreferredWidth(225);
			table.getColumnModel().getColumn(1).setPreferredWidth(225);
			table.getColumnModel().getColumn(2).setPreferredWidth(50);
		}
		scrollPane.setViewportView(table);
	}
	
	public void setSongs(Collection<Cancion> songs) {
		model.setCanciones(new LinkedList<>(songs));
	}
	
	public JTable getTable() {
		return table;
	}

	public List<Cancion> getSongs() {
		return model.getCanciones();
	}

	public int getSelection() {
		return table.getSelectedRow();
	}

	public Cancion getSelectedSong() {
		int selection = table.getSelectedRow();
		if(selection == -1)
			return null;

		return getSongs().get(selection);
	}

	public SongTableModel getModel() {
		return model;
	}

	public void removeSelectedSong() {
		Cancion song = getSelectedSong();
		if(song != null)
			model.removeSong(song);
	}

	public void addSong(Cancion song) {
		model.addSong(song);
	}

	public void removeSong(Cancion song) {
		model.removeSong(song);
	}

	public void clear() {
		model.clearTable();
	}

	public void selectSong(Cancion c) {
		int index = model.getCanciones().indexOf(c);
		table.getSelectionModel().setSelectionInterval(index, index);
	}
}