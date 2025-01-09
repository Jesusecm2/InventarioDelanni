/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Ventas;

import com.delanni.inversiones.frontend.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
     *
 * @author Jesusecm
 */
public class VentasCuerpoController implements Initializable{

    
    @FXML
    private Button create_btn;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
       create_btn.setOnMouseClicked((e)->{
           App.cargarVentanaModal("Modo Ventas", "fxml/VentaForm", true);
       });
    }
    
    
}
