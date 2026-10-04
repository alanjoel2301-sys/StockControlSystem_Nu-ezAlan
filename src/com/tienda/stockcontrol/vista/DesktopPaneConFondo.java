
package com.tienda.stockcontrol.vista;

/**
 *
 * @author Dell
 */
public class DesktopPaneConFondo extends javax.swing.JDesktopPane {

    private final java.awt.Image imagenFondoOscuro;
    private final java.awt.Image imagenFondoClaro;

    public DesktopPaneConFondo() {
        imagenFondoOscuro = cargarImagen("/com/tienda/stockcontrol/vista/fondo_oscuro.png");
        imagenFondoClaro = cargarImagen("/com/tienda/stockcontrol/vista/fondo_claro.png");
    }

    private java.awt.Image cargarImagen(String ruta) {
        java.net.URL url = getClass().getResource(ruta);
        return (url != null) ? new javax.swing.ImageIcon(url).getImage() : null;
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        java.awt.Image imagenActual =
                com.formdev.flatlaf.FlatLaf.isLafDark() ? imagenFondoOscuro : imagenFondoClaro;
        if (imagenActual != null) {
            java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
            g2d.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION,
                    java.awt.RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(java.awt.RenderingHints.KEY_RENDERING,
                    java.awt.RenderingHints.VALUE_RENDER_QUALITY);
            g2d.drawImage(imagenActual, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
