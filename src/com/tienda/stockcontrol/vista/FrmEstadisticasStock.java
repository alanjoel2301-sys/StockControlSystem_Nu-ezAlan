
package com.tienda.stockcontrol.vista;

/**
 *
 * @author Dell
 */
public class FrmEstadisticasStock extends javax.swing.JInternalFrame {

    private final com.tienda.stockcontrol.controlador.ControladorEstadisticas controlador =
            new com.tienda.stockcontrol.controlador.ControladorEstadisticas(this);
    
    public FrmEstadisticasStock() {
        initComponents();
        controlador.cargarGraficos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Estadisticas");
        setFrameIcon(null);
        setMinimumSize(new java.awt.Dimension(861, 541));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void agregarPestana(String titulo, javax.swing.JPanel panel) {
        tabs.addTab(titulo, panel);
    }

    public void mostrarError(Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this,
                "Error al generar estadisticas: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane tabs;
    // End of variables declaration//GEN-END:variables
}
