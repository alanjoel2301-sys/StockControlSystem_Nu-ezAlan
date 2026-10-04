package com.tienda.stockcontrol;

/**
 *
 * @author Dell
 */
import com.tienda.stockcontrol.vista.FrmLogin;
import com.tienda.stockcontrol.vista.MainFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        java.util.prefs.Preferences prefs = java.util.prefs.Preferences.userNodeForPackage(Main.class);
        boolean modoOscuro = prefs.getBoolean("modoOscuro", true);
        if (modoOscuro) {
            com.formdev.flatlaf.FlatDarkLaf.setup();
        } else {
            com.formdev.flatlaf.FlatLightLaf.setup();
        }
        SwingUtilities.invokeLater(Main::mostrarLoginYAbrirPrincipal);
    }

    public static void mostrarLoginYAbrirPrincipal() {
        FrmLogin login = new FrmLogin(null, true);
        login.setLocationRelativeTo(null);
        login.setVisible(true);

        if (login.fueExitoso()) {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        } else {
            System.exit(0);
        }
    }
}