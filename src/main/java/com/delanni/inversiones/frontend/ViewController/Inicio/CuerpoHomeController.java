/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Inicio;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Controllers.ConfigSystemImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.DiagramaControllerImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.PagoImpl;
import com.delanni.inversiones.frontend.Backend.Entity.SystemParam;
import com.delanni.inversiones.frontend.Backend.Entity.TpIngreso;
import com.delanni.inversiones.frontend.Backend.Entity.Transacciones;
import com.delanni.inversiones.frontend.Backend.Interfaces.ConfigSystem;
import com.delanni.inversiones.frontend.Backend.Interfaces.DiagramaController;
import com.delanni.inversiones.frontend.Backend.Interfaces.PagoBackend;
import com.delanni.inversiones.frontend.ViewController.Ingresos.EgresoFormController;
import com.delanni.inversiones.frontend.ViewController.Ingresos.IngresoFormController;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Getfile;
import com.delanni.inversiones.frontend.ViewController.Inicio.TCuerpoEntity.TIngreso;
import com.delanni.inversiones.frontend.ViewController.Interfaces.Controladores;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private ChoiceBox<String> reporte_tipo;

    @FXML
    private TableView<TIngreso> tb_egresos;

    private TableColumn<TIngreso, TpIngreso> tb_descripcion;

    private TableColumn<TIngreso, String> tb_ref;

    private TableColumn<TIngreso, String> tb_monto;

    @FXML
    private TableView<TIngreso> tb_ingreso;

    private TableColumn<TIngreso, TpIngreso> tb_descripcion1;

    private TableColumn<TIngreso, String> tb_ref1;

    private TableColumn<TIngreso, String> tb_monto1;

    @FXML
    private LineChart<String, Number> chart_main;

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

        xAxis.setTickLabelGap(0.1);

        String[] tiposchart = new String[3];
        tiposchart[0] = "Semanal";
        tiposchart[1] = "Mensual";
        tiposchart[2] = "Anual";
        reporte_tipo.setItems(FXCollections.observableArrayList(tiposchart));
        tb_descripcion = new TableColumn<>("Descripcion");
        tb_descripcion.setCellValueFactory(new PropertyValueFactory<>("ingreso"));

        tb_ref = new TableColumn<>("Referencia");
        tb_ref.setCellValueFactory(new PropertyValueFactory<>("transref"));

        tb_monto = new TableColumn<>("Monto");
        tb_monto.setCellValueFactory(new PropertyValueFactory<>("fmonto"));

        tb_descripcion1 = new TableColumn<>("Descripcion");
        tb_descripcion1.setCellValueFactory(new PropertyValueFactory<>("ingreso"));

        tb_ref1 = new TableColumn<>("Referencia");
        tb_ref1.setCellValueFactory(new PropertyValueFactory<>("transref"));

        tb_monto1 = new TableColumn<>("Monto");
        tb_monto1.setCellValueFactory(new PropertyValueFactory<>("fmonto"));

        tb_egresos.getColumns().clear();
        tb_egresos.getColumns().add(tb_descripcion);
        tb_egresos.getColumns().add(tb_ref);
        tb_egresos.getColumns().add(tb_monto);

        tb_ingreso.getColumns().clear();
        tb_ingreso.getColumns().add(tb_descripcion1);
        tb_ingreso.getColumns().add(tb_ref1);
        tb_ingreso.getColumns().add(tb_monto1);

        //
        cargarEIngresos();
        reporte_tipo.setOnAction((e) -> {
            cargarDiagrama();

        });

        add_btn.setOnAction(e -> {
            IngresoFormController control = App.cargarVentanaModal("Agregar Ingreso", "fxml/IngresoForm", true);
        });
        rest_btn.setOnAction(e -> {
            EgresoFormController control = App.cargarVentanaModal("Agregar Egreso", "fxml/EgresoForm", true);
        });

        /*yAxis.setAutoRanging(false);
        yAxis.setLowerBound(-20);
        yAxis.setUpperBound(2);
        yAxis.setTickUnit(5);
         */
