/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Inicio;

import com.delanni.inversiones.frontend.ViewController.Login.*;
import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Getfile;
import com.delanni.inversiones.frontend.ViewController.Interfaces.Controladores;
import com.delanni.inversiones.frontend.ViewController.Size.AltoSize;
import com.delanni.inversiones.frontend.ViewController.Size.NormalSize;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Jesusecm
 */
public class InicioController implements Controladores {

    @FXML
    private MenuButton menu_btn;
    @FXML
    private Button home_btn;

    @FXML
    private Button inventory_btn;

    @FXML
    private Button product_btn;
    /*@FXML
    private Button cat_btn;*/

    @FXML
    private Button fact_btn;
    @FXML
    private Button ventas_btn;
    /*@FXML
    private Button product_btn;*/

    @FXML
    private Button mant_btn;

    @FXML
    private Button ingresos_btn;
    @FXML
    private SplitMenuButton split;

    @FXML
    StackPane stack_pane;

    @FXML
    private ImageView img_icon;

    private Parent lastRoot;

    private Parent newRoot;

    public Parent getLastRoot() {
        return lastRoot;
    }

    public void setLastRoot(Parent lastRoot) {
        this.lastRoot = lastRoot;
    }

    @FXML
    GridPane cuerpoPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setRollovers800();
        menu_btn.setContentDisplay(ContentDisplay.TOP);
        menu_btn.setGraphic(Getfile.getIcono("normal/usuario64.png"));
        home_btn.setGraphic(Getfile.getIcono("normal/Casa64.png"));
        product_btn.setGraphic(Getfile.getIcono("normal/Producto64.png"));
        inventory_btn.setGraphic(Getfile.getIcono("normal/Inventario64.png"));
        mant_btn.setGraphic(Getfile.getIcono("normal/Mantenimiento64.png"));
        //cat_btn.setGraphic(Getfile.getIcono("normal/catalogo64.png"));
        fact_btn.setGraphic(Getfile.getIcono("normal/factura-64.png"));
        ventas_btn.setGraphic(Getfile.getIcono("normal/dinero-64.png"));
        img_icon.setImage(Getfile.getIcono("minilogo.png").getImage());
        img_icon.setFitHeight(64);
        img_icon.setFitWidth(64);
        Button btns[] = new Button[]{home_btn, product_btn, inventory_btn, mant_btn
        };
        Parent root;
        try {
            root = App.loadFXML("fxml/CuerpoHome");
            CuerpoHomeController control = App.loadctual.getController();
            control.setStack_pane(stack_pane);
            control.setNewRoot(root);
            control.setLastRoot(root);
            setLastRoot(root);
            setNewRoot(root);
            stack_pane.getChildren().add(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        ingresos_btn.setOnAction((e) -> {
            cerrarHiloCentral();
            loadIngreso();
        });

        product_btn.setOnAction((e) -> {
            cerrarHiloCentral();
            loadProducto();
        });

        fact_btn.setOnAction((e) -> {
            cerrarHiloCentral();
            loadFacturas();
        });

        inventory_btn.setOnAction((e) -> {
            cerrarHiloCentral();
            loadProducto();
        });

        ventas_btn.setOnAction((e) -> {
            cerrarHiloCentral();
            cargarBody("fxml/VentasCuerpo", null);
        });

        home_btn.setOnAction((e) -> {
            cerrarHiloCentral();
           loadHome();
        });

    }

    @Override
    public void responsive800() {
        //****************REMOVE ALL

    }

    @Override
    public void responsive1600() {

    }

    @Override
    public void setRollovers800() {

    }

    private void loadHome() {

        cargarBody("fxml/CuerpoHome", null);
    }

    private void loadProducto() {
        cargarBody("fxml/ProductoCuerpo", null);
    }

    private void loadFacturas() {
        cargarBody("fxml/FacturaCuerpo", null);
    }

    private void loadCatalogo() {
        cargarBody("fxml/CatalogoCuerpo", null);
    }

    private void loadIngreso() {
        cargarBody("fxml/IngresosEgresosCuerpo", null);
    }

    private void MantenimientoCuerpo() {
        cargarBody("fxml/MantenimientoCuerpo", null);
    }

    public Parent getNewRoot() {
        return newRoot;
    }

    public void setNewRoot(Parent newRoot) {
        this.newRoot = newRoot;
    }

    @Override
    public void setRollovers1600() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void cargarBody(String fxml, Object control) {
        try {
            this.setLastRoot(getNewRoot());
            Parent root = null;
            if (control != null) {
                root = App.loadFXML(fxml, control);
            } else {
                root = App.loadFXML(fxml);
            }

            this.setNewRoot(root);
            Scene secene = menu_btn.getScene();
            root.translateXProperty().set(secene.getWidth());
            stack_pane.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(t -> {
                stack_pane.getChildren().remove(lastRoot);

            });
            timeline.play();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void cerrarHiloCentral() {
        if (App.hilocentral != null) {
            App.hilocentral.interrupt();
        }
    }
}
