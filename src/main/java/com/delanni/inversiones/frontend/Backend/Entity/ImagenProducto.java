/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.image.ImageView;

/**
 *
 * @author Jesusecm
 */
public class ImagenProducto {

    private Long id;

    private String imagen;

    private Integer sequence;
    
    @JsonIgnore
    private ImageView frontView;

    @JsonIgnore
    private Producto producto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public ImagenProducto(Long id, String imagen, Integer sequence) {
        super();
        this.id = id;
        this.imagen = imagen;
        this.sequence = sequence;
    }

    public ImagenProducto(ImageView frontView) {
        this.frontView = frontView;
    }
    
    

    public ImagenProducto() {
        super();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public ImageView getFrontView() {
        return frontView;
    }

    public void setFrontView(ImageView frontView) {
        this.frontView = frontView;
    }
    
    
}
