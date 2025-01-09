/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Controllers;

import com.delanni.inversiones.frontend.Backend.Conection.Conexion;
import com.delanni.inversiones.frontend.Backend.Conection.Peticion;
import com.delanni.inversiones.frontend.Backend.Conection.Transaccional;
import com.delanni.inversiones.frontend.Backend.Entity.Categoria;
import com.delanni.inversiones.frontend.Backend.Interfaces.ImagenController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

/**
 *
 * @author Jesusecm
 */
public class ImageControllerImpl implements ImagenController {

    private Transaccional trans;
    private ObjectMapper mapeo;
    private Conexion conn;
    private Peticion pet;

    public ImageControllerImpl() {
        this.mapeo = new ObjectMapper();
        this.trans = new Transaccional(new Conexion());
        this.conn = new Conexion();
        this.pet = new Peticion();

    }

    @Override
    public String imageString(String url) {
        try {
            pet.addParameter("path", url);
            trans.HttpGetObject("/api/archivo/archivos/consultar/imagen", pet);
            if (pet.getCabecera().get("resp_cod").equals("200")) {
                Map<String,Object> respuesta = mapeo.readValue(pet.getCuerpo().get("response"), Map.class);
                return String.valueOf(respuesta.get("imagen"));
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
