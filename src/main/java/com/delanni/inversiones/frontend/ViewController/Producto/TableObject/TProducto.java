/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Producto.TableObject;

import com.delanni.inversiones.frontend.Backend.Entity.Producto;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Getfile;
import javafx.scene.control.Button;

/**
 *
 * @author Jesusecm
 */
public class TProducto {

    private Producto producto;

    private String nombre;

    private String cantidad;

    private String pu;

    private String pv;

    private Button btn_modify;

    public TProducto(Producto producto) {
        this.producto = producto;
        this.nombre = producto.getNombre();
        this.cantidad = String.format("%.2f Unidades", producto.getCant_actual());
        this.pu = String.format("%.2f$", producto.getPrecio_unit());//String.valueOf(producto.getPrecio_unit());
        this.pv = String.format("%.2f$", producto.getPrecio_vent());//String.valueOf(producto.getPrecio_vent());
        this.btn_modify = new Button("Modificar");
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPu() {
        return pu;
    }

    public void setPu(String pu) {
        this.pu = pu;
    }

    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    public Button getBtn_modify() {
        return btn_modify;
    }

    public void setBtn_modify(Button btn_modify) {
        this.btn_modify = btn_modify;
    }

}
