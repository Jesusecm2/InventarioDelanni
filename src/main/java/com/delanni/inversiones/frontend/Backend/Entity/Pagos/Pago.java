/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Entity.Pagos;

import java.util.Date;

/**
 *
 * @author Jesusecm
 */
public class Pago {

    private Long id;

    private String narrativa;

    private String cod_ejecucion;

    private Date registro;

    private Date ejecucion;

    private Double monto;

    private ComprobantePago comprobante;

    private TipodePago tipo;

    private Moneda moneda;

    private ValorMoneda valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNarrativa() {
        return narrativa;
    }

    public void setNarrativa(String narrativa) {
        this.narrativa = narrativa;
    }

    public String getCod_ejecucion() {
        return cod_ejecucion;
    }

    public void setCod_ejecucion(String cod_ejecucion) {
        this.cod_ejecucion = cod_ejecucion;
    }

    public Date getRegistro() {
        return registro;
    }

    public void setRegistro(Date registro) {
        this.registro = registro;
    }

    public Date getEjecucion() {
        return ejecucion;
    }

    public void setEjecucion(Date ejecucion) {
        this.ejecucion = ejecucion;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public ComprobantePago getComprobante() {
        return comprobante;
    }

    public void setComprobante(ComprobantePago comprobante) {
        this.comprobante = comprobante;
    }

    public TipodePago getTipo() {
        return tipo;
    }

    public void setTipo(TipodePago tipo) {
        this.tipo = tipo;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public ValorMoneda getValor() {
        return valor;
    }

    public void setValor(ValorMoneda valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return tipo.getTipo()+" " + cod_ejecucion + " : " +String.valueOf(monto)+" "+moneda.getMoneda();
    }

}
