
package com.tienda.stockcontrol.controlador;

/**
 *
 * @author Dell
 */
public class LoginController {
    private final com.tienda.stockcontrol.modelo.UsuarioDAO usuarioDAO = new com.tienda.stockcontrol.modelo.UsuarioDAO();
    private final com.tienda.stockcontrol.vista.FrmLogin vista;

    public LoginController(com.tienda.stockcontrol.vista.FrmLogin vista) {
        this.vista = vista;
    }

    public void autenticar(String username, String passwordTexto) {
        if (username == null || username.trim().isEmpty()
                || passwordTexto == null || passwordTexto.isEmpty()) {
            vista.mostrarError("Debe ingresar usuario y contraseña.");
            return;
        }

        try {
            com.tienda.stockcontrol.modelo.Usuario usuario = usuarioDAO.buscarPorUsername(username.trim());

            if (usuario == null) {
                vista.mostrarError("Usuario o contraseña incorrectos.");
                return;
            }
            if (!usuario.isActivo()) {
                vista.mostrarError("El usuario esta inactivo. Consulte al administrador.");
                return;
            }
            boolean coincide = com.tienda.stockcontrol.modelo.PasswordUtil.verificar(
                    passwordTexto, usuario.getSalt(), usuario.getPasswordHash());
            if (!coincide) {
                vista.mostrarError("Usuario o contraseña incorrectos.");
                return;
            }

            com.tienda.stockcontrol.controlador.SesionUsuario.getInstancia().iniciarSesion(usuario);
            vista.loginExitoso();
        } catch (java.sql.SQLException e) {
            vista.mostrarError("Error de conexion: " + e.getMessage());
        }
    }
}
