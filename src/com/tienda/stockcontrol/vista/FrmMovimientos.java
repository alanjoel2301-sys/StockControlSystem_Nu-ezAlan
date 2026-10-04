
package com.tienda.stockcontrol.vista;

/**
 *
 * @author Dell
 */
public class FrmMovimientos extends javax.swing.JInternalFrame {

    private static final java.time.format.DateTimeFormatter FORMATO_FECHA =
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final com.tienda.stockcontrol.controlador.ControladorMovimientos controlador =
            new com.tienda.stockcontrol.controlador.ControladorMovimientos(this);
    private javax.swing.table.DefaultTableModel modeloHistorial;
    
    public FrmMovimientos() {
        initComponents();
        
        modeloHistorial = new javax.swing.table.DefaultTableModel(
                new Object[]{"Fecha", "Producto", "Tipo", "Cantidad", "Motivo", "Usuario"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 3 ? Integer.class : String.class;
            }
        };
        tablaHistorial.setModel(modeloHistorial);

        txtUsuario.setText(com.tienda.stockcontrol.controlador.SesionUsuario.getInstancia()
                .getUsuarioActual().getNombreCompleto());
        txtUsuario.setEditable(false);

        controlador.cargarCombo();
        controlador.cargarHistorial();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelFormulario = new javax.swing.JPanel();
        lblProducto = new javax.swing.JLabel();
        comboProducto = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        radioEntrada = new javax.swing.JRadioButton();
        radioSalida = new javax.swing.JRadioButton();
        lblCantidad = new javax.swing.JLabel();
        spinnerCantidad = new javax.swing.JSpinner();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblMotivo = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        scrollHistorial = new javax.swing.JScrollPane();
        tablaHistorial = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Movimientos de Stock");
        setFrameIcon(null);
        setMinimumSize(new java.awt.Dimension(444, 370));

        panelFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder("Registrar Movimiento"));

        lblProducto.setText("Producto:");

        jLabel1.setText("Tipo:");

        buttonGroup1.add(radioEntrada);
        radioEntrada.setSelected(true);
        radioEntrada.setText("Entrada");

        buttonGroup1.add(radioSalida);
        radioSalida.setText("Salida");

        lblCantidad.setText("Cantidad:");

        spinnerCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        lblUsuario.setText("Usuario:");

        lblMotivo.setText("Motivo:");

        btnRegistrar.setText("Registrar movimiento");
        btnRegistrar.addActionListener(this::btnRegistrarActionPerformed);

        javax.swing.GroupLayout panelFormularioLayout = new javax.swing.GroupLayout(panelFormulario);
        panelFormulario.setLayout(panelFormularioLayout);
        panelFormularioLayout.setHorizontalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormularioLayout.createSequentialGroup()
                        .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblCantidad)
                            .addComponent(lblProducto)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFormularioLayout.createSequentialGroup()
                                .addComponent(radioEntrada)
                                .addGap(18, 18, 18)
                                .addComponent(radioSalida))
                            .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(comboProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelFormularioLayout.createSequentialGroup()
                                    .addComponent(spinnerCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblUsuario)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelFormularioLayout.createSequentialGroup()
                            .addComponent(lblMotivo, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFormularioLayout.setVerticalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProducto)
                    .addComponent(comboProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(radioEntrada)
                    .addComponent(radioSalida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantidad)
                    .addComponent(spinnerCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMotivo)
                    .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegistrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollHistorial.setBorder(javax.swing.BorderFactory.createTitledBorder("Historial de movimientos"));

        tablaHistorial.setAutoCreateRowSorter(true);
        tablaHistorial.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollHistorial.setViewportView(tablaHistorial);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollHistorial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        controlador.registrar(
                (com.tienda.stockcontrol.modelo.Producto) comboProducto.getSelectedItem(),
                radioEntrada.isSelected()
                        ? com.tienda.stockcontrol.modelo.TipoMovimiento.ENTRADA
                        : com.tienda.stockcontrol.modelo.TipoMovimiento.SALIDA,
                (Integer) spinnerCantidad.getValue(),
                txtMotivo.getText());
    }//GEN-LAST:event_btnRegistrarActionPerformed

    public void mostrarProductosEnCombo(java.util.List<com.tienda.stockcontrol.modelo.Producto> productos) {
        comboProducto.setModel(new javax.swing.DefaultComboBoxModel<>(
                productos.toArray(new com.tienda.stockcontrol.modelo.Producto[0])));
    }

    public void mostrarHistorial(java.util.List<com.tienda.stockcontrol.modelo.MovimientoStock> movimientos) {
        modeloHistorial.setRowCount(0);
        for (com.tienda.stockcontrol.modelo.MovimientoStock m : movimientos) {
            modeloHistorial.addRow(new Object[]{
                m.getFecha().format(FORMATO_FECHA),
                m.getProducto().getNombre(),
                m.getTipo() == com.tienda.stockcontrol.modelo.TipoMovimiento.ENTRADA ? "Entrada" : "Salida",
                m.getCantidad(),
                m.getMotivo(),
                m.getUsuario()
            });
        }
    }

    public void limpiarFormulario() {
        txtMotivo.setText("");
        spinnerCantidad.setValue(1);
    }

    public void mostrarMensaje(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(this, mensaje);
    }

    public void mostrarAviso(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(this, mensaje, "No se pudo registrar",
                javax.swing.JOptionPane.WARNING_MESSAGE);
    }

    public void mostrarError(Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this,
                "Ocurrio un error: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<com.tienda.stockcontrol.modelo.Producto> comboProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblMotivo;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JRadioButton radioEntrada;
    private javax.swing.JRadioButton radioSalida;
    private javax.swing.JScrollPane scrollHistorial;
    private javax.swing.JSpinner spinnerCantidad;
    private javax.swing.JTable tablaHistorial;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
