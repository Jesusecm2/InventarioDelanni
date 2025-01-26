/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Authentication;

import com.delanni.inversiones.frontend.Backend.Conection.Conexion;
import com.delanni.inversiones.frontend.Backend.Conection.Peticion;
import com.delanni.inversiones.frontend.Backend.Conection.Transaccional;
import com.delanni.inversiones.frontend.Backend.Entity.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
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

    public AuthenticationImpl() {
        //this.conexion = conexion;
        this.map = new ObjectMapper();
        //this.peticion = peticion;
        //this.transaccional = trans;
        this.peticion = new Peticion();
    }

    @Override
    public AuthenticationInfo getToken(Usuario user) {
        try {
            System.out.println(map.writeValueAsString(user));
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
         
        } catch (JsonProcessingException ex) {
            //Logger.getLogger(AuthenticationImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                    return new AuthenticationInfo(map.readValue(peticion.getCuerpo().get("resp_body"), Map.class));
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
