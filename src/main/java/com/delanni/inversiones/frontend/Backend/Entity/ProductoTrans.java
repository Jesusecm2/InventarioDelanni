/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Entity;

import java.util.Date;

/**
 *
 * @author Jesusecm
 */
public class ProductoTrans {

    private Long id;

    private String tope;

    private Integer cantidad;

    // 0 o 5 
    private Integer accion;

    private Producto producto;

    private Integer trn;

    private Date create_at;

    private Date mod_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTope() {
        return tope;
    }

    public void setTope(String tope) {
        this.tope = tope;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getAccion() {
        return accion;
    }

    public void setAccion(Integer accion) {
        this.accion = accion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getTrn() {
        return trn;
    }

    public void setTrn(Integer trn) {
        this.trn = trn;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getMod_at() {
        return mod_at;
    }

    public void setMod_at(Date mod_at) {
        this.mod_at = mod_at;
    }
}
