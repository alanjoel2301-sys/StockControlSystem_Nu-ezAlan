
package com.tienda.stockcontrol.vista;

/**
 *
 * @author Dell
 */
public class FrmProducto extends javax.swing.JInternalFrame {

    private final com.tienda.stockcontrol.controlador.ControladorProducto controlador =
            new com.tienda.stockcontrol.controlador.ControladorProducto(this);
    private javax.swing.table.DefaultTableModel modeloTabla;
    
    public FrmProducto() {
        initComponents();
        modeloTabla = new javax.swing.table.DefaultTableModel(
                new Object[]{"ID", "Nombre", "Categoria", "Precio", "Stock", "Stock min."}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0: return Integer.class;
                    case 3: return java.math.BigDecimal.class;
                    case 4: return Integer.class;
                    case 5: return Integer.class;
                    default: return String.class;
                }
            }
        };
        tabla.setModel(modeloTabla);
        tabla.setDefaultRenderer(Object.class, new BajoStockRenderer());
        tabla.getSelectionModel().addListSelectionListener(evt -> {
            if (!evt.getValueIsAdjusting() && tabla.getSelectedRow() != -1) {
                int filaModelo = tabla.convertRowIndexToModel(tabla.getSelectedRow());
                int idProducto = (Integer) modeloTabla.getValueAt(filaModelo, 0);
                controlador.seleccionarFila(idProducto);
            }
        });
        controlador.cargarCombo();
        controlador.cargarTabla();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFormulario = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        lblCategoria = new javax.swing.JLabel();
        comboCategoria = new javax.swing.JComboBox<>();
        lblPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblStockInicial = new javax.swing.JLabel();
        spinnerStockInicial = new javax.swing.JSpinner();
        lblStockMinimo = new javax.swing.JLabel();
        spinnerStockMinimo = new javax.swing.JSpinner();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnVerHistorial = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Productos");
        setFrameIcon(null);
        setMinimumSize(new java.awt.Dimension(479, 388));

        panelFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del producto"));

        lblNombre.setText("Nombre:");

        lblDescripcion.setText("Descripcion:");

        lblCategoria.setText("Categoria:");

        lblPrecio.setText("Precio:");

        lblStockInicial.setText("Stock Inicial:");

        spinnerStockInicial.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        lblStockMinimo.setText("Stock Minimo:");

        spinnerStockMinimo.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(this::btnAgregarActionPerformed);

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(this::btnModificarActionPerformed);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        btnVerHistorial.setText("Historial");
        btnVerHistorial.addActionListener(this::btnVerHistorialActionPerformed);

        javax.swing.GroupLayout panelFormularioLayout = new javax.swing.GroupLayout(panelFormulario);
        panelFormulario.setLayout(panelFormularioLayout);
        panelFormularioLayout.setHorizontalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar)
                        .addGap(18, 18, 18)
                        .addComponent(btnVerHistorial))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDescripcion)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelFormularioLayout.createSequentialGroup()
                            .addComponent(lblStockInicial)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(spinnerStockInicial)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblStockMinimo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(spinnerStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelFormularioLayout.createSequentialGroup()
                            .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPrecio))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelFormularioLayout.createSequentialGroup()
                            .addComponent(lblCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFormularioLayout.setVerticalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescripcion)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategoria)
                    .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStockInicial)
                    .addComponent(spinnerStockInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStockMinimo)
                    .addComponent(spinnerStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnVerHistorial))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabla.setAutoCreateRowSorter(true);
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollTabla.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        controlador.agregar(txtNombre.getText(), txtDescripcion.getText(),
                (com.tienda.stockcontrol.modelo.Categoria) comboCategoria.getSelectedItem(),
                txtPrecio.getText(), (Integer) spinnerStockInicial.getValue(), (Integer) spinnerStockMinimo.getValue());
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        controlador.modificar(txtNombre.getText(), txtDescripcion.getText(),
                (com.tienda.stockcontrol.modelo.Categoria) comboCategoria.getSelectedItem(),
                txtPrecio.getText(), (Integer) spinnerStockInicial.getValue(), (Integer) spinnerStockMinimo.getValue());
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        controlador.eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        controlador.limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnVerHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerHistorialActionPerformed
        int filaVista = tabla.getSelectedRow();
        if (filaVista == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla.",
                    "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        int filaModelo = tabla.convertRowIndexToModel(filaVista);
        int idProducto = (Integer) modeloTabla.getValueAt(filaModelo, 0);
        String nombreProducto = (String) modeloTabla.getValueAt(filaModelo, 1);
        controlador.verHistorial(idProducto, nombreProducto);
    }//GEN-LAST:event_btnVerHistorialActionPerformed

    public void mostrarProductos(java.util.List<com.tienda.stockcontrol.modelo.Producto> productos) {
        modeloTabla.setRowCount(0);
        for (com.tienda.stockcontrol.modelo.Producto p : productos) {
            modeloTabla.addRow(new Object[]{
                p.getIdProducto(), p.getNombre(), p.getCategoria().getNombre(),
                p.getPrecio(), p.getStockActual(), p.getStockMinimo()
            });
        }
    }

    public void mostrarCategoriasEnCombo(java.util.List<com.tienda.stockcontrol.modelo.Categoria> categorias) {
        comboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(
                categorias.toArray(new com.tienda.stockcontrol.modelo.Categoria[0])));
    }

    public void mostrarEnFormulario(com.tienda.stockcontrol.modelo.Producto p) {
        txtNombre.setText(p.getNombre());
        txtDescripcion.setText(p.getDescripcion());
        comboCategoria.setSelectedItem(p.getCategoria());
        txtPrecio.setText(p.getPrecio().toPlainString());
        spinnerStockInicial.setValue(p.getStockActual());
        spinnerStockInicial.setEnabled(false);
        spinnerStockMinimo.setValue(p.getStockMinimo());
    }

    public void limpiarFormulario() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        spinnerStockInicial.setValue(0);
        spinnerStockInicial.setEnabled(true);
        spinnerStockMinimo.setValue(0);
        tabla.clearSelection();
    }
   
    public void mostrarHistorialProducto(String nombreProducto,
            java.util.List<com.tienda.stockcontrol.modelo.MovimientoStock> movimientos) {
        com.tienda.stockcontrol.vista.HistorialProductoDialog dialogo =
                new com.tienda.stockcontrol.vista.HistorialProductoDialog(null, false);
        dialogo.mostrarHistorial(nombreProducto, movimientos);
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }

    public void mostrarMensaje(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(this, mensaje);
    }

    public void mostrarAviso(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(this, mensaje, "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
    }

    public void mostrarError(Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this,
                "Ocurrio un error: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    public boolean confirmarEliminacion() {
        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Seguro que desea eliminar el producto seleccionado?\n"
                        + "(No se podra eliminar si tiene movimientos registrados)",
                "Confirmar eliminacion", javax.swing.JOptionPane.YES_NO_OPTION);
        return confirmacion == javax.swing.JOptionPane.YES_OPTION;
    }
    
    private class BajoStockRenderer extends javax.swing.table.DefaultTableCellRenderer {
        @Override
        public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            java.awt.Component c = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
            try {
                int stock = (int) table.getValueAt(row, 4);
                int stockMinimo = (int) table.getValueAt(row, 5);
                if (!isSelected) {
                    if (stock <= stockMinimo) {
                        if (com.formdev.flatlaf.FlatLaf.isLafDark()) {
                            c.setBackground(new java.awt.Color(107, 58, 58));
                            c.setForeground(java.awt.Color.WHITE);
                        } else {
                            c.setBackground(new java.awt.Color(255, 205, 205));
                            c.setForeground(java.awt.Color.BLACK);
                        }
                    } else {
                        c.setBackground(table.getBackground());
                        c.setForeground(table.getForeground());
                    }
                }
            } catch (Exception ignored) {
            }
            return c;
        }
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnVerHistorial;
    private javax.swing.JComboBox<com.tienda.stockcontrol.modelo.Categoria> comboCategoria;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblStockInicial;
    private javax.swing.JLabel lblStockMinimo;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JSpinner spinnerStockInicial;
    private javax.swing.JSpinner spinnerStockMinimo;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
