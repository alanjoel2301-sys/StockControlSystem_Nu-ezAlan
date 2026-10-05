
package com.tienda.stockcontrol.modelo;

/**
 *
 * @author Dell
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public void insertar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (username, password_hash, salt, nombre_completo, rol, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPasswordHash());
            ps.setString(3, usuario.getSalt());
            ps.setString(4, usuario.getNombreCompleto());
            ps.setString(5, usuario.getRol().name());
            ps.setBoolean(6, usuario.isActivo());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setIdUsuario(rs.getInt(1));
                }
            }
        }
    }
    
    public void actualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nombre_completo = ?, rol = ?, activo = ? WHERE id_usuario = ?";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombreCompleto());
            ps.setString(2, usuario.getRol().name());
            ps.setBoolean(3, usuario.isActivo());
            ps.setInt(4, usuario.getIdUsuario());
            ps.executeUpdate();
        }
    }
    
    public void cambiarEstado(int idUsuario, boolean activo) throws SQLException {
        String sql = "UPDATE usuarios SET activo = ? WHERE id_usuario = ?";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, activo);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
        }
    }
    
    public void actualizarPassword(int idUsuario, String passwordHash, String salt) throws SQLException {
        String sql = "UPDATE usuarios SET password_hash = ?, salt = ? WHERE id_usuario = ?";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, passwordHash);
            ps.setString(2, salt);
            ps.setInt(3, idUsuario);
            ps.executeUpdate();
        }
    }
    
    public int contarAdminsActivos() throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE rol = 'ADMIN' AND activo = TRUE";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
    
    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id_usuario, username, nombre_completo, rol, activo "
                + "FROM usuarios ORDER BY username";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearSinCredenciales(rs));
            }
        }
        return lista;
    }
    
    public Usuario buscarPorUsername(String username) throws SQLException {
        String sql = "SELECT id_usuario, username, password_hash, salt, nombre_completo, rol, activo "
                + "FROM usuarios WHERE username = ?";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }
    
    public Usuario buscarPorId(int idUsuario) throws SQLException {
        String sql = "SELECT id_usuario, username, password_hash, salt, nombre_completo, rol, activo "
                + "FROM usuarios WHERE id_usuario = ?";
        try (Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }
    
    private Usuario mapear(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("id_usuario"));
        usuario.setUsername(rs.getString("username"));
        usuario.setPasswordHash(rs.getString("password_hash"));
        usuario.setSalt(rs.getString("salt"));
        usuario.setNombreCompleto(rs.getString("nombre_completo"));
        usuario.setRol(Rol.valueOf(rs.getString("rol")));
        usuario.setActivo(rs.getBoolean("activo"));
        return usuario;
    }
    
    private Usuario mapearSinCredenciales(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("id_usuario"));
        usuario.setUsername(rs.getString("username"));
        usuario.setNombreCompleto(rs.getString("nombre_completo"));
        usuario.setRol(Rol.valueOf(rs.getString("rol")));
        usuario.setActivo(rs.getBoolean("activo"));
        return usuario;
    }
}
