/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Ventas.TEntity;

import com.delanni.inversiones.frontend.Backend.Entity.Transacciones;

/**
 *
 * @author Jesusecm
 */
public class VentaTrans {

    private Transacciones trn;

    private String nombre;

    private String tp_pago;

    private String monto_pag;

    private Long idtrn;

    public VentaTrans(Transacciones trn) {
        this.trn = trn;
        if (trn.getFactura().getIdCliente() != null) {
            this.nombre = trn.getFactura().getIdCliente().getNombre();
        }
        if (trn.getPago() != null) {
            this.tp_pago = trn.getPago().getTipo().getTipo();
            this.monto_pag = String.format("%.2f$", trn.getPago().getMonto());
        }

        this.idtrn = trn.getId();
    }

    public String getMonto_pag() {
        return monto_pag;
    }

    public void setMonto_pag(String monto_pag) {
        this.monto_pag = monto_pag;
    }

    public Transacciones getTrn() {
        return trn;
    }

    public void setTrn(Transacciones trn) {
        this.trn = trn;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTp_pago() {
        return tp_pago;
    }

    public void setTp_pago(String tp_pago) {
        this.tp_pago = tp_pago;
    }

    public Long getIdtrn() {
        return idtrn;
    }

    public void setIdtrn(Long idtrn) {
        this.idtrn = idtrn;
    }

}
