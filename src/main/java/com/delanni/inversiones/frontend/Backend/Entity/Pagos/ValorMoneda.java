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
public class ValorMoneda {

    private Long id;


    private Moneda moneda;

    private Double valor;

    private Date registro;
    //***********************A pago, B Desactivado, H historico
    private String status;

    private String casa_cambio;

    private String uso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getRegistro() {
        return registro;
    }

    public void setRegistro(Date registro) {
        this.registro = registro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCasa_cambio() {
        return casa_cambio;
    }

    public void setCasa_cambio(String casa_cambio) {
        this.casa_cambio = casa_cambio;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }
    
    
}
