/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Producto;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Conection.Conexion;
import com.delanni.inversiones.frontend.Backend.Conection.Peticion;
import com.delanni.inversiones.frontend.Backend.Controllers.ImageControllerImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.InventarioControllerImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Categoria;
import com.delanni.inversiones.frontend.Backend.Interfaces.ImagenController;
import com.delanni.inversiones.frontend.Backend.Interfaces.InventarioBackend;
import com.delanni.inversiones.frontend.Backend.util.ImageConverter;
import com.delanni.inversiones.frontend.Backend.util.SelecionArchivos;
import com.delanni.inversiones.frontend.ViewController.Ingresos.Precarga.NormalImage;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Alerta;
import com.delanni.inversiones.frontend.ViewController.Interfaces.Controladores;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;

/**
 *
 * @author Jesusecm
 */
public class CategoriaFormController implements Controladores {

    @FXML
    private Button save_btn;
    @FXML
    private Button subir_btn;

    @FXML
    private Button volver_btn;

    @FXML
    private ListView<Categoria> list_cat;

    @FXML
    private TextField cat_name_tf;

    @FXML
    private GridPane error_cnt;

    @FXML
    private Categoria categoria;

    @FXML
    private Categoria guardar;

    @FXML
    private GridPane sucess_cnt;

    private Stage window;

    @FXML
    private Label error_msg;

    @FXML
    private Label success_msg;

    private ImageConverter convertidor;

    @FXML
    private ImageView lg_logo_imgv;

    private boolean loaded;

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
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setRollovers1600() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loaded = false;
        volver_btn.setGraphic(NormalImage.left_btn);

        volver_btn.setOnMouseClicked((e) -> {
            cerrar();
        });
        save_btn.setOnMouseClicked((e) -> {
            crearCategoria();
        });
        subir_btn.setOnMouseClicked((e) -> {
            SubirImagen();
        });

        list_cat.setOnMouseClicked((e) -> {
            if (e.getClickCount() >= 2 && e.getButton() == MouseButton.PRIMARY) {
                Seleccion();
            } else {//
                
                guardar = list_cat.getSelectionModel().getSelectedItem();
                if (guardar != null) {
                    cat_name_tf.setText(guardar.getNombre());
                    if(guardar.getImage()!=null){
                        ImagenController control = new ImageControllerImpl();
                        ImageConverter convert = new ImageConverter(control.imageString(guardar.getImage()));
                        lg_logo_imgv.setImage(convert.getImage());
                    }
                    
                }
            }

        });
        /*Categoria ct = new Categoria();
        ct.setId(1);
        ct.setNombre("Nombre");
        list_cat.getItems().add(ct);*/

        InventarioControllerImpl controller = new InventarioControllerImpl();
        List<Categoria> cat = null;
        try {
            cat = controller.ListadoCategoria();
        } catch (Exception e) {
            cat = null;
        }

        if (cat == null) {
            boolean salir = false;
            do {
                Alert alerta = Alerta.getAlert(Alert.AlertType.CONFIRMATION, "¿Desea reintentar?", "Ha ocurrido un error al cargar su formulario", null);
                Optional<ButtonType> act = alerta.showAndWait();
                salir = act.get() == ButtonType.OK;
                if (salir) {
                    try {
                        cat = controller.ListadoCategoria();
                    } catch (Exception e) {
                        cat = null;
                    }
                }
            } while (salir && cat == null);
        }

        if (cat != null) {
            list_cat.setItems(FXCollections.observableList(cat));
            loaded = true;
        }
        //Temporal
        loaded = true;
    }

    public void crearCategoria() {

        InventarioBackend back = new InventarioControllerImpl();

        if (!cat_name_tf.getText().isBlank()) {
            if (guardar == null) {
                guardar = new Categoria();
            }

            guardar.setNombre(cat_name_tf.getText());
            if (convertidor != null) {
                guardar.setImage(convertidor.getbase64img());
            }

            Alert alerta = Alerta.getAlert(Alert.AlertType.CONFIRMATION, "¿Esta de acuerdo?", "Guardar:" + guardar.getNombre(), null);
            Optional<ButtonType> act = alerta.showAndWait();
            if (act.get() == ButtonType.OK) {
                try {
                    categoria = back.GuardarCategoria(guardar);
                    Peticion pet = Conexion.ultima;
                    if (pet.getCabecera().get("resp_cod").equals("200")) {
                        success_msg.setText("Guardado Correctamente");
                        SuccessMsg();
                        cerrar();
                    }
                } catch (Exception el) {
                    Alert errormsg = Alerta.getAlert(Alert.AlertType.ERROR, "Error", "Error de conexión", null);
                    errormsg.showAndWait();
                }
            }
        } else {
            error_msg.setText("Contiene el nombre vacío");
            ErrorMsg();

        }
    }

    private void SubirImagen() {
        File fl = SelecionArchivos.seleccionarImagen();
        if (fl != null) {
            convertidor = new ImageConverter(fl);
            lg_logo_imgv.setImage(convertidor.getImage());
        }
    }

    private void ErrorMsg() {
        System.out.println("Error");
        error_cnt.setVisible(true);
        error_cnt.setOpacity(-1);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), error_cnt);
        fadeTransition.setToValue(100);
        fadeTransition.setByValue(-1);
        fadeTransition.setOnFinished((e) -> {
            try {
                Thread.sleep(1500);
                FadeTransition fadeTransition3 = new FadeTransition(Duration.millis(500), error_cnt);
                fadeTransition3.setByValue(-1);
                fadeTransition3.setOnFinished((l) -> {
                    error_cnt.setVisible(false);

                });
                fadeTransition3.play();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        fadeTransition.play();
    }

    private void SuccessMsg() {
        System.out.println("acepted");
        sucess_cnt.setVisible(true);
        sucess_cnt.setOpacity(-1);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), sucess_cnt);
        fadeTransition.setToValue(100);
        fadeTransition.setByValue(-1);
        fadeTransition.setOnFinished((e) -> {
            try {
                Thread.sleep(1500);
                FadeTransition fadeTransition3 = new FadeTransition(Duration.millis(500), sucess_cnt);
                fadeTransition3.setByValue(-1);
                fadeTransition3.setOnFinished((l) -> {
                    sucess_cnt.setVisible(false);
                    cerrar();
                });
                fadeTransition3.play();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        fadeTransition.play();

    }

    public Categoria getCategoria() {
        return categoria;
    }

    private void Seleccion() {
        this.categoria = list_cat.getSelectionModel().getSelectedItem();
        if (categoria != null) {
            cerrar();
        }
    }

    private void cerrar() {
        Stage tg = (Stage) this.cat_name_tf.getParent().getScene().getWindow();
        tg.close();
    }

}
