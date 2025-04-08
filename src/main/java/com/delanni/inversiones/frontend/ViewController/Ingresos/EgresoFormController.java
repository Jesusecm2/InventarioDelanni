/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Ingresos;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Controllers.ImageControllerImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.PagoImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.ComprobantePago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Moneda;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Pago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.TipodePago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.ValorMoneda;
import com.delanni.inversiones.frontend.Backend.Entity.TpIngreso;
import com.delanni.inversiones.frontend.Backend.Entity.Transacciones;
import com.delanni.inversiones.frontend.Backend.Interfaces.ImagenController;
import com.delanni.inversiones.frontend.Backend.Interfaces.PagoBackend;
import com.delanni.inversiones.frontend.Backend.util.ImageConverter;
import com.delanni.inversiones.frontend.Backend.util.SelecionArchivos;
import com.delanni.inversiones.frontend.ViewController.Inicio.CarruselController;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Alerta;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Getfile;
import com.delanni.inversiones.frontend.ViewController.Pagos.ValorMonedaFormController;
import java.io.File;
import java.io.IOException;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Jesusecm
 */
public class EgresoFormController implements Initializable {

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
    private Button comprobante_btn;

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
    private ComboBox<TpIngreso> egreso_comb;

    @FXML
    private TextField cod_tf;

    @FXML
    private Label amnt_lbl;

    @FXML
    private Label lb_img_nme;

    private Factura factura;

    @FXML
    private ImageView img_src;
    
        @FXML
    private GridPane main_grid;

    @FXML
    private CheckBox chk_parte;

    @FXML
    private CheckBox chk_fecha;

    @FXML
    private Label lbl_date;

    @FXML
    private Button ver_comprobante;

    private Transacciones trn;

    @FXML
    private ImageView imagen;
    private Parent carrusel;

