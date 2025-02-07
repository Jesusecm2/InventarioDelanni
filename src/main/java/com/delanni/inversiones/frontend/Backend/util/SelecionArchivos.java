/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.util;

import java.io.File;
import javafx.stage.FileChooser;

/**
 *
 * @author Jesusecm
 */
public class SelecionArchivos {
    
    public static File seleccionarImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        //fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "\\Pictures"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG & PNG", "*.png", "*.jpg")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null && file.exists()) {
            return file;
            
        }
        return null;
    }
}
