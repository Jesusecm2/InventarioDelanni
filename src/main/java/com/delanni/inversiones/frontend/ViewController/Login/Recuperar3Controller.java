/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Login;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Controllers.IuserController;
import com.delanni.inversiones.frontend.Backend.Entity.Usuario;
import com.delanni.inversiones.frontend.Backend.Interfaces.IUser;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Alerta;
import com.delanni.inversiones.frontend.ViewController.Interfaces.Controladores;
import com.delanni.inversiones.frontend.ViewController.Size.AltoSize;
import com.delanni.inversiones.frontend.ViewController.Size.NormalSize;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 *
 * @author Jesusecm
 */
public class Recuperar3Controller implements Controladores {

    @FXML
    private Label rc3_titulo_lb;

    @FXML
    private Label rc3_info_lb;

    @FXML
    private TextField rc3_usuario_tf;

    @FXML
    private Button rc3_acces_btn;

    @FXML
    private Button rc3_cancel_btn;

    @FXML
    private ImageView rc3_logo_imgv;

    @FXML
    private PasswordField rc3_pg_pw;

    @FXML
    private PasswordField rc3_pg_pw1;

    @FXML
    private Label rc3_pg_lb;
    @FXML
    private Label rc3_pg1_lb;

    private Usuario cambio;

    public Recuperar3Controller() {
    }

    public Recuperar3Controller(Usuario cambio) {
        this.cambio = cambio;
    }

    public Usuario getCambio() {
        return cambio;
    }

    public void setCambio(Usuario cambio) {
        this.cambio = cambio;
    }

    @Override
    public void responsive800() {
        //****************REMOVE ALL
        rc3_titulo_lb.getStyleClass().removeAll(AltoSize.TITULO);
        rc3_acces_btn.getStyleClass().removeAll(AltoSize.BTNG);

        rc3_pg_pw.getStyleClass().removeAll(AltoSize.TF_INTERMEDIO);
        rc3_pg_pw1.getStyleClass().removeAll(AltoSize.TF_INTERMEDIO);
        //rc3_pg_pw2.getStyleClass().removeAll(AltoSize.TF_INTERMEDIO);

        rc3_pg_lb.getStyleClass().removeAll(AltoSize.TEXTO);
        rc3_pg1_lb.getStyleClass().removeAll(AltoSize.TEXTO);
        //rc3_pg2_lb.getStyleClass().removeAll(AltoSize.TEXTO);

        //**************ADD OR SET
        rc3_titulo_lb.getStyleClass().addAll(NormalSize.TITULO);
        rc3_pg_pw.getStyleClass().addAll(NormalSize.TF_INTERMEDIO);
        rc3_pg_pw1.getStyleClass().addAll(NormalSize.TF_INTERMEDIO);
        //rc3_pg_pw2.getStyleClass().addAll(NormalSize.TF_INTERMEDIO);

        rc3_pg_lb.getStyleClass().addAll(NormalSize.TEXTO);
        rc3_pg1_lb.getStyleClass().addAll(NormalSize.TEXTO);
        //rc3_pg2_lb.getStyleClass().addAll(NormalSize.TEXTO);
        rc3_acces_btn.getStyleClass().addAll(NormalSize.TEXTO);

        //***********image
        rc3_logo_imgv.setFitHeight(400);
        rc3_logo_imgv.setFitWidth(400);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void responsive1600() {
        //****************REMOVE ALL
        rc3_titulo_lb.getStyleClass().removeAll(NormalSize.TITULO);

        rc3_pg_lb.getStyleClass().removeAll(NormalSize.TEXTO);
        rc3_pg1_lb.getStyleClass().removeAll(NormalSize.TEXTO);
        //rc3_pg2_lb.getStyleClass().removeAll(NormalSize.TEXTO);

        rc3_acces_btn.getStyleClass().addAll(NormalSize.BTNG);
        //**************ADD OR SET
        rc3_titulo_lb.getStyleClass().addAll(AltoSize.TITULO);

        rc3_pg_lb.getStyleClass().addAll(AltoSize.TEXTO);
        rc3_pg1_lb.getStyleClass().addAll(AltoSize.TEXTO);
        //rc3_pg2_lb.getStyleClass().addAll(AltoSize.TEXTO);

        rc3_acces_btn.getStyleClass().addAll(AltoSize.TEXTO);
        //***********image
        rc3_logo_imgv.setFitHeight(600);
        rc3_logo_imgv.setFitWidth(600);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (App.IsResize()) {
            responsive1600();
        } else {
            responsive800();
        }

        rc3_acces_btn.setOnAction((e) -> {
            System.out.println("cambio");
            cambioContrasena();
        });
        rc3_cancel_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    App.setRoot("InicioSesion");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        });
    }

    @Override
    public void setRollovers800() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setRollovers1600() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean validar() {
        if (!rc3_pg_pw.getText().equals(rc3_pg_pw1.getText())) {
            Alert al = Alerta.getAlert(Alert.AlertType.ERROR, "Contrasena Incorrecta", "Ambos campos deben ser iguales", null);
            al.showAndWait();
            return false;
        }
        return true;
    }

    private void cambioContrasena() {
        if (validar()) {
            IUser control = new IuserController();
            cambio.setPassword(rc3_pg_pw.getText());
            int resultado = control.cambioContrasena(cambio);
            if(resultado==1){
                Alert alerta = Alerta.getAlert(Alert.AlertType.INFORMATION, "Aceptado", "Se ha ejecutado correctamente", null);
                alerta.showAndWait();
            }else{
                Alert alerta = Alerta.getAlert(Alert.AlertType.ERROR, "Error", "No se ha completado la solicitud", null);
                alerta.showAndWait();
            }
            rc3_cancel_btn.fire();
        }
    }

}
