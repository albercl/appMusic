package um.tds.appMusic.gui.mainPanels;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class FavouritesPanel extends JPanel {
	private JTable table;

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

}
