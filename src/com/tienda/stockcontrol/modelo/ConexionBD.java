
package com.tienda.stockcontrol.modelo;

/**
 *
 * @author Dell
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final java.util.Properties PROPIEDADES = cargarPropiedades();
    private static final String URL = PROPIEDADES.getProperty("db.url",
            "jdbc:mysql://localhost:3306/db_stock_control?"
            + "useSSL=false&serverTimezone=America/Argentina/Buenos_Aires&allowPublicKeyRetrieval=true");
    private static final String USUARIO = PROPIEDADES.getProperty("db.usuario", "root");
    private static final String CLAVE = PROPIEDADES.getProperty("db.clave", "");

    private static java.util.Properties cargarPropiedades() {
        java.util.Properties props = new java.util.Properties();
        try (java.io.FileInputStream entrada = new java.io.FileInputStream("db.properties")) {
            props.load(entrada);
        } catch (java.io.IOException e) {
            System.err.println("No se encontro db.properties en la carpeta del programa. "
                    + "Se usaran valores por defecto (puede que la conexion falle).");
        }
        return props;
    }

    //Constructor
    private ConexionBD() {
    }
    
    public static Connection getConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontro el driver de MySQL (mysql-connector-j). "
                    + "Verifique que el .jar este agregado a las librerias del proyecto.", e);
        }
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
    
    public static void cerrar(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
