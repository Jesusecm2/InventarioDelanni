/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Factura.Table;

import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesusecm
 */
public class TFacturaInicio {

    private String nombre;

    private Double monto;

    private Double pagado;

    private String fmonto;

    private String fpagado;

    private Factura factura;

    private List<TLineaFactura> lineas;

    public TFacturaInicio(Factura factura) {
        if (factura.getIdProveedor() != null) {
            this.nombre = factura.getIdProveedor().getNombre();
        }
        if (factura.getIdCliente() != null) {
            this.nombre = factura.getIdCliente().getNombre();
        }
        this.monto = factura.getSaldo();
        this.fmonto = String.format("%.2f$", this.monto);
        this.factura = factura;
        this.pagado = factura.getSaldo_pagado();
        this.fpagado = String.format("%.2f$", this.pagado);
        this.lineas = new ArrayList<>();
        factura.getLineas().forEach((l) -> {
            TLineaFactura fc = new TLineaFactura(l);
            lineas.add(fc);
        });
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Double getPagado() {
        return pagado;
    }

    public void setPagado(Double pagado) {
        this.pagado = pagado;
    }

    public List<TLineaFactura> getLineas() {
        return lineas;
    }

    public void setLineas(List<TLineaFactura> lineas) {
        this.lineas = lineas;
    }

    public String getFmonto() {
        return fmonto;
    }

    public void setFmonto(String fmonto) {
        this.fmonto = fmonto;
    }

    public String getFpagado() {
        return fpagado;
    }

    public void setFpagado(String fpagado) {
        this.fpagado = fpagado;
    }

    
}
