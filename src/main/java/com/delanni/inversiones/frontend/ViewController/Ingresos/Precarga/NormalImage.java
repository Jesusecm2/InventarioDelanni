/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Ingresos.Precarga;

import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Getfile;
import javafx.scene.image.ImageView;

/**
 *
 * @author Jesusecm
 */
public class NormalImage {
 
    
    
    public static ImageView add_btn;
    public static ImageView add_btn_rol;
    public static ImageView add_btn_click;
    
        public static ImageView rest_btn;
    public static ImageView rest_btn_rol;
    public static ImageView rest_btn_click;
    
    
    public static ImageView addimg_btn;
    
    public static ImageView left_btn;
    
    public static ImageView left_btn_rol;
    
    public static void precarga(){
        add_btn = Getfile.getIcono("normal/add64.png");
        add_btn_rol = Getfile.getIcono("normal/add64-rol.png");
        add_btn_click = Getfile.getIcono("normal/add64-click.png");
        
        rest_btn = Getfile.getIcono("normal/rest64.png");
        rest_btn_rol = Getfile.getIcono("normal/rest64-rol.png");
        rest_btn_click = Getfile.getIcono("normal/rest64-click.png");
        
        addimg_btn = Getfile.getIcono("normal/addimg64.png");
        
        left_btn = Getfile.getIcono("normal/left20.png");
        left_btn_rol = Getfile.getIcono("normal/left20-rol.png");
        
        
    }
            
            
}
