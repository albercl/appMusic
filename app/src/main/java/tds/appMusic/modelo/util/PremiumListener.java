package tds.appMusic.modelo.util;

import tds.appMusic.modelo.Usuario;

public interface PremiumListener {
    void premiumChanged(Usuario user, boolean isPremium);
}
