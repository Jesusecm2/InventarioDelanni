/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Interfaces;

import com.delanni.inversiones.frontend.Backend.Conection.Peticion;

/**
 *
 * @author Jesusecm
 */
public interface Transaccion {
    
    
    
    public Peticion HttpGetObject(String url,Peticion peticion);

    public Peticion HttpPostObject(String url,Peticion peticion);        
}
