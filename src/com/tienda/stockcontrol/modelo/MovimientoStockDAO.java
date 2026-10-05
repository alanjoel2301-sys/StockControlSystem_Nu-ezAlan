
package com.tienda.stockcontrol.modelo;

/**
 *
 * @author Dell
 */

import com.tienda.stockcontrol.modelo.ConexionBD;
import com.tienda.stockcontrol.modelo.Categoria;
import com.tienda.stockcontrol.modelo.MovimientoStock;
import com.tienda.stockcontrol.modelo.Producto;
import com.tienda.stockcontrol.modelo.TipoMovimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MovimientoStockDAO {
    private final ProductoDAO productoDAO = new ProductoDAO();
    
    public void registrarMovimiento(MovimientoStock movimiento) throws SQLException, StockException {
        if (movimiento == null || movimiento.getProducto() == null || movimiento.getTipo() == null) {
            throw new StockException("Datos de movimiento incompletos.");
        }
        if (movimiento.getCantidad() <= 0) {
            throw new StockException("La cantidad debe ser mayor a cero.");
        }
        String sqlInsert = "INSERT INTO movimientos "
                + "(id_producto, tipo, cantidad, fecha, motivo, usuario) VALUES (?, ?, ?, ?, ?, ?)";

        Connection con = null;
        try {
            con = ConexionBD.getConexion();
            con.setAutoCommit(false);

            int idProducto = movimiento.getProducto().getIdProducto();
            int cantidad = movimiento.getCantidad();

            if (movimiento.getTipo() == TipoMovimiento.SALIDA) {
                boolean descontado = productoDAO.descontarStockSiAlcanza(con, idProducto, cantidad);
                if (!descontado) {
                    throw new StockException("Stock insuficiente o el producto ya no existe.");
                }
            } else {
                productoDAO.ajustarStock(con, idProducto, cantidad);
            }

            try (PreparedStatement ps = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, idProducto);
                ps.setString(2, movimiento.getTipo().name());
                ps.setInt(3, cantidad);
                ps.setTimestamp(4, Timestamp.valueOf(movimiento.getFecha()));
                ps.setString(5, movimiento.getMotivo());
                ps.setString(6, movimiento.getUsuario());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        movimiento.setIdMovimiento(rs.getInt(1));
                    }
                }
            }

            con.commit();
        } catch (SQLException | StockException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException ignored) {
                }
                ConexionBD.cerrar(con);
            }
        }
    }
    
    public List<MovimientoStock> listarTodos() throws SQLException {
        List<MovimientoStock> lista = new ArrayList<>();
        String sql = "SELECT m.id_movimiento, m.tipo, m.cantidad, m.fecha, m.motivo, m.usuario, "
                + "p.id_producto, p.nombre AS nombre_producto, p.descripcion AS descripcion_producto, "
                + "p.precio, p.stock_actual, p.stock_minimo, p.fecha_alta, "
                + "c.id_categoria, c.nombre AS nombre_categoria, c.descripcion AS descripcion_categoria "
                + "FROM movimientos m "
                + "JOIN productos p ON m.id_producto = p.id_producto "
                + "JOIN categorias c ON p.id_categoria = c.id_categoria "
                + "ORDER BY m.fecha DESC";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }
    
    public List<MovimientoStock> listarPorProducto(int idProducto) throws SQLException {
        List<MovimientoStock> lista = new ArrayList<>();
        String sql = "SELECT m.id_movimiento, m.tipo, m.cantidad, m.fecha, m.motivo, m.usuario, "
                + "p.id_producto, p.nombre AS nombre_producto, p.descripcion AS descripcion_producto, "
                + "p.precio, p.stock_actual, p.stock_minimo, p.fecha_alta, "
                + "c.id_categoria, c.nombre AS nombre_categoria, c.descripcion AS descripcion_categoria "
                + "FROM movimientos m "
                + "JOIN productos p ON m.id_producto = p.id_producto "
                + "JOIN categorias c ON p.id_categoria = c.id_categoria "
                + "WHERE p.id_producto = ? ORDER BY m.fecha DESC";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        }
        return lista;
    }
    
    public Map<String, Map<String, Integer>> totalesPorMes(int meses) throws SQLException {
        Map<String, Map<String, Integer>> resultado = new LinkedHashMap<>();
        String sql = "SELECT DATE_FORMAT(fecha, '%Y-%m') AS periodo, tipo, SUM(cantidad) AS total "
                + "FROM movimientos "
                + "WHERE fecha >= DATE_SUB(CURDATE(), INTERVAL ? MONTH) "
                + "GROUP BY periodo, tipo ORDER BY periodo ASC";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, meses);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String periodo = rs.getString("periodo");
                    String tipo = rs.getString("tipo");
                    int total = rs.getInt("total");
                    resultado.computeIfAbsent(periodo, k -> new LinkedHashMap<>()).put(tipo, total);
                }
            }
        }
        return resultado;
    }
    
    public Map<String, Integer> stockPorCategoria() throws SQLException {
        Map<String, Integer> resultado = new LinkedHashMap<>();
        String sql = "SELECT c.nombre AS categoria, SUM(p.stock_actual) AS total "
                + "FROM productos p JOIN categorias c ON p.id_categoria = c.id_categoria "
                + "GROUP BY c.nombre ORDER BY total DESC";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.put(rs.getString("categoria"), rs.getInt("total"));
            }
        }
        return resultado;
    }
    
    public Map<String, Integer> productosMasSalidas(int limite) throws SQLException {
        Map<String, Integer> resultado = new LinkedHashMap<>();
        String sql = "SELECT p.nombre AS producto, SUM(m.cantidad) AS total "
                + "FROM movimientos m JOIN productos p ON m.id_producto = p.id_producto "
                + "WHERE m.tipo = 'SALIDA' "
                + "GROUP BY p.nombre ORDER BY total DESC LIMIT ?";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, limite);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultado.put(rs.getString("producto"), rs.getInt("total"));
                }
            }
        }
        return resultado;
    }
    
    private MovimientoStock mapear(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria(
                rs.getInt("id_categoria"),
                rs.getString("nombre_categoria"),
                rs.getString("descripcion_categoria"));

        Producto producto = new Producto();
        producto.setIdProducto(rs.getInt("id_producto"));
        producto.setNombre(rs.getString("nombre_producto"));
        producto.setDescripcion(rs.getString("descripcion_producto"));
        producto.setCategoria(categoria);
        producto.setPrecio(rs.getBigDecimal("precio"));
        producto.setStockActual(rs.getInt("stock_actual"));
        producto.setStockMinimo(rs.getInt("stock_minimo"));

        MovimientoStock movimiento = new MovimientoStock();
        movimiento.setIdMovimiento(rs.getInt("id_movimiento"));
        movimiento.setProducto(producto);
        movimiento.setTipo(TipoMovimiento.valueOf(rs.getString("tipo")));
        movimiento.setCantidad(rs.getInt("cantidad"));
        movimiento.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
        movimiento.setMotivo(rs.getString("motivo"));
        movimiento.setUsuario(rs.getString("usuario"));
        return movimiento;
    }
}
