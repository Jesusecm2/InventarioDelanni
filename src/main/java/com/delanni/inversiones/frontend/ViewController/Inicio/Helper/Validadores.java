/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Inicio.Helper;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

/**
 *
 * @author Jesusecm
 */
public class Validadores {

    
    
    public static void tooltipAndValidadorShowing(TextArea param,Integer value){
        Tooltip tp = new Tooltip("Límite de ".concat(String.valueOf(value)).concat(" caracteres"));
        tp.setAutoHide(false);
        param.setTooltip(tp);
        param.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    System.out.println("Focus");
                     tp.show(param.getScene().getWindow());
                }else{
                    System.out.println("UnFocus");
                      tp.hide();
                }
            }
        });
        param.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            if (newValue.length() >= value) {
                param.setText(oldValue);
                if (!tp.isShowing()) {
                    tp.show(param.getScene().getWindow());
                }

            } else {
                tp.hide();
            }
        });
    }

    public static void tooltipAndValidadorShowingField(TextField param,Integer value){
        Tooltip tp = new Tooltip("Límite de ".concat(String.valueOf(value)).concat(" caracteres"));
        tp.setAutoHide(false);
        param.setTooltip(tp);
        param.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    System.out.println("Focus");
                     tp.show(param.getScene().getWindow());
                }else{
                    System.out.println("UnFocus");
                      tp.hide();
                }
            }
        });
        param.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            if (newValue.length() >= value) {
                param.setText(oldValue);
                if (!tp.isShowing()) {
                    tp.show(param.getScene().getWindow());
                }

            } else {
                tp.hide();
            }
        });
    }
}
