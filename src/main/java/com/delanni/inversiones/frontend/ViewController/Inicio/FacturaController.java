/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Inicio;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Controllers.FacturaControllerImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.LineaFactura;
import com.delanni.inversiones.frontend.Backend.Entity.Producto;
import com.delanni.inversiones.frontend.Backend.Entity.Proveedor;
import com.delanni.inversiones.frontend.Backend.Interfaces.FacturaBackend;
import com.delanni.inversiones.frontend.ViewController.Factura.FacturaFormController;
import com.delanni.inversiones.frontend.ViewController.Factura.Table.TFacturaInicio;
import com.delanni.inversiones.frontend.ViewController.Factura.Table.TLineaFactura;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Getfile;
import com.delanni.inversiones.frontend.ViewController.Interfaces.Controladores;
import com.delanni.inversiones.frontend.ViewController.Pagos.PagoFacturaController;
import com.delanni.inversiones.frontend.ViewController.Producto.CategoriaFormController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 *
 * @author Jesusecm
 */
public class FacturaController implements Controladores {

    private Parent lastRoot;

    private Parent newRoot;

    private StackPane stack_pane;

    @FXML
    private ImageView img_src;

    @FXML
    private TableView<TFacturaInicio> tv_factura;

    @FXML
    private TableColumn<TFacturaInicio, String> tc_nombre;

    @FXML
    private TableColumn<TFacturaInicio, Double> tc_monto;

    @FXML
    private TableColumn<TFacturaInicio, Double> tc_pagado;

    @FXML
    private TableView<TLineaFactura> tv_detalle;

    @FXML
    private TableColumn<TLineaFactura, String> td_pr;

    @FXML
    private TableColumn<TLineaFactura, Double> td_cnt;

    @FXML
    private TableColumn<TLineaFactura, Double> td_total;

    @FXML
    private Button create_btn;

    @FXML
    private Button pagar_btn;

    @FXML
    private ComboBox<Proveedor> cat_box;

    public Parent getLastRoot() {
        return lastRoot;
    }

    public void setLastRoot(Parent lastRoot) {
        this.lastRoot = lastRoot;
    }

    public Parent getNewRoot() {
        return newRoot;
    }

    public void setNewRoot(Parent newRoot) {
        this.newRoot = newRoot;
    }

    public StackPane getStack_pane() {
        return stack_pane;
    }

    public void setStack_pane(StackPane stack_pane) {
        this.stack_pane = stack_pane;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //   throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        create_btn.setOnMouseClicked((e) -> {
            loadForm();
        });
        tc_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tc_monto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        tc_pagado.setCellValueFactory(new PropertyValueFactory<>("pagado"));

        tv_factura.setOnMouseClicked((e) -> {
            TFacturaInicio tf = tv_factura.getSelectionModel().getSelectedItem();
            if(tf!=null){
               
                tv_detalle.setItems(FXCollections.observableArrayList(tf.getLineas()));
            }
        });

        td_pr.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        td_cnt.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        td_total.setCellValueFactory(new PropertyValueFactory<>("total"));

        tv_factura.setRowFactory(tv -> new TableRow<TFacturaInicio>() {
            @Override
            protected void updateItem(TFacturaInicio item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setStyle("");
                } else {
                    if(item.getPagado()==0.0){
                        
                    }
                    setStyle("-fx-background-color: lightgreen;");
                }

            }
        });

        llenarTable();
        FacturaBackend bk = new FacturaControllerImpl();
        try {
            List<Proveedor> prov_list = bk.listadoProveedor();
            if (prov_list != null) {
                cat_box.setItems(FXCollections.observableArrayList(bk.listadoProveedor()));
            } else {
            }
        } catch (Exception e) {
        }
        pagar_btn.setOnMouseClicked((e) -> {
            loadPago();
        });

    }

    private void loadPago() {
           PagoFacturaController control = App.cargarVentanaModal("Cargar Pago", "fxml/PagoForm", false);
           control.setFactura(tv_factura.getSelectionModel().getSelectedItem().getFactura());
    }

    private void loadForm() {
        App.bodycenter.cargarBody("fxml/FacturaFormV2");
    }

    private void llenarTable() {
        FacturaBackend facturaService = new FacturaControllerImpl();
        List<Factura> listado = facturaService.listadoFactura();
        List<TFacturaInicio> inicio = new ArrayList<>();
        listado.forEach((e) -> {
            TFacturaInicio t = new TFacturaInicio(e);
            inicio.add(t);
        });
        tv_factura.setItems(FXCollections.observableArrayList(inicio));
        tv_factura.refresh();
    }
}
