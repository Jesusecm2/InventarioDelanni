/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Controllers;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Entity.Categoria;
import com.delanni.inversiones.frontend.Backend.Entity.DailyAudit;
import com.delanni.inversiones.frontend.Backend.Entity.SystemParam;
import com.delanni.inversiones.frontend.Backend.Interfaces.ConfigSystem;
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
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Jesusecm
 */
public class ConfigSystemImpl implements ConfigSystem {

    private final String server = App.AppIP;
    private ObjectMapper mapeo;

    public ConfigSystemImpl() {
        mapeo = new ObjectMapper();
    }

    @Override
    public SystemParam guardarParametro(SystemParam guardar) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/configuracion/parametros/guardar")))
                    .POST(HttpRequest.BodyPublishers.ofString(mapeo.writeValueAsString(guardar)))
                    .header("Content-Type", "application/json")
                    //  .header("system", system)
                    // .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return mapeo.readValue(response.body(), SystemParam.class);
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public SystemParam obtenerParametro(Integer table, String referencia) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/configuracion/parametros/buscar/tabla/referencia?table=").concat(String.valueOf(table)).concat("&reference=").concat(referencia)))
                    .GET()
                    // .header("system", system)
                    //  .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return mapeo.readValue(response.body(), SystemParam.class);
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
    public List<SystemParam> obtenerParametro(Integer table) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/configuracion/parametros/buscar/tabla?table=").concat(String.valueOf(table))))
                    .GET()
                    // .header("system", system)
                    //  .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return Arrays.asList(mapeo.readValue(response.body(), SystemParam[].class));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public List<DailyAudit> obtenerLogs(Date date) {
         try {
             SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/auditoria/auditoria/obtener/fecha?fecha=").concat(String.valueOf(formato.format(date)))))
                    .GET()
                    // .header("system", system)
                    //  .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return Arrays.asList(mapeo.readValue(response.body(), DailyAudit[].class));
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
ex.printStackTrace();
        } catch (InterruptedException ex) {
ex.printStackTrace();
        }
        return null;
    }

}
