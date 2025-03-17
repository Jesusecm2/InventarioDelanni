/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Inicio.Helper;

import com.delanni.inversiones.frontend.App;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Jesusecm
 */
public class Getfile {

private static String baseurl = "images/";

    public static ImageView getIcono(String url) {
        FileInputStream strem = null;
        System.out.println();
        //File sistem = App.class.getResource(baseurl+url).get
        //strem = new FileInputStream(App.class.getResource(baseurl+url).getFile());

        //strem = new FileInputStream(file);
        //strem = new FileInputStream("/src/main/resources/com/arantxa/sistemas/icons/"+url);
        //ImageView view = new ImageView(new Image(strem));
        try{
            ImageView view = new ImageView(new Image(App.class.getResourceAsStream(baseurl + url)));
        return view;
        }catch(Exception e){
            return null;
        }
        
    }
    }
