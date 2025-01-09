/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Conection;

import com.delanni.inversiones.frontend.Backend.Interfaces.Transaccion;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Alerta;
import java.io.IOException;
import java.net.HttpURLConnection;
import javafx.scene.control.Alert;

/**
 *
 * @author Jesusecm
 */
public class Transaccional implements Transaccion {

    private Conexion con;

    public Transaccional(Conexion con) {
        this.con = con;
    }

    @Override
    public Peticion HttpGetObject(String url, Peticion peticion) {
        String value = null;
        try {
            if (!peticion.getUrlparameters().isEmpty()) {
                url = url + peticion.getParameters();
            }
            HttpURLConnection request = this.con.HttpGet(url, true, true, true);
            Conexion.ultima = peticion;
            this.con.conectar();

            if (request.getResponseCode() == 404) {
                return null;
            } else if (request.getResponseCode() == 400) {

                setHeader(request, peticion);
                peticion.addBody("response", this.con.getBuild().toString());

            } else {
                setHeader(request, peticion);
                peticion.addBody("response", this.con.getBuild().toString());
            }

        } catch (IOException ex) {
            peticion = null;
        }
        return peticion;
    }

    @Override
    public Peticion HttpPostObject(String url, Peticion peticion) {
        String value = null;

        try {
            if (!peticion.getUrlparameters().isEmpty()) {
                url = url + peticion.getParameters();
            }
            if (!peticion.getCuerpo().isEmpty()) {
                value = peticion.getCuerpo().get("request");
                System.out.println(value);
            }
            Conexion.ultima = peticion;
            HttpURLConnection request = this.con.HttpPost(url, true, true, true, value);
            this.con.conectar();
            switch (request.getResponseCode()) {
                case 404:
                    Alerta.getAlert(Alert.AlertType.ERROR, "Error de Conexion " + request.getResponseCode(), request.getResponseMessage(), null).showAndWait();
                    setHeader(request, peticion);
                    peticion.addBody("response", this.con.getBuild().toString());
                    return null;
                case 405:
                    
                    Alerta.getAlert(Alert.AlertType.ERROR, "Error de Conexion " + request.getResponseCode(), request.getResponseMessage(), null).showAndWait();
                    setHeader(request, peticion);
                    peticion.addBody("response", this.con.getBuild().toString());
                    return null;
                case 400:
                    Alerta.getAlert(Alert.AlertType.ERROR, "Error de Conexion " + request.getResponseCode(), request.getResponseMessage(), null).showAndWait();
                    setHeader(request, peticion);
                    peticion.addBody("response", this.con.getBuild().toString());
                    return null;
                case 200:
                    setHeader(request, peticion);
                    peticion.addBody("response", this.con.getBuild().toString());
                case 202:
                    setHeader(request, peticion);
                    peticion.addBody("response", this.con.getBuild().toString());
                //default:
                // Alerta.getAlert(Alert.AlertType.ERROR, "Error Desconocido "+request.getResponseCode(), request.getResponseMessage(), null).showAndWait();
            }

        } catch (IOException ex) {
            peticion = null;
        }
        return peticion;
    }

    public Peticion PostFormEncoded(String url, Peticion peticion) {
        String value = null;
        try {
            if (!peticion.getUrlparameters().isEmpty()) {
                url = url + peticion.getParameters();
            }
            if (!peticion.getCuerpo().isEmpty()) {
                value = peticion.getCuerpo().get("request");
            }
            HttpURLConnection request = this.con.HttpPost(url, true, true, true, value);
            request.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            this.con.conectar();
            if (request.getResponseCode() == 404) {
                return null;
            } else {
                setHeader(request, peticion);
                peticion.addBody("response", this.con.getBuild().toString());
            }

        } catch (IOException ex) {
            peticion = null;
        }
        return peticion;
    }

    private void setHeader(HttpURLConnection request, Peticion peticion) {
        try {
            peticion.addHeader("resp_cod", String.valueOf(request.getResponseCode()));
            peticion.addHeader("resp_code", "success");
        } catch (IOException ex) {
            //
        }

    }

}
