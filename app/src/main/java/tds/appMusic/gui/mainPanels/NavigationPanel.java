package tds.appMusic.gui.mainPanels;

import javax.swing.JPanel;

import tds.appMusic.modelo.AppMusic;
import tds.appMusic.oldgui.GuiUtils;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class NavigationPanel extends JPanel {
	private final Font BUTTON_FONT = new Font("Tahoma", Font.PLAIN, 13);

	private final AppMusic controlador = AppMusic.getInstanciaUnica();

	private final GridBagLayout gridBagLayout;
	private final JButton searchButton;
	private final GridBagConstraints gbc_searchButton;
	private final JButton newListButton;
	private final GridBagConstraints gbc_newListButton;
	private final JButton recentsButton;
	private final GridBagConstraints gbc_recentsButton;
	private final JButton myListsButton;
	private final GridBagConstraints gbc_myListsButton;
	private final JButton favouritesButton;
	private final GridBagConstraints gbc_favouritesButton;

	/**
	 * Create the panel.
	 */
	public NavigationPanel() {
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {110};
		gridBagLayout.rowHeights = new int[] {25, 25, 25, 25, 25};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);

		searchButton = new JButton("Buscar");
		searchButton.setOpaque(false);
		searchButton.setMargin(new Insets(5, 5, 5, 5));
		searchButton.setIconTextGap(5);
		searchButton.setFocusPainted(false);
		searchButton.setContentAreaFilled(false);
		searchButton.setBorderPainted(false);
		searchButton.setFont(BUTTON_FONT);
		gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.anchor = GridBagConstraints.WEST;
		gbc_searchButton.gridx = 0;
		gbc_searchButton.gridy = 0;
		ImageIcon searchIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoSearch.png"));
		searchButton.setIcon(searchIcon);
		add(searchButton, gbc_searchButton);

		newListButton = new JButton("Nueva lista");
		newListButton.setOpaque(false);
		newListButton.setMargin(new Insets(5, 5, 5, 5));
		newListButton.setIconTextGap(5);
		newListButton.setFont(BUTTON_FONT);
		newListButton.setFocusPainted(false);
		newListButton.setContentAreaFilled(false);
		newListButton.setBorderPainted(false);
		gbc_newListButton = new GridBagConstraints();
		gbc_newListButton.anchor = GridBagConstraints.WEST;
		gbc_newListButton.gridx = 0;
		gbc_newListButton.gridy = 1;
		ImageIcon newListIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoNewList.png"));
		newListButton.setIcon(newListIcon);
		add(newListButton, gbc_newListButton);

		recentsButton = new JButton("Recientes");
		recentsButton.setOpaque(false);
		recentsButton.setMargin(new Insets(5, 5, 5, 5));
		recentsButton.setIconTextGap(5);
		recentsButton.setFont(BUTTON_FONT);
		recentsButton.setFocusPainted(false);
		recentsButton.setContentAreaFilled(false);
		recentsButton.setBorderPainted(false);
		gbc_recentsButton = new GridBagConstraints();
		gbc_recentsButton.anchor = GridBagConstraints.WEST;
		gbc_recentsButton.gridwidth = 2;
		gbc_recentsButton.gridx = 0;
		gbc_recentsButton.gridy = 2;
		ImageIcon recentsIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoRecents.png"));
		recentsButton.setIcon(recentsIcon);
		add(recentsButton, gbc_recentsButton);

		myListsButton = new JButton("Mis listas");
		myListsButton.setOpaque(false);
		myListsButton.setMargin(new Insets(5, 5, 5, 5));
		myListsButton.setIconTextGap(5);
		myListsButton.setFont(BUTTON_FONT);
		myListsButton.setFocusPainted(false);
		myListsButton.setContentAreaFilled(false);
		myListsButton.setBorderPainted(false);
		gbc_myListsButton = new GridBagConstraints();
		gbc_myListsButton.anchor = GridBagConstraints.WEST;
		gbc_myListsButton.gridwidth = 2;
		gbc_myListsButton.gridx = 0;
		gbc_myListsButton.gridy = 3;
		ImageIcon myListsIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoMyLists.png"));
		myListsButton.setIcon(myListsIcon);
		add(myListsButton, gbc_myListsButton);

		favouritesButton = new JButton("Favoritas");
		favouritesButton.setOpaque(false);
		favouritesButton.setMargin(new Insets(5, 5, 5, 5));
		favouritesButton.setFont(BUTTON_FONT);
		favouritesButton.setFocusPainted(false);
		favouritesButton.setContentAreaFilled(false);
		favouritesButton.setBorderPainted(false);
		gbc_favouritesButton = new GridBagConstraints();
		gbc_favouritesButton.anchor = GridBagConstraints.WEST;
		gbc_favouritesButton.gridwidth = 2;
		gbc_favouritesButton.gridx = 0;
		gbc_favouritesButton.gridy = 4;
		ImageIcon favouritesIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoMasEscuchadas.png")); // load the image to a																					// imageIcon
		favouritesButton.setIcon(favouritesIcon);

		favouritesButton.setVisible(controlador.isPremium());

		controlador.addPremiumListener((user, isPremium) -> favouritesButton.setVisible(isPremium));

		add(favouritesButton, gbc_favouritesButton);
	}
	
	public void addSearchActionListener(ActionListener listener) {
		searchButton.addActionListener(listener);
	}
	
	public void addNewListActionListener(ActionListener listener) {
		newListButton.addActionListener(listener);
	}
	
	public void addRecentsActionListener(ActionListener listener) {
		recentsButton.addActionListener(listener);
	}
	
	public void addMyListsActionListener(ActionListener listener) {
		myListsButton.addActionListener(listener);
	}
	
	public void addFavouritesActionListener(ActionListener listener) {
		favouritesButton.addActionListener(listener);
	}
}
