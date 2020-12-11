package um.tds.appMusic.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel topPanel = new JPanel();
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel welcomeMessageLabel = new JLabel("Hola %user%");
		welcomeMessageLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		topPanel.add(welcomeMessageLabel);
		
		JButton upgradeButton = new JButton("Mejora tu cuenta");
		upgradeButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		topPanel.add(upgradeButton);
		
		JButton logoutButton = new JButton("Log out");
		logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		topPanel.add(logoutButton);
		
		JPanel centerPanel = new JPanel();
		FlowLayout fl_centerPanel = (FlowLayout) centerPanel.getLayout();
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		JPanel navigationPanel = new JPanel();
		frame.getContentPane().add(navigationPanel, BorderLayout.WEST);
		GridBagLayout gbl_navigationPanel = new GridBagLayout();
		gbl_navigationPanel.columnWidths = new int[]{0, 0};
		gbl_navigationPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_navigationPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_navigationPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		navigationPanel.setLayout(gbl_navigationPanel);
		
		JButton exploreButton = new JButton("Explorar");
		exploreButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_exploreButton = new GridBagConstraints();
		gbc_exploreButton.insets = new Insets(0, 0, 5, 0);
		gbc_exploreButton.gridx = 0;
		gbc_exploreButton.gridy = 0;
		navigationPanel.add(exploreButton, gbc_exploreButton);
		
		JButton newListButton = new JButton("Nueva lista");
		newListButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_newListButton = new GridBagConstraints();
		gbc_newListButton.insets = new Insets(0, 0, 5, 0);
		gbc_newListButton.gridx = 0;
		gbc_newListButton.gridy = 1;
		navigationPanel.add(newListButton, gbc_newListButton);
		
		JButton recentsButton = new JButton("Reciente");
		recentsButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_recentsButton = new GridBagConstraints();
		gbc_recentsButton.insets = new Insets(0, 0, 5, 0);
		gbc_recentsButton.gridx = 0;
		gbc_recentsButton.gridy = 2;
		navigationPanel.add(recentsButton, gbc_recentsButton);
		
		JButton myListsButton = new JButton("Mis listas");
		myListsButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_myListsButton = new GridBagConstraints();
		gbc_myListsButton.gridx = 0;
		gbc_myListsButton.gridy = 3;
		navigationPanel.add(myListsButton, gbc_myListsButton);
	}

}
