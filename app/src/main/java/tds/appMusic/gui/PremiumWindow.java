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
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.settings.ThemeSettings;
import com.github.weisj.darklaf.theme.DarculaTheme;

import tds.appMusic.modelo.AppMusic;
import tds.appMusic.modelo.IDescuento;
import tds.appMusic.modelo.Usuario;

import javax.swing.Box;

public class PremiumWindow {

	private JFrame frame;

	private final AppMusic controlador;
	private final Usuario loggedUser;
	private MainWindow mainWindow;

	private static final float PRECIO = 50f;

	private ImageIcon iconoAppMusic;
	private JPanel mainPanel;
	private GridBagLayout gbl_mainPanel;
	private JLabel premiumLabel;// load the image to a imageIcon
	private ImageIcon imageIcon;// transform it
	private Image image;// scale it the smooth way
	private Image newimg;
	private GridBagConstraints gbc_premiumLabel;
	private JLabel premiumLabel2;
	private GridBagConstraints gbc_premiumLabel2;
	private JButton button;
	private GridBagConstraints gbc_button;
	private JPanel registerPanel;
	private Component verticalStrut;
	private JLabel priceLabel;
	private JLabel pdfIconLabel;
	private JLabel masEscuchadasLabel;
	private JLabel ventajasLabel;
	private JLabel signoLabel_1;
	private JLabel descuentoLabel;
	private JLabel separatorLabel;
	private JLabel separatorLabel2;

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

	private PremiumWindow() {
		controlador = AppMusic.getInstanciaUnica();
		loggedUser = controlador.getLoggedUser();
		initialize();
	}

