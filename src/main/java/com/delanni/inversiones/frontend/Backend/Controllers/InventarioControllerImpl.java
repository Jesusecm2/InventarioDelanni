/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Controllers;

import com.delanni.inversiones.frontend.Backend.Conection.Conexion;
import com.delanni.inversiones.frontend.Backend.Conection.Peticion;
import com.delanni.inversiones.frontend.Backend.Conection.Transaccional;
import com.delanni.inversiones.frontend.Backend.Entity.Categoria;
import com.delanni.inversiones.frontend.Backend.Entity.Cliente;
import com.delanni.inversiones.frontend.Backend.Entity.ImagenProducto;
import com.delanni.inversiones.frontend.Backend.Entity.Producto;
import com.delanni.inversiones.frontend.Backend.Interfaces.InventarioBackend;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.ResponseCache;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
    private final String server = "http://localhost:8090";

    public InventarioControllerImpl() {
        this.mapeo = new ObjectMapper();
        this.trans = new Transaccional(new Conexion());
        this.conn = new Conexion();
        this.pet = new Peticion();
    }

    @Override
    public Categoria GuardarCategoria(Categoria categoria) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/categoria/guardar")))
                    .POST(HttpRequest.BodyPublishers.ofString(mapeo.writeValueAsString(categoria)))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return mapeo.readValue(response.body(), Categoria.class);
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

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
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/categoria/listado")))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return (Arrays.asList(mapeo.readValue(response.body(), Categoria[].class)));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

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
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/producto/listacat")))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return Arrays.asList(mapeo.readValue(response.body(), Producto[].class));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public List<Producto> obtenerProducto(String cod) {

        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/producto/codigo?cod=".concat(cod))))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return Arrays.asList(mapeo.readValue(response.body(), Producto[].class));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

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
        pet.addParameter("obj", obj.getId().toString());
        trans.HttpPostObject("/api/inventario/inventario/imagen/eliminar", pet);
        if (pet.getCabecera().get("resp_cod").equals("200")) {

        }
    }

    @Override
    public Cliente guardarCliente(Cliente save) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/cliente/guardar")))
                    .POST(HttpRequest.BodyPublishers.ofString(mapeo.writeValueAsString(save)))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return mapeo.readValue(response.body(), Cliente.class);
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public Cliente BuscarCedulaCliente(String cedula) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/cliente/cedula?cd=".concat(cedula))))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return mapeo.readValue(response.body(), Cliente.class);
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public List<Cliente> listadoCliente() {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/cliente/todos")))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return Arrays.asList(mapeo.readValue(response.body(), Cliente[].class));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

}