// Mostrar el valor cuando el punto sea seleccionado 
        ConfigSystem sistema = new ConfigSystemImpl();
        SystemParam pr_diag = sistema.obtenerParametro(100, "AAA");
        if (pr_diag.getValueNum() != null) {
            if (pr_diag.getValueNum().equals(1.0)) {
                reporte_tipo.getSelectionModel().select(0);
            }
            if (pr_diag.getValueNum().equals(2.0)) {
                reporte_tipo.getSelectionModel().select(1);
            }
            if (pr_diag.getValueNum().equals(3.0)) {
                reporte_tipo.getSelectionModel().select(2);
            }
        }
        refrescarPantalla();
        //refrescarPantalla();
    }

    @Override
    public void setRollovers800() {

    }

    @Override
    public void setRollovers1600() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    private XYChart.Series<String, Number> cargarDiagrama(int tipo) {
        System.out.println("Ejecutar:" + tipo);
        XYChart.Series<String, Number> series = new XYChart.Series();
        series.setName("No of schools in an year");
        DiagramaController diagraam = new DiagramaControllerImpl();
        LinkedHashMap<String, String> link = diagraam.obtenerDetalle(tipo);
        for (Map.Entry<String, String> entry : link.entrySet()) {
            try {
                if (entry != null) {
                    entry.setValue(entry.getValue().replace(",", "."));
                    Double convertir = Double.valueOf(entry.getValue().trim());

                    series.getData().add(new XYChart.Data<>(entry.getKey(), convertir));
                    System.out.println("Procesar:" + entry.getKey() + " / " + entry.getValue());
                }

            } catch (Exception e) {
                series.getData().add(new XYChart.Data(entry.getKey(), 0.0));
                System.out.println("Error");
            }
        }
        return series;
    }

    private void aplicarDiagrama(XYChart.Series<String, Number> values) {
        XYChart.Series<String, Number> series = values;
        chart_main.setData(FXCollections.observableArrayList(series));
        chart_main.applyCss();
        chart_main.layout();
// Añadir Tooltips a cada punto de datos 
        for (XYChart.Data<String, Number> data : series.getData()) {
            if (data.getNode() != null) {
                Tooltip tooltip = new Tooltip();
                tooltip.setText(String.valueOf(data.getYValue()).concat("$"));
                tooltip.setShowDelay(Duration.millis(10));
                Tooltip.install(data.getNode(), tooltip);

                data.getNode().setOnMouseEntered(event -> data.getNode().getStyleClass().add("onHover"));
                data.getNode().setOnMouseExited(event -> data.getNode().getStyleClass().remove("onHover"));
            }

        }
    }

    private void cargarDiagrama() {
        String selected = reporte_tipo.getSelectionModel().getSelectedItem();
        switch (selected) {
            case "Semanal":
                aplicarDiagrama(cargarDiagrama(1));
                break;
            case "Mensual":
                aplicarDiagrama(cargarDiagrama(2));
                break;
            case "Anual":
                aplicarDiagrama(cargarDiagrama(3));
                break;

        }
    }

    private void cargarEIngresos() {
        PagoBackend back = new PagoImpl();
        List<Transacciones> egresos = back.obtenerEgresos();
        if (egresos != null && !egresos.isEmpty()) {
            List<TIngreso> listado = new ArrayList<>();
            egresos.forEach((e) -> {
                TIngreso egresoT = new TIngreso(e);
                listado.add(egresoT);
            });
            tb_egresos.setItems(FXCollections.observableArrayList(listado));
        }

        List<Transacciones> ingresos = back.obtenerIngresos();
        if (ingresos != null && !ingresos.isEmpty()) {
            List<TIngreso> listado = new ArrayList<>();
            ingresos.forEach((e) -> {
                TIngreso egresoT = new TIngreso(e);
                listado.add(egresoT);
            });
            tb_ingreso.setItems(FXCollections.observableArrayList(listado));
        }
    }

    private void refrescarPantalla() {
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                do {
                    Platform.runLater(() -> {
                        cargarEIngresos();
                        cargarDiagrama();

                    });
                    Thread.sleep(10000);
                } while (true);
                
            }

        };

        Thread tr = new Thread(task);

        tr.setDaemon(
                true);
        tr.start();
        App.hilocentral = tr;

    }

}
