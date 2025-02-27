/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Login;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Entity.Usuario;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 *
 * @author Jesusecm
 */
public class Recuperar1Controller implements Controladores{
    
    
    @FXML
    private Label rc1_titulo_lb;
    
    @FXML
    private Label rc1_info_lb;
    
    @FXML
    private TextField rc1_usuario_tf;
    
    @FXML
    private Button rc1_acces_btn;
    
    @FXML
    private Button rc1_cancel_btn;
    
    @FXML
    private ImageView rc1_logo_imgv;

    @Override
    public void responsive800() {
        //****************REMOVE ALL
        rc1_titulo_lb.getStyleClass().removeAll(AltoSize.TITULO);
        rc1_info_lb.getStyleClass().removeAll(AltoSize.TEXTO);
        rc1_usuario_tf.getStyleClass().removeAll(AltoSize.TF_ALTO);
        rc1_acces_btn.getStyleClass().removeAll(AltoSize.BTNG);
        rc1_cancel_btn.getStyleClass().removeAll(AltoSize.BTNG);
        //**************ADD OR SET
        rc1_titulo_lb.getStyleClass().addAll(NormalSize.TITULO);
        rc1_info_lb.getStyleClass().addAll(NormalSize.TEXTO);
        rc1_usuario_tf.getStyleClass().addAll(NormalSize.TF_ALTO);
        
        //***********image
        rc1_logo_imgv.setFitHeight(400);
        rc1_logo_imgv.setFitWidth(400);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void responsive1600() {
         //****************REMOVE ALL
        rc1_titulo_lb.getStyleClass().removeAll(NormalSize.TITULO);
        rc1_info_lb.getStyleClass().removeAll(NormalSize.TEXTO);
        rc1_usuario_tf.getStyleClass().removeAll(NormalSize.TF_ALTO);

        //**************ADD OR SET
        rc1_titulo_lb.getStyleClass().addAll(AltoSize.TITULO);
        rc1_info_lb.getStyleClass().addAll(AltoSize.TEXTO);
        rc1_usuario_tf.getStyleClass().addAll(AltoSize.TF_ALTO);
       // rc1_acces_btn.getStyleClass().addAll(AltoSize.TEXTO);
        
        rc1_acces_btn.getStyleClass().addAll(AltoSize.BTNG);
        rc1_cancel_btn.getStyleClass().addAll(AltoSize.BTNG);
        //***********image
        rc1_logo_imgv.setFitHeight(600);
        rc1_logo_imgv.setFitWidth(600);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(App.IsResize()){
            responsive1600();
        }else{
            responsive800();
        }
        rc1_acces_btn.setOnAction((event)->{
            try {
                    Recuperar2Controller rc = new Recuperar2Controller(inicializar());
                    Parent fx = App.loadFXML("fxml/RecuperarContrasena2", rc);
                    App.setRoot(fx);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                     ex.printStackTrace();
                    Alert msg = Alerta.getAlert(Alert.AlertType.ERROR, "Error", "No se pudo obtener el usuario", null);
                    msg.showAndWait();
            }
        });
        rc1_cancel_btn.setOnAction((event)->{
                try {
                    App.setRoot("InicioSesion");
                } catch (IOException ex) {
                    ex.printStackTrace();
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
    
    private Usuario inicializar(){
        Usuario us = new Usuario();
        us.setUsername(rc1_usuario_tf.getText());
        return us;
    }
    
}
