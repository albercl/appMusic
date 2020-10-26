package um.tds.appMusic.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import java.awt.GridLayout;

public class Login {

	private JFrame frame;
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 466, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) SystemColor.activeCaption));
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		
		JLabel titleLabel = new JLabel("AppMusic");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		titlePanel.add(titleLabel);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBorder(null);
		frame.getContentPane().add(loginPanel, BorderLayout.CENTER);
		GridBagLayout gbl_loginPanel = new GridBagLayout();
		gbl_loginPanel.columnWidths = new int[] {200};
		gbl_loginPanel.rowHeights = new int[] {30, 30, 30, 30, 30};
		gbl_loginPanel.columnWeights = new double[]{0.0};
		gbl_loginPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		loginPanel.setLayout(gbl_loginPanel);
		
		JLabel userLabel = new JLabel("Usuario:");
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		userLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_userLabel = new GridBagConstraints();
		gbc_userLabel.fill = GridBagConstraints.BOTH;
		gbc_userLabel.insets = new Insets(0, 0, 5, 0);
		gbc_userLabel.gridx = 0;
		gbc_userLabel.gridy = 0;
		loginPanel.add(userLabel, gbc_userLabel);
		
		userField = new JTextField();
		GridBagConstraints gbc_userField = new GridBagConstraints();
		gbc_userField.fill = GridBagConstraints.BOTH;
		gbc_userField.insets = new Insets(0, 0, 5, 0);
		gbc_userField.gridx = 0;
		gbc_userField.gridy = 1;
		loginPanel.add(userField, gbc_userField);
		userField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Contrase\u00F1a:");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.fill = GridBagConstraints.BOTH;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 0);
		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 2;
		loginPanel.add(passwordLabel, gbc_passwordLabel);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 3;
		loginPanel.add(passwordField, gbc_passwordField);
		
		JButton loginButton = new JButton("Iniciar Sesi\u00F3n");
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.fill = GridBagConstraints.BOTH;
		gbc_loginButton.gridx = 0;
		gbc_loginButton.gridy = 4;
		loginPanel.add(loginButton, gbc_loginButton);
		
		JPanel registerPanel = new JPanel();
		frame.getContentPane().add(registerPanel, BorderLayout.SOUTH);
		registerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel registerLabel = new JLabel("\u00BFNo tienes cuenta?");
		registerLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		registerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		registerPanel.add(registerLabel);
		
		JLabel arrowLabel = new JLabel("---->");
		registerPanel.add(arrowLabel);
		arrowLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton registerButton = new JButton("Reg\u00EDstrate");
		registerPanel.add(registerButton);
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
	}

}
