package um.tds.appMusic.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.settings.ThemeSettings;
import com.github.weisj.darklaf.theme.DarculaTheme;

import um.tds.appMusic.modelo.AppMusic;
import javax.swing.Box;

public class LoginWindow {

	private JFrame frame;
	private JTextField userField;
	private JPasswordField passwordField;

	private AppMusic controlador;
	private ImageIcon iconoAppMusic;
	private JPanel loginPanel;
	private GridBagLayout gbl_loginPanel;
	private JLabel titleLabel;// load the image to a imageIcon
	private ImageIcon imageIcon;// transform it
	private Image image;// scale it the smooth way
	private Image newimg;
	private GridBagConstraints gbc_titleLabel;
	private JLabel userLabel;
	private GridBagConstraints gbc_userLabel;
	private GridBagConstraints gbc_userField;
	private JLabel passwordLabel;
	private GridBagConstraints gbc_passwordLabel;
	private GridBagConstraints gbc_passwordField;
	private JButton loginButton;
	private GridBagConstraints gbc_loginButton;
	private JPanel registerPanel;
	private JLabel registerLabel;
	private JLabel arrowLabel;
	private JButton registerButton;
	private Component verticalStrut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ThemeSettings.getInstance().setSystemPreferencesEnabled(true);
		LafManager.install(new DarculaTheme());
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
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
	public LoginWindow() {
		initialize();
	}
	
	public void setVisible(boolean value) {
		frame.setVisible(value);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		controlador = AppMusic.getInstanciaUnica();

		frame = new JFrame();
		frame.setTitle("AppMusic");
		frame.setBounds(630, 300, 550, 325);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		iconoAppMusic = new ImageIcon(GuiUtils.loadAppIcon("icons/iconoAppMusic.png"));
		frame.setIconImage(iconoAppMusic.getImage());


		loginPanel = new JPanel();
		frame.getContentPane().add(loginPanel, BorderLayout.CENTER);
		gbl_loginPanel = new GridBagLayout();
		gbl_loginPanel.columnWidths = new int[] {200};
		gbl_loginPanel.rowHeights = new int[] {0, 30, 30, 30, 30, 0, 30, 0};
		gbl_loginPanel.columnWeights = new double[]{0.0};
		gbl_loginPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		loginPanel.setLayout(gbl_loginPanel);

		titleLabel = new JLabel("AppMusic");
		imageIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoCircular.png"));
		image = imageIcon.getImage();
		newimg = image.getScaledInstance(40, 40,  Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		titleLabel.setIcon(imageIcon);
		titleLabel.setForeground(new Color(178, 34, 34));
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;

		loginPanel.add(titleLabel, gbc_titleLabel);

		userLabel = new JLabel("Usuario:");
		userLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		gbc_userLabel = new GridBagConstraints();
		gbc_userLabel.fill = GridBagConstraints.BOTH;
		gbc_userLabel.insets = new Insets(0, 0, 5, 0);
		gbc_userLabel.gridx = 0;
		gbc_userLabel.gridy = 1;
		loginPanel.add(userLabel, gbc_userLabel);
		
		userField = new JTextField();
		userField.setMargin(new Insets(2, 10, 2, 2));
		gbc_userField = new GridBagConstraints();
		gbc_userField.fill = GridBagConstraints.BOTH;
		gbc_userField.insets = new Insets(0, 0, 5, 0);
		gbc_userField.gridx = 0;
		gbc_userField.gridy = 2;
		loginPanel.add(userField, gbc_userField);
		userField.setColumns(10);

		passwordLabel = new JLabel("Contrase\u00F1a:");
		passwordLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.fill = GridBagConstraints.BOTH;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 0);
		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 3;
		loginPanel.add(passwordLabel, gbc_passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 13));
		passwordField.setMargin(new Insets(2, 10, 2, 2));
		gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 4;
		loginPanel.add(passwordField, gbc_passwordField);

		loginButton = new JButton("Iniciar sesión");
		loginButton.setFocusPainted(false);
		loginButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		loginButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		loginButton.setBackground(new Color(178, 34, 34));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        String user = userField.getText();
		        String password = String.valueOf(passwordField.getPassword());
				if(controlador.login(user, password)) {
					frame.setVisible(false);
			        MainWindow MainFrame = new MainWindow();
			        MainFrame.setVisible(true);
				}
				else {
					String[] opt1 = {"Aceptar"};
					JOptionPane.showOptionDialog(frame, 
						"Nombre de usuario o contraseña no válido.", 
						"Error", 
						JOptionPane.OK_OPTION, 
						JOptionPane.ERROR_MESSAGE, 
						null, opt1, opt1[0]);
				}

			}
		});
		gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.insets = new Insets(0, 0, 5, 0);
		gbc_loginButton.fill = GridBagConstraints.BOTH;
		gbc_loginButton.gridx = 0;
		gbc_loginButton.gridy = 6;
		loginPanel.add(loginButton, gbc_loginButton);

		registerPanel = new JPanel();
		frame.getContentPane().add(registerPanel, BorderLayout.SOUTH);
		registerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		registerLabel = new JLabel("<html>¿<b>No</b> tienes cuenta?</html>");
		registerLabel.setForeground(Color.BLACK);
		registerLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		registerPanel.add(registerLabel);

		arrowLabel = new JLabel("---->");
		arrowLabel.setForeground(new Color(178, 34, 34));
		registerPanel.add(arrowLabel);
		arrowLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

		registerButton = new JButton("Reg\u00EDstrate");
		registerButton.setFocusPainted(false);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
		        RegisterWindow RegisterFrame = new RegisterWindow();
		        RegisterFrame.setVisible(true);
			}
		});
		registerButton.setBackground(Color.DARK_GRAY);
		registerButton.setForeground(Color.WHITE);
		registerPanel.add(registerButton);
		registerButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		

		userField.setText("luisgrego_");
		passwordField.setText("wsl2");
		
		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 5;
		loginPanel.add(verticalStrut, gbc_verticalStrut);

		loginButton.doClick();
	}

}
