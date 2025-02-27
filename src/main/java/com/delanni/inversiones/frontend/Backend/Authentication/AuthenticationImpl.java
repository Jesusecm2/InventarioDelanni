/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Authentication;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Conection.Conexion;
import com.delanni.inversiones.frontend.Backend.Conection.Peticion;
import com.delanni.inversiones.frontend.Backend.Conection.Transaccional;
import com.delanni.inversiones.frontend.Backend.Entity.Cuenta;
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Jesusecm
 */
public class AuthenticationImpl implements IAuthentication {

    //  private final Conexion conexion;
    private Scanner scan;
    private final ObjectMapper map;
    private Peticion peticion;
    private Transaccional transaccional;
    private final String server = App.AppIP;

    private final String provider = "Inversiones Delanni App 1.0";
    private final String system = System.getProperty("os.name");

    public AuthenticationImpl() {
        //this.conexion = conexion;
        this.map = new ObjectMapper();
        //this.peticion = peticion;
        //this.transaccional = trans;
        this.peticion = new Peticion();
    }

    @Override
    public AuthenticationInfo getToken(Usuario user) {
        String usr = "admin";
        String pss = "12345";
        String auth = usr+":"+pss;
        String encoded = Base64.getEncoder().encodeToString(auth.getBytes());
        try {
            HttpRequest requested = HttpRequest.newBuilder()
                    .uri(new URI(server.concat("/api/security/oauth/token?")
                            .concat("username=")
                          .concat(user.getUsername().concat("&password=").concat(user.getPassword())
                                   .concat("&grant_type=").concat("password"))
                    ))
                    
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Basic "+encoded)
                    .header("system", system)
                    .header("provider", provider)
                    
                    .POST(HttpRequest.BodyPublishers.ofString(map.writeValueAsString(user)))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(requested, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode()==200){
                return new AuthenticationInfo(map.readValue(response.body(), Map.class));
            }else{
                return null;
            }
            
        } catch (URISyntaxException ex) {

        } catch (IOException ex) {

        } catch (InterruptedException ex) {

            /* peticion.addParameter("username", user.getUsername());
            peticion.addParameter("password", user.getPassword());
            peticion.addParameter("grant_type", "password");
            this.transaccional.PostFormEncoded("/api/security/oauth/token", peticion);
            if (peticion != null && peticion.getCuerpo() != null) {
            Conexion.ultima = this.peticion;
            if (peticion.getCabecera().get("resp_code") == "success") {
            if (peticion.getCabecera().get("resp_cod").equals("200")) {
            try {
            return new AuthenticationInfo(map.readValue(peticion.getCuerpo().get("response"), Map.class));
            } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            return null;
            }
            }else{
            
            }
            
            } else {
            return null;
            }
            }*/
        }

        //Logger.getLogger(AuthenticationImpl.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }

    @Override
    public AuthenticationInfo updateToken(String token) {
        peticion.addParameter("grant_type", "refresh_token");
        peticion.addParameter("refresh_token", token);
        this.transaccional.PostFormEncoded("/api/security/oauth/token", peticion);
        if (peticion != null && peticion.getCuerpo() != null) {
            Conexion.ultima = this.peticion;

            if (peticion.getCabecera().get("resp_code") == "success") {
                try {
                    return new AuthenticationInfo(map.readValue(peticion.getCuerpo().get("resp_body"), Map.class
                    ));
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        }
        return null;
    }

    public Peticion getPeticion() {
        return peticion;
    }

    public void setPeticion(Peticion peticion) {
        this.peticion = peticion;
    }

}
