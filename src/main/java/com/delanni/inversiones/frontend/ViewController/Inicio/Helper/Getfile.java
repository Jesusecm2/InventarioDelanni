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
        try {
            strem = new FileInputStream(App.class.getResource(baseurl+url).getFile());
            ImageView view = new ImageView(new Image(strem));
            return view;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                strem.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
