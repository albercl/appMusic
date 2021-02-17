package um.tds.appMusic.gui.mainPanels;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import um.tds.appMusic.gui.auxiliarPanels.SearchTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class MainPanel extends JPanel {
	private SearchPanel searchPanel;
	private PlaylistModificationPanel playlistPanel;
	private SearchTable recentsPanel;
	private FavouritesPanel favouritesPanel;
	
	public MainPanel() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {300};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		favouritesPanel = new FavouritesPanel();
		GridBagConstraints gbc_favouritesPanel = new GridBagConstraints();
		gbc_favouritesPanel.fill = GridBagConstraints.BOTH;
		gbc_favouritesPanel.gridx = 0;
		gbc_favouritesPanel.gridy = 0;
		add(favouritesPanel, gbc_favouritesPanel);
		searchPanel = new SearchPanel();
		GridBagConstraints gbc_searchPanel = new GridBagConstraints();
		gbc_searchPanel.fill = GridBagConstraints.BOTH;
		gbc_searchPanel.gridx = 1;
		gbc_searchPanel.gridy = 0;
		add(searchPanel, gbc_searchPanel);
		
		recentsPanel = new SearchTable();
		GridBagConstraints gbc_recentsPanel = new GridBagConstraints();
		gbc_recentsPanel.fill = GridBagConstraints.BOTH;
		gbc_recentsPanel.gridx = 2;
		gbc_recentsPanel.gridy = 0;
		add(recentsPanel, gbc_recentsPanel);
		
		playlistPanel = new PlaylistModificationPanel();
		GridBagConstraints gbc_playlistPanel = new GridBagConstraints();
		gbc_playlistPanel.fill = GridBagConstraints.BOTH;
		gbc_playlistPanel.gridx = 3;
		gbc_playlistPanel.gridy = 0;
		add(playlistPanel, gbc_playlistPanel);
		
		resetView();
	}
	
	
	public void resetView() {
		searchPanel.setVisible(false);
		playlistPanel.setVisible(false);
		recentsPanel.setVisible(false);
		favouritesPanel.setVisible(false);
		setBorder(null);
	}
	
	public void setSearchPanelView() {
		resetView();
		this.setBorder(new TitledBorder("Búsqueda de canciones"));
		searchPanel.setVisible(true);
	}
	
	public void setPlaylistPanelView() {
		resetView();
		this.setBorder(new TitledBorder("Modificación de playlist"));
		playlistPanel.setVisible(true);
	}
	
	public void setRecentsView() {
		resetView();
		this.setBorder(new TitledBorder("Canciones recientes"));
		recentsPanel.setVisible(true);
	}
	
	public void setFavouritesView() {
		resetView();
		this.setBorder(new TitledBorder("Canciones favoritas"));
		favouritesPanel.setVisible(true);
	}
}
