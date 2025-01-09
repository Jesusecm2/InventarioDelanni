/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Controllers;

import com.delanni.inversiones.frontend.Backend.Conection.Conexion;
import com.delanni.inversiones.frontend.Backend.Conection.Peticion;
import com.delanni.inversiones.frontend.Backend.Conection.Transaccional;
import com.delanni.inversiones.frontend.Backend.Entity.Categoria;
import com.delanni.inversiones.frontend.Backend.Entity.ImagenProducto;
import com.delanni.inversiones.frontend.Backend.Entity.Producto;
import com.delanni.inversiones.frontend.Backend.Interfaces.InventarioBackend;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.ResponseCache;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Jesusecm
 */
public class InventarioControllerImpl implements InventarioBackend {

    private Transaccional trans;
    private ObjectMapper mapeo;
    private Conexion conn;
    private Peticion pet;

    public InventarioControllerImpl() {
        this.mapeo = new ObjectMapper();
        this.trans = new Transaccional(new Conexion());
        this.conn = new Conexion();
        this.pet = new Peticion();
    }

    @Override
    public Categoria GuardarCategoria(Categoria categoria) {
        try {
            pet.addBody("request", mapeo.writeValueAsString(categoria));
            trans.HttpPostObject("/api/inventario/inventario/categoria/guardar", pet);
            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return mapeo.readValue(pet.getCuerpo().get("response"), Categoria.class);
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Categoria ObtenerCategoriaId(Categoria categoria) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Categoria> ListadoCategoria() {
        try {
            //pet.addBody("request", mapeo.writeValueAsString(categoria));
            trans.HttpGetObject("/api/inventario/inventario/categoria/listado", pet);
            if (pet.getCabecera().get("resp_cod").equals("200")) {
                if (!pet.getCuerpo().get("response").isBlank()) {
                    return (Arrays.asList(mapeo.readValue(pet.getCuerpo().get("response"), Categoria[].class)));
                }

                return new ArrayList<>();
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Producto GuardarProducto(Producto producto, String categoria, Double valor) {
        try {
            pet.addBody("request", mapeo.writeValueAsString(producto));
            pet.addParameter("action", categoria);
            pet.addParameter("value", String.valueOf(valor));
            trans.HttpPostObject("/api/inventario/inventario/producto/guardar", pet);
            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return mapeo.readValue(pet.getCuerpo().get("response"), Producto.class);
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Producto> ListadoProducto() {
        try {
            trans.HttpGetObject("/api/inventario/inventario/producto/lista", pet);
            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return Arrays.asList(mapeo.readValue(pet.getCuerpo().get("response"), Producto[].class));
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Producto> ListadoProducto(Categoria cat) {
        try {
            pet.addParameter("id", String.valueOf(cat.getId()));
            trans.HttpGetObject("/api/inventario/inventario/producto/listacat", pet);

            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return Arrays.asList(mapeo.readValue(pet.getCuerpo().get("response"), Producto[].class));
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Producto> obtenerProducto(String cod) {
        try {
            pet.addParameter("cod", cod);
            trans.HttpGetObject("/api/inventario/inventario/producto/codigo", pet);
            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return Arrays.asList(mapeo.readValue(pet.getCuerpo().get("response"), Producto[].class));
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Producto> buscarNombre(String nombre) {
        try {
            pet.addParameter("nombre", nombre);
            trans.HttpGetObject("/api/inventario/inventario/producto/nombre", pet);
            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return Arrays.asList(mapeo.readValue(pet.getCuerpo().get("response"), Producto[].class));
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void EliminarImagen(ImagenProducto obj) {
        pet.addParameter("obj",obj.getId().toString());
        trans.HttpPostObject("/api/inventario/inventario/imagen/eliminar", pet);
        if (pet.getCabecera().get("resp_cod").equals("200")) {
            
        }
    }

}
