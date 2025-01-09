/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Producto;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Controllers.ImageControllerImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.InventarioControllerImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Producto;
import com.delanni.inversiones.frontend.Backend.Interfaces.ImagenController;
import com.delanni.inversiones.frontend.Backend.Interfaces.InventarioBackend;
import com.delanni.inversiones.frontend.ViewController.Factura.CuentaFormController;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Getfile;
import com.delanni.inversiones.frontend.ViewController.Interfaces.Controladores;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Jesusecm
 */
public class ProductoBusquedaController implements Controladores {

    private Stage window;
    @FXML
    private TableView<Producto> table_producto;

    @FXML
    private TableColumn<Producto, String> tc_nombre;

    @FXML
    private TableColumn<Producto, Double> tc_cantidad;

    @FXML
    private TableColumn<Producto, String> tc_accion;

    @FXML
    private TextField nombre_tf;
    @FXML
    private Button volver_btn;
    @FXML
    private Button create_btn;
    @FXML
    private Button crear_producto;

    private Producto producto;
    
    @FXML
    private Label name_lb;
    
    @FXML
    private Label precio_unt;
    
    @FXML
    private Label precio_vnt;

    private boolean loaded;

    public Stage getWindow() {
        return window;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    @Override
    public void responsive800() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        volver_btn.setGraphic(Getfile.getIcono("normal/left20.png"));
        crear_producto.setOnMouseClicked((e) -> {
            crearProducto();
        });
        volver_btn.setOnMouseClicked((e) -> {
            close();
        });
        tc_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tc_cantidad.setCellValueFactory(new PropertyValueFactory<>("cant_actual"));
        nombre_tf.setOnKeyReleased((e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                String nombre = nombre_tf.getText();
                BuscarProducto(nombre);
            }
        });
        table_producto.setOnMouseClicked((e) -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                if (e.getClickCount() == 2) {
                    if (table_producto.getSelectionModel().getSelectedItem() != null) {
                        producto = table_producto.getSelectionModel().getSelectedItem();
                       close();
                    }
                }else if(table_producto.getSelectionModel().getSelectedItem() != null){
                    producto = table_producto.getSelectionModel().getSelectedItem();
                    displayProducto(producto);
                }
            }
        });

        create_btn.setOnMouseClicked((e) -> {
            if (table_producto.getSelectionModel().getSelectedItem() != null) {
                producto = table_producto.getSelectionModel().getSelectedItem();
                close();
            }
        });

    }

    private void BuscarProducto(String nombre) {
        InventarioBackend backend = new InventarioControllerImpl();
        List<Producto> listado = backend.buscarNombre(nombre);
        if (listado != null) {
            table_producto.setItems(FXCollections.observableList(listado));
        }
    }

    private void crearProducto() {
       ProductoFormController control = App.cargarVentanaModal("Crear Producto", "fxml/ProductoForm", false);
       control.setCloseform(true);
    }

    private void close() {
        Stage stg = (Stage) volver_btn.getParent().getScene().getWindow();
        stg.close();
    }
    
    private void displayProducto(Producto pr){
        name_lb.setText(pr.getNombre());
        precio_unt.setText(String.valueOf(pr.getPrecio_unit()));
        precio_vnt.setText(String.valueOf(pr.getPrecio_vent()));
       /* if(!pr.getImagenes().isEmpty()){            
            ImagenController imgControll = new ImageControllerImpl();
            imgControll.imageString(pr.getImagenes())
                    
        }*/
    }
}
