/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Login;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.ViewController.Interfaces.Controladores;
import com.delanni.inversiones.frontend.ViewController.Size.AltoSize;
import com.delanni.inversiones.frontend.ViewController.Size.NormalSize;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 *
 * @author Jesusecm
 */
public class Recuperar2Controller implements Controladores {

    @FXML
    private Label rc1_titulo_lb;

    @FXML
    private Label rc1_info_lb;

    @FXML
    private TextField rc1_usuario_tf;

    @FXML
    private Button rc2_acces_btn;
    
        @FXML
    private Button rc2_cancel_btn;

    @FXML
    private ImageView rc1_logo_imgv;

    @FXML
    private PasswordField rc2_pg_pw;

    @FXML
    private PasswordField rc2_pg_pw1;
    @FXML
    private PasswordField rc2_pg_pw2;

    @FXML
    private Label rc2_pg_lb;
    @FXML
    private Label rc2_pg1_lb;
    @FXML
    private Label rc2_pg2_lb;

    @Override
    public void responsive800() {
        //****************REMOVE ALL
        rc1_titulo_lb.getStyleClass().removeAll(AltoSize.TITULO);
        rc2_acces_btn.getStyleClass().removeAll(AltoSize.BTNG);
        rc2_cancel_btn.getStyleClass().removeAll(AltoSize.BTNG);

        rc2_pg_pw.getStyleClass().removeAll(AltoSize.TF_INTERMEDIO);
        rc2_pg_pw1.getStyleClass().removeAll(AltoSize.TF_INTERMEDIO);
        rc2_pg_pw2.getStyleClass().removeAll(AltoSize.TF_INTERMEDIO);

        rc2_pg_lb.getStyleClass().removeAll(AltoSize.TEXTO);
        rc2_pg1_lb.getStyleClass().removeAll(AltoSize.TEXTO);
        rc2_pg2_lb.getStyleClass().removeAll(AltoSize.TEXTO);

        //**************ADD OR SET
        rc1_titulo_lb.getStyleClass().addAll(NormalSize.TITULO);
        rc2_pg_pw.getStyleClass().addAll(NormalSize.TF_INTERMEDIO);
        rc2_pg_pw1.getStyleClass().addAll(NormalSize.TF_INTERMEDIO);
        rc2_pg_pw2.getStyleClass().addAll(NormalSize.TF_INTERMEDIO);

        rc2_pg_lb.getStyleClass().addAll(NormalSize.TEXTO);
        rc2_pg1_lb.getStyleClass().addAll(NormalSize.TEXTO);
        rc2_pg2_lb.getStyleClass().addAll(NormalSize.TEXTO);
        rc2_acces_btn.getStyleClass().addAll(NormalSize.TEXTO);

        //***********image
        rc1_logo_imgv.setFitHeight(400);
        rc1_logo_imgv.setFitWidth(400);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void responsive1600() {
        //****************REMOVE ALL
        rc1_titulo_lb.getStyleClass().removeAll(NormalSize.TITULO);

        rc2_pg_lb.getStyleClass().removeAll(NormalSize.TEXTO);
        rc2_pg1_lb.getStyleClass().removeAll(NormalSize.TEXTO);
        rc2_pg2_lb.getStyleClass().removeAll(NormalSize.TEXTO);

        rc2_acces_btn.getStyleClass().addAll(AltoSize.BTNG);
        rc2_cancel_btn.getStyleClass().addAll(AltoSize.BTNG);
        //**************ADD OR SET
        rc1_titulo_lb.getStyleClass().addAll(AltoSize.TITULO);
        
        
        rc2_pg_lb.getStyleClass().addAll(AltoSize.TEXTO);
        rc2_pg1_lb.getStyleClass().addAll(AltoSize.TEXTO);
        rc2_pg2_lb.getStyleClass().addAll(AltoSize.TEXTO);

        //rc2_acces_btn.getStyleClass().addAll(AltoSize.TEXTO);
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
        rc2_acces_btn.setOnAction((event)->{
            try {
                    App.setRoot("RecuperarContrasena3");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        });
        rc2_cancel_btn.setOnAction(new EventHandler<ActionEvent>(){
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

}
