package tds.appMusic;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.settings.ThemeSettings;
import com.github.weisj.darklaf.theme.DarculaTheme;
import tds.appMusic.gui.LoginWindow;

public class Lanzador {
    public static void main(String[] args) {
        //Instalación del tema (borrar ambas líneas para tema estándar de Swing)
        ThemeSettings.getInstance().setSystemPreferencesEnabled(true);
        LafManager.install(new DarculaTheme());

        //Inicio de la aplicación
        LoginWindow lg = new LoginWindow();
        lg.setVisible(true);
    }
}
