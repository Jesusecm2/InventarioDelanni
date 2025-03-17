/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Interfaces;

import com.delanni.inversiones.frontend.Backend.Entity.Usuario;

/**
 *
 * @author Jesusecm
 */
public interface IUser {
    
    
    public Usuario obtenerSeguridad(Usuario user);
    
    public Usuario validarRespuesta(Usuario user);
    
    public Usuario guardarSeguridad(Usuario user);
    
    public int cambioContrasena(Usuario user);
}
