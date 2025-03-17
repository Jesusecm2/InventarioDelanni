/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Login;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Authentication.AuthenticationImpl;
import com.delanni.inversiones.frontend.Backend.Authentication.AuthenticationInfo;
import com.delanni.inversiones.frontend.Backend.Authentication.IAuthentication;
import com.delanni.inversiones.frontend.Backend.Conection.Conexion;
import com.delanni.inversiones.frontend.Backend.Conection.Transaccional;
import com.delanni.inversiones.frontend.Backend.Entity.Usuario;
import com.delanni.inversiones.frontend.Backend.Interfaces.Transaccion;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Alerta;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Validadores;
import com.delanni.inversiones.frontend.ViewController.Inicio.InicioController;
import com.delanni.inversiones.frontend.ViewController.Interfaces.Controladores;
import com.delanni.inversiones.frontend.ViewController.Size.AltoSize;
import com.delanni.inversiones.frontend.ViewController.Size.NormalSize;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Jesusecm
 */
public class InicioSesionController implements Controladores {

    @FXML
    private Label lg_titulo_lb;
    @FXML
    private Label lg_olvido_lb;

    private ObjectMapper map;

    @FXML
    private TextField lg_username_tf;

    @FXML
    private GridPane msg_error_grid;

    @FXML
    private Label error_msg_lbl;

    @FXML
    private PasswordField lg_password_tf;

    @FXML
    private CheckBox lg_recordar_ch;

    @FXML
    private Button lg_login_btn;

    @FXML
    private ImageView lg_logo_imgv;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Validadores.tooltipAndValidadorShowingField(lg_username_tf, 100);
        map = new ObjectMapper();
        String vl = App.propiedades.getKey("recordar");
        
        if(vl.equals("1")){
            lg_recordar_ch.setSelected(true);
            lg_username_tf.setText(App.propiedades.getKey("usuario"));
            lg_password_tf.setText(App.propiedades.getKey("password"));
        }else{
            lg_recordar_ch.setSelected(false);
            lg_username_tf.setText("");
            lg_password_tf.setText("");
        }
        lg_olvido_lb.setOnMouseClicked((event) -> {
            try {
                App.setRoot("RecuperarContrasena1");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        lg_login_btn.setOnMouseClicked((e) -> {
            IniciarSesion();
        });
    }

    @Override
    public void responsive800() {

    }

    @Override
    public void responsive1600() {
        //***********Image
        lg_logo_imgv.setFitHeight(600);
        lg_logo_imgv.setFitWidth(600);
    }

    @Override
    public void setRollovers800() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setRollovers1600() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void IniciarSesion() {
        if (validar()) {
            Conexion conn = new Conexion();
            AuthenticationImpl auth = new AuthenticationImpl();
            Usuario user = new Usuario();
            user.setUsername(lg_username_tf.getText());
            user.setPassword(lg_password_tf.getText());
            //System.out.println(Conexion.ultima.getCabecera().get("resp_cod"));

            AuthenticationInfo info = auth.getToken(user);

            //System.out.println(Conexion.ultima.getCuerpo().get("response"));
            if (info == null) {
                System.out.println(" no valido");
                Alert msg = Alerta.getAlert(Alert.AlertType.ERROR, "Usuario o contraseña incorrecto", "", null);
                msg.showAndWait();
                // msg_error_grid.setVisible(true);

            } else {
                try {
                    if(lg_recordar_ch.isSelected()){
                        App.propiedades.setKey("usuario", lg_username_tf.getText());
                    App.propiedades.setKey("password", lg_password_tf.getText());
                    }else{
                        App.propiedades.setKey("usuario", "");
                        App.propiedades.setKey("password", "");
                    }
                    
                    InicioController control = new InicioController();
                    Parent main = App.loadFXML("fxml/Inicio", control);
                    App.bodycenter = control;
                    App.setRoot(main);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            /*try {
                App.setRoot("Inicio");
            } catch (IOException ex) {
                ex.printStackTrace();
            }*/
        }
    }

    private boolean validar() {
        if (lg_username_tf.getText().isEmpty() || lg_password_tf.getText().isEmpty() ||
                lg_username_tf.getText().length() < 5 || lg_username_tf.getText().length() > 30 ||
                lg_password_tf.getText().length() < 5 || lg_password_tf.getText().length() > 30) {
              Alert msg = Alerta.getAlert(Alert.AlertType.ERROR, "Usuario no permitido", "", null);
                msg.showAndWait();
            return false;
        }

        return true;
    }

}
