
package com.tienda.stockcontrol.controlador;

/**
 *
 * @author Dell
 */
public class SesionUsuario {
    private static SesionUsuario instancia;
    private com.tienda.stockcontrol.modelo.Usuario usuarioActual;
    
    private SesionUsuario() {
    }
    
    public static SesionUsuario getInstancia() {
        if (instancia == null) {
            instancia = new SesionUsuario();
        }
        return instancia;
    }
    
    public void iniciarSesion(com.tienda.stockcontrol.modelo.Usuario usuario) {
        this.usuarioActual = usuario;
    }

    public void cerrarSesion() {
        this.usuarioActual = null;
    }

    public com.tienda.stockcontrol.modelo.Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public boolean haySesionActiva() {
        return usuarioActual != null;
    }

    public boolean esAdministrador() {
        return usuarioActual != null && usuarioActual.esAdministrador();
    }
}
