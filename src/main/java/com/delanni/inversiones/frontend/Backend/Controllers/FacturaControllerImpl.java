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
import com.delanni.inversiones.frontend.Backend.Entity.Cuenta;
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Pago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.ValorMoneda;
import com.delanni.inversiones.frontend.Backend.Entity.Proveedor;
import com.delanni.inversiones.frontend.Backend.Interfaces.FacturaBackend;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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

    private final String provider = "Inversiones Delanni App 1.0";
    private final String system = System.getProperty("os.name");

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
    public List<Factura> listadoFacturas(Proveedor id) {
        try {
            //pet.addBody("request", mapeo.writeValueAsString(categoria));
            pet.addParameter("id", String.valueOf(id.getId()));
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
    public List<Factura> listadoFacturas() {
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
    public List<Factura> listadoFacturasNotNull() {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/factura/listado/nonulo")))
                    .header("Content-Type", "application/json")
                    .header("system", system)
                    .header("provider", provider)
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

    @Override
    public List<Factura> listadoFacturas(Proveedor prov, String parametro) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/factura/buscar/estatusProveedor?id=")
                            .concat(String.valueOf(prov.getId()))
                            .concat("&").concat("status=").concat(parametro)))
                    .header("Content-Type", "application/json")
                    .header("system", system)
                    .header("provider", provider)
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

    @Override
    public List<Factura> listadoFacturas(String parametro) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/factura/buscar/estatus?status=").concat(parametro)))
                    .header("Content-Type", "application/json")
                    .header("system", system)
                    .header("provider", provider)
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

    @Override
    public List<Factura> listadoVentas() {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/factura/cliente")))
                    .header("Content-Type", "application/json")
                    .header("system", system)
                    .header("provider", provider)
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

    @Override
    public List<Factura> listadoVentas(Cliente cl) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/factura/cliente?cliente=").concat(String.valueOf(cl.getId()))))
                    .header("Content-Type", "application/json")
                    .header("system", system)
                    .header("provider", provider)
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

    @Override
    public List<Factura> listadoVentas(Cliente cl, String sts) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/factura/cliente?cliente=")
                            .concat(String.valueOf(cl.getId()))
                            .concat("&sts=").concat(sts)))
                    .header("Content-Type", "application/json")
                    .header("system", system)
                    .header("provider", provider)
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

    @Override
    public List<Factura> listadoFacturas(Date start) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/factura/buscar?date=").concat(start.toString())))
                    .GET()
                    .header("Content-Type", "application/json")
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            return Arrays.asList(mapeo.readValue(response.body(), Factura[].class));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public List<Factura> listadoVentas(Date date) {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
         try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/factura/buscar/cliente?date=").concat(formato.format(date))))
                    .GET()
                    .header("Content-Type", "application/json")
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return Arrays.asList(mapeo.readValue(response.body(), Factura[].class));
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
    public List<Factura> listadoVentas(Cliente cl, Date start) {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
         try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/inventario/factura/buscar/cliente?date=").concat(formato.format(start))
                            .concat("&id=").concat(String.valueOf(cl.getId()))))
                    .GET()
                    .header("Content-Type", "application/json")
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return Arrays.asList(mapeo.readValue(response.body(), Factura[].class));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public InputStream reporteFactura(Factura factura) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/reporte/producto/factura?id_factura=").concat(String.valueOf(factura.getId()))))
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

}
