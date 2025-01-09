/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Authentication;

import com.delanni.inversiones.frontend.Backend.Conection.Peticion;
import com.delanni.inversiones.frontend.Backend.Entity.Usuario;
import java.util.Map;

/**
 *
 * @author Jesusecm
 */
public interface IAuthentication {
    
    
    public AuthenticationInfo getToken(Usuario usuario);
    
    public AuthenticationInfo updateToken(String refresh);
   
}
