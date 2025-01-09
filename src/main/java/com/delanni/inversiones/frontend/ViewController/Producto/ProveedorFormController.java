/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Producto;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Controllers.FacturaControllerImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Cuenta;
import com.delanni.inversiones.frontend.Backend.Entity.Proveedor;
import com.delanni.inversiones.frontend.Backend.Interfaces.FacturaBackend;
import com.delanni.inversiones.frontend.ViewController.Factura.CuentaFormController;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Alerta;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Getfile;
import com.delanni.inversiones.frontend.ViewController.Interfaces.Controladores;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Jesusecm
 */
public class ProveedorFormController implements Controladores {

    private Stage window;

    private Proveedor proveedor;

    @FXML
    private TextField rif_tf;

    @FXML
    private TextField name_tf;

    @FXML
    private TextField email_tf;

    @FXML
    private TextField cuent_tf;
    @FXML
    private TextField tel_tf;

    @FXML
    private Button volver_btn;

    @FXML
    private Button save_btn;

    @FXML
    private Button save_btn1;

    private Cuenta cuenta;

    @FXML
    private ListView<Proveedor> list_cat;

    private boolean loaded;

    public boolean isLoaded() {
        return loaded;
    }

    public Stage getWindow() {
        return window;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        volver_btn.setGraphic(Getfile.getIcono("normal/left20.png"));
        volver_btn.setOnMouseClicked((e) -> {
            cerrar();
        });
        save_btn.setOnMouseClicked((e) -> {
            guardarProveedor();
        });

        save_btn1.setOnMouseClicked((e) -> {
            cargarFormulario();
        });

        list_cat.setOnMouseClicked((e) -> {
            Proveedor seleccion = list_cat.getSelectionModel().getSelectedItem();
            if (seleccion != null) {
                this.proveedor = seleccion;
               cerrar();
            }
        });

        boolean recargar = false;
        do {
            List<Proveedor> listado = null;
            FacturaBackend back = new FacturaControllerImpl();
            try {
                listado = back.listadoProveedor();
            } catch (Exception e) {
                listado = null;
            }

            if (listado != null) {
                list_cat.setItems(FXCollections.observableList(listado));
                recargar = true;
                loaded = false;
            } else {
                Alert alert = Alerta.getAlert(Alert.AlertType.CONFIRMATION, "¿Desea reintentar?", "No se pudo cargar su formulario", null);
                Optional<ButtonType> act = alert.showAndWait();
                if (act.get() == ButtonType.OK) {
                    recargar = false;
                } else {
                    recargar = true;
                    loaded = false;
                }
            }

        } while (!recargar);

    }

    private void guardarProveedor() {
        proveedor = new Proveedor();
        proveedor.setRif(rif_tf.getText());
        proveedor.setNombre(name_tf.getText());
        proveedor.setEmail(email_tf.getText());
        proveedor.setNumero(tel_tf.getText());
        proveedor.setCuenta(cuenta);
        FacturaBackend back = new FacturaControllerImpl();
        Proveedor guardar = back.guardarProveedor(proveedor);
        if (guardar != null) {
            System.out.println("Guardado");
            proveedor = guardar;
            cerrar();
        }
    }

    private void cargarFormulario() {
        CuentaFormController categoria = App.cargarVentanaModal("Agregar Productor", "fxml/CuentaForm", true);
    }
    
    private void cerrar(){
        Stage stg = (Stage)this.cuent_tf.getParent().getScene().getWindow();
        stg.close();
    }

}
