
package com.tienda.stockcontrol.controlador;

/**
 *
 * @author Dell
 */
public class ControladorMovimientos {
    private final com.tienda.stockcontrol.modelo.MovimientoStockDAO movimientoDAO = new com.tienda.stockcontrol.modelo.MovimientoStockDAO();
    private final com.tienda.stockcontrol.modelo.ProductoDAO productoDAO = new com.tienda.stockcontrol.modelo.ProductoDAO();
    private final com.tienda.stockcontrol.vista.FrmMovimientos vista;

    public ControladorMovimientos(com.tienda.stockcontrol.vista.FrmMovimientos vista) {
        this.vista = vista;
    }

    public void cargarCombo() {
        try {
            java.util.List<com.tienda.stockcontrol.modelo.Producto> productos = productoDAO.listarTodos();
            vista.mostrarProductosEnCombo(productos);
        } catch (java.sql.SQLException e) {
            vista.mostrarError(e);
        }
    }

    public void cargarHistorial() {
        try {
            java.util.List<com.tienda.stockcontrol.modelo.MovimientoStock> movimientos = movimientoDAO.listarTodos();
            vista.mostrarHistorial(movimientos);
        } catch (java.sql.SQLException e) {
            vista.mostrarError(e);
        }
    }

    public void registrar(com.tienda.stockcontrol.modelo.Producto producto,
            com.tienda.stockcontrol.modelo.TipoMovimiento tipo, int cantidad, String motivo) {
        if (producto == null) {
            vista.mostrarAviso("No hay productos cargados. Cree un producto primero.");
            return;
        }
        if (cantidad <= 0) {
            vista.mostrarAviso("La cantidad debe ser mayor a cero.");
            return;
        }
        if (!com.tienda.stockcontrol.controlador.SesionUsuario.getInstancia().haySesionActiva()) {
            vista.mostrarAviso("No hay una sesion activa. Vuelva a iniciar sesion.");
            return;
        }
        String usuario = com.tienda.stockcontrol.controlador.SesionUsuario.getInstancia()
                .getUsuarioActual().getNombreCompleto();
        com.tienda.stockcontrol.modelo.MovimientoStock movimiento = new com.tienda.stockcontrol.modelo.MovimientoStock(
                producto, tipo, cantidad, motivo == null ? "" : motivo.trim(), usuario);

        try {
            movimientoDAO.registrarMovimiento(movimiento);
            com.tienda.stockcontrol.modelo.Producto productoActualizado =
                    productoDAO.buscarPorId(producto.getIdProducto());
            int nuevoStock = productoActualizado != null ? productoActualizado.getStockActual() : -1;
            vista.mostrarMensaje("Movimiento registrado correctamente.\n"
                    + "Nuevo stock de " + producto.getNombre() + ": " + nuevoStock);
            vista.limpiarFormulario();
            cargarCombo();
            cargarHistorial();
        } catch (com.tienda.stockcontrol.modelo.StockException e) {
            vista.mostrarAviso(e.getMessage());
        } catch (java.sql.SQLException e) {
            vista.mostrarError(e);
        }
    }
}
