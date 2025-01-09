/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Factura;

import com.delanni.inversiones.frontend.Backend.Controllers.FacturaControllerImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Cuenta;
import com.delanni.inversiones.frontend.Backend.Interfaces.FacturaBackend;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Getfile;
import com.delanni.inversiones.frontend.ViewController.Interfaces.Controladores;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Jesusecm
 */
public class CuentaFormController implements Controladores {

    @FXML
    private TextField desc_tf;

    @FXML
    private TextField num_tf;

    @FXML
    private TextField moneda_tf;

    @FXML
    private Spinner<Double> saldo_spin;

    @FXML
    private Button save_btn;
    @FXML
    private Button volver_btn;

    private Stage window;
    
    private Cuenta cuenta;

    public Stage getWindow() {
        return window;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }
    
    

    public void setWindow(Stage window) {
        this.window = window;
    }

    @Override
    public void responsive800() {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void responsive1600() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setRollovers800() {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setRollovers1600() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        volver_btn.setOnMouseClicked((e) -> {
            this.window.close();
        });
        volver_btn.setGraphic(Getfile.getIcono("normal/left20.png"));
        
        save_btn.setOnMouseClicked((e)->{
            guardarCuenta();
        });
    }

    private void guardarCuenta() {
        Cuenta cuenta = new Cuenta();

        cuenta.setDescripcion(desc_tf.getText());
        cuenta.setNumero_cuenta(num_tf.getText());
        cuenta.setMoneda(moneda_tf.getText());
        cuenta.setSaldo(saldo_spin.getValue());
        FacturaBackend backend = new FacturaControllerImpl();
        Cuenta guardad = backend.guardarCuenta(cuenta);
        if (guardad != null) {
            System.out.println("Guardada");
            this.cuenta = guardad;
            this.window.close();
        }
    }

}
