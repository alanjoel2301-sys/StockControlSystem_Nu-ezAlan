
package com.tienda.stockcontrol.controlador;

/**
 *
 * @author Dell
 */
public class ControladorUsuario {
    private final com.tienda.stockcontrol.modelo.UsuarioDAO usuarioDAO = new com.tienda.stockcontrol.modelo.UsuarioDAO();
    private final com.tienda.stockcontrol.vista.FrmUsuario vista;
    private Integer idSeleccionado = null;
    private boolean activoSeleccionado = true;
    private com.tienda.stockcontrol.modelo.Rol rolSeleccionado = null;

    public ControladorUsuario(com.tienda.stockcontrol.vista.FrmUsuario vista) {
        this.vista = vista;
    }

    public void cargarTabla() {
        try {
            java.util.List<com.tienda.stockcontrol.modelo.Usuario> usuarios = usuarioDAO.listarTodos();
            vista.mostrarUsuarios(usuarios);
        } catch (java.sql.SQLException e) {
            vista.mostrarError(e);
        }
    }

    public void seleccionarFila(int idUsuario) {
        try {
            com.tienda.stockcontrol.modelo.Usuario usuario = usuarioDAO.buscarPorId(idUsuario);
            if (usuario == null) {
                return;
            }
            idSeleccionado = usuario.getIdUsuario();
            activoSeleccionado = usuario.isActivo();
            rolSeleccionado = usuario.getRol();
            vista.mostrarEnFormulario(usuario);
        } catch (java.sql.SQLException e) {
            vista.mostrarError(e);
        }
    }

    public void agregar(String username, String passwordTexto, String nombreCompleto,
            com.tienda.stockcontrol.modelo.Rol rol) {
        if (!tienePermiso()) {
            return;
        }
        if (username == null || username.trim().isEmpty()) {
            vista.mostrarAviso("El usuario es obligatorio.");
            return;
        }
        if (passwordTexto == null || passwordTexto.isEmpty()) {
            vista.mostrarAviso("La contraseña es obligatoria.");
            return;
        }
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            vista.mostrarAviso("El nombre completo es obligatorio.");
            return;
        }
        if (rol == null) {
            vista.mostrarAviso("Debe seleccionar un rol.");
            return;
        }

        String salt = com.tienda.stockcontrol.modelo.PasswordUtil.generarSalt();
        String hash = com.tienda.stockcontrol.modelo.PasswordUtil.calcularHash(passwordTexto, salt);
        com.tienda.stockcontrol.modelo.Usuario usuario = new com.tienda.stockcontrol.modelo.Usuario(
                username.trim(), hash, salt, nombreCompleto.trim(), rol, true);

        try {
            usuarioDAO.insertar(usuario);
            vista.mostrarMensaje("Usuario creado correctamente.");
            limpiarEstado();
            cargarTabla();
        } catch (java.sql.SQLException e) {
            if (e.getErrorCode() == 1062) {
                vista.mostrarAviso("Ese nombre de usuario ya existe.");
            } else {
                vista.mostrarError(e);
            }
        }
    }

    public void modificar(String nombreCompleto, com.tienda.stockcontrol.modelo.Rol rol) {
        if (!tienePermiso()) {
            return;
        }
        if (idSeleccionado == null) {
            vista.mostrarAviso("Seleccione un usuario de la tabla.");
            return;
        }
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            vista.mostrarAviso("El nombre completo es obligatorio.");
            return;
        }
        if (rol == null) {
            vista.mostrarAviso("Debe seleccionar un rol.");
            return;
        }

        try {
            boolean leQuitaAdmin = activoSeleccionado
                    && rolSeleccionado == com.tienda.stockcontrol.modelo.Rol.ADMIN
                    && rol != com.tienda.stockcontrol.modelo.Rol.ADMIN;
            if (leQuitaAdmin && usuarioDAO.contarAdminsActivos() <= 1) {
                vista.mostrarAviso("No se puede quitar el rol de administrador: es el unico activo del sistema.");
                return;
            }

            com.tienda.stockcontrol.modelo.Usuario usuario = new com.tienda.stockcontrol.modelo.Usuario();
            usuario.setIdUsuario(idSeleccionado);
            usuario.setNombreCompleto(nombreCompleto.trim());
            usuario.setRol(rol);
            usuario.setActivo(activoSeleccionado);

            usuarioDAO.actualizar(usuario);
            vista.mostrarMensaje("Usuario modificado correctamente.");
            limpiarEstado();
            cargarTabla();
        } catch (java.sql.SQLException e) {
            vista.mostrarError(e);
        }
    }

    public void cambiarEstado() {
        if (!tienePermiso()) {
            return;
        }
        if (idSeleccionado == null) {
            vista.mostrarAviso("Seleccione un usuario de la tabla.");
            return;
        }
        try {
            boolean loDesactiva = activoSeleccionado && rolSeleccionado == com.tienda.stockcontrol.modelo.Rol.ADMIN;
            if (loDesactiva && usuarioDAO.contarAdminsActivos() <= 1) {
                vista.mostrarAviso("No se puede desactivar: es el unico administrador activo del sistema.");
                return;
            }

            usuarioDAO.cambiarEstado(idSeleccionado, !activoSeleccionado);
            vista.mostrarMensaje(activoSeleccionado ? "Usuario desactivado." : "Usuario activado.");
            limpiarEstado();
            cargarTabla();
        } catch (java.sql.SQLException e) {
            vista.mostrarError(e);
        }
    }
    
    public boolean haySeleccion() {
        return idSeleccionado != null;
    }

    public void restablecerContrasena(String nuevaPasswordTexto) {
        if (!tienePermiso()) {
            return;
        }
        if (idSeleccionado == null) {
            vista.mostrarAviso("Seleccione un usuario de la tabla.");
            return;
        }
        if (nuevaPasswordTexto == null || nuevaPasswordTexto.isEmpty()) {
            vista.mostrarAviso("La contraseña no puede estar vacia.");
            return;
        }
        String nuevoSalt = com.tienda.stockcontrol.modelo.PasswordUtil.generarSalt();
        String nuevoHash = com.tienda.stockcontrol.modelo.PasswordUtil.calcularHash(nuevaPasswordTexto, nuevoSalt);
        try {
            usuarioDAO.actualizarPassword(idSeleccionado, nuevoHash, nuevoSalt);
            vista.mostrarMensaje("Contraseña restablecida correctamente.");
        } catch (java.sql.SQLException e) {
            vista.mostrarError(e);
        }
    }

    public void limpiar() {
        limpiarEstado();
    }

    private boolean tienePermiso() {
        boolean esAdmin = com.tienda.stockcontrol.controlador.SesionUsuario.getInstancia().esAdministrador();
        if (!esAdmin) {
            vista.mostrarAviso("No tiene permisos de administrador para realizar esta accion.");
        }
        return esAdmin;
    }
    
    private void limpiarEstado() {
        idSeleccionado = null;
        activoSeleccionado = true;
        rolSeleccionado = null;
        vista.limpiarFormulario();
    }
}
