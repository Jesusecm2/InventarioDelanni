/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Inicio.Helper;

import com.delanni.inversiones.frontend.App;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Jesusecm
 */
public class Alerta {

    public static Alert getAlert(Alert.AlertType a, String header, String body, ImageView icon) {
        Alert alerta = new Alert(a, body);
        alerta.getDialogPane().getStylesheets().add(App.class.getResource("css/DialogConfirm.css").toExternalForm());
        alerta.setHeaderText(header);
        if (icon != null) {
            alerta.setGraphic(icon);
        }
        Stage scena = (Stage) alerta.getDialogPane().getScene().getWindow();
        scena.getIcons().add(Getfile.getIcono("minilogo.png").getImage());
        return alerta;
    }
}
