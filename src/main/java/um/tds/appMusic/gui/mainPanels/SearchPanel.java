package um.tds.appMusic.gui.mainPanels;

import javax.swing.JPanel;

import um.tds.appMusic.gui.auxiliarPanels.SearchControls;
import um.tds.appMusic.gui.auxiliarPanels.SearchTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;

public class SearchPanel extends JPanel {

	private SearchControls searchControls;
	private SearchTable searchTable;
	
	/**
	 * Create the panel.
	 */
	public SearchPanel() {
		setLayout(new BorderLayout(0, 10));
		
		searchTable = new SearchTable();
		add(searchTable, BorderLayout.CENTER);
		
		searchControls = new SearchControls(searchTable);
		add(searchControls, BorderLayout.NORTH);
	}

}
