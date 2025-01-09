/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Pagos;

import com.delanni.inversiones.frontend.Backend.Controllers.PagoImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Moneda;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.ValorMoneda;
import com.delanni.inversiones.frontend.Backend.Interfaces.PagoBackend;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

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

    @FXML
    private Button save_btn;
    
    private Moneda moneda;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fecha_chk.setOnAction((e) -> {
            if (fecha_chk.isSelected()) {
                date_pick.setDisable(false);
            } else {
                date_pick.setDisable(true);
            }
        });
        
        save_btn.setOnAction((e)->{
            guardarMonedaValor();
        });

        saldo_spin.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 999999999, 0));
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
        moneda_tf.setText(moneda.getMoneda());
    }
    
    private void guardarMonedaValor(){
         ValorMoneda valorMoneda = new ValorMoneda();
         valorMoneda.setValor(saldo_spin.getValue());
         valorMoneda.setMoneda(moneda);
         if(fecha_chk.isSelected()){
             valorMoneda.setRegistro(null);
         }
         
         PagoBackend bck = new PagoImpl();
         ValorMoneda guardado = bck.guardarValorMoneda(valorMoneda);
         if(guardado!=null){
             System.out.println("guardado");
         }
         
    }
    
    

}
