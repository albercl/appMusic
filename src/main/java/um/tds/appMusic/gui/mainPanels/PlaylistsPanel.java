package um.tds.appMusic.gui.mainPanels;

import javax.naming.directory.SearchControls;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import um.tds.appMusic.gui.auxiliarPanels.SearchTable;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class PlaylistsPanel extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public PlaylistsPanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JList playlistsList = new JList();
		playlistsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(playlistsList);

	}

}
