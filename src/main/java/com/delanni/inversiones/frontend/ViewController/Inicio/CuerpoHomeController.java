/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Inicio;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.ViewController.Ingresos.EgresoFormController;
import com.delanni.inversiones.frontend.ViewController.Ingresos.IngresoFormController;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Getfile;
import com.delanni.inversiones.frontend.ViewController.Interfaces.Controladores;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 *
 * @author Jesusecm
 */
public class CuerpoHomeController implements Controladores {

    @FXML
    private Button add_btn;

    @FXML
    private Button rest_btn;

    @FXML
    private Parent lastPage;

    private Parent lastRoot;

    private Parent newRoot;

    private StackPane stack_pane;

    @FXML
    private Pagination ingresos_pagination;

    @FXML
    private Pagination egreso_pagination;

    @FXML
    private LineChart<CategoryAxis, NumberAxis> chart_main;

    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @Override
    public void responsive800() {

    }

    @Override
    public void responsive1600() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //add_btn.setGraphic(Getfile.getIcono("normal/add64.png"));
        //rest_btn.setGraphic(Getfile.getIcono("normal/rest64.png"));
        add_btn.setOnAction(e -> {
            IngresoFormController control = App.cargarVentanaModal("Agregar Ingreso", "fxml/IngresoForm", true);
        });
        rest_btn.setOnAction(e ->{
            EgresoFormController control = App.cargarVentanaModal("Agregar Egreso", "fxml/EgresoForm", true);
        });
        ingresos_pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                return TablaIngresos();
            }
        });
        egreso_pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                return TablaIngresos();
            }
        });

        XYChart.Series series = new XYChart.Series();
        series.setName("No of schools in an year");

        series.getData().add(new XYChart.Data("6:00", 100));
        series.getData().add(new XYChart.Data("7:00", 30));
        series.getData().add(new XYChart.Data("8:00", 200));
        series.getData().add(new XYChart.Data("9:00", 50));
        series.getData().add(new XYChart.Data("10:00", 900));
        series.getData().add(new XYChart.Data("11:00", 700));
        series.getData().add(new XYChart.Data("12:00", 700));
        series.getData().add(new XYChart.Data("01:00", 700));
        series.getData().add(new XYChart.Data("02:00", 700));
        series.getData().add(new XYChart.Data("03:00", 700));
        series.getData().add(new XYChart.Data("04:00", 700));
        series.getData().add(new XYChart.Data("05:00", 700));
        series.getData().add(new XYChart.Data("06:00", 700));

        chart_main.getData().add(series);

    }

    @Override
    public void setRollovers800() {

    }

    @Override
    public void setRollovers1600() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void loadIngresoForm() {
        App.bodycenter.cargarBody("fxml/IngresoForm");
    }

    private void loadEgresoForm() {
        App.bodycenter.cargarBody("fxml/EgresoForm");

    }

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

    private TableView<String> TablaIngresos() {
        TableView tb2 = new TableView();
        tb2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<String, String> ctc = new TableColumn<>("Categoría");

        TableColumn<String, String> ctn = new TableColumn<>("Descripcion");
        TableColumn<String, String> ctl = new TableColumn<>("Monto");
        tb2.getColumns().addAll(ctc, ctn, ctl);
        return tb2;
    }

}
