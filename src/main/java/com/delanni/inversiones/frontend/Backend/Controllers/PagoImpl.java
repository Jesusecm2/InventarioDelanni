/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Controllers;

import com.delanni.inversiones.frontend.Backend.Conection.Conexion;
import com.delanni.inversiones.frontend.Backend.Conection.Peticion;
import com.delanni.inversiones.frontend.Backend.Conection.Transaccional;
import com.delanni.inversiones.frontend.Backend.Entity.Categoria;
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Moneda;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Pago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.TipodePago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.ValorMoneda;
import com.delanni.inversiones.frontend.Backend.Entity.TpIngreso;
import com.delanni.inversiones.frontend.Backend.Entity.Transacciones;
import com.delanni.inversiones.frontend.Backend.Interfaces.PagoBackend;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
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

    public PagoImpl() {
        this.mapeo = new ObjectMapper();
        this.trans = new Transaccional(new Conexion());
        this.conn = new Conexion();
        this.pet = new Peticion();
    }

    @Override
    public List<TipodePago> obtenerTiposPago() {
        try {
            trans.HttpGetObject("/api/inventario/pago/tipospago", pet);
            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return (Arrays.asList(mapeo.readValue(pet.getCuerpo().get("response"), TipodePago[].class)));
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Moneda> obtenerMonedas() {
        try {
            trans.HttpGetObject("/api/inventario/pago/moneda", pet);
            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return (Arrays.asList(mapeo.readValue(pet.getCuerpo().get("response"), Moneda[].class)));
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ValorMoneda obtenerValorMonedaHoy(Moneda param) {
        try {
            pet.addBody("request", mapeo.writeValueAsString(param));
            trans.HttpPostObject("/api/inventario/pago/valorMoneda/hoy", pet);
            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return mapeo.readValue(pet.getCuerpo().get("response"), ValorMoneda.class);
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ValorMoneda guardarValorMoneda(ValorMoneda param) {
        try {
            pet.addBody("request", mapeo.writeValueAsString(param));
            trans.HttpPostObject("/api/inventario/pago/valorMoneda/guardar", pet);
            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return mapeo.readValue(pet.getCuerpo().get("response"), ValorMoneda.class);
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Pago guardarPagoFactura(Factura factura, Pago pago) {
        Map<String, String> valores = new HashMap<>();

        try {
            valores.put("factura", mapeo.writeValueAsString(factura));
            valores.put("pago", mapeo.writeValueAsString(pago));
            pet.addBody("request", mapeo.writeValueAsString(valores));

            trans.HttpPostObject("/api/inventario/pago/guardar/pagofactura", pet);
            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return mapeo.readValue(pet.getCuerpo().get("response"), Pago.class);
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TpIngreso> obtenerIngreso(String tipo) {
        try {
            pet.addParameter("tipo", tipo);
            trans.HttpGetObject("/api/inventario/pago/obtener/tingreso", pet);

            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return (Arrays.asList(mapeo.readValue(pet.getCuerpo().get("response"), TpIngreso[].class)));
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Pago guardarPagoIngreso(TpIngreso ingreso, Pago pago) {
        Map<String, String> valores = new HashMap<>();

        try {
            valores.put("ingreso", mapeo.writeValueAsString(ingreso));
            valores.put("pago", mapeo.writeValueAsString(pago));
            pet.addBody("request", mapeo.writeValueAsString(valores));

            trans.HttpPostObject("/api/inventario/pago/guardar/pagoingreso", pet);
            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return mapeo.readValue(pet.getCuerpo().get("response"), Pago.class);
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Transacciones> obtenerEgresos() {
        try {
            trans.HttpGetObject("/api/inventario/pago/obtener/Egresos", pet);

            if (pet.getCabecera().get("resp_cod").equals("200")) {
                return (Arrays.asList(mapeo.readValue(pet.getCuerpo().get("response"), Transacciones[].class)));
            } else {
                return null;
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
