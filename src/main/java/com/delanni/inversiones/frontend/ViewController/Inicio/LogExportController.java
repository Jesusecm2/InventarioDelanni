/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Inicio;

import com.delanni.inversiones.frontend.Backend.Controllers.ConfigSystemImpl;
import com.delanni.inversiones.frontend.Backend.Entity.DailyAudit;
import com.delanni.inversiones.frontend.Backend.Interfaces.ConfigSystem;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Alerta;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

/**
 *
 * @author Jesusecm
 */
public class LogExportController implements Initializable {

    @FXML
    private TableView<DailyAudit> table_main;

    @FXML
    private DatePicker fecha_pick;

    @FXML
    private Button save_btn;

    @FXML
    private Button exp_btn;

    private Date f_log;

    @FXML
    private TableColumn<DailyAudit, String> tc_entity;

    @FXML
    private TableColumn<DailyAudit, String> tc_rute;

    @FXML
    private TableColumn<DailyAudit, String> tc_ip;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tc_entity.setCellValueFactory(new PropertyValueFactory<>("entity_obj"));
        tc_rute.setCellValueFactory(new PropertyValueFactory<>("route"));
        tc_ip.setCellValueFactory(new PropertyValueFactory<>("con_ip"));
        save_btn.setOnAction((e) -> {
            buscarLog();
        });

        exp_btn.setOnAction((e) -> {
            guardarLog();
        });
    }

    private void buscarLog() {

        if (fecha_pick.getValue() != null) {
            ConfigSystem sistema = new ConfigSystemImpl();
            f_log = Date.from(fecha_pick.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            List<DailyAudit> listado = sistema.obtenerLogs(f_log);
            if (listado != null && !listado.isEmpty()) {
                table_main.getItems().setAll(listado);
            }
        } else {
            Alert error = Alerta.getAlert(Alert.AlertType.ERROR, "Error", "Debe seleccionar una fecha", null);
            error.showAndWait();
            f_log = null;
        }

    }

    private void guardarLog() {
        if (!table_main.getItems().isEmpty()) {

            FileChooser chooser = new FileChooser();
            chooser.setTitle("Reporte Factura");
            chooser.setInitialFileName("Log");
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt", "*.txt"));
            File guardar = chooser.showSaveDialog(table_main.getParent().getScene().getWindow());
            if (guardar != null) {
                FileOutputStream output = null;
                try {
                    byte[] buffer = new byte[4096];
                    output = new FileOutputStream(guardar);
                    for(DailyAudit d : table_main.getItems()){
                        String texto = d.getCon_ip()+" : "+d.getRoute()+" : "+d.getServicio()+" : " +d.getSys_op()+" : "
                                +d.getTp_acceso()+" : "+d.getEntity_obj()+"\n";
                        output.write(texto.getBytes());
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        output.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        }
    }
}
