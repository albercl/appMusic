package um.tds.appMusic.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GuiUtils {
    public static Image loadImage(String name, int w, int h) {
        Image imagen = null;
        URL url = ClassLoader.getSystemResource(name);
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(url);
            imagen=myPicture.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        } catch (IOException e1) {
            e1.printStackTrace();
            System.exit(1);
        }

        return imagen;
    }

    public static Image loadImage(String name) {
        return loadImage(name, 35, 35);
    }

    public static Image loadAppIcon(String name) {
        return loadImage(name, 100, 100);
    }

    public static void centerTable(JTable table, DefaultTableCellRenderer cellRenderer) {
        for (int i = 0; i < table.getColumnCount() ; ++i) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }

    public static void showTime(JLabel label) {
        new Timer(1000, e -> {
            Date d = new Date();
            String strDateFormat = "kk:mm:ss zz"; // El formato de fecha está especificado
            SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
            label.setText("- "+objSDF.format(d)+" -");
        }) .start();
    }
}
