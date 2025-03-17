/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Pagos;

import com.delanni.inversiones.frontend.Backend.Controllers.PagoImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Moneda;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.ValorMoneda;
import com.delanni.inversiones.frontend.Backend.Interfaces.PagoBackend;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Alerta;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Jesusecm
 */
public class ValorMonedaFormController implements Initializable {

    @FXML
    private TextField moneda_tf;

    @FXML
    private CheckBox fecha_chk;

    @FXML
    private DatePicker date_pick;

    @FXML
    private Spinner<Double> saldo_spin;

    private ValorMoneda valorMoneda;

    @FXML
    private Button save_btn;

    private Moneda moneda;

    @FXML
    private ComboBox<Moneda> moneda_Combo;

    private LocalDate date;

    public ValorMonedaFormController(Moneda moneda, LocalDate date) {
        this.moneda = moneda;
        this.date = date;
    }

    public ValorMonedaFormController() {
    }

    public ValorMonedaFormController(ValorMoneda valorMoneda) {
        this.valorMoneda = valorMoneda;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fecha_chk.setOnAction((e) -> {
            if (fecha_chk.isSelected()) {
                date_pick.setDisable(false);
            } else {
                date_pick.setDisable(true);
            }
            if (date != null) {
                fecha_chk.setSelected(true);
                date_pick.setDisable(true);
            }
        });
        moneda_Combo.getItems().addAll(new PagoImpl().obtenerMonedas());
        if (moneda != null) {
            moneda_Combo.getSelectionModel().select(moneda);
            moneda_Combo.setDisable(true);
        }
        date_pick.setOnAction((e) -> {
            setMoneda();
        });
        moneda_Combo.setOnAction((e) -> {
            setMoneda();
        });

        save_btn.setOnAction((e) -> {
            guardarMonedaValor();
        });
        if (date != null) {
            setDate();
        }
        if (moneda != null) {
            setMoneda();
        }

        saldo_spin.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 999999999, 0));
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda() {
        moneda = moneda_Combo.getValue();
        if (moneda != null && moneda.getConverted().equals("1")) {

            PagoBackend impl = new PagoImpl();
            if (fecha_chk.isSelected()) {
                Date t1 = Date.from(date_pick.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                ValorMoneda valor = impl.obtenerValorMoneda(moneda, t1);
                if (valor != null) {
                    saldo_spin.getValueFactory().setValue(valor.getValor());
                }
            } else {
                ValorMoneda valor = impl.obtenerValorMonedaHoy(moneda);
                if (valor != null) {
                    saldo_spin.getValueFactory().setValue(valor.getValor());
                }
            }

            moneda_tf.setText(moneda.getMoneda());

        } else {
            moneda_tf.setText("");
        }
    }

    public void setDate() {
        date_pick.setValue(date);
        fecha_chk.setDisable(true);
        fecha_chk.setSelected(true);
    }

    public ValorMoneda getValorMoneda() {
        return valorMoneda;
    }

    public void setValorMoneda(ValorMoneda valorMoneda) {
        this.valorMoneda = valorMoneda;
    }

    private void guardarMonedaValor() {
        ValorMoneda valorMoneda = new ValorMoneda();
        valorMoneda.setValor(saldo_spin.getValue());
        valorMoneda.setMoneda(moneda);
        if (fecha_chk.isSelected()) {

            valorMoneda.setRegistro(Date.from(date_pick.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        PagoBackend bck = new PagoImpl();
        ValorMoneda guardado = null;
        if(valorMoneda.getRegistro()!=null){
            guardado = bck.guardarValorMoneda(valorMoneda,valorMoneda.getRegistro());
        }else{
            guardado = bck.guardarValorMoneda(valorMoneda);
        }
        

        if (guardado != null) {
            Alert alerta = Alerta.getAlert(Alert.AlertType.INFORMATION, "Guardado Exitoso", "", null);
            alerta.showAndWait();
            valorMoneda = guardado;
            Stage stg = (Stage) date_pick.getParent().getScene().getWindow();
            stg.close();
        } else {
            Alert alerta = Alerta.getAlert(Alert.AlertType.ERROR, "Error de guardado", "", null);
            alerta.showAndWait();
        }

    }

}
