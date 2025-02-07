/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Entity;

/**
 *
 * @author Jesusecm
 */
public class SystemParam {

    private Long id;

    private Integer tbcod;

    private String reference;
    private String descripcion;

    private Double valueNum;

    private String valuetxt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTbcod() {
        return tbcod;
    }

    public void setTbcod(Integer tbcod) {
        this.tbcod = tbcod;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Double getValueNum() {
        return valueNum;
    }

    public void setValueNum(Double valueNum) {
        this.valueNum = valueNum;
    }

    public String getValuetxt() {
        return valuetxt;
    }

    public void setValuetxt(String valuetxt) {
        this.valuetxt = valuetxt;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
