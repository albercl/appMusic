package tds.appMusic.gui;

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

import tds.appMusic.modelo.AppMusic;
import tds.appMusic.modelo.IDescuento;
import tds.appMusic.modelo.Usuario;

import javax.swing.Box;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PremiumWindow {

	private JFrame frame;

	private final AppMusic controlador;
	private final Usuario loggedUser;

	private static final float PRECIO = 50f;

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
	private JButton loginButton;
	private GridBagConstraints gbc_loginButton;
	private JPanel registerPanel;
	private Component verticalStrut;
	private JLabel lblNewLabel;
	private JLabel pdfIconLabel;
	private JLabel masEscuchadasLabel;
	private JLabel lblHola;
	private JLabel signoLabel_1;
	private JLabel lblahorra;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ThemeSettings.getInstance().setSystemPreferencesEnabled(true);
		LafManager.install(new DarculaTheme());
		
		EventQueue.invokeLater(() -> {
			try {
				PremiumWindow window = new PremiumWindow();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PremiumWindow() {
		controlador = AppMusic.getInstanciaUnica();
		loggedUser = controlador.getLoggedUser();


		initialize();
	}
	
	public void setVisible(boolean value) {
		frame.setVisible(value);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("AppMusic Premium");
		frame.setBounds(630, 300, 730, 450);

		iconoAppMusic = new ImageIcon(GuiUtils.loadAppIcon("icons/iconoAppMusicPremium.png"));
		frame.setIconImage(iconoAppMusic.getImage());


		loginPanel = new JPanel();
		frame.getContentPane().add(loginPanel, BorderLayout.CENTER);
		gbl_loginPanel = new GridBagLayout();
		gbl_loginPanel.columnWidths = new int[] {200, 0};
		gbl_loginPanel.rowHeights = new int[] {0, 30, 30, 30, 30, 0, 0, 0, 30, 0};
		gbl_loginPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_loginPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		loginPanel.setLayout(gbl_loginPanel);

		titleLabel = new JLabel("<html><p><b><span style=\"color: #2271b3\">AppMusic<span style=\"color: #DAA520\"> Premium</span></b></p></html>");
		imageIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoPremium.png"));
		image = imageIcon.getImage();
		newimg = image.getScaledInstance(40, 40,  Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		titleLabel.setIcon(imageIcon);
		titleLabel.setForeground(new Color(218, 165, 32));
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;

		loginPanel.add(titleLabel, gbc_titleLabel);
		
		userLabel = new JLabel("<html>\r\n<p>Pásate ya a <span style=\"color: #daa520\">Premium</span> y</p><p>empieza a disfrutar de múltiples <u><span style=\"color: #2271b3\">ventajas</u></span>.</p>\r\n<html>");
		userLabel.setBackground(new Color(0, 0, 0));
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		userLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		gbc_userLabel = new GridBagConstraints();
		gbc_userLabel.fill = GridBagConstraints.BOTH;
		gbc_userLabel.insets = new Insets(0, 0, 5, 5);
		gbc_userLabel.gridx = 0;
		gbc_userLabel.gridy = 2;
		loginPanel.add(userLabel, gbc_userLabel);
		
		lblNewLabel_1 = new JLabel("-----------------------------------------------------------------------------------------------------------");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		loginPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		lblNewLabel = new JLabel("<html>\r\n<p>Precio final: <span style=\"color: #af1c2f\"><strike>50$</strike></span> --> <span style=\"color: green\"><u>25$</u></p>\r\n</html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		loginPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		lblahorra = new JLabel();
		lblahorra.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblahorra = new GridBagConstraints();
		gbc_lblahorra.insets = new Insets(0, 0, 5, 5);
		gbc_lblahorra.gridx = 0;
		gbc_lblahorra.gridy = 5;

		IDescuento mejorDescuento = loggedUser.getMejorDescuento();

		if(mejorDescuento != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("<html>\r\n<p><span style=\"color: #FFA500\">")
					.append(mejorDescuento.getTextoDescuento())
					.append("</p>\r\n</html>");
			lblahorra.setText(sb.toString());

			sb = new StringBuilder();
			sb.append("<html>\r\n<p>Precio final: <span style=\"color: #af1c2f\"><strike>")
					.append(PRECIO)
					.append("$</strike></span> --> <span style=\"color: green\"><u>")
					.append(mejorDescuento.getPrecioFinal(PRECIO))
					.append("$</u></p>\r\n</html>");
			lblNewLabel.setText(sb.toString());
		} else {
			lblNewLabel.setText("<html>\r\n<p>Precio final: <span style=\"color: green\"><u>50$</u></p>\r\n</html>");
			lblahorra.setText("<html>\r\n<p><span style=\"color: #FFA500\">No tienes ningún descuento disponible :(</p>\r\n</html>");
		}

		loginPanel.add(lblahorra, gbc_lblahorra);
		
		lblNewLabel_2 = new JLabel("-----------------------------------------------------------------------------------------------------------");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 6;
		loginPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		loginButton = new JButton("Quiero empezar a ser premium");
		loginButton.addActionListener(arg0 -> {
			JOptionPane.showMessageDialog(frame, "A partir de ahora eres usuario premium");
			controlador.setPremium(true);
			frame.setVisible(false);
		});
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		loginButton.setFocusPainted(false);
		loginButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		loginButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		loginButton.setBackground(new Color(178, 34, 34));
		loginButton.setForeground(new Color(255, 255, 255));

		gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.insets = new Insets(0, 0, 5, 5);
		gbc_loginButton.fill = GridBagConstraints.VERTICAL;
		gbc_loginButton.gridx = 0;
		gbc_loginButton.gridy = 8;
		loginPanel.add(loginButton, gbc_loginButton);

		registerPanel = new JPanel();
		frame.getContentPane().add(registerPanel, BorderLayout.SOUTH);
		registerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		signoLabel_1 = new JLabel("");
		registerPanel.add(signoLabel_1);
		
		lblHola = new JLabel("<html>\r\n<p><span style=\"color: #2271b3\">* Podrás descargar pdfs de tus playlists y </p><p><span style=\"color: #2271b3\">tendrás acceso a tus 10 canciones más escuchadas </p></p><span style=\"color: #2271b3\">de AppMusic para <u><span style=\"color: #daa520\">siempre</span></u></p>\r\n<html>");
		lblHola.setVerticalAlignment(SwingConstants.BOTTOM);
		lblHola.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHola.setBackground(Color.BLACK);
		registerPanel.add(lblHola);
		
		pdfIconLabel = new JLabel("");
		imageIcon = new ImageIcon(GuiUtils.loadImage("icons/pdfMas.png"));
		image = imageIcon.getImage();
		newimg = image.getScaledInstance(55, 55,  Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		pdfIconLabel.setIcon(imageIcon);
		registerPanel.add(pdfIconLabel);
		
		masEscuchadasLabel = new JLabel("");
		imageIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoMasEscuchadasMas.png"));
		image = imageIcon.getImage();
		newimg = image.getScaledInstance(45, 45,  Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		masEscuchadasLabel.setIcon(imageIcon);
		registerPanel.add(masEscuchadasLabel);
		
		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 7;
		loginPanel.add(verticalStrut, gbc_verticalStrut);
	}
}
