
package com.tienda.stockcontrol.vista;

/**
 *
 * @author Dell
 */
public class FrmUsuario extends javax.swing.JInternalFrame {

    private final com.tienda.stockcontrol.controlador.ControladorUsuario controlador =
            new com.tienda.stockcontrol.controlador.ControladorUsuario(this);
    private javax.swing.table.DefaultTableModel modeloTabla;
    private char echoCharOriginal;
    
    public FrmUsuario() {
        initComponents();
        echoCharOriginal = txtPassword.getEchoChar();
        modeloTabla = new javax.swing.table.DefaultTableModel(
                new Object[]{"ID", "Usuario", "Nombre completo", "Rol", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Integer.class : String.class;
            }
        };
        tabla.setModel(modeloTabla);
        tabla.getSelectionModel().addListSelectionListener(evt -> {
            if (!evt.getValueIsAdjusting() && tabla.getSelectedRow() != -1) {
                int idUsuario = (Integer) modeloTabla.getValueAt(tabla.getSelectedRow(), 0);
                controlador.seleccionarFila(idUsuario);
            }
        });

        comboRol.setModel(new javax.swing.DefaultComboBoxModel<>(com.tienda.stockcontrol.modelo.Rol.values()));

        controlador.cargarTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        panelFormulario = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        txtNombreCompleto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        comboRol = new javax.swing.JComboBox<>();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnCambiarEstado = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnResetPassword = new javax.swing.JButton();
        checkMostrarPassword = new javax.swing.JCheckBox();
        scrollTabla = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        jCheckBox1.setText("jCheckBox1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestion de usuarios");
        setFrameIcon(null);
        setMinimumSize(new java.awt.Dimension(567, 493));

        panelFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del usuario"));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Usuario:");

        jLabel2.setText("Contraseña:");

        jLabel3.setText("Nombre completo:");

        txtNombreCompleto.addActionListener(this::txtNombreCompletoActionPerformed);

        jLabel4.setText("Rol:");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(this::btnAgregarActionPerformed);

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(this::btnModificarActionPerformed);

        btnCambiarEstado.setText("Cambiar estado");
        btnCambiarEstado.addActionListener(this::btnCambiarEstadoActionPerformed);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        btnResetPassword.setText("Reestablecer contraseña");
        btnResetPassword.addActionListener(this::btnResetPasswordActionPerformed);

        checkMostrarPassword.addActionListener(this::checkMostrarPasswordActionPerformed);

        javax.swing.GroupLayout panelFormularioLayout = new javax.swing.GroupLayout(panelFormulario);
        panelFormulario.setLayout(panelFormularioLayout);
        panelFormularioLayout.setHorizontalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormularioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiar))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPassword)
                            .addComponent(txtNombreCompleto)
                            .addComponent(txtUsername)
                            .addComponent(comboRol, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnResetPassword)
                            .addComponent(btnCambiarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelFormularioLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(checkMostrarPassword)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFormularioLayout.setVerticalGroup(
            panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkMostrarPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(comboRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCambiarEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnResetPassword))
                .addContainerGap(16, Short.MAX_VALUE))
        );

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
            .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(scrollTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkMostrarPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkMostrarPasswordActionPerformed
        txtPassword.setEchoChar(checkMostrarPassword.isSelected() ? (char) 0 : echoCharOriginal);
    }//GEN-LAST:event_checkMostrarPasswordActionPerformed

    private void btnResetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPasswordActionPerformed
        if (!controlador.haySeleccion()) {
            mostrarAviso("Seleccione un usuario de la tabla.");
            return;
        }
        javax.swing.JPasswordField campoNuevaPassword = new javax.swing.JPasswordField();
        int resultado = javax.swing.JOptionPane.showConfirmDialog(this, campoNuevaPassword,
            "Ingrese la nueva contraseña", javax.swing.JOptionPane.OK_CANCEL_OPTION);
        if (resultado == javax.swing.JOptionPane.OK_OPTION) {
            controlador.restablecerContrasena(new String(campoNuevaPassword.getPassword()));
        }
    }//GEN-LAST:event_btnResetPasswordActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        controlador.limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnCambiarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarEstadoActionPerformed
        controlador.cambiarEstado();
    }//GEN-LAST:event_btnCambiarEstadoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        controlador.modificar(txtNombreCompleto.getText(), (com.tienda.stockcontrol.modelo.Rol) comboRol.getSelectedItem());
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        controlador.agregar(txtUsername.getText(), new String(txtPassword.getPassword()),
            txtNombreCompleto.getText(), (com.tienda.stockcontrol.modelo.Rol) comboRol.getSelectedItem());
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtNombreCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreCompletoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCompletoActionPerformed

    public void mostrarUsuarios(java.util.List<com.tienda.stockcontrol.modelo.Usuario> usuarios) {
        modeloTabla.setRowCount(0);
        for (com.tienda.stockcontrol.modelo.Usuario u : usuarios) {
            modeloTabla.addRow(new Object[]{
                u.getIdUsuario(), u.getUsername(), u.getNombreCompleto(),
                u.getRol().name(), u.isActivo() ? "Activo" : "Inactivo"
            });
        }
    }

    public void mostrarEnFormulario(com.tienda.stockcontrol.modelo.Usuario usuario) {
        txtUsername.setText(usuario.getUsername());
        txtUsername.setEnabled(false);
        txtPassword.setText("");
        txtPassword.setEnabled(false);
        txtNombreCompleto.setText(usuario.getNombreCompleto());
        comboRol.setSelectedItem(usuario.getRol());
    }

    public void limpiarFormulario() {
        txtUsername.setText("");
        txtUsername.setEnabled(true);
        txtPassword.setText("");
        txtPassword.setEnabled(true);
        txtPassword.setEchoChar(echoCharOriginal);
        checkMostrarPassword.setSelected(false);
        txtNombreCompleto.setText("");
        comboRol.setSelectedIndex(0);
        tabla.clearSelection();
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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCambiarEstado;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnResetPassword;
    private javax.swing.JCheckBox checkMostrarPassword;
    private javax.swing.JComboBox<com.tienda.stockcontrol.modelo.Rol> comboRol;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