    private CarruselController controlcarrusel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        img_src.setImage(Getfile.getIcono("normal/addimg64.png").getImage());
        mto_pagado.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 999999999, 0));
        mto_pagado.setDisable(false);
        agregar_pago.setDisable(false);
        agregar_pago.setOnAction((e) -> {
            registroPago();

        });
        
       
        comprobante_btn.setOnAction((e) -> {
            AgregarImagen();
        });

        chk_fecha.setOnAction((e) -> {
            if (chk_fecha.isSelected()) {
                fecha_ejec.setDisable(false);
            } else {
                fecha_ejec.setDisable(true);
            }
        });
        
        
        ver_comprobante.setOnAction((e)->{
            loadCarrusel();
        });
        
        
        PagoBackend bck = new PagoImpl();
        List<Moneda> moneda = bck.obtenerMonedas();
        if (moneda != null) {
            moneda_Combo.setItems(FXCollections.observableArrayList(moneda));
        }
        List<TpIngreso> egresos = bck.obtenerIngreso("E");
        if (egresos != null) {

            egreso_comb.setItems(FXCollections.observableArrayList(egresos));
        }

        cargarTiposPago();
        moneda_Combo.setOnAction((e) -> {
            Moneda mon = moneda_Combo.getValue();
            if (mon != null && mon.getConverted().equals("1")) {
                PagoBackend bl = new PagoImpl();
                if (trn == null) {
                    valor = bl.obtenerValorMonedaHoy(mon);
                }
                if (valor == null && trn == null) {
                     ValorMonedaFormController control = new ValorMonedaFormController(mon, fecha_ejec.getValue());
                    App.cargarVentanaModal("fxml/ValorMonedaForm", control, true, "Registrar Tasa");
                    moneda_Combo.getSelectionModel().clearSelection();

                } else {

                    amnt_lbl.setText(String.format("%.2f", valor.getValor()));
                    lbl_monto.setText("Monto en ".concat(mon.getCcy()));
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

        if (file != null) {
            ImageConverter convertidor = new ImageConverter(file);
            ComprobantePago comprobante = new ComprobantePago();
            comprobante.setImagen(convertidor.getbase64img());
            pago.setComprobante(comprobante);
            file = null;
        }
        PagoBackend bcl = new PagoImpl();
        Pago retorno = bcl.guardarPagoIngreso(egreso_comb.getValue(), pago);
        if (retorno != null) {

            clearPagoForm();
        }

    }

    private void clearPagoForm() {
        Alert alert = Alerta.getAlert(Alert.AlertType.INFORMATION, "Completado", "Se guardo exitosamente", null);
        alert.showAndWait();
        Stage st = (Stage) this.combo_pagos.getParent().getScene().getWindow();
        st.close();
        /*valor = null;
        
        
        combo_pagos.getSelectionModel().clearSelection();
        
        combo_pagos.getEditor().clear();
        

        //combo_pagos.getEditor().setPromptText("Alocate");
        //chk_parte.setSelected(false);
        mto_pagado.getValueFactory().setValue(0.0);
        //mto_pagado.setDisable(true);
        moneda_Combo.getSelectionModel().clearSelection();
        narra_pag.setText("");
        ref_pag.setText("");
        lb_img_nme.setText("Empty");
         */
    }

    private Double montoPagado() {
        return this.factura.getSaldo_pagado();
    }

    /*private Double calcularTotal() {
        return this.factura.getSaldo();
    }*/
    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {

        this.factura = factura;

    }

    private void calcularValorTotal() {
        return;
        /*if (moneda_Combo.getSelectionModel().getSelectedItem() != null) {
            if (valor.getMoneda().getConverted().equals("1")) {
                Double temp = valor.getValor() * (calcularTotal() - montoPagado());
                mto_pagado.getValueFactory().setValue(temp);
                return;
            }
        }
        Double temp = valor.getValor() * (calcularTotal() - montoPagado());
        mto_pagado.getValueFactory().setValue(temp);*/
    }

    public Transacciones getPago() {
        return trn;
    }

    public void setPago(Transacciones trn) {
        this.trn = trn;
        ver_comprobante.setVisible(true);
        comprobante_btn.setVisible(false);
        chk_fecha.setDisable(true);
        egreso_comb.getSelectionModel().select(trn.getTpIngreso());
        combo_pagos.getSelectionModel().select(trn.getPago().getTipo());
        mto_pagado.getValueFactory().setValue(trn.getPago().getMonto());
        narra_pag.setText(trn.getPago().getNarrativa());
        narra_pag.setEditable(false);
        ref_pag.setEditable(false);
        egreso_comb.setDisable(true);
        combo_pagos.setDisable(true);
        this.valor = trn.getPago().getValor();

        moneda_Combo.getSelectionModel().select(trn.getPago().getMoneda());
        moneda_Combo.setDisable(true);
        mto_pagado.setDisable(true);
        ref_pag.setText(trn.getPago().getCod_ejecucion());
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");
        lbl_date.setText(formato.format(trn.getFecha()));

        if (trn.getPago().getComprobante() != null) {
            ver_comprobante.setDisable(false);
            ImagenController controller = new ImageControllerImpl();
            String img = controller.imageString(trn.getPago().getComprobante().getImagen());
            ImageConverter convertidor = new ImageConverter(img);
            this.imagen = new ImageView(convertidor.getImage());
            this.imagen.setFitHeight(600);
            this.imagen.setFitWidth(400);
            this.imagen.getStyleClass().add("img_pagin");
        }else{
            ver_comprobante.setDisable(true);
        }
         try {
            this.carrusel = App.loadFXML("fxml/Carrusel");
            this.controlcarrusel = App.loadctual.getController();
           // this.pg_pagination = controlcarrusel.getPg_nation();
            carrusel.setVisible(false);
            main_grid.add(carrusel, 0, 0, GridPane.REMAINING, GridPane.REMAINING);
        } catch (IOException ex) {

        }

    }
    
     private void loadCarrusel() {
         System.out.println("cargar carrusel");
        if (this.imagen != null) {
            List<ImageView> listado = new ArrayList<>();
            listado.add(imagen);
            controlcarrusel.setImg_viewls(listado);
            carrusel.setVisible(true);
        }
    }

}
