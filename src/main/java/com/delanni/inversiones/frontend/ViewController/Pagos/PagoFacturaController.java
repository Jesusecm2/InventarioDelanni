/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Pagos;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Controllers.PagoImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.ComprobantePago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Moneda;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Pago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.TipodePago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.ValorMoneda;
import com.delanni.inversiones.frontend.Backend.Entity.Transacciones;
import com.delanni.inversiones.frontend.Backend.Interfaces.PagoBackend;
import com.delanni.inversiones.frontend.Backend.util.ImageConverter;
import com.delanni.inversiones.frontend.Backend.util.SelecionArchivos;
import com.delanni.inversiones.frontend.ViewController.Factura.Table.TProducto;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Getfile;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Jesusecm
 */
public class PagoFacturaController implements Initializable {

    private Stage window;

    private ValorMoneda valor;

    @FXML
    private Label pago_lbl_restante;
    @FXML
    private Button save_btn11;

    @FXML
    private Button agregar_pago;

    @FXML
    private ComboBox<Moneda> moneda_Combo;

    @FXML
    private ListView<Pago> list_pagos;

    @FXML
    private Label lbl_monto;

    @FXML
    private DatePicker fecha_ejec;

    @FXML
    private TextField narra_pag;

    @FXML
    private TextField ref_pag;

    private File file;

    @FXML
    private Spinner<Double> mto_pagado;

    @FXML
    private ComboBox<TipodePago> combo_pagos;

    @FXML
    private TextField cod_tf;

    @FXML
    private Label amnt_lbl;

    @FXML
    private Pago pagosave;

    @FXML
    private Label lb_img_nme;

    private Factura factura;

    @FXML
    private ImageView img_src;

    @FXML
    private CheckBox chk_parte;

