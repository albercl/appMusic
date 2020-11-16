package um.tds.appMusic.gui;

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

public class Register {

	private JFrame frmRegistroAppmusic;
	private JTextField userField;
	private JPasswordField passwordField;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField birthdateField;
	private JTextField emailField;
	private JPasswordField repeatField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frmRegistroAppmusic.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistroAppmusic = new JFrame();
		frmRegistroAppmusic.setTitle("Registro AppMusic");
		frmRegistroAppmusic.setBounds(100, 100, 466, 344);
		frmRegistroAppmusic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) SystemColor.activeCaption));
		frmRegistroAppmusic.getContentPane().add(titlePanel, BorderLayout.NORTH);
		
		JLabel registerLabel = new JLabel("Registro");
		registerLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		titlePanel.add(registerLabel);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBorder(null);
		frmRegistroAppmusic.getContentPane().add(loginPanel, BorderLayout.CENTER);
		GridBagLayout gbl_loginPanel = new GridBagLayout();
		gbl_loginPanel.columnWidths = new int[] {0, 250};
		gbl_loginPanel.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30};
		gbl_loginPanel.columnWeights = new double[]{1.0, 1.0};
		gbl_loginPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		loginPanel.setLayout(gbl_loginPanel);
		
		nameField = new JTextField();
		nameField.setColumns(30);
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.anchor = GridBagConstraints.WEST;
		gbc_nameField.insets = new Insets(0, 0, 5, 0);
		gbc_nameField.fill = GridBagConstraints.VERTICAL;
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 0;
		loginPanel.add(nameField, gbc_nameField);
		
		surnameField = new JTextField();
		surnameField.setColumns(30);
		GridBagConstraints gbc_surnameField = new GridBagConstraints();
		gbc_surnameField.anchor = GridBagConstraints.WEST;
		gbc_surnameField.insets = new Insets(0, 0, 5, 0);
		gbc_surnameField.fill = GridBagConstraints.VERTICAL;
		gbc_surnameField.gridx = 1;
		gbc_surnameField.gridy = 1;
		loginPanel.add(surnameField, gbc_surnameField);
		
		birthdateField = new JTextField();
		birthdateField.setColumns(30);
		GridBagConstraints gbc_birthdateField = new GridBagConstraints();
		gbc_birthdateField.anchor = GridBagConstraints.WEST;
		gbc_birthdateField.insets = new Insets(0, 0, 5, 0);
		gbc_birthdateField.fill = GridBagConstraints.VERTICAL;
		gbc_birthdateField.gridx = 1;
		gbc_birthdateField.gridy = 2;
		loginPanel.add(birthdateField, gbc_birthdateField);
		
		emailField = new JTextField();
		emailField.setColumns(30);
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.anchor = GridBagConstraints.WEST;
		gbc_emailField.insets = new Insets(0, 0, 5, 0);
		gbc_emailField.fill = GridBagConstraints.VERTICAL;
		gbc_emailField.gridx = 1;
		gbc_emailField.gridy = 3;
		loginPanel.add(emailField, gbc_emailField);
		
		JLabel userLabel = new JLabel("Usuario:");
		userLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		userLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_userLabel = new GridBagConstraints();
		gbc_userLabel.anchor = GridBagConstraints.EAST;
		gbc_userLabel.fill = GridBagConstraints.VERTICAL;
		gbc_userLabel.insets = new Insets(0, 0, 5, 5);
		gbc_userLabel.gridx = 0;
		gbc_userLabel.gridy = 4;
		loginPanel.add(userLabel, gbc_userLabel);
		
		userField = new JTextField();
		GridBagConstraints gbc_userField = new GridBagConstraints();
		gbc_userField.anchor = GridBagConstraints.WEST;
		gbc_userField.fill = GridBagConstraints.VERTICAL;
		gbc_userField.insets = new Insets(0, 0, 5, 0);
		gbc_userField.gridx = 1;
		gbc_userField.gridy = 4;
		loginPanel.add(userField, gbc_userField);
		userField.setColumns(30);
		
		JLabel passwordLabel = new JLabel("Contrase\u00F1a:");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.EAST;
		gbc_passwordLabel.fill = GridBagConstraints.VERTICAL;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 5;
		loginPanel.add(passwordLabel, gbc_passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(30);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.WEST;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.VERTICAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 5;
		loginPanel.add(passwordField, gbc_passwordField);
		
		JLabel nameLabel = new JLabel("Nombre:");
		nameLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 0;
		loginPanel.add(nameLabel, gbc_nameLabel);
		
		JLabel surnameLabel = new JLabel("Apellidos:");
		surnameLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		surnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		surnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_surnameLabel = new GridBagConstraints();
		gbc_surnameLabel.anchor = GridBagConstraints.EAST;
		gbc_surnameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_surnameLabel.gridx = 0;
		gbc_surnameLabel.gridy = 1;
		loginPanel.add(surnameLabel, gbc_surnameLabel);
		
		JLabel birthdateLabel = new JLabel("Fecha nacimiento:");
		birthdateLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		birthdateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		birthdateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_birthdateLabel = new GridBagConstraints();
		gbc_birthdateLabel.anchor = GridBagConstraints.EAST;
		gbc_birthdateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_birthdateLabel.gridx = 0;
		gbc_birthdateLabel.gridy = 2;
		loginPanel.add(birthdateLabel, gbc_birthdateLabel);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.anchor = GridBagConstraints.EAST;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 0;
		gbc_emailLabel.gridy = 3;
		loginPanel.add(emailLabel, gbc_emailLabel);
		
		JLabel repeatLabel = new JLabel("Repite contraseña:");
		repeatLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		repeatLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		repeatLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_repeatLabel = new GridBagConstraints();
		gbc_repeatLabel.anchor = GridBagConstraints.EAST;
		gbc_repeatLabel.insets = new Insets(0, 0, 0, 5);
		gbc_repeatLabel.gridx = 0;
		gbc_repeatLabel.gridy = 6;
		loginPanel.add(repeatLabel, gbc_repeatLabel);
		
		repeatField = new JPasswordField();
		repeatField.setColumns(30);
		GridBagConstraints gbc_repeatField = new GridBagConstraints();
		gbc_repeatField.anchor = GridBagConstraints.WEST;
		gbc_repeatField.fill = GridBagConstraints.VERTICAL;
		gbc_repeatField.gridx = 1;
		gbc_repeatField.gridy = 6;
		loginPanel.add(repeatField, gbc_repeatField);
		
		JPanel buttonPanel = new JPanel();
		frmRegistroAppmusic.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		JButton registerButton = new JButton("Registrar");
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonPanel.add(registerButton);
		
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonPanel.add(cancelButton);
	}

}
