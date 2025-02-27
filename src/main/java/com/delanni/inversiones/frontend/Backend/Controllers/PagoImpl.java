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
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Moneda;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Pago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.TipodePago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.ValorMoneda;
import com.delanni.inversiones.frontend.Backend.Entity.Producto;
import com.delanni.inversiones.frontend.Backend.Entity.TpIngreso;
import com.delanni.inversiones.frontend.Backend.Entity.Transacciones;
import com.delanni.inversiones.frontend.Backend.Interfaces.PagoBackend;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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
public class PagoImpl implements PagoBackend {

    private Transaccional trans;
    private ObjectMapper mapeo;
    private Conexion conn;
    private Peticion pet;

    private final String server = App.AppIP;

    private final String provider = "Inversiones Delanni App 1.0";
    private final String system = System.getProperty("os.name");

    public PagoImpl() {
        this.mapeo = new ObjectMapper();
        this.trans = new Transaccional(new Conexion());
        this.conn = new Conexion();
        this.pet = new Peticion();
    }

    @Override
    public List<TipodePago> obtenerTiposPago() {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/pago/tipospago")))
                    .GET()
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return (Arrays.asList(mapeo.readValue(response.body(), TipodePago[].class)));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public List<Moneda> obtenerMonedas() {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/pago/moneda")))
                    .GET()
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return (Arrays.asList(mapeo.readValue(response.body(), Moneda[].class)));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public ValorMoneda obtenerValorMonedaHoy(Moneda param) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/pago/valorMoneda/hoy")))
                    .POST(HttpRequest.BodyPublishers.ofString(mapeo.writeValueAsString(param)))
                    .header("Content-Type", "application/json")
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return mapeo.readValue(response.body(), ValorMoneda.class);
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public ValorMoneda guardarValorMoneda(ValorMoneda param) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/pago/valorMoneda/guardar")))
                    .POST(HttpRequest.BodyPublishers.ofString(mapeo.writeValueAsString(param)))
                    .header("Content-Type", "application/json")
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return mapeo.readValue(response.body(), ValorMoneda.class);
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public Pago guardarPagoFactura(Factura factura, Pago pago) {
        Map<String, String> valores = new HashMap<>();
        try {
            valores.put("factura", mapeo.writeValueAsString(factura));
            valores.put("pago", mapeo.writeValueAsString(pago));
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/pago/guardar/pagofactura")))
                    .POST(HttpRequest.BodyPublishers.ofString(mapeo.writeValueAsString(valores)))
                    .header("Content-Type", "application/json")
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return mapeo.readValue(response.body(), Pago.class);
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {
        }
        return null;
    }

    @Override
    public List<TpIngreso> obtenerIngreso(String tipo) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/pago/obtener/tingreso?tipo=".concat(tipo))))
                    .GET()
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return (Arrays.asList(mapeo.readValue(response.body(), TpIngreso[].class)));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public Pago guardarPagoIngreso(TpIngreso ingreso, Pago pago) {
        Map<String, String> valores = new HashMap<>();
        try {
            valores.put("ingreso", mapeo.writeValueAsString(ingreso));
            valores.put("pago", mapeo.writeValueAsString(pago));
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/pago/guardar/pagoingreso")))
                    .POST(HttpRequest.BodyPublishers.ofString(mapeo.writeValueAsString(valores)))
                    .header("Content-Type", "application/json")
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return mapeo.readValue(response.body(), Pago.class);
        } catch (URISyntaxException ex) {
        } catch (IOException ex) {
        } catch (InterruptedException ex) {
        }
        return null;
    }

    @Override
    public List<Transacciones> obtenerEgresos() {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/pago/obtener/egresos/dia")))
                    .GET()
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return (Arrays.asList(mapeo.readValue(response.body(), Transacciones[].class)));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public List<Transacciones> obtenerIngresos() {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/pago/obtener/ingresos/dia")))
                    .GET()
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return (Arrays.asList(mapeo.readValue(response.body(), Transacciones[].class)));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public ValorMoneda guardarValorMoneda(ValorMoneda param, Date date) {
        try {
            
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/pago/valorMoneda/guardar?date=").concat(new SimpleDateFormat("dd-MM-yyyy").format(date))))
                    .POST(HttpRequest.BodyPublishers.ofString(mapeo.writeValueAsString(param)))
                    .header("Content-Type", "application/json")
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return mapeo.readValue(response.body(), ValorMoneda.class);
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public List<Transacciones> obtenerVentasHoy() {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/pago/obtener/ventas/dia")))
                    .GET()
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return (Arrays.asList(mapeo.readValue(response.body(), Transacciones[].class)));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public List<Transacciones> obtenerPago(Factura factura) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/pago/obtener/pagos/factura?id_factura=").concat(String.valueOf(factura.getId()))))
                    .GET()
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return (Arrays.asList(mapeo.readValue(response.body(), Transacciones[].class)));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public List<Transacciones> obtenerTransacciones(Factura factura) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/pago/obtener/pagos/factura?id_factura=").concat(String.valueOf(factura.getId()))))
                    .GET()
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return (Arrays.asList(mapeo.readValue(response.body(), Transacciones[].class)));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public List<Transacciones> obtenerTransacciones(TpIngreso tp) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/pago/obtener/pagos/tpPago?id=").concat(String.valueOf(tp.getId()))))
                    .GET()
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return (Arrays.asList(mapeo.readValue(response.body(), Transacciones[].class)));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public List<Transacciones> obtenerTransacciones(Date inicio, Date fin, TpIngreso tp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Transacciones> obtenerTransacciones(Date start, Date end) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Transacciones> obtenerVentas(Date start, Date end) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Transacciones> obtenerIngresosEgresos(Date start, Date end) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/pago/obtener/pagos/tpPago/date?dt1=").concat(format.format(start)
                            .concat("&dt2=").concat(format.format(end)))))
                    .GET()
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return (Arrays.asList(mapeo.readValue(response.body(), Transacciones[].class)));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public ValorMoneda obtenerValorMoneda(Moneda mon, Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/inventario/pago/valorMoneda/fecha?date=").concat(format.format(date))))
                    .POST(HttpRequest.BodyPublishers.ofString(mapeo.writeValueAsString(mon)))
                    .header("Content-Type", "application/json")
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return mapeo.readValue(response.body(), ValorMoneda.class);
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

}
