
package com.tienda.stockcontrol.modelo;

/**
 *
 * @author Dell
 */

import com.tienda.stockcontrol.modelo.ConexionBD;
import com.tienda.stockcontrol.modelo.Categoria;
import com.tienda.stockcontrol.modelo.Producto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    public void insertar(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos "
                + "(nombre, descripcion, id_categoria, precio, stock_actual, stock_minimo, fecha_alta) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setInt(3, producto.getCategoria().getIdCategoria());
            ps.setBigDecimal(4, producto.getPrecio());
            ps.setInt(5, producto.getStockActual());
            ps.setInt(6, producto.getStockMinimo());
            ps.setDate(7, Date.valueOf(
                    producto.getFechaAlta() != null ? producto.getFechaAlta() : LocalDate.now()));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    producto.setIdProducto(rs.getInt(1));
                }
            }
        }
    }
    
    public void actualizar(Producto producto) throws SQLException {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, id_categoria = ?, "
                + "precio = ?, stock_minimo = ? WHERE id_producto = ?";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setInt(3, producto.getCategoria().getIdCategoria());
            ps.setBigDecimal(4, producto.getPrecio());
            ps.setInt(5, producto.getStockMinimo());
            ps.setInt(6, producto.getIdProducto());
            ps.executeUpdate();
        }
    }
    
    public void eliminar(int idProducto) throws SQLException {
        String sql = "DELETE FROM productos WHERE id_producto = ?";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            ps.executeUpdate();
        }
    }
    
    public List<Producto> listarTodos() throws SQLException {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT p.id_producto, p.nombre, p.descripcion, p.precio, "
                + "p.stock_actual, p.stock_minimo, p.fecha_alta, "
                + "c.id_categoria, c.nombre AS nombre_categoria, c.descripcion AS descripcion_categoria "
                + "FROM productos p JOIN categorias c ON p.id_categoria = c.id_categoria "
                + "ORDER BY p.nombre";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }
    
    public List<Producto> listarBajoStock() throws SQLException {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT p.id_producto, p.nombre, p.descripcion, p.precio, "
                + "p.stock_actual, p.stock_minimo, p.fecha_alta, "
                + "c.id_categoria, c.nombre AS nombre_categoria, c.descripcion AS descripcion_categoria "
                + "FROM productos p JOIN categorias c ON p.id_categoria = c.id_categoria "
                + "WHERE p.stock_actual <= p.stock_minimo "
                + "ORDER BY p.stock_actual ASC";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }
    
    public List<Producto> buscarPorNombre(String texto) throws SQLException {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT p.id_producto, p.nombre, p.descripcion, p.precio, "
                + "p.stock_actual, p.stock_minimo, p.fecha_alta, "
                + "c.id_categoria, c.nombre AS nombre_categoria, c.descripcion AS descripcion_categoria "
                + "FROM productos p JOIN categorias c ON p.id_categoria = c.id_categoria "
                + "WHERE p.nombre LIKE ? ORDER BY p.nombre";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + texto + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        }
        return lista;
    }
    
    public Producto buscarPorId(int idProducto) throws SQLException {
        String sql = "SELECT p.id_producto, p.nombre, p.descripcion, p.precio, "
                + "p.stock_actual, p.stock_minimo, p.fecha_alta, "
                + "c.id_categoria, c.nombre AS nombre_categoria, c.descripcion AS descripcion_categoria "
                + "FROM productos p JOIN categorias c ON p.id_categoria = c.id_categoria "
                + "WHERE p.id_producto = ?";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }

    protected boolean descontarStockSiAlcanza(Connection con, int idProducto, int cantidad) throws SQLException {
        String sql = "UPDATE productos SET stock_actual = stock_actual - ? "
                + "WHERE id_producto = ? AND stock_actual >= ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cantidad);
            ps.setInt(2, idProducto);
            ps.setInt(3, cantidad);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }
    }   
 
    protected void ajustarStock(Connection con, int idProducto, int cantidadConSigno) throws SQLException {
        String sql = "UPDATE productos SET stock_actual = stock_actual + ? WHERE id_producto = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cantidadConSigno);
            ps.setInt(2, idProducto);
            ps.executeUpdate();
        }
    }
    
    private Producto mapear(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria(
                rs.getInt("id_categoria"),
                rs.getString("nombre_categoria"),
                rs.getString("descripcion_categoria"));

        Producto producto = new Producto();
        producto.setIdProducto(rs.getInt("id_producto"));
        producto.setNombre(rs.getString("nombre"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setCategoria(categoria);
        producto.setPrecio(rs.getBigDecimal("precio"));
        producto.setStockActual(rs.getInt("stock_actual"));
        producto.setStockMinimo(rs.getInt("stock_minimo"));
        Date fecha = rs.getDate("fecha_alta");
        if (fecha != null) {
            producto.setFechaAlta(fecha.toLocalDate());
        }
        return producto;
    }
}
