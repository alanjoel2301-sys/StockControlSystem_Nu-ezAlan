
package com.tienda.stockcontrol.controlador;

/**
 *
 * @author Dell
 */
public class ControladorProducto {
    private final com.tienda.stockcontrol.modelo.ProductoDAO productoDAO = new com.tienda.stockcontrol.modelo.ProductoDAO();
    private final com.tienda.stockcontrol.modelo.CategoriaDAO categoriaDAO = new com.tienda.stockcontrol.modelo.CategoriaDAO();
    private final com.tienda.stockcontrol.modelo.MovimientoStockDAO movimientoDAO = new com.tienda.stockcontrol.modelo.MovimientoStockDAO();
    private final com.tienda.stockcontrol.vista.FrmProducto vista;
    private Integer idSeleccionado = null;

    public ControladorProducto(com.tienda.stockcontrol.vista.FrmProducto vista) {
        this.vista = vista;
    }

    public void cargarCombo() {
        try {
            java.util.List<com.tienda.stockcontrol.modelo.Categoria> categorias = categoriaDAO.listarTodas();
            vista.mostrarCategoriasEnCombo(categorias);
        } catch (java.sql.SQLException e) {
            vista.mostrarError(e);
        }
    }

    public void cargarTabla() {
        try {
            java.util.List<com.tienda.stockcontrol.modelo.Producto> productos = productoDAO.listarTodos();
            vista.mostrarProductos(productos);
        } catch (java.sql.SQLException e) {
            vista.mostrarError(e);
        }
    }

    public void seleccionarFila(int idProducto) {
        try {
            com.tienda.stockcontrol.modelo.Producto producto = productoDAO.buscarPorId(idProducto);
            if (producto == null) {
                return;
            }
            idSeleccionado = producto.getIdProducto();
            vista.mostrarEnFormulario(producto);
        } catch (java.sql.SQLException e) {
            vista.mostrarError(e);
        }
    }
    
    public void verHistorial(int idProducto, String nombreProducto) {
        try {
            java.util.List<com.tienda.stockcontrol.modelo.MovimientoStock> movimientos =
                    movimientoDAO.listarPorProducto(idProducto);
            vista.mostrarHistorialProducto(nombreProducto, movimientos);
        } catch (java.sql.SQLException e) {
            vista.mostrarError(e);
        }
    }

    public void agregar(String nombre, String descripcion, com.tienda.stockcontrol.modelo.Categoria categoria,
            String precioTexto, int stockInicial, int stockMinimo) {
        com.tienda.stockcontrol.modelo.Producto producto =
                validarYArmar(nombre, descripcion, categoria, precioTexto, stockInicial, stockMinimo);
        if (producto == null) {
            return;
        }
        producto.setStockActual(0);
        try {
            productoDAO.insertar(producto);
            if (stockInicial > 0) {
                String usuario = com.tienda.stockcontrol.controlador.SesionUsuario.getInstancia()
                        .getUsuarioActual().getNombreCompleto();
                com.tienda.stockcontrol.modelo.MovimientoStock movimientoInicial =
                        new com.tienda.stockcontrol.modelo.MovimientoStock(
                                producto, com.tienda.stockcontrol.modelo.TipoMovimiento.ENTRADA,
                                stockInicial, "Alta de producto", usuario);
                movimientoDAO.registrarMovimiento(movimientoInicial);
            }
            vista.mostrarMensaje("Producto agregado correctamente.");
            limpiarEstado();
            cargarTabla();
        } catch (java.sql.SQLException e) {
            if (esDuplicado(e)) {
                vista.mostrarAviso("Ya existe un producto con ese nombre.");
            } else {
                vista.mostrarError(e);
            }
        } catch (com.tienda.stockcontrol.modelo.StockException e) {
            vista.mostrarError(e);
        }
    }

    public void modificar(String nombre, String descripcion, com.tienda.stockcontrol.modelo.Categoria categoria,
            String precioTexto, int stockInicial, int stockMinimo) {
        if (idSeleccionado == null) {
            vista.mostrarAviso("Seleccione un producto de la tabla.");
            return;
        }
        com.tienda.stockcontrol.modelo.Producto producto =
                validarYArmar(nombre, descripcion, categoria, precioTexto, stockInicial, stockMinimo);
        if (producto == null) {
            return;
        }
        producto.setIdProducto(idSeleccionado);
                try {
            productoDAO.actualizar(producto);
            vista.mostrarMensaje("Producto modificado correctamente.");
            limpiarEstado();
            cargarTabla();
        } catch (java.sql.SQLException e) {
            if (esDuplicado(e)) {
                vista.mostrarAviso("Ya existe un producto con ese nombre.");
            } else {
                vista.mostrarError(e);
            }
        }
    }

    public void eliminar() {
        if (idSeleccionado == null) {
            vista.mostrarAviso("Seleccione un producto de la tabla.");
            return;
        }
        if (!vista.confirmarEliminacion()) {
            return;
        }
        try {
            productoDAO.eliminar(idSeleccionado);
            vista.mostrarMensaje("Producto eliminado correctamente.");
            limpiarEstado();
            cargarTabla();
        } catch (java.sql.SQLException e) {
            if (esRestriccionForanea(e)) {
                vista.mostrarAviso("No se puede eliminar: el producto tiene movimientos registrados.");
            } else {
                vista.mostrarError(e);
            }
        }
    }

    public void limpiar() {
        limpiarEstado();
    }

    private void limpiarEstado() {
        idSeleccionado = null;
        vista.limpiarFormulario();
        cargarCombo();
    }
    
    private boolean esDuplicado(java.sql.SQLException e) {
        return e.getErrorCode() == 1062;
    }

    private boolean esRestriccionForanea(java.sql.SQLException e) {
        return e.getErrorCode() == 1451;
    }

    private com.tienda.stockcontrol.modelo.Producto validarYArmar(String nombre, String descripcion,
            com.tienda.stockcontrol.modelo.Categoria categoria, String precioTexto,
            int stockInicial, int stockMinimo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            vista.mostrarAviso("El nombre es obligatorio.");
            return null;
        }
        if (categoria == null) {
            vista.mostrarAviso("Debe existir al menos una categoria cargada.");
            return null;
        }
        java.math.BigDecimal precio;
        try {
            precio = new java.math.BigDecimal(precioTexto.trim().replace(",", "."));
            if (precio.compareTo(java.math.BigDecimal.ZERO) <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            vista.mostrarAviso("El precio debe ser un numero valido y positivo.");
            return null;
        }

        com.tienda.stockcontrol.modelo.Producto producto = new com.tienda.stockcontrol.modelo.Producto();
        producto.setNombre(nombre.trim());
        producto.setDescripcion(descripcion == null ? "" : descripcion.trim());
        producto.setCategoria(categoria);
        producto.setPrecio(precio);
        producto.setStockActual(stockInicial);
        producto.setStockMinimo(stockMinimo);
        return producto;
    }
}
