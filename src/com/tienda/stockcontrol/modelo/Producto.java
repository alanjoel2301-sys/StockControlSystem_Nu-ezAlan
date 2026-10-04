
package com.tienda.stockcontrol.modelo;

/**
 *
 * @author Dell
 */

import java.math.BigDecimal;
import java.time.LocalDate;

public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private Categoria categoria;
    private BigDecimal precio;
    private int stockActual;
    private int stockMinimo;
    private LocalDate fechaAlta;
    //Constructores
    public Producto() {
    }

    public Producto(String nombre, String descripcion, Categoria categoria,
            BigDecimal precio, int stockActual, int stockMinimo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.fechaAlta = LocalDate.now();
    }
    //Getters y Setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    
    public boolean isBajoStock() {
        return stockActual <= stockMinimo;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}
