
package com.tienda.stockcontrol.vista;

/**
 *
 * @author Dell
 */
public class FrmStock extends javax.swing.JInternalFrame {

    private final com.tienda.stockcontrol.controlador.ControladorStock controlador =
            new com.tienda.stockcontrol.controlador.ControladorStock(this);
    private javax.swing.table.DefaultTableModel modeloTabla;
    
    public FrmStock() {
        initComponents();
        
        modeloTabla = new javax.swing.table.DefaultTableModel(
                new Object[]{"ID", "Nombre", "Categoria", "Precio", "Stock actual", "Stock minimo", "Estado"}, 0) {
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
        tabla.setDefaultRenderer(Object.class, new EstadoRenderer());

        controlador.actualizar(false);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFiltros = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        checkSoloBajoStock = new javax.swing.JCheckBox();
        btnActualizar = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        lblTotales = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consulta de stock");
        setFrameIcon(null);
        setMinimumSize(new java.awt.Dimension(778, 258));

        panelFiltros.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));

        lblNombre.setText("Nombre:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        checkSoloBajoStock.setText("Ver solo productos con bajo stock");
        checkSoloBajoStock.addActionListener(this::checkSoloBajoStockActionPerformed);

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        javax.swing.GroupLayout panelFiltrosLayout = new javax.swing.GroupLayout(panelFiltros);
        panelFiltros.setLayout(panelFiltrosLayout);
        panelFiltrosLayout.setHorizontalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFiltrosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkSoloBajoStock)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnActualizar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFiltrosLayout.setVerticalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrosLayout.createSequentialGroup()
                .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(checkSoloBajoStock)
                    .addComponent(btnActualizar))
                .addGap(0, 13, Short.MAX_VALUE))
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
            .addComponent(panelFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollTabla)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        controlador.buscar(txtBuscar.getText(), checkSoloBajoStock.isSelected());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void checkSoloBajoStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSoloBajoStockActionPerformed
        controlador.actualizar(checkSoloBajoStock.isSelected());
    }//GEN-LAST:event_checkSoloBajoStockActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        txtBuscar.setText("");
        checkSoloBajoStock.setSelected(false);
        controlador.actualizar(false);
    }//GEN-LAST:event_btnActualizarActionPerformed

    public void mostrarProductos(java.util.List<com.tienda.stockcontrol.modelo.Producto> productos) {
        modeloTabla.setRowCount(0);
        int totalUnidades = 0;
        int cantidadBajoStock = 0;
        for (com.tienda.stockcontrol.modelo.Producto p : productos) {
            String estado = p.isBajoStock() ? "BAJO STOCK" : "OK";
            modeloTabla.addRow(new Object[]{
                p.getIdProducto(), p.getNombre(), p.getCategoria().getNombre(),
                p.getPrecio(), p.getStockActual(), p.getStockMinimo(), estado
            });
            totalUnidades += p.getStockActual();
            if (p.isBajoStock()) {
                cantidadBajoStock++;
            }
        }
        lblTotales.setText(String.format(
                "  Productos listados: %d | Unidades totales: %d | Con bajo stock: %d",
                productos.size(), totalUnidades, cantidadBajoStock));
    }

    public void mostrarError(Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this,
                "Ocurrio un error: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    private class EstadoRenderer extends javax.swing.table.DefaultTableCellRenderer {
        @Override
        public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            java.awt.Component c = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
            try {
                String estado = (String) table.getValueAt(row, 6);
                if (!isSelected) {
                    if ("BAJO STOCK".equals(estado)) {
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
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JCheckBox checkSoloBajoStock;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTotales;
    private javax.swing.JPanel panelFiltros;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
