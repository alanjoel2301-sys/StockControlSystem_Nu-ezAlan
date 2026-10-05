
package com.tienda.stockcontrol.controlador;

/**
 *
 * @author Dell
 */
public class ControladorStock {
    private final com.tienda.stockcontrol.modelo.ProductoDAO productoDAO = new com.tienda.stockcontrol.modelo.ProductoDAO();
    private final com.tienda.stockcontrol.vista.FrmStock vista;

    public ControladorStock(com.tienda.stockcontrol.vista.FrmStock vista) {
        this.vista = vista;
    }

    public void actualizar(boolean soloBajoStock) {
        try {
            java.util.List<com.tienda.stockcontrol.modelo.Producto> productos = soloBajoStock
                    ? productoDAO.listarBajoStock()
                    : productoDAO.listarTodos();
            vista.mostrarProductos(productos);
        } catch (java.sql.SQLException e) {
            vista.mostrarError(e);
        }
    }

    public void buscar(String texto, boolean soloBajoStock) {
        if (texto == null || texto.trim().isEmpty()) {
            actualizar(soloBajoStock);
            return;
        }
        try {
            java.util.List<com.tienda.stockcontrol.modelo.Producto> productos = productoDAO.buscarPorNombre(texto.trim());
            if (soloBajoStock) {
                productos.removeIf(p -> !p.isBajoStock());
            }
            vista.mostrarProductos(productos);
        } catch (java.sql.SQLException e) {
            vista.mostrarError(e);
        }
    }
}
