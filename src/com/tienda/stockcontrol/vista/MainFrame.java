
package com.tienda.stockcontrol.vista;

/**
 *
 * @author Dell
 */

public class MainFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());
    // Tiempo de espera antes de volver a recordar: 5 minutos.
    private static final int MINUTOS_RECORDATORIO_STOCK = 5;

    // Temporizador para programar el próximo aviso.
    private javax.swing.Timer temporizadorAlertaStock;

    // Diálogo de alerta que esté abierto, si existe.
    private javax.swing.JDialog dialogoAlertaStock;

    // Si es true, no se mostrarán más alertas durante esta ejecución.
    private boolean noRecordarStockDuranteSesion = false;
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
        itemExportarPdf = new javax.swing.JMenuItem();
        menuEstadisticas = new javax.swing.JMenu();
        itemGraficos = new javax.swing.JMenuItem();
        menuAyuda = new javax.swing.JMenu();
        itemGuiaUsuario = new javax.swing.JMenuItem();
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

        itemExportarPdf.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemExportarPdf.setText("Exportar a PDF");
        itemExportarPdf.addActionListener(this::itemExportarPdfActionPerformed);
        menuConsultas.add(itemExportarPdf);

        jMenuBar1.add(menuConsultas);

        menuEstadisticas.setText("Estadisticas");

        itemGraficos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemGraficos.setText("Ver graficos");
        itemGraficos.addActionListener(this::itemGraficosActionPerformed);
        menuEstadisticas.add(itemGraficos);

        jMenuBar1.add(menuEstadisticas);

        menuAyuda.setText("Ayuda");

        itemGuiaUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        itemGuiaUsuario.setText("Guia de usuario");
        itemGuiaUsuario.addActionListener(this::itemGuiaUsuarioActionPerformed);
        menuAyuda.add(itemGuiaUsuario);

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
        javax.swing.SwingUtilities.invokeLater(com.tienda.stockcontrol.controlador.Main::mostrarLoginYAbrirPrincipal);
    }//GEN-LAST:event_itemCerrarSesionActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void itemAlternarTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAlternarTemaActionPerformed
        try {
            java.util.prefs.Preferences prefs = java.util.prefs.Preferences.userNodeForPackage(
                    com.tienda.stockcontrol.controlador.Main.class);
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

    private void itemGuiaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGuiaUsuarioActionPerformed

    if (!java.awt.Desktop.isDesktopSupported()) {
        javax.swing.JOptionPane.showMessageDialog(
                this,
                "No se puede abrir el PDF en este equipo.",
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    try (java.io.InputStream recurso =
            getClass().getResourceAsStream(
                "/com/tienda/stockcontrol/vista/Guia_de_Usuario_StockControlSystem.pdf")) {

        if (recurso == null) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "No se encontró la guía de usuario.",
                    "Archivo no encontrado",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        java.nio.file.Path archivoTemporal =
                java.nio.file.Files.createTempFile(
                        "Guia_de_Usuario_", ".pdf");

        java.nio.file.Files.copy(
                recurso,
                archivoTemporal,
                java.nio.file.StandardCopyOption.REPLACE_EXISTING
        );

        java.awt.Desktop.getDesktop().open(
                archivoTemporal.toFile()
        );

    } catch (java.io.IOException
            | java.lang.UnsupportedOperationException ex) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "No se pudo abrir la guía de usuario:\n"
                        + ex.getMessage(),
                "Error al abrir la guía",
                javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_itemGuiaUsuarioActionPerformed

    private void itemExportarPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExportarPdfActionPerformed
        ExportadorPdf.exportarVentana(this, desktopPane.getSelectedFrame());
    }//GEN-LAST:event_itemExportarPdfActionPerformed

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


private void abrirInternalFrame(
        javax.swing.JInternalFrame internalFrame) {

    // 1. Si esta ventana ya está abierta, seleccionarla.
    for (javax.swing.JInternalFrame abierta
            : desktopPane.getAllFrames()) {

        if (abierta.getClass().equals(
                internalFrame.getClass())) {

            try {
                abierta.setIcon(false);
                abierta.setSelected(true);
                abierta.toFront();
            } catch (java.beans.PropertyVetoException ignored) {
            }

            return;
        }
    }

    // 2. Agregar la nueva ventana al DesktopPane.
    desktopPane.add(internalFrame);

    int anchoDesktop = desktopPane.getWidth();
    int altoDesktop = desktopPane.getHeight();

    int anchoVentana = internalFrame.getWidth();
    int altoVentana = internalFrame.getHeight();

    // Evitar cálculos negativos si una ventana es
    // más grande que el espacio disponible.
    int xDerecha = Math.max(
            0, anchoDesktop - anchoVentana);

    int yAbajo = Math.max(
            0, altoDesktop - altoVentana);

    int xCentro = Math.max(
            0, (anchoDesktop - anchoVentana) / 2);

    int yCentro = Math.max(
            0, (altoDesktop - altoVentana) / 2);

    // 3. Definir las cinco posiciones en orden.
    int[][] posiciones = {
        {0, 0},                         // 1. Superior izquierda
        {xDerecha, 0},                  // 2. Superior derecha
        {0, yAbajo},                    // 3. Inferior izquierda
        {xDerecha, yAbajo},             // 4. Inferior derecha
        {xCentro, yCentro}              // 5. Centro
    };

    // 4. Buscar la primera posición libre.
    int posicionElegida = -1;

    for (int i = 0; i < posiciones.length; i++) {

        boolean ocupada = false;

        for (javax.swing.JInternalFrame abierta
                : desktopPane.getAllFrames()) {

            Object propiedad =
                    abierta.getClientProperty("posicionVentana");

            if (propiedad instanceof Integer
                    && ((Integer) propiedad) == i) {

                ocupada = true;
                break;
            }
        }

        if (!ocupada) {
            posicionElegida = i;
            break;
        }
    }

    // 5. Ubicar la ventana en la primera posición libre.
    if (posicionElegida != -1) {

        internalFrame.putClientProperty(
                "posicionVentana", posicionElegida);

        internalFrame.setLocation(
                posiciones[posicionElegida][0],
                posiciones[posicionElegida][1]);

    } else {

        // Si las cinco posiciones están ocupadas,
        // abrir las ventanas adicionales en cascada
        // alrededor del centro.
        int cantidadVentanas =
                desktopPane.getAllFrames().length;

        int desplazamiento =
                (cantidadVentanas - 5) * 25;

        int x = Math.min(
                xCentro + desplazamiento,
                xDerecha);

        int y = Math.min(
                yCentro + desplazamiento,
                yAbajo);

        internalFrame.putClientProperty(
                "posicionVentana", -1);

        internalFrame.setLocation(x, y);
    }

    // 6. Mostrar y seleccionar la nueva ventana.
    internalFrame.setVisible(true);

    try {
        internalFrame.setSelected(true);
        internalFrame.toFront();
    } catch (java.beans.PropertyVetoException ignored) {
    }

    desktopPane.revalidate();
    desktopPane.repaint();
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

    // Si el usuario eligió "No recordar", no mostrar más
    // alertas durante esta ejecución del programa.
    if (noRecordarStockDuranteSesion) {
        return;
    }

    // Evitar abrir una segunda alerta mientras ya hay una abierta.
    if (dialogoAlertaStock != null
            && dialogoAlertaStock.isDisplayable()) {
        return;
    }

    try {
        // Consultar nuevamente el estado actual de la base de datos.
        java.util.List<com.tienda.stockcontrol.modelo.Producto> bajoStock =
                new com.tienda.stockcontrol.modelo.ProductoDAO()
                        .listarBajoStock();

        // Si ya no hay productos con stock bajo, no mostrar nada.
        if (bajoStock.isEmpty()) {
            return;
        }

        String mensaje = "Hay " + bajoStock.size()
                + " producto(s) con stock en el mínimo o por debajo.\n"
                + "Podés revisarlos en Consultar stock (Ctrl+4).\n\n"
                + "¿Qué querés hacer con este recordatorio?";

        Object[] opciones = {
            "Recordar más tarde",
            "No recordar"
        };

        javax.swing.JOptionPane panelAviso =
                new javax.swing.JOptionPane(
                        mensaje,
                        javax.swing.JOptionPane.WARNING_MESSAGE,
                        javax.swing.JOptionPane.DEFAULT_OPTION,
                        null,
                        opciones,
                        opciones[0]
                );

        javax.swing.JDialog dialogo =
                panelAviso.createDialog(this, "Alerta de stock");

        dialogoAlertaStock = dialogo;

        // Mantener el aviso no modal, como el original.
        dialogo.setModal(false);

        // Posicionar el aviso abajo a la derecha de la pantalla.
        java.awt.Rectangle limites =
                java.awt.GraphicsEnvironment
                        .getLocalGraphicsEnvironment()
                        .getMaximumWindowBounds();

        dialogo.setLocation(
                limites.x + limites.width
                        - dialogo.getWidth() - 16,
                limites.y + limites.height
                        - dialogo.getHeight() - 16
        );

        // Detectar la elección del usuario.
        panelAviso.addPropertyChangeListener(event -> {

            if (!javax.swing.JOptionPane.VALUE_PROPERTY.equals(
                    event.getPropertyName())) {
                return;
            }

            Object seleccion = panelAviso.getValue();

            // Ignorar el valor inicial del JOptionPane.
            if (seleccion == null
                    || seleccion == javax.swing.JOptionPane.UNINITIALIZED_VALUE) {
                return;
            }

            // Cerrar el diálogo y liberar la referencia.
            dialogo.dispose();

            if (dialogoAlertaStock == dialogo) {
                dialogoAlertaStock = null;
            }

            // Recordar más tarde.
            if ("Recordar más tarde".equals(seleccion)) {

                // Detener un temporizador anterior, si existiera.
                if (temporizadorAlertaStock != null) {
                    temporizadorAlertaStock.stop();
                }

                // Programar una nueva comprobación dentro de 5 minutos.
                temporizadorAlertaStock = new javax.swing.Timer(
                        MINUTOS_RECORDATORIO_STOCK * 60 * 1000,
                        e -> {
                            temporizadorAlertaStock.stop();
                            temporizadorAlertaStock = null;

                            // Volver a consultar la base de datos.
                            verificarBajoStock();
                        }
                );

                temporizadorAlertaStock.setRepeats(false);
                temporizadorAlertaStock.start();

            } else if ("No recordar".equals(seleccion)) {

                // No volver a avisar durante esta ejecución.
                noRecordarStockDuranteSesion = true;

                // Cancelar cualquier recordatorio pendiente.
                if (temporizadorAlertaStock != null) {
                    temporizadorAlertaStock.stop();
                    temporizadorAlertaStock = null;
                }
            }

            // Restablecer el valor para evitar reutilizar la elección.
            panelAviso.setValue(
                    javax.swing.JOptionPane.UNINITIALIZED_VALUE
            );
        });

        // Si el usuario cierra con la X, liberar el diálogo.
        dialogo.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                if (dialogoAlertaStock == dialogo) {
                    dialogoAlertaStock = null;
                }
            }
        });

        dialogo.setVisible(true);

    } catch (java.sql.SQLException e) {

        logger.log(
                java.util.logging.Level.WARNING,
                "No se pudo verificar el stock bajo",
                e
        );
    }
}

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem itemAcerca;
    private javax.swing.JMenuItem itemAlternarTema;
    private javax.swing.JMenuItem itemCategorias;
    private javax.swing.JMenuItem itemCerrarSesion;
    private javax.swing.JMenuItem itemExportarPdf;
    private javax.swing.JMenuItem itemGraficos;
    private javax.swing.JMenuItem itemGuiaUsuario;
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
