/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Authentication;


import com.delanni.inversiones.frontend.Backend.Entity.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Jesusecm
 */
public class AuthenticationInfo {
    private AuthenticationFiles authf;
    
    private String access_token;
    private String token_type;

    private String refresh_token;
    private String expires_in;
    private String scope;
    private String correo;
    private String nombre;
    private String username;
    private List<Role> roles;
    
    public AuthenticationInfo() {
        if(authf==null)authf = new AuthenticationFiles();
        this.access_token = authf.getMessage("access_token");
        this.token_type = authf.getMessage("token_type");
        this.refresh_token = authf.getMessage("refresh_token");
        this.expires_in = authf.getMessage("expires_in");
        this.scope = authf.getMessage("scope");        
        this.correo  = authf.getMessage("correo");
        this.nombre = authf.getMessage("nombre");
        this.username  = authf.getMessage("username"); 
        if(!"logout".equals(this.access_token))testDecodeJWT();
        
    }
    
    
        public AuthenticationInfo(Map<String,Object>info) {
        if(authf==null)authf = new AuthenticationFiles();
        this.access_token = String.valueOf(info.get("access_token"));
        this.token_type = (String) info.get("token_type");
        this.refresh_token =(String) info.get("refresh_token");
        this.expires_in = String.valueOf(info.get("expires_in"));
        this.scope = (String)info.get("scope");        
        this.correo  = (String)info.get("correo");
        this.nombre = (String)info.get("nombre");
        if(!"logout".equals(this.access_token))testDecodeJWT();
        setFile();
        
    }
    
    public void setFile(){
         authf.setMessage("access_token",this.access_token);
         authf.setMessage("token_type",this.token_type);
         authf.setMessage("refresh_token",this.refresh_token);
         authf.setMessage("expires_in",this.expires_in);
         authf.setMessage("scope",this.scope);        
         authf.setMessage("correo",this.correo);
         authf.setMessage("nombre",this.nombre);
         authf.setMessage("username", this.username);
         authf.storeFile();
    }
    
    
    public void logout(){
        this.access_token = "logout";
        this.token_type = "";
        this.refresh_token ="";
        this.expires_in = "";
        this.scope = "";     
        this.correo  = "";
        this.nombre = "";
        this.username  = "";
        setFile();
    }

    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }

    
    
    public void setAuthf(AuthenticationFiles authf) {
        this.authf = authf;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public List<Role> getRoles() {
        return roles;
    }
    
    
    
      private void testDecodeJWT(){
        String jwtToken = this.access_token;
        //System.out.println("------------ Decode JWT ------------");
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedHeader = split_string[0];
        String base64EncodedBody = split_string[1];
        String base64EncodedSignature = split_string[2];
        //System.out.println("~~~~~~~~~ JWT Header ~~~~~~~");
        java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder();
        String header = new String(decoder.decode(base64EncodedHeader));
        //System.out.println("JWT Header : " + header);
        //System.out.println("~~~~~~~~~ JWT Body ~~~~~~~");
        String body = new String(decoder.decode(base64EncodedBody));
        //System.out.println("JWT Body : "+body);     
          ObjectMapper map = new ObjectMapper();
          Map<String,Object> temp;
        try {
            temp = map.readValue(body, Map.class);
            //temp.forEach((k,v) -> System.out.println("Key: " + k + ": Value: " + v));
            this.username = (String) temp.get("user_name");
             if(temp.containsKey("authorities")){
              List<String> list = (List<String>) temp.get("authorities");
              roles = new ArrayList<>();
              list.forEach(e->{
              Role rol = new Role();
              rol.setNombre(e);
              roles.add(rol);
              });
            }
        } catch (JsonProcessingException ex) {
          //  Logger.getLogger(AuthenticationInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
    }
    
    
    
    
    
    
    
}
