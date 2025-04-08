/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Inicio.TCuerpoEntity;

import com.delanni.inversiones.frontend.Backend.Conection.Transaccional;
import com.delanni.inversiones.frontend.Backend.Entity.TpIngreso;
import com.delanni.inversiones.frontend.Backend.Entity.Transacciones;

/**
 *
 * @author Jesusecm
 */
public class TIngreso {

    private TpIngreso ingreso;

    private Transacciones trans;

    private String transref;

    private Double monto;

    private String fmonto;

    public TIngreso(Transacciones trans) {
        this.trans = trans;
        this.ingreso = trans.getTpIngreso();
        if (trans.getPago() != null) {
            this.transref = trans.getPago().getNarrativa();

            if (this.trans.getPago().getValor() != null && this.trans.getPago().getMoneda().getConverted().equals("1")) {
                this.monto = trans.getPago().getMonto() * trans.getPago().getValor().getValor();
                this.fmonto = String.format("%.2f", trans.getPago().getMonto()).concat(" ".concat(trans.getPago().getMoneda().getCcy()));
            } else {
                this.monto = trans.getPago().getMonto();
                this.fmonto = String.format("%.2f", trans.getPago().getMonto()).concat(" ".concat(trans.getPago().getMoneda().getCcy()));
            }

        } else {
            this.transref = trans.getRef();
        }

    }

    public TpIngreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(TpIngreso ingreso) {
        this.ingreso = ingreso;
    }

    public Transacciones getTrans() {
        return trans;
    }

    public void setTrans(Transacciones trans) {
        this.trans = trans;
    }

    public String getTransref() {
        return transref;
    }

    public void setTransref(String transref) {
        this.transref = transref;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getFmonto() {
        return fmonto;
    }

    public void setFmonto(String fmonto) {
        this.fmonto = fmonto;
    }

}
