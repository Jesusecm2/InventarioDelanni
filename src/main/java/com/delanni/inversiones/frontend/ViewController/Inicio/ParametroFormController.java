/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Inicio;

import com.delanni.inversiones.frontend.Backend.Controllers.ConfigSystemImpl;
import com.delanni.inversiones.frontend.Backend.Entity.SystemParam;
import com.delanni.inversiones.frontend.Backend.Interfaces.ConfigSystem;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Alerta;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

/**
 *
 * @author Jesusecm
 */
public class ParametroFormController implements Initializable {

    @FXML
    private TableView<SystemParam> table_main;

    @FXML
    private TableColumn<SystemParam, Integer> tc_cod;

    @FXML
    private TableColumn<SystemParam, String> tc_ref;

    @FXML
    private TableColumn<SystemParam, String> tc_desc;

    @FXML
    private Spinner<Integer> cod_spin;

    @FXML
    private SystemParam paramSelected;

    @FXML
    private TextField tf_desc;

    @FXML
    private TextField tf_ref;

    @FXML
    private TextField tf_valor;

    @FXML
    private Spinner<Double> valor_spin;

    @FXML
    private Button save_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tc_cod.setCellValueFactory(new PropertyValueFactory<>("tbcod"));
        tc_ref.setCellValueFactory(new PropertyValueFactory<>("reference"));
        tc_desc.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        cod_spin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
        valor_spin.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE));
        table_main.setOnMouseClicked((e) -> {
            if (table_main.getSelectionModel().getSelectedItem() == null) {
                paramSelected = null;
            }
            if (e.getClickCount() > 1
                    && e.getButton() == MouseButton.PRIMARY
                    && table_main.getSelectionModel().getSelectedItem() != null) {
                paramSelected = table_main.getSelectionModel().getSelectedItem();
                if (paramSelected != null) {
                    cod_spin.getValueFactory().setValue(paramSelected.getTbcod());
                    tf_desc.setText(paramSelected.getDescripcion());
                    valor_spin.getValueFactory().setValue(paramSelected.getValueNum());
                    tf_ref.setText(paramSelected.getReference());
                    tf_valor.setText(paramSelected.getValuetxt());
                }
            }
        });
        save_btn.setOnAction((e) -> {
            guardarParametro();
        });

        tf_ref.setOnKeyReleased((e) -> {
            if (tf_ref.getText().length() > 3) {
                e.consume();
            }
        });
        tf_desc.setOnKeyReleased((e) -> {
            if (tf_ref.getText().length() > 50) {
                e.consume();
            }
        });

        llenarTabla();

    }

    private SystemParam crearParametro() {
        SystemParam request = null;
        if (paramSelected != null) {
            request = paramSelected;
        } else {
            request = new SystemParam();
        }
        request.setValueNum(valor_spin.getValue());
        request.setValuetxt(tf_valor.getText());
        request.setTbcod(cod_spin.getValue());
        request.setDescripcion(tf_desc.getText());
        request.setReference(tf_ref.getText());
        ConfigSystem system = new ConfigSystemImpl();
        SystemParam param = system.guardarParametro(request);
        if(param!=null){
            Alert guardado = Alerta.getAlert(Alert.AlertType.INFORMATION, "Guardado Exitoso", "", null);
            guardado.showAndWait();
        }
        return request;
    }

    private void llenarTabla() {
        ConfigSystem sisback = new ConfigSystemImpl();
        List<SystemParam> listado = sisback.obtenerParametro(100);
        if (listado != null) {
            table_main.setItems(FXCollections.observableArrayList(listado));
        }

    }

    private void guardarParametro() {
        ConfigSystem sisback = new ConfigSystemImpl();
        SystemParam guardar = crearParametro();
        if (paramSelected != null) {
            guardar.setId(paramSelected.getId());
        }
        SystemParam guardado = sisback.guardarParametro(guardar);
        if (guardado != null) {
            Alert msg = Alerta.getAlert(Alert.AlertType.INFORMATION, "Completado", "Se ha guardado", null);
            msg.showAndWait();
        }
    }

}
