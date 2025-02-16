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

    private Double monto;

    private Double total;

    private LineaFactura linea;

    private String mformat;

    private String tformat;

    private String cformat;

    public TLineaFactura(LineaFactura linea) {
        this.nombre = linea.getId_producto().getNombre();
        this.cantidad = linea.getCantidad();
        this.monto = linea.getPrecio_unit();
        this.linea = linea;
        recalcular();
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
        this.linea.setCantidad(cantidad);
        recalcular();
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
        recalcular();
    }

    public LineaFactura getLinea() {
        return linea;
    }

    public void setLinea(LineaFactura linea) {
        this.linea = linea;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
        this.linea.setPrecio_unit(monto);
        recalcular();
    }

    public String getMformat() {
        return mformat;
    }

    public void setMformat(String mformat) {
        this.mformat = mformat;
    }

    public String getTformat() {
        return tformat;
    }

    public void setTformat(String tformat) {
        this.tformat = tformat;
    }

    public String getCformat() {
        return cformat;
    }

    public void setCformat(String cformat) {
        this.cformat = cformat;
    }

    private void recalcular() {
        this.total = linea.getCantidad() * linea.getPrecio_unit();
        this.mformat = String.format("%.2f", this.monto);
        this.tformat = String.format("%.2f", linea.getCantidad() * linea.getPrecio_unit());
        this.cformat = String.format("%.2f", this.cantidad);
    }

}
