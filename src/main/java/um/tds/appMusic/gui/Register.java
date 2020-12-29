package um.tds.appMusic.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import com.toedter.calendar.JDateChooser;
import um.tds.appMusic.modelo.AppMusic;

import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import java.util.Arrays;
import java.util.Date;

public class Register {

	private JFrame RegisterFrame;
	private JTextField userField;
	private JPasswordField passwordField;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField emailField;
	private JPasswordField repeatField;

	private AppMusic controlador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.RegisterFrame.setVisible(true);
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
		RegisterFrame.setVisible(b);
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
		controlador = AppMusic.getInstanciaUnica();

		RegisterFrame = new JFrame();
		RegisterFrame.setTitle("Registro AppMusic");
		RegisterFrame.setBounds(675, 235, 475, 500);
		RegisterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon iconoAppMusic = new ImageIcon(GuiUtils.loadAppIcon("icons/iconoAppMusic.png"));
		RegisterFrame.setIconImage(iconoAppMusic.getImage());

		JPanel titlePanel = new JPanel();
		RegisterFrame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		JLabel titleLabel = new JLabel("AppMusic");
		ImageIcon imageIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoCircular.png")); // load the image to a
																							// imageIcon
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon = new ImageIcon(newimg);
		titleLabel.setIcon(imageIcon);
		titleLabel.setForeground(new Color(178, 34, 34));
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titlePanel.add(titleLabel);

