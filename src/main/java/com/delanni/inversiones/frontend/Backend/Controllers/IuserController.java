/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Controllers;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.Usuario;
import com.delanni.inversiones.frontend.Backend.Interfaces.IUser;
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
import java.util.Map;

/**
 *
 * @author Jesusecm
 */
public class IuserController implements IUser{
    
    private static String server = App.AppIP;
    
    private ObjectMapper mapeo;

    public IuserController() {
        this.mapeo = new ObjectMapper();
    }
    
    

    @Override
    public Usuario obtenerSeguridad(Usuario user) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/usuarios/usersecurity/consulta/seguridad?username=".concat(user.getUsername()))))
                    .header("Content-Type", "application/json")
                    .header("system", App.system)
                    .header("provider", App.provider)
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return (mapeo.readValue(response.body(), Usuario.class));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public Usuario validarRespuesta(Usuario user) {
       try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/usuarios/usersecurity/validar/seguridad")))
                    .header("Content-Type", "application/json")
                    .header("system", App.system)
                    .header("provider", App.provider)
                    .POST(HttpRequest.BodyPublishers.ofString(mapeo.writeValueAsString(user)))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return (mapeo.readValue(response.body(), Usuario.class));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public Usuario guardarSeguridad(Usuario user) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/usuarios/usersecurity/guardar/seguridad")))
                    .header("Content-Type", "application/json")
                    .header("system", App.system)
                   // .header("Authorization", "Bearer ".concat(App.authinfo.getAccess_token()))
                    .header("provider", App.provider)
                    .POST(HttpRequest.BodyPublishers.ofString(mapeo.writeValueAsString(user)))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return (mapeo.readValue(response.body(), Usuario.class));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return null;
    }

    @Override
    public int cambioContrasena(Usuario user) {
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/usuarios/usersecurity/cambiar/contrasena")))
                    .header("Content-Type", "application/json")
                    .header("system", App.system)
                    //.header("Authorization", "Bearer ".concat(App.authinfo.getAccess_token()))
                    .header("provider", App.provider)
                    .POST(HttpRequest.BodyPublishers.ofString(mapeo.writeValueAsString(user)))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            return (mapeo.readValue(response.body(), int.class));
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

        }
        return -1;
    }
    
}
