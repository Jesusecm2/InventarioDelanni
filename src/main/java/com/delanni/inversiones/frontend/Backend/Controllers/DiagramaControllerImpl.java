/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Controllers;

import com.delanni.inversiones.frontend.Backend.Conection.Conexion;
import com.delanni.inversiones.frontend.Backend.Conection.Peticion;
import com.delanni.inversiones.frontend.Backend.Conection.Transaccional;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.TipodePago;
import com.delanni.inversiones.frontend.Backend.Interfaces.DiagramaController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 *
 * @author Jesusecm
 */
public class DiagramaControllerImpl implements DiagramaController {

    private Transaccional trans;
    private ObjectMapper mapeo;
    private Conexion conn;
    private Peticion pet;

    public DiagramaControllerImpl() {
        this.mapeo = new ObjectMapper();
        this.trans = new Transaccional(new Conexion());
        this.conn = new Conexion();
        this.pet = new Peticion();
    }

    @Override
    public LinkedHashMap<String, String> obtenerDetalleSemanal() {
 try {
            trans.HttpGetObject("/api/inventario/graficos/trans/semanal", pet);
            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return mapeo.readValue(pet.getCuerpo().get("response"), LinkedHashMap.class);
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
