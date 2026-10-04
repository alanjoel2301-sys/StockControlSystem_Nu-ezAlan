
package com.tienda.stockcontrol.modelo;

/**
 *
 * @author Dell
 */
public class Usuario {
    private int idUsuario;
    private String username;
    private String passwordHash;
    private String salt;
    private String nombreCompleto;
    private Rol rol;
    private boolean activo;
    
        public Usuario() {
    }

    public Usuario(String username, String passwordHash, String salt,
            String nombreCompleto, Rol rol, boolean activo) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
        this.activo = activo;
    }

    public Usuario(int idUsuario, String username, String passwordHash, String salt,
            String nombreCompleto, Rol rol, boolean activo) {
        this(username, passwordHash, salt, nombreCompleto, rol, activo);
        this.idUsuario = idUsuario;
    }
    
        public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
        public boolean esAdministrador() {
        return rol == Rol.ADMIN;
    }

    @Override
    public String toString() {
        return nombreCompleto + " (" + username + ")";
    }
}
