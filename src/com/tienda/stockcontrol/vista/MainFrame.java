
package com.tienda.stockcontrol.vista;

/**
 *
 * @author Dell
 */

public class MainFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());

    public MainFrame() {
        initComponents();
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        actualizarEstadoSesion();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                verificarBajoStock();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new com.tienda.stockcontrol.vista.DesktopPaneConFondo();
        lblEstado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuGestion = new javax.swing.JMenu();
        itemCategorias = new javax.swing.JMenuItem();
        itemProductos = new javax.swing.JMenuItem();
        itemUsuarios = new javax.swing.JMenuItem();
        menuMovimientos = new javax.swing.JMenu();
        itemRegistrar = new javax.swing.JMenuItem();
        menuConsultas = new javax.swing.JMenu();
        itemStock = new javax.swing.JMenuItem();
        menuEstadisticas = new javax.swing.JMenu();
        itemGraficos = new javax.swing.JMenuItem();
        menuAyuda = new javax.swing.JMenu();
        itemAcerca = new javax.swing.JMenuItem();
        menuSesion = new javax.swing.JMenu();
        itemAlternarTema = new javax.swing.JMenuItem();
        itemCerrarSesion = new javax.swing.JMenuItem();
        itemSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Control de Stock");
        setIconImages(java.util.Arrays.asList(
            new com.formdev.flatlaf.extras.FlatSVGIcon("com/tienda/stockcontrol/vista/app-icon.svg").derive(16, 16).getImage(),
            new com.formdev.flatlaf.extras.FlatSVGIcon("com/tienda/stockcontrol/vista/app-icon.svg").derive(32, 32).getImage(),
            new com.formdev.flatlaf.extras.FlatSVGIcon("com/tienda/stockcontrol/vista/app-icon.svg", 1.5f).getImage(),
            new com.formdev.flatlaf.extras.FlatSVGIcon("com/tienda/stockcontrol/vista/app-icon.svg").derive(64, 64).getImage(),
            new com.formdev.flatlaf.extras.FlatSVGIcon("com/tienda/stockcontrol/vista/app-icon.svg").derive(256, 256).getImage()
        ));

        desktopPane.setPreferredSize(new java.awt.Dimension(1920, 1080));

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1920, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
        );

        lblEstado.setToolTipText("");
        lblEstado.setOpaque(true);
        lblEstado.setRequestFocusEnabled(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Copyright © 2026 Alan Joel Nuñez. Todos los derechos reservados.   ");

        menuGestion.setText("Gestion");

        itemCategorias.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemCategorias.setText("Categorias");
        itemCategorias.addActionListener(this::itemCategoriasActionPerformed);
        menuGestion.add(itemCategorias);

        itemProductos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemProductos.setText("Productos");
        itemProductos.addActionListener(this::itemProductosActionPerformed);
        menuGestion.add(itemProductos);

        itemUsuarios.setText("Usuarios");
        itemUsuarios.addActionListener(this::itemUsuariosActionPerformed);
        menuGestion.add(itemUsuarios);

        jMenuBar1.add(menuGestion);

        menuMovimientos.setText("Movimientos");

        itemRegistrar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemRegistrar.setText("Registrar entrada/salida");
        itemRegistrar.addActionListener(this::itemRegistrarActionPerformed);
        menuMovimientos.add(itemRegistrar);

        jMenuBar1.add(menuMovimientos);

        menuConsultas.setText("Consultas");

        itemStock.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemStock.setText("Consultar stock");
        itemStock.addActionListener(this::itemStockActionPerformed);
        menuConsultas.add(itemStock);

        jMenuBar1.add(menuConsultas);

        menuEstadisticas.setText("Estadisticas");

        itemGraficos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemGraficos.setText("Ver graficos");
        itemGraficos.addActionListener(this::itemGraficosActionPerformed);
        menuEstadisticas.add(itemGraficos);

        jMenuBar1.add(menuEstadisticas);

        menuAyuda.setText("Ayuda");

        itemAcerca.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        itemAcerca.setText("Acerca de");
        itemAcerca.addActionListener(this::itemAcercaActionPerformed);
        menuAyuda.add(itemAcerca);

        jMenuBar1.add(menuAyuda);

        menuSesion.setText("Sesión");

        itemAlternarTema.setText("Alternar tema");
        itemAlternarTema.addActionListener(this::itemAlternarTemaActionPerformed);
        menuSesion.add(itemAlternarTema);

        itemCerrarSesion.setText("Cerrar sesión");
        itemCerrarSesion.setToolTipText("");
        itemCerrarSesion.addActionListener(this::itemCerrarSesionActionPerformed);
        menuSesion.add(itemCerrarSesion);

        itemSalir.setText("Salir");
        itemSalir.addActionListener(this::itemSalirActionPerformed);
        menuSesion.add(itemSalir);

        jMenuBar1.add(menuSesion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCategoriasActionPerformed
        abrirInternalFrame(new FrmCategoria());
    }//GEN-LAST:event_itemCategoriasActionPerformed

    private void itemProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemProductosActionPerformed
        abrirInternalFrame(new FrmProducto());
    }//GEN-LAST:event_itemProductosActionPerformed

    private void itemRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRegistrarActionPerformed
        abrirInternalFrame(new FrmMovimientos());
    }//GEN-LAST:event_itemRegistrarActionPerformed

    private void itemStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemStockActionPerformed
        abrirInternalFrame(new FrmStock());
    }//GEN-LAST:event_itemStockActionPerformed

    private void itemGraficosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGraficosActionPerformed
        abrirInternalFrame(new FrmEstadisticasStock());
    }//GEN-LAST:event_itemGraficosActionPerformed

    private void itemAcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAcercaActionPerformed
        javax.swing.JOptionPane.showMessageDialog(this,
                "Sistema de Control de Stock v1.0.0\nJava + Swing + MySQL + JFreeChart\nCopyright © 2026 Alan Joel Nuñez. Todos los derechos reservados.",
                "Acerca de", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_itemAcercaActionPerformed

    private void itemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemUsuariosActionPerformed
        abrirInternalFrame(new com.tienda.stockcontrol.vista.FrmUsuario());
    }//GEN-LAST:event_itemUsuariosActionPerformed

    private void itemCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCerrarSesionActionPerformed
        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Seguro que desea cerrar sesion?", "Cerrar sesion", javax.swing.JOptionPane.YES_NO_OPTION);
        if (confirmacion != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }
        com.tienda.stockcontrol.controlador.SesionUsuario.getInstancia().cerrarSesion();
        dispose();
        javax.swing.SwingUtilities.invokeLater(com.tienda.stockcontrol.Main::mostrarLoginYAbrirPrincipal);
    }//GEN-LAST:event_itemCerrarSesionActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void itemAlternarTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAlternarTemaActionPerformed
        try {
            java.util.prefs.Preferences prefs = java.util.prefs.Preferences.userNodeForPackage(
                    com.tienda.stockcontrol.Main.class);
            boolean modoOscuroActual = prefs.getBoolean("modoOscuro", true);
            boolean nuevoModoOscuro = !modoOscuroActual;

            if (nuevoModoOscuro) {
                javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
            } else {
                javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
            }
            com.formdev.flatlaf.FlatLaf.updateUI();

            prefs.putBoolean("modoOscuro", nuevoModoOscuro);
        } catch (javax.swing.UnsupportedLookAndFeelException e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "No se pudo cambiar el tema: " + e.getMessage(),
                    "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_itemAlternarTemaActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

    private void abrirInternalFrame(javax.swing.JInternalFrame internalFrame) {         
        for (javax.swing.JInternalFrame abierta : desktopPane.getAllFrames()) {             
            if (abierta.getClass().equals(internalFrame.getClass())) {                 
                try {                     
                    abierta.setSelected(true);                 
                } catch (java.beans.PropertyVetoException ignored) {                 
                }                 
                return;             
            }         
        }         

        desktopPane.add(internalFrame);         

        int x = 0;
        int y = 0;
        int desktopWidth = desktopPane.getWidth();
        int desktopHeight = desktopPane.getHeight();
        int frameWidth = internalFrame.getWidth();
        int frameHeight = internalFrame.getHeight();

        if (internalFrame instanceof FrmCategoria) { 
            x = 0;
            y = 0;
        } 
        else if (internalFrame instanceof FrmProducto) { 
            x = desktopWidth - frameWidth;
            y = 0;
        } 
        else if (internalFrame instanceof FrmMovimientos) { 
            x = 0;
            y = desktopHeight - frameHeight;
        } 
        else if (internalFrame instanceof FrmStock) { 
            x = desktopWidth - frameWidth;
            y = desktopHeight - frameHeight;
        } 
        else {
            x = (desktopWidth - frameWidth) / 2;
            y = (desktopHeight - frameHeight) / 2;
        }

        internalFrame.setLocation(x, y);
        internalFrame.setVisible(true);         
        try {             
            internalFrame.setSelected(true);         
        } catch (java.beans.PropertyVetoException ignored) {         
        }     
    }

    
    private void actualizarEstadoSesion() {
        com.tienda.stockcontrol.modelo.Usuario usuario =
                com.tienda.stockcontrol.controlador.SesionUsuario.getInstancia().getUsuarioActual();
        if (usuario != null) {
            lblEstado.setText("  Conectado como: " + usuario.getNombreCompleto()
                    + " (" + usuario.getRol().name() + ")");
        }
        boolean esAdmin = com.tienda.stockcontrol.controlador.SesionUsuario.getInstancia().esAdministrador();
        itemUsuarios.setVisible(esAdmin);
    }
    
    private void verificarBajoStock() {
        try {
            java.util.List<com.tienda.stockcontrol.modelo.Producto> bajoStock =
                    new com.tienda.stockcontrol.modelo.ProductoDAO().listarBajoStock();
            if (!bajoStock.isEmpty()) {
                javax.swing.JOptionPane panelAviso = new javax.swing.JOptionPane(
                        "Hay " + bajoStock.size() + " producto(s) con stock por debajo del mínimo.\n"
                                + "Podes revisarlos en Consultar stock (Ctrl+4).",
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                javax.swing.JDialog dialogoAviso = panelAviso.createDialog(this, "Alerta de stock");
                dialogoAviso.setModal(false);

                java.awt.Rectangle limites =
                        java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
                dialogoAviso.setLocation(
                        limites.x + limites.width - dialogoAviso.getWidth() - 16,
                        limites.y + limites.height - dialogoAviso.getHeight() - 16);

                dialogoAviso.setVisible(true);
            }
        } catch (java.sql.SQLException e) {
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem itemAcerca;
    private javax.swing.JMenuItem itemAlternarTema;
    private javax.swing.JMenuItem itemCategorias;
    private javax.swing.JMenuItem itemCerrarSesion;
    private javax.swing.JMenuItem itemGraficos;
    private javax.swing.JMenuItem itemProductos;
    private javax.swing.JMenuItem itemRegistrar;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JMenuItem itemStock;
    private javax.swing.JMenuItem itemUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenu menuConsultas;
    private javax.swing.JMenu menuEstadisticas;
    private javax.swing.JMenu menuGestion;
    private javax.swing.JMenu menuMovimientos;
    private javax.swing.JMenu menuSesion;
    // End of variables declaration//GEN-END:variables
}