		JPanel registerPanel = new JPanel();
		registerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		RegisterFrame.getContentPane().add(registerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_registerPanel = new GridBagLayout();
		gbl_registerPanel.columnWidths = new int[] { 100, 0 };
		gbl_registerPanel.rowHeights = new int[] {0, 0, 0, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		gbl_registerPanel.columnWeights = new double[] { 0.0, 0.0 };
		gbl_registerPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		registerPanel.setLayout(gbl_registerPanel);

		JLabel messageLabel = new JLabel(
				"<html><p><b>Regístrate <span style=\"color: green\">gratis</span>!</b></p></html>");
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_messageLabel = new GridBagConstraints();
		gbc_messageLabel.fill = GridBagConstraints.VERTICAL;
		gbc_messageLabel.insets = new Insets(0, 0, 5, 0);
		gbc_messageLabel.gridx = 1;
		gbc_messageLabel.gridy = 0;
		registerPanel.add(messageLabel, gbc_messageLabel);

		JLabel nameLabel = new JLabel("Nombre:");
		nameLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 1;
		registerPanel.add(nameLabel, gbc_nameLabel);

		nameField = new JTextField();
		nameField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nameField.setColumns(20);
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.anchor = GridBagConstraints.WEST;
		gbc_nameField.insets = new Insets(0, 0, 5, 0);
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 1;
		registerPanel.add(nameField, gbc_nameField);

		JLabel surnameLabel = new JLabel("Apellidos:");
		surnameLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		surnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		surnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_surnameLabel = new GridBagConstraints();
		gbc_surnameLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_surnameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_surnameLabel.gridx = 0;
		gbc_surnameLabel.gridy = 2;
		registerPanel.add(surnameLabel, gbc_surnameLabel);

		surnameField = new JTextField();
		surnameField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		surnameField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		surnameField.setColumns(20);
		GridBagConstraints gbc_surnameField = new GridBagConstraints();
		gbc_surnameField.anchor = GridBagConstraints.WEST;
		gbc_surnameField.insets = new Insets(0, 0, 5, 0);
		gbc_surnameField.gridx = 1;
		gbc_surnameField.gridy = 2;
		registerPanel.add(surnameField, gbc_surnameField);

		JLabel birthdateLabel = new JLabel("Fecha de nacimiento:");
		birthdateLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		birthdateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		birthdateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_birthdateLabel = new GridBagConstraints();
		gbc_birthdateLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_birthdateLabel.insets = new Insets(0, 0, 5, 5);
		gbc_birthdateLabel.gridx = 0;
		gbc_birthdateLabel.gridy = 3;
		registerPanel.add(birthdateLabel, gbc_birthdateLabel);

		JDateChooser dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooser.gridx = 1;
		gbc_dateChooser.gridy = 3;
		registerPanel.add(dateChooser, gbc_dateChooser);

		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 0;
		gbc_emailLabel.gridy = 4;
		registerPanel.add(emailLabel, gbc_emailLabel);

		emailField = new JTextField();
		emailField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		emailField.setColumns(20);
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.anchor = GridBagConstraints.WEST;
		gbc_emailField.insets = new Insets(0, 0, 5, 0);
		gbc_emailField.gridx = 1;
		gbc_emailField.gridy = 4;
		registerPanel.add(emailField, gbc_emailField);

		JLabel userLabel = new JLabel("Nombre de usuario:");
		userLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		userLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_userLabel = new GridBagConstraints();
		gbc_userLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_userLabel.insets = new Insets(0, 0, 5, 5);
		gbc_userLabel.gridx = 0;
		gbc_userLabel.gridy = 5;
		registerPanel.add(userLabel, gbc_userLabel);

		userField = new JTextField();
		userField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		userField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_userField = new GridBagConstraints();
		gbc_userField.anchor = GridBagConstraints.WEST;
		gbc_userField.insets = new Insets(0, 0, 5, 0);
		gbc_userField.gridx = 1;
		gbc_userField.gridy = 5;
		registerPanel.add(userField, gbc_userField);
		userField.setColumns(20);

		JLabel passwordLabel = new JLabel("Contrase\u00F1a:");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 6;
		registerPanel.add(passwordLabel, gbc_passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordField.setColumns(20);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.anchor = GridBagConstraints.WEST;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 6;
		registerPanel.add(passwordField, gbc_passwordField);

		JLabel repeatLabel = new JLabel("Confirmar contraseña:");
		repeatLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		repeatLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		repeatLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_repeatLabel = new GridBagConstraints();
		gbc_repeatLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_repeatLabel.insets = new Insets(0, 0, 5, 5);
		gbc_repeatLabel.gridx = 0;
		gbc_repeatLabel.gridy = 7;
		registerPanel.add(repeatLabel, gbc_repeatLabel);

		repeatField = new JPasswordField();
		repeatField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		repeatField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		repeatField.setColumns(20);
		GridBagConstraints gbc_repeatField = new GridBagConstraints();
		gbc_repeatField.insets = new Insets(0, 0, 5, 0);
		gbc_repeatField.anchor = GridBagConstraints.WEST;
		gbc_repeatField.gridx = 1;
		gbc_repeatField.gridy = 7;
		registerPanel.add(repeatField, gbc_repeatField);

		JLabel passwordErrorLabel = new JLabel("* Las contraseñas deben coincidir");
		passwordErrorLabel.setForeground(Color.RED);
		passwordErrorLabel.setVisible(false);
		passwordErrorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_passwordErrorLabel = new GridBagConstraints();
		gbc_passwordErrorLabel.anchor = GridBagConstraints.WEST;
		gbc_passwordErrorLabel.insets = new Insets(0, 0, 5, 0);
		gbc_passwordErrorLabel.fill = GridBagConstraints.VERTICAL;
		gbc_passwordErrorLabel.gridx = 1;
		gbc_passwordErrorLabel.gridy = 9;
		registerPanel.add(passwordErrorLabel, gbc_passwordErrorLabel);
		passwordErrorLabel.setVisible(false);

		JButton registerButton = new JButton("Registrarse");
		registerButton.setFocusPainted(false);

		registerButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		registerButton.setBackground(new Color(178, 34, 34));
		registerButton.setForeground(Color.WHITE);
		GridBagConstraints gbc_registerButton = new GridBagConstraints();
		gbc_registerButton.fill = GridBagConstraints.BOTH;
		gbc_registerButton.insets = new Insets(0, 0, 5, 0);
		gbc_registerButton.gridx = 1;
		gbc_registerButton.gridy = 8;
		registerPanel.add(registerButton, gbc_registerButton);
		registerButton.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel userErrorLabel = new JLabel("* El usuario ya existe");
		userErrorLabel.setForeground(Color.RED);
		userErrorLabel.setVisible(false);
		userErrorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_userErrorLabel = new GridBagConstraints();
		gbc_userErrorLabel.anchor = GridBagConstraints.WEST;
		gbc_userErrorLabel.insets = new Insets(0, 0, 5, 0);
		gbc_userErrorLabel.gridx = 1;
		gbc_userErrorLabel.gridy = 10;
		registerPanel.add(userErrorLabel, gbc_userErrorLabel);
		
		JLabel emptyFieldsLabel = new JLabel("* No deben haber campos vacíos");
		emptyFieldsLabel.setVisible(false);
		emptyFieldsLabel.setForeground(Color.RED);
		emptyFieldsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_emptyFieldsLabel = new GridBagConstraints();
		gbc_emptyFieldsLabel.anchor = GridBagConstraints.WEST;
		gbc_emptyFieldsLabel.gridx = 1;
		gbc_emptyFieldsLabel.gridy = 11;
		registerPanel.add(emptyFieldsLabel, gbc_emptyFieldsLabel);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = String.valueOf(nameField.getText());
				String surname = String.valueOf(nameField.getText());
				Date birthdate = dateChooser.getDate();
				String email = String.valueOf(emailField.getText());
				String user = String.valueOf(userField.getText());
				String pass1 = String.valueOf(passwordField.getPassword());
				String pass2 = String.valueOf(repeatField.getPassword());

				boolean ok = true;
				if(anyEmptyField(name, surname, email, user, pass1, pass2) || birthdate == null) {
					emptyFieldsLabel.setVisible(true);
					ok = false;
				} else {
					emptyFieldsLabel.setVisible(false);
				}

				if (!(pass1.equals(pass2))) {
					passwordErrorLabel.setVisible(true);
					ok = false;
				} else {
					passwordErrorLabel.setVisible(false);
				}

				if (!controlador.checkUsername(user)) {
					userErrorLabel.setVisible(true);
					ok = false;
				} else
					userErrorLabel.setVisible(false);

				if (ok) {
					controlador.register(name + " " + surname, birthdate, email, user, pass1);
					String[] opt1 = {"Aceptar"};
					JOptionPane.showOptionDialog(RegisterFrame,
							"Te acabas de registrar en AppMusic.",
							"Éxito",
							JOptionPane.OK_OPTION,
							JOptionPane.INFORMATION_MESSAGE,
							null, opt1, opt1[0]);

					RegisterFrame.setVisible(false);
					Login LoginFrame = new Login();
					LoginFrame.setVisible(true);
				}

			}
		});

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		RegisterFrame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

		JLabel message2Label = new JLabel("¿Ya tienes cuenta?");
		message2Label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		bottomPanel.add(message2Label);

		JButton loginButton = new JButton(
				"<html><p><span style=\"color: rgb(178, 34, 34)\"><u>Inicia sesión</u></span></p></html>");
		loginButton.setFocusPainted(false);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterFrame.setVisible(false);
				Login LoginFrame = new Login();
				LoginFrame.setVisible(true);
			}
		});
		loginButton.setMargin(new Insets(2, 0, 2, 14));
		loginButton.setIconTextGap(0);
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		loginButton.setOpaque(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setBorderPainted(false);
		bottomPanel.add(loginButton);
	}

	private boolean anyEmptyField(String... fields) {
		return Arrays.stream(fields).anyMatch(s -> s.isEmpty());
	}
}
