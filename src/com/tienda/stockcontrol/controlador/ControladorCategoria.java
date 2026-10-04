
package com.tienda.stockcontrol.controlador;

/**
 *
 * @author Dell
 */
public class ControladorCategoria {
        private final com.tienda.stockcontrol.modelo.CategoriaDAO categoriaDAO = new com.tienda.stockcontrol.modelo.CategoriaDAO();
    private final com.tienda.stockcontrol.vista.FrmCategoria vista;
    private Integer idSeleccionado = null;

    public ControladorCategoria(com.tienda.stockcontrol.vista.FrmCategoria vista) {
        this.vista = vista;
    }

    public void cargarTabla() {
        try {
            java.util.List<com.tienda.stockcontrol.modelo.Categoria> categorias = categoriaDAO.listarTodas();
            vista.mostrarCategorias(categorias);
        } catch (java.sql.SQLException e) {
            vista.mostrarError(e);
        }
    }

    public void seleccionarFila(int idCategoria) {
        try {
            com.tienda.stockcontrol.modelo.Categoria categoria = categoriaDAO.buscarPorId(idCategoria);
            if (categoria == null) {
                return;
            }
            idSeleccionado = categoria.getIdCategoria();
            vista.mostrarEnFormulario(categoria);
        } catch (java.sql.SQLException e) {
            vista.mostrarError(e);
        }
    }

    public void agregar(String nombre, String descripcion) {
        if (nombre == null || nombre.trim().isEmpty()) {
            vista.mostrarAviso("El nombre es obligatorio.");
            return;
        }
        try {
            com.tienda.stockcontrol.modelo.Categoria categoria =
                    new com.tienda.stockcontrol.modelo.Categoria(nombre.trim(), descripcion.trim());
            categoriaDAO.insertar(categoria);
            vista.mostrarMensaje("Categoria agregada correctamente.");
            limpiarEstado();
            cargarTabla();
        } catch (java.sql.SQLException e) {
            if (esDuplicado(e)) {
                vista.mostrarAviso("Ya existe una categoria con ese nombre.");
            } else {
                vista.mostrarError(e);
            }
        }
    }

    public void modificar(String nombre, String descripcion) {
        if (idSeleccionado == null) {
            vista.mostrarAviso("Seleccione una categoria de la tabla.");
            return;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            vista.mostrarAviso("El nombre es obligatorio.");
            return;
        }
        try {
            com.tienda.stockcontrol.modelo.Categoria categoria = new com.tienda.stockcontrol.modelo.Categoria(
                    idSeleccionado, nombre.trim(), descripcion.trim());
            categoriaDAO.actualizar(categoria);
            vista.mostrarMensaje("Categoria modificada correctamente.");
            limpiarEstado();
            cargarTabla();
        } catch (java.sql.SQLException e) {
            if (esDuplicado(e)) {
                vista.mostrarAviso("Ya existe una categoria con ese nombre.");
            } else {
                vista.mostrarError(e);
            }
        }
    }

    public void eliminar() {
        if (idSeleccionado == null) {
            vista.mostrarAviso("Seleccione una categoria de la tabla.");
            return;
        }
        if (!vista.confirmarEliminacion()) {
            return;
        }
        try {
            categoriaDAO.eliminar(idSeleccionado);
            vista.mostrarMensaje("Categoria eliminada correctamente.");
            limpiarEstado();
            cargarTabla();
        } catch (java.sql.SQLException e) {
            if (esRestriccionForanea(e)) {
                vista.mostrarAviso("No se puede eliminar: la categoria tiene productos asociados.");
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
    }
    
    private boolean esDuplicado(java.sql.SQLException e) {
        return e.getMessage() != null && e.getMessage().contains("Duplicate entry");
    }

    private boolean esRestriccionForanea(java.sql.SQLException e) {
        return e.getMessage() != null && e.getMessage().contains("foreign key constraint fails");
    }
    
}
