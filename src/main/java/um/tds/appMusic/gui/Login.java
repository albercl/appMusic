package um.tds.appMusic.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

import java.awt.Component;
import java.awt.SystemColor;


public class Login {

	private JFrame LoginFrame;
	private JTextField userField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.LoginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Change the JFrame visibility.
	 */
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		LoginFrame.setVisible(b);
	}
	
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		LoginFrame = new JFrame();
		LoginFrame.setTitle("AppMusic");
		LoginFrame.setBounds(450, 250, 480, 300);
		LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon iconoAppMusic = new ImageIcon(GuiUtils.loadAppIcon("icons/iconoAppMusic.png"));
		LoginFrame.setIconImage(iconoAppMusic.getImage());
		
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBorder(null);
		LoginFrame.getContentPane().add(loginPanel, BorderLayout.CENTER);
		GridBagLayout gbl_loginPanel = new GridBagLayout();
		gbl_loginPanel.columnWidths = new int[] {200};
		gbl_loginPanel.rowHeights = new int[] {0, 30, 30, 30, 30, 0, 30};
		gbl_loginPanel.columnWeights = new double[]{0.0};
		gbl_loginPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		loginPanel.setLayout(gbl_loginPanel);
		
		JLabel titleLabel = new JLabel("AppMusic");
		ImageIcon imageIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoCircular.png")); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);
		titleLabel.setIcon(imageIcon);
		titleLabel.setForeground(new Color(178, 34, 34));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;

		loginPanel.add(titleLabel, gbc_titleLabel);
		
		JLabel userLabel = new JLabel("Usuario:");
		userLabel.setForeground(Color.BLACK);
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		userLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_userLabel = new GridBagConstraints();
		gbc_userLabel.fill = GridBagConstraints.BOTH;
		gbc_userLabel.insets = new Insets(0, 0, 5, 0);
		gbc_userLabel.gridx = 0;
		gbc_userLabel.gridy = 1;
		loginPanel.add(userLabel, gbc_userLabel);
		
		userField = new JTextField();
		userField.setBackground(new Color(245, 245, 245));
		userField.setMargin(new Insets(2, 10, 2, 2));
		userField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		userField.setForeground(Color.BLACK);
		GridBagConstraints gbc_userField = new GridBagConstraints();
		gbc_userField.fill = GridBagConstraints.BOTH;
		gbc_userField.insets = new Insets(0, 0, 5, 0);
		gbc_userField.gridx = 0;
		gbc_userField.gridy = 2;
		loginPanel.add(userField, gbc_userField);
		userField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Contrase\u00F1a:");
		passwordLabel.setForeground(Color.BLACK);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.fill = GridBagConstraints.BOTH;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 0);
		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 3;
		loginPanel.add(passwordLabel, gbc_passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(245, 245, 245));
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 13));
		passwordField.setMargin(new Insets(2, 10, 2, 2));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 4;
		loginPanel.add(passwordField, gbc_passwordField);
		
		JButton loginButton = new JButton("Iniciar sesión");
		loginButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		loginButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		loginButton.setBackground(new Color(178, 34, 34));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        LoginFrame.setVisible(false);
		        Main MainFrame = new Main();
		        MainFrame.setVisible(true);
			}
		});
		
		JLabel SplitLabel = new JLabel("label de separación");
		SplitLabel.setForeground(SystemColor.menu);
		SplitLabel.setFont(new Font("Tahoma", Font.PLAIN, 8));
		GridBagConstraints gbc_SplitLabel = new GridBagConstraints();
		gbc_SplitLabel.insets = new Insets(0, 0, 5, 0);
		gbc_SplitLabel.gridx = 0;
		gbc_SplitLabel.gridy = 5;
		loginPanel.add(SplitLabel, gbc_SplitLabel);
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.fill = GridBagConstraints.BOTH;
		gbc_loginButton.gridx = 0;
		gbc_loginButton.gridy = 6;
		loginPanel.add(loginButton, gbc_loginButton);
		
		JPanel registerPanel = new JPanel();
		LoginFrame.getContentPane().add(registerPanel, BorderLayout.SOUTH);
		registerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel registerLabel = new JLabel("<html>¿<b>No</b> tienes cuenta?</html>");
		registerLabel.setForeground(Color.BLACK);
		registerLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		registerPanel.add(registerLabel);
		
		JLabel arrowLabel = new JLabel("---->");
		arrowLabel.setForeground(new Color(178, 34, 34));
		registerPanel.add(arrowLabel);
		arrowLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton registerButton = new JButton("Reg\u00EDstrate");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        LoginFrame.setVisible(false);
		        Register RegisterFrame = new Register();
		        RegisterFrame.setVisible(true);
			}
		});
		registerButton.setBackground(Color.DARK_GRAY);
		registerButton.setForeground(Color.WHITE);
		registerPanel.add(registerButton);
		registerButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		

	}

}
