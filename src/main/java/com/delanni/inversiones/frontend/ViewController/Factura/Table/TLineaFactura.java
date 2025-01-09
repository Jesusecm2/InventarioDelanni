/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Factura.Table;

import com.delanni.inversiones.frontend.Backend.Entity.LineaFactura;

/**
 *
 * @author Jesusecm
 */
public class TLineaFactura {

    private String nombre;

    private Double cantidad;

    private Double total;

    private LineaFactura linea;

    public TLineaFactura(LineaFactura linea) {
        this.nombre = linea.getId_producto().getNombre();
        this.cantidad = linea.getCantidad();
        this.total = linea.getCantidad() * linea.getPrecio_unit();
        this.linea = linea;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LineaFactura getLinea() {
        return linea;
    }

    public void setLinea(LineaFactura linea) {
        this.linea = linea;
    }

}
