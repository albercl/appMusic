package um.tds.appMusic.gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

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
}
