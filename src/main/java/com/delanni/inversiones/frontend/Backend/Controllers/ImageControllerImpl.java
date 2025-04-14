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
import com.delanni.inversiones.frontend.Backend.Interfaces.ImagenController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

    private final String server = App.AppIP;

    private final String provider = "Inversiones Delanni App 1.0";
    private final String system = System.getProperty("os.name");

    public ImageControllerImpl() {
        this.mapeo = new ObjectMapper();
        this.trans = new Transaccional(new Conexion());
        this.conn = new Conexion();
        this.pet = new Peticion();

    }

    @Override
    public String imageString(String url) {

        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/archivo/archivos/consultar/imagen?path=").concat(url)))
                    .GET()
                    .header("Content-Type", "application/json")
                    .header("system", system)
                    .header("provider", provider)
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            Map<String,String> valores =  mapeo.readValue(response.body(), Map.class);
            return valores.get("imagen");
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;

    }

}
