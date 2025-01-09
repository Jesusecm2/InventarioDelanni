/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Factura.Table;

import com.delanni.inversiones.frontend.Backend.Entity.Producto;

/**
 *
 * @author Jesusecm
 */
public class TProducto {

    private String nombre;

    private Producto producto;

    private Double cantidad;

    private Double precio_unit;
    
    

    private Double total;

    private String Stotal;

    private String Sprecio_unit;

    private String Scantidad;

    public TProducto() {
    }

    public TProducto(Producto producto, Double cantidad) {
        this.nombre = producto.getNombre();
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio_unit = producto.getPrecio_unit();
        this.precio_unit = producto.getPrecio_vent();
        this.total = precio_unit*cantidad;
        this.Sprecio_unit = String.format("%.2f", precio_unit);
        this.Scantidad = String.format("%.2f*%.2f", precio_unit,cantidad);
        this.Stotal = String.format("%.2f", total);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
        this.Scantidad = String.format("%.2f*%.2f", precio_unit,cantidad);
        this.total = precio_unit*cantidad;
        this.Stotal = String.format("%.2f", total);
    }

    public Double getPrecio_unit() {
        return precio_unit;
    }

    public void setPrecio_unit(Double precio_unit) {
        this.precio_unit = precio_unit;
        this.Sprecio_unit = String.format("%.2f", precio_unit);
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
        this.Stotal = String.format("%.2f", total);
    }

    public String getStotal() {
        return Stotal;
    }

    public void setStotal(String Stotal) {
        this.Stotal = Stotal;
    }

    public String getSprecio_unit() {
        return Sprecio_unit;
    }

    public void setSprecio_unit(String Sprecio_unit) {
        this.Sprecio_unit = Sprecio_unit;
    }

    public String getScantidad() {
        return Scantidad;
    }

    public void setScantidad(String Scantidad) {
        this.Scantidad = Scantidad;
    }

}
