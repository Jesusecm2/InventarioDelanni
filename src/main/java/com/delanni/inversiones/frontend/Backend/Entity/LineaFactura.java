/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Entity;

/**
 *
 * @author Jesusecm
 */
public class LineaFactura {

    private Long id;

    private Double cantidad;

    private Double precio_unit;

    private Producto id_producto;

    private Factura id_factura;

    public LineaFactura(Long id, Double cantidad, Double precio_unit, Producto id_producto, Factura id_factura) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio_unit = precio_unit;
        this.id_producto = id_producto;
        this.id_factura = id_factura;
    }

    public LineaFactura() {
    }
    
    
    

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecio_unit() {
        return precio_unit;
    }

    public void setPrecio_unit(Double precio_unit) {
        this.precio_unit = precio_unit;
    }

    public Producto getId_producto() {
        return id_producto;
    }

    public void setId_producto(Producto id_producto) {
        this.id_producto = id_producto;
    }

    public Factura getId_factura() {
        return id_factura;
    }

    public void setId_factura(Factura id_factura) {
        this.id_factura = id_factura;
    }

}