    @FXML
    private CheckBox chk_fecha;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        img_src.setImage(Getfile.getIcono("normal/addimg64.png").getImage());
        mto_pagado.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 999999999, 0));
        agregar_pago.setDisable(false);
        agregar_pago.setOnAction((e) -> {
            registroPago();
        });
        chk_parte.setSelected(true);

        chk_parte.setOnAction((e) -> {

            if (!chk_parte.isSelected()) {
                mto_pagado.setDisable(false);
                //agregar_pago.setDisable(false);
            } else {
                mto_pagado.setDisable(true);
                calcularValorTotal();
                //agregar_pago.setDisable(true);
            }

        });
        PagoBackend bck = new PagoImpl();
        List<Moneda> moneda = bck.obtenerMonedas();
        if (moneda != null) {
            moneda_Combo.setItems(FXCollections.observableArrayList(moneda));
        }
        cargarTiposPago();
        moneda_Combo.setOnAction((e) -> {
            Moneda mon = moneda_Combo.getValue();
            if (mon != null && mon.getConverted().equals("1")) {
                PagoBackend bl = new PagoImpl();
                if (pagosave == null) {
                    valor = bl.obtenerValorMonedaHoy(mon);
                }
                if (valor == null && pagosave == null) {
                    ValorMonedaFormController control = App.cargarVentanaModal("Crear Valor", "fxml/ValorMonedaForm", false);
                    control.setMoneda(mon);
                    moneda_Combo.getSelectionModel().clearSelection();

                } else {

                    amnt_lbl.setText(String.format("%.2f", valor.getValor()));
                    lbl_monto.setText("Monto en".concat(mon.getCcy()));
                    calcularValorTotal();
                }
            } else if (mon != null) {
                amnt_lbl.setText("");
                lbl_monto.setText("Monto en ".concat(mon.getCcy()));
            }
        });
        save_btn11.setOnAction((e) -> {
            AgregarImagen();
        });
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    private void cargarTiposPago() {
        PagoBackend service = new PagoImpl();
        List<TipodePago> list = service.obtenerTiposPago();
        if (list != null && !list.isEmpty()) {
            combo_pagos.setItems(FXCollections.observableArrayList(list));
        }
    }

    private void AgregarImagen() {
        File fl = SelecionArchivos.seleccionarImagen();
        if (fl != null) {
            lb_img_nme.setText(fl.getName());
            file = fl;
        }
    }

    private void registroPago() {
        Pago pago = new Pago();
        if (pagosave != null) {
            pago = pagosave;
        }
        pago.setTipo(combo_pagos.getValue());
        pago.setNarrativa(narra_pag.getText());
        pago.setCod_ejecucion(ref_pag.getText());
        if (chk_fecha.isSelected()) {

            pago.setEjecucion(Date.from(fecha_ejec.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        pago.setMoneda(moneda_Combo.getValue());
        if (pago.getMoneda().getConverted().equals("1")) {
            pago.setMonto(mto_pagado.getValue() / valor.getValor());
            pago.setValor(valor);
        } else {
            pago.setMonto(mto_pagado.getValue());
        }

        if (chk_parte.isSelected()) {
            if (pago.getMoneda().getConverted().equals("1")) {
                pago.setMonto(calcularTotal());
                pago.setValor(valor);

            }

            if (calcularTotal() < (montoPagado() + pago.getMonto())) {
                return;
            }
            if (file != null) {
                ImageConverter convertidor = new ImageConverter(file);
                ComprobantePago comprobante = new ComprobantePago();
                comprobante.setImagen(convertidor.getbase64img());
                pago.setComprobante(comprobante);
                file = null;
            }
            if (pagosave != null) {
                Stage stg = (Stage) agregar_pago.getParent().getScene().getWindow();
                stg.close();
            }
            PagoBackend bcl = new PagoImpl();
            bcl.guardarPagoFactura(factura, pago);
            pago_lbl_restante.setText(String.format("¨P: %.2f / T: %.2f", montoPagado(), calcularTotal()));
            clearPagoForm();
        } else {
            if (pago.getMoneda().getConverted().equals("1")) {
                pago.setMonto(calcularTotal());
                pago.setValor(valor);

            }

            if (calcularTotal() < (montoPagado() + pago.getMonto())) {
                return;
            }
            if (file != null) {
                ImageConverter convertidor = new ImageConverter(file);
                ComprobantePago comprobante = new ComprobantePago();
                comprobante.setImagen(convertidor.getbase64img());
                pago.setComprobante(comprobante);
                file = null;
            }
            if (pagosave != null) {
                Stage stg = (Stage) agregar_pago.getParent().getScene().getWindow();
                stg.close();
            }
            PagoBackend bcl = new PagoImpl();
            bcl.guardarPagoFactura(factura, pago);
            pago_lbl_restante.setText(String.format("¨P: %.2f / T: %.2f", montoPagado(), calcularTotal()));
            clearPagoForm();
        }

    }

    private void clearPagoForm() {
        valor = null;
        combo_pagos.getSelectionModel().clearSelection();
        //chk_parte.setSelected(false);
        mto_pagado.getValueFactory().setValue(0.0);
        //mto_pagado.setDisable(true);
        moneda_Combo.getSelectionModel().clearSelection();
        narra_pag.setText("");
        ref_pag.setText("");
        lb_img_nme.setText("Empty");

    }

    private Double montoPagado() {
        return this.factura.getSaldo_pagado();
    }

    private Double calcularTotal() {
        return this.factura.getSaldo();
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {

        this.factura = factura;
        pago_lbl_restante.setText(String.format("P: %.2f / T: %.2f", montoPagado(), calcularTotal()));
        calcularValorTotal();
    }

    private void calcularValorTotal() {
        if (moneda_Combo.getSelectionModel().getSelectedItem() != null) {
            if (valor.getMoneda().getConverted().equals("1")) {
                Double temp = valor.getValor() * (calcularTotal() - montoPagado());
                mto_pagado.getValueFactory().setValue(temp);
                return;
            }
        }
        Double temp =  (calcularTotal() - montoPagado());
        mto_pagado.getValueFactory().setValue(temp);
    }

    public void setPago(Pago trn) {
        this.pagosave = trn;
        // ver_comprobante.setVisible(true);
        // comprobante_btn.setVisible(false);
        chk_fecha.setDisable(true);
        // egreso_comb.getSelectionModel().select(trn.getTpIngreso());
        combo_pagos.getSelectionModel().select(pagosave.getTipo());
        mto_pagado.getValueFactory().setValue(pagosave.getMonto());
        narra_pag.setText(pagosave.getNarrativa());
        narra_pag.setEditable(false);
        if (this.pagosave.getMoneda().getConverted().equals("1")) {
            this.valor = this.pagosave.getValor();
        }

        ref_pag.setEditable(false);
        // egreso_comb.setDisable(true);
        combo_pagos.setDisable(true);

        moneda_Combo.getSelectionModel().select(pagosave.getMoneda());
        moneda_Combo.setDisable(true);
        mto_pagado.setDisable(true);
        ref_pag.setText(pagosave.getCod_ejecucion());
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");
        // lbl_date.setText(formato.format(trn.getFecha()));

        if (pagosave.getComprobante() == null) {
            // ver_comprobante.setDisable(true);
        }

    }

}
