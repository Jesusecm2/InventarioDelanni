/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewObject.Previews;

import com.delanni.inversiones.frontend.App;
import java.io.IOException;
import javafx.scene.Parent;

/**
 *
 * @author Jesusecm
 */
public class PreloadFXML {
    
    
    public static Parent CreateForm;
    
    public static void loadParent(){
        try {
            CreateForm  = App.loadFXML("fxml/ProductoForm");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
