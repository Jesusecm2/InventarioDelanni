/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Controllers;

import com.delanni.inversiones.frontend.App;
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
import java.io.InputStream;
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
import java.util.Map;


/**
 *
 * @author Jesusecm
 */
public class InventarioControllerImpl implements InventarioBackend {
    
    private Transaccional trans;
    private ObjectMapper mapeo;
    private Conexion conn;
    private Peticion pet;
    private final String server = App.AppIP;
    
    private final String provider = "Inversiones Delanni App 1.0";
    private final String system = System.getProperty("os.name");
    private List<String> errors;
    
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
                    .header("system", system)
                    .header("provider", provider)
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
                    .header("system", system)
                    .header("provider", provider)
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
        HttpResponse<String> response = null;
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/producto/guardar?action=")
                            .concat(categoria)
                            .concat("&")
                            .concat("value=").concat(String.valueOf(valor))))
                    .POST(HttpRequest.BodyPublishers.ofString(mapeo.writeValueAsString(producto)))
                    .header("Content-Type", "application/json")
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return mapeo.readValue(response.body(), Producto.class);
        } catch (URISyntaxException ex) {
            
        } catch (IOException ex) {
            if(response.statusCode()==400){
                try {
                    Map<String,Object> error = mapeo.readValue(response.body(), Map.class);
                    errors = (List<String>)error.get("errors");
                } catch (JsonProcessingException ex1) {
                    ex.printStackTrace();
                }
            }
        } catch (InterruptedException ex) {
            //mapeo.readValue(response.body(), Producto.class);
            System.out.println("Error Interrumped"+response.statusCode());
        }
        return null;
    }
    
    @Override
    public List<Producto> ListadoProducto() {
        
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/producto/lista")))
                    .GET()
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            
            return (Arrays.asList(mapeo.readValue(response.body(), Producto[].class)));
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
             ex.printStackTrace();
        } catch (InterruptedException ex) {
             ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<Producto> ListadoProducto(Categoria cat) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/producto/listacat?id=").concat(String.valueOf(cat.getId()))))
                    .GET()
                    .header("system", system)
                    .header("provider", provider)
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
                    .header("system", system)
                    .header("provider", provider)
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
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/producto/nombre?nombre=".concat(nombre))))
                    .GET()
                    .header("system", system)
                    .header("provider", provider)
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
                    .header("system", system)
                    .header("provider", provider)
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
                    .header("system", system)
                    .header("provider", provider)
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
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return Arrays.asList(mapeo.readValue(response.body(), Cliente[].class));
        } catch (URISyntaxException ex) {
            
        } catch (IOException ex) {
            
        } catch (InterruptedException ex) {
            
        }
        return null;
    }
    
    @Override
    public List<Producto> buscarCategoriaNombre(Categoria cat, String nombre) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/producto/catnombre?id=")
                            .concat(String.valueOf(cat.getId())
                                    .concat("&").concat("nombre=")
                                    .concat(nombre))))
                    .GET()
                    .header("system", system)
                    .header("provider", provider)
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
    public InputStream reporteProductoCategoria(Categoria categoria) {
            try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/reporte/producto/categoria?id_Categoria=").concat(String.valueOf(categoria.getId()))))
                    .GET()
                    .header("Content-Type", "application/json")
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<InputStream> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofInputStream());
            return response.body();
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }

    
    
    
}
