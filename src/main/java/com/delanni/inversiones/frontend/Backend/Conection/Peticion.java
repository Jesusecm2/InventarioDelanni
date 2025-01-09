/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Conection;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jesusecm
 */
public class Peticion {
    

    private Map<String, String> cabecera;

    private Map<String, String> cuerpo;

    private Map<String, String> urlparameters;
    
    private StringBuilder parameters;
        
    private ObjectMapper mapper;

    /**
     * Formato de Cabecera
     * 1 - Codigo de retorno 
     */
   
    /**
     * Cuerpo
     * Objetos parametrizados entre back y front
     * 
     */
    
    /**
     * Url Parameters
     * Contiene los parametros enbeidos en la url
     */
    public Peticion() {
        this.cabecera = new HashMap<>();
        this.cuerpo = new HashMap<>();
        this.urlparameters = new HashMap<>();
        this.mapper = new ObjectMapper();
    }

    public Map<String, String> getCabecera() {
        return cabecera;
    }

    public void setCabecera(Map<String, String> cabecera) {
        this.cabecera = cabecera;
    }

    public Map<String, String> getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(Map<String, String> cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Map<String, String> getUrlparameters() {
        return urlparameters;
    }

    public void setUrlparameters(Map<String, String> urlparameters) {
        this.urlparameters = urlparameters;
    }

    public void addHeader(String name, String value) {
        this.cabecera.put(name, value);
    }

    public void addBody(String name, String value) {
        this.cuerpo.put(name, value);
    }

    public void addParameter(String name, String value) {
        this.urlparameters.put(name, value);
    }
    
    public String getParameters(){
       parameters = new StringBuilder();
       parameters.append("?");
       if(urlparameters != null && !this.urlparameters.isEmpty()){
          urlparameters.forEach((t, u) -> {
             parameters.append(""+t+"="+u+"&");
          });
       }
       if (parameters.charAt(parameters.length()-1) == '&'){
           parameters.replace(parameters.length(), parameters.length()," ");
       }
       return parameters.toString();
    }
    
  

}
