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
    
    private Double precio_vent;
    
    
    private Double total_unit;
    
    private Double total_vent;
    
    private String Stotal_vent;

    private String Stotal;

    private String Sprecio_unit;
    
    private String Sprecio_vent;

    private String Scantidad;

    public TProducto() {
    }

    public TProducto(Producto producto, Double cantidad) {
        this.nombre = producto.getNombre();
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio_unit = producto.getPrecio_unit();
        this.precio_vent = producto.getPrecio_vent();
       // this.precio_unit = producto.getPrecio_vent();
       
        this.total_unit = precio_unit*cantidad;
        this.total_vent = precio_vent*cantidad;
        this.Sprecio_unit = String.format("%.2f", precio_unit);
        this.Sprecio_vent = String.format("%.2f", precio_vent);
        
        
        this.Scantidad = String.format("%.2f", cantidad);
        this.Stotal = String.format("%.2f", total_unit);
        this.Stotal_vent = String.format("%.2f", total_vent);
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
        this.Scantidad = String.format("%.2f",cantidad);
        this.total_unit = precio_unit*cantidad;
        this.total_vent = precio_vent*cantidad;
        this.Stotal = String.format("%.2f", total_unit);
        this.Stotal_vent = String.format("%.2f", total_vent);
    }

    public Double getPrecio_unit() {
        return precio_unit;
    }

    public void setPrecio_unit(Double precio_unit) {
        this.precio_unit = precio_unit;
        this.Sprecio_unit = String.format("%.2f", precio_unit);
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

    public Double getPrecio_vent() {
        return precio_vent;
    }

    public void setPrecio_vent(Double precio_vent) {
        this.precio_vent = precio_vent;
    }

    public String getSprecio_vent() {
        return Sprecio_vent;
    }

    public void setSprecio_vent(String Sprecio_vent) {
        this.Sprecio_vent = Sprecio_vent;
    }

    public Double getTotal_unit() {
        return total_unit;
    }

    public void setTotal_unit(Double total_unit) {
        this.total_unit = total_unit;
    }

    public Double getTotal_vent() {
        return total_vent;
    }

    public void setTotal_vent(Double total_vent) {
        this.total_vent = total_vent;
    }

    public String getStotal_vent() {
        return Stotal_vent;
    }

    public void setStotal_vent(String Stotal_vent) {
        this.Stotal_vent = Stotal_vent;
    }
    
    
    

}
