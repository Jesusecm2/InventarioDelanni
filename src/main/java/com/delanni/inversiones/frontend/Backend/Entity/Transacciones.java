/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Entity;

import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Pago;
import java.util.Date;

/**
 *
 * @author Jesusecm
 */
public class Transacciones {


    private Long id;

    private Cuenta cuenta;

    private String accion;

    private String user;

    private Date Fecha;

    private String ref;

    private Pago pago;


    private Cliente id_cliente;

    
    private Producto id_producto;

  
    private Factura id_factura;

    private TpIngreso id_TP_Ingreso;

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return id_cliente;
    }

    public void setCliente(Cliente cliente) {
        this.id_cliente = cliente;
    }

    public Producto getProducto() {
        return id_producto;
    }

    public void setProducto(Producto producto) {
        this.id_producto = producto;
    }

    public Factura getFactura() {
        return id_factura;
    }

    public void setFactura(Factura factura) {
        this.id_factura = factura;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Cliente getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Cliente id_cliente) {
        this.id_cliente = id_cliente;
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

    public TpIngreso getId_TP_Ingreso() {
        return id_TP_Ingreso;
    }

    public void setId_TP_Ingreso(TpIngreso id_TP_Ingreso) {
        this.id_TP_Ingreso = id_TP_Ingreso;
    }

}
