/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Login;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Controllers.IuserController;
import com.delanni.inversiones.frontend.Backend.Entity.Usuario;
import com.delanni.inversiones.frontend.Backend.Interfaces.IUser;
import com.delanni.inversiones.frontend.ViewController.Interfaces.Controladores;
import com.delanni.inversiones.frontend.ViewController.Size.AltoSize;
import com.delanni.inversiones.frontend.ViewController.Size.NormalSize;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
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
    private Label prg_lb;

    @FXML
    private PasswordField prg_pf;
    @FXML
    private Button rc2_acces_btn;

    @FXML
    private Button rc2_cancel_btn;

    @FXML
    private ImageView rc1_logo_imgv;

    private int pregunta;

    private Usuario busqueda;

    public Recuperar2Controller(Usuario busqueda) throws Exception {

        IUser backend = new IuserController();
        Usuario temp = backend.obtenerSeguridad(busqueda);
        if (temp != null) {
            this.busqueda = temp;
        } else {
            throw new Exception("No se pudo cargar el usuario");
        }
    }

    @Override
    public void responsive800() {
        //****************REMOVE ALL
        rc1_titulo_lb.getStyleClass().removeAll(AltoSize.TITULO);
        rc2_acces_btn.getStyleClass().removeAll(AltoSize.BTNG);
        rc2_cancel_btn.getStyleClass().removeAll(AltoSize.BTNG);

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

        rc2_acces_btn.getStyleClass().addAll(AltoSize.BTNG);
        rc2_cancel_btn.getStyleClass().addAll(AltoSize.BTNG);
        //**************ADD OR SET
        rc1_titulo_lb.getStyleClass().addAll(AltoSize.TITULO);

        //rc2_acces_btn.getStyleClass().addAll(AltoSize.TEXTO);
        //***********image
        rc1_logo_imgv.setFitHeight(600);
        rc1_logo_imgv.setFitWidth(600);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (App.IsResize()) {
            responsive1600();
        } else {
            responsive800();
        }
        rc2_acces_btn.setOnAction((event) -> {
            try {
                IUser control = new IuserController();
                switch(pregunta){
                    case 1:
                        busqueda.getSecurity().setResp1(prg_pf.getText());
                        break;
                    case 2:
                        busqueda.getSecurity().setResp2(prg_pf.getText());
                        break;
                    case 3:
                        busqueda.getSecurity().setResp3(prg_pf.getText());
                        break;
                }
                
                Usuario respuesta = control.validarRespuesta(busqueda);
                if(respuesta!=null){
                    System.out.println("Validado correctamente");
                    Recuperar3Controller rc = new Recuperar3Controller(respuesta);
                    Parent fx = App.loadFXML("fxml/RecuperarContrasena3", rc);
                    App.setRoot(fx);
                }else{
                    System.out.println("Error de validacion");
                }
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        rc2_cancel_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    App.setRoot("InicioSesion");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        });
        if (busqueda != null) {
            this.pregunta = new Random().nextInt(3) + 1;
            switch (pregunta) {
                case 1:
                    prg_lb.setText(busqueda.getSecurity().getPreg1());
                    busqueda.getSecurity().setPreg2(null);
                    busqueda.getSecurity().setPreg3(null);
                    System.out.println("1");
                    break;
                case 2:
                    prg_lb.setText(busqueda.getSecurity().getPreg2());
                    busqueda.getSecurity().setPreg1(null);
                    busqueda.getSecurity().setPreg3(null);
                    System.out.println("2");
                    break;
                case 3:
                    prg_lb.setText(busqueda.getSecurity().getPreg3());
                    busqueda.getSecurity().setPreg1(null);
                    busqueda.getSecurity().setPreg2(null);
                    System.out.println("3");
                    break;

            }
        }
    }

    @Override
    public void setRollovers800() {

    }

    @Override
    public void setRollovers1600() {

    }

    private void validarRespuesta() {

    }

}
