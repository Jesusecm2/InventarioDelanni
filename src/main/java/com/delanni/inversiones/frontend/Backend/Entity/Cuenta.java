/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Entity;

/**
 *
 * @author Jesusecm
 */
public class Cuenta {

    private Integer id;

    private String descripcion;

    private String numero_cuenta;

    //Ultimos 4 números
    private Integer ult_num;

    private Double ingreso;

    private Double egreso;

    private Double saldo;

    private Double saldo_ant;

    private String moneda;

    //Tipo de cuenta
    private String tpp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public Integer getUlt_num() {
        return ult_num;
    }

    public void setUlt_num(Integer ult_num) {
        this.ult_num = ult_num;
    }

    public Double getIngreso() {
        return ingreso;
    }

    public void setIngreso(Double ingreso) {
        this.ingreso = ingreso;
    }

    public Double getEgreso() {
        return egreso;
    }

    public void setEgreso(Double egreso) {
        this.egreso = egreso;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getSaldo_ant() {
        return saldo_ant;
    }

    public void setSaldo_ant(Double saldo_ant) {
        this.saldo_ant = saldo_ant;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getTpp() {
        return tpp;
    }

    public void setTpp(String tpp) {
        this.tpp = tpp;
    }

    
    
    
}
