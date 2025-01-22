/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Controllers;

import com.delanni.inversiones.frontend.Backend.Conection.Conexion;
import com.delanni.inversiones.frontend.Backend.Conection.Peticion;
import com.delanni.inversiones.frontend.Backend.Conection.Transaccional;
import com.delanni.inversiones.frontend.Backend.Entity.Categoria;
import com.delanni.inversiones.frontend.Backend.Entity.Cuenta;
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Pago;
import com.delanni.inversiones.frontend.Backend.Entity.Proveedor;
import com.delanni.inversiones.frontend.Backend.Interfaces.FacturaBackend;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jesusecm
 */
public class FacturaControllerImpl implements FacturaBackend {

    private Transaccional trans;
    private ObjectMapper mapeo;
    private Conexion conn;
    private Peticion pet;
    private final String server = "http://localhost:8090";
    

    public FacturaControllerImpl() {
        this.mapeo = new ObjectMapper();
        this.trans = new Transaccional(new Conexion());
        this.conn = new Conexion();
        this.pet = new Peticion();

    }

    @Override
    public Proveedor guardarProveedor(Proveedor save) {
        try {
            pet.addBody("request", mapeo.writeValueAsString(save));
            trans.HttpPostObject("/api/inventario/inventario/factura/proveedor/guardar", pet);
            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return mapeo.readValue(pet.getCuerpo().get("response"), Proveedor.class);
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    @Override
    public Cuenta guardarCuenta(Cuenta cuenta) {
        try {
            pet.addBody("request", mapeo.writeValueAsString(cuenta));
            trans.HttpPostObject("/api/inventario/inventario/factura/cuenta/guardar", pet);
            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return mapeo.readValue(pet.getCuerpo().get("response"), Cuenta.class);
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    @Override
    public List<Proveedor> listadoProveedor() {
        try {
            //pet.addBody("request", mapeo.writeValueAsString(categoria));
            trans.HttpGetObject("/api/inventario/inventario/factura/proveedor/listado", pet);
            if (pet.getCabecera().get("resp_cod").equals("200")) {

                return (Arrays.asList(mapeo.readValue(pet.getCuerpo().get("response"), Proveedor[].class)));
                //return cat;
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Factura guardarFactura(Factura factura, List<Pago> pago) {
        Map<String, String> valores = new HashMap<>();

        try {
            valores.put("factura", mapeo.writeValueAsString(factura));
            valores.put("pagos", mapeo.writeValueAsString(pago));
            pet.addBody("request", mapeo.writeValueAsString(valores));

            trans.HttpPostObject("/api/inventario/inventario/factura/guardar", pet);
            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return mapeo.readValue(pet.getCuerpo().get("response"), Factura.class);
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Factura> facturaProveedor(Integer id) {
        try {
            //pet.addBody("request", mapeo.writeValueAsString(categoria));
            pet.addParameter("id", String.valueOf(id));
            trans.HttpGetObject("/api/inventario/inventario/factura/proveedor/buscar", pet);

            if (pet.getCabecera().get("resp_cod").equals("200")) {

                return (Arrays.asList(mapeo.readValue(pet.getCuerpo().get("response"), Factura[].class)));
                //return cat;
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Factura> listadoFactura() {
        try {
            trans.HttpGetObject("/api/inventario/inventario/factura/listado", pet);

            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return (Arrays.asList(mapeo.readValue(pet.getCuerpo().get("response"), Factura[].class)));
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Factura> listadoFacturaNonuloProveedor() {
         try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/factura/listado/nonulo")))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return (Arrays.asList(mapeo.readValue(response.body(), Factura[].class)));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

}