	/**
	 * Create the application.
	 */
	public PremiumWindow(MainWindow mainWindow) {
		this();
		this.mainWindow = mainWindow;
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

		mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[] {200, 0};
		gbl_mainPanel.rowHeights = new int[] {0, 30, 30, 30, 30, 0, 0, 0, 30, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		mainPanel.setLayout(gbl_mainPanel);

		premiumLabel = new JLabel("<html><p><b><span style=\"color: #2271b3\">AppMusic<span style=\"color: #DAA520\"> Premium</span></b></p></html>");
		imageIcon = new ImageIcon(GuiUtils.loadImage("icons/iconoPremium.png"));
		image = imageIcon.getImage();
		newimg = image.getScaledInstance(40, 40,  Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		premiumLabel.setIcon(imageIcon);
		premiumLabel.setForeground(new Color(218, 165, 32));
		premiumLabel.setFont(new Font("Arial", Font.BOLD, 24));
		gbc_premiumLabel = new GridBagConstraints();
		gbc_premiumLabel.insets = new Insets(0, 0, 5, 5);
		gbc_premiumLabel.gridx = 0;
		gbc_premiumLabel.gridy = 0;

		mainPanel.add(premiumLabel, gbc_premiumLabel);
		
		premiumLabel2 = new JLabel("<html>\r\n<p>Pásate ya a <span style=\"color: #daa520\">Premium</span> y</p><p>empieza a disfrutar de múltiples <u><span style=\"color: #2271b3\">ventajas</u></span>.</p>\r\n<html>");
		premiumLabel2.setBackground(new Color(0, 0, 0));
		premiumLabel2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		premiumLabel2.setVerticalAlignment(SwingConstants.BOTTOM);
		gbc_premiumLabel2 = new GridBagConstraints();
		gbc_premiumLabel2.fill = GridBagConstraints.BOTH;
		gbc_premiumLabel2.insets = new Insets(0, 0, 5, 5);
		gbc_premiumLabel2.gridx = 0;
		gbc_premiumLabel2.gridy = 2;
		mainPanel.add(premiumLabel2, gbc_premiumLabel2);
		
		separatorLabel = new JLabel("-----------------------------------------------------------------------------------------------------------");
		GridBagConstraints gbc_separatorLabel = new GridBagConstraints();
		gbc_separatorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_separatorLabel.gridx = 0;
		gbc_separatorLabel.gridy = 3;
		mainPanel.add(separatorLabel, gbc_separatorLabel);
		
		priceLabel = new JLabel();
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		GridBagConstraints gbc_priceLabel = new GridBagConstraints();
		gbc_priceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_priceLabel.gridx = 0;
		gbc_priceLabel.gridy = 4;
		mainPanel.add(priceLabel, gbc_priceLabel);
		
		descuentoLabel = new JLabel();
		descuentoLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_descuentoLabel = new GridBagConstraints();
		gbc_descuentoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_descuentoLabel.gridx = 0;
		gbc_descuentoLabel.gridy = 5;

		IDescuento mejorDescuento = loggedUser.getMejorDescuento();

		if(mejorDescuento != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("<html>\r\n<p><span style=\"color: #FFA500\">")
					.append(mejorDescuento.getTextoDescuento())
					.append("</p>\r\n</html>");
			descuentoLabel.setText(sb.toString());

			DecimalFormat formatter = new DecimalFormat();

			sb = new StringBuilder();
			sb.append("<html>\r\n<p>Precio final: <span style=\"color: #af1c2f\"><strike>")
					.append(formatter.format(PRECIO))
					.append("$</strike></span> --> <span style=\"color: green\"><u>")
					.append(formatter.format(mejorDescuento.getPrecioFinal(PRECIO)))
					.append("$</u></p>\r\n</html>");
			priceLabel.setText(sb.toString());
		} else {
			priceLabel.setText("<html>\r\n<p>Precio final: <span style=\"color: green\"><u>50$</u></p>\r\n</html>");
			descuentoLabel.setText("<html>\r\n<p><span style=\"color: #FFA500\">No tienes ningún descuento disponible :(</p>\r\n</html>");
		}

		mainPanel.add(descuentoLabel, gbc_descuentoLabel);
		
		separatorLabel2 = new JLabel("-----------------------------------------------------------------------------------------------------------");
		GridBagConstraints gbc_separatorLabel2 = new GridBagConstraints();
		gbc_separatorLabel2.insets = new Insets(0, 0, 5, 5);
		gbc_separatorLabel2.gridx = 0;
		gbc_separatorLabel2.gridy = 6;
		mainPanel.add(separatorLabel2, gbc_separatorLabel2);

		button = new JButton("Quiero empezar a ser premium");
		button.addActionListener(arg0 -> {
			JOptionPane.showMessageDialog(frame, "A partir de ahora eres usuario premium");
			controlador.setPremium(true);
			mainWindow.updatePremium(true);

			frame.setVisible(false);
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button.setFocusPainted(false);
		button.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		button.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		button.setBackground(new Color(178, 34, 34));
		button.setForeground(new Color(255, 255, 255));

		gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.fill = GridBagConstraints.VERTICAL;
		gbc_button.gridx = 0;
		gbc_button.gridy = 8;
		mainPanel.add(button, gbc_button);

		registerPanel = new JPanel();
		frame.getContentPane().add(registerPanel, BorderLayout.SOUTH);
		registerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		signoLabel_1 = new JLabel("");
		registerPanel.add(signoLabel_1);
		
		ventajasLabel = new JLabel("<html>\r\n<p><span style=\"color: #2271b3\">* Podrás descargar pdfs de tus playlists y </p><p><span style=\"color: #2271b3\">tendrás acceso a tus 10 canciones más escuchadas </p></p><span style=\"color: #2271b3\">de AppMusic para <u><span style=\"color: #daa520\">siempre</span></u></p>\r\n<html>");
		ventajasLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		ventajasLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		ventajasLabel.setBackground(Color.BLACK);
		registerPanel.add(ventajasLabel);
		
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
		mainPanel.add(verticalStrut, gbc_verticalStrut);
	}
}
