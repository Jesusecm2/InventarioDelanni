/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Ventas;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Controllers.FacturaControllerImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.InventarioControllerImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.PagoImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Cliente;
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.LineaFactura;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.ComprobantePago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Moneda;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Pago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.TipodePago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.ValorMoneda;
import com.delanni.inversiones.frontend.Backend.Entity.Producto;
import com.delanni.inversiones.frontend.Backend.Entity.Proveedor;
import com.delanni.inversiones.frontend.Backend.Interfaces.FacturaBackend;
import com.delanni.inversiones.frontend.Backend.Interfaces.InventarioBackend;
import com.delanni.inversiones.frontend.Backend.Interfaces.PagoBackend;
import com.delanni.inversiones.frontend.Backend.util.ImageConverter;
import com.delanni.inversiones.frontend.Backend.util.SelecionArchivos;
import com.delanni.inversiones.frontend.ViewController.Factura.ClienteFormController;
import com.delanni.inversiones.frontend.ViewController.Factura.Table.TProducto;
import com.delanni.inversiones.frontend.ViewController.Pagos.ValorMonedaFormController;
import com.delanni.inversiones.frontend.ViewController.Producto.ProductoFormController;
import com.delanni.inversiones.frontend.ViewController.Producto.ProveedorFormController;
import java.io.File;
import java.net.URL;
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
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Jesusecm
 */
public class VentaFormController implements Initializable {

    @FXML
    private Button prov_create_btn;

    @FXML
    private Button elim_prod;

    @FXML
    private TabPane tab_window;

    @FXML
    private Button create_btn;

    //**********************************Page 1
    @FXML
    private Label prov_lb;

    @FXML
    private Label lb_img_nme;

    @FXML
    private TextField cod_tf;

    @FXML
    private ComboBox<Producto> combo_box;

    @FXML
    private ComboBox<TipodePago> combo_pagos;

    @FXML
    private CheckBox chk_parte;

    @FXML
    private CheckBox chk_fecha;

    private TextField combo_tf;

    @FXML
    private TableView<TProducto> table_view;

    @FXML
    private Spinner<Double> mto_pagado;

    @FXML
    private Spinner<Double> iva_value;

    @FXML
    private Spinner<Double> excento_value;

    @FXML
    private Button save_btn11;

    @FXML
    private Label amnt_lbl;

    @FXML
    private Label lbl_monto;

    @FXML
    private DatePicker fecha_ejec;

    @FXML
    private ListView<Pago> list_pagos;

    @FXML
    private Button agregar_pago;

    @FXML
    private ComboBox<Moneda> moneda_Combo;

    @FXML
    private TableColumn<TProducto, String> tc_name;

    @FXML
    private TableColumn<TProducto, String> tc_cant;

    @FXML
    private TableColumn<TProducto, String> tc_ad;

    @FXML
    private TableColumn<TProducto, String> tc_tot;

    @FXML
    private TableColumn<TProducto, String> tc_precio;

    @FXML
    private TextField ref_pag;

    @FXML
    private Button fin_factura;

    @FXML
    private Button upd_amnt;

    @FXML
    private Spinner<Double> upd_spin;

    @FXML
    private TextField narra_pag;

    @FXML
    private Label monto_tot_lb;

    @FXML
    private Label pago_lbl_restante;

    private Cliente cliente;

    private Integer pagina;

    @FXML
    private Button nxt_btn;

    @FXML
    private Button bck_btn;

    private Producto last_Select;

    private List<TProducto> table_productos;

    private List<File> list_file;

    private File file;

    private List<Pago> listado_pagos;
    private ValorMoneda valor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mto_pagado.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 999999999, 0));
        upd_spin.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 999999999, 0));
        iva_value.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 999999999, 0));
        excento_value.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 999999999, 0));

        iva_value.getValueFactory().setValue(0.0);
        excento_value.getValueFactory().setValue(0.0);

        chk_parte.setSelected(true);
        chk_parte.setOnAction((e) -> {
            if (listado_pagos == null || listado_pagos.isEmpty()) {
                if (!chk_parte.isSelected()) {
                    mto_pagado.setDisable(false);
                    agregar_pago.setDisable(false);
                } else {
                    mto_pagado.setDisable(true);
                    agregar_pago.setDisable(true);
                    calcularValorTotal();
                }
            } else {
                chk_parte.setSelected(false);
                mto_pagado.setDisable(false);
                agregar_pago.setDisable(false);
            }
        });

        nxt_btn.setOnAction((e) -> {
            tab_window.getSelectionModel().getSelectedItem().setDisable(true);
            tab_window.getSelectionModel().selectNext();
            tab_window.getSelectionModel().getSelectedItem().setDisable(false);
        });

        bck_btn.setOnAction((e) -> {
            tab_window.getSelectionModel().getSelectedItem().setDisable(true);
            tab_window.getSelectionModel().selectPrevious();
            tab_window.getSelectionModel().getSelectedItem().setDisable(false);
        });

        agregar_pago.setOnAction((e) -> {
            registroPago();
        });

        fin_factura.setOnAction((e) -> {
            guardarFactura();
        });

        table_view.setOnMouseClicked((e) -> {
            if (table_view.getSelectionModel().getSelectedIndex() != -1) {
                upd_amnt.setDisable(false);
                upd_spin.setDisable(false);
                upd_spin.getValueFactory().setValue(table_view.getSelectionModel().getSelectedItem().getCantidad());

            }
        });

        chk_fecha.setOnAction((e) -> {
            if (chk_fecha.isSelected()) {
                fecha_ejec.setDisable(false);
            } else {
                fecha_ejec.setDisable(true);
            }
        });

        save_btn11.setOnAction((e) -> {
            AgregarImagen();
        });

        upd_amnt.setOnAction((e) -> {
            upd_amnt.setDisable(true);
            upd_spin.setDisable(true);
            TProducto pr = table_view.getSelectionModel().getSelectedItem();
            pr.setCantidad(upd_spin.getValue());
            table_view.refresh();
            table_view.getSelectionModel().clearSelection();
            monto_tot_lb.setText(String.format("%.2f $", calcularTotal()));
            upd_spin.getValueFactory().setValue(0.0);
        });

        moneda_Combo.setOnAction((e) -> {
            Moneda mon = moneda_Combo.getValue();
            if (mon != null && mon.getConverted().equals("1")) {
                PagoBackend bl = new PagoImpl();
                valor = bl.obtenerValorMonedaHoy(mon);
                if (valor == null) {
                    ValorMonedaFormController control = App.cargarVentanaModal("Crear Valor", "fxml/ValorMonedaForm", false);
                    control.setMoneda(mon);
                    moneda_Combo.getSelectionModel().clearSelection();

                } else {
                    amnt_lbl.setText(String.format("%.2f", valor.getValor()));
                    lbl_monto.setText("Monto en".concat(mon.getCcy()));
                }
            } else if (mon != null) {
                amnt_lbl.setText("");
                lbl_monto.setText("Monto en".concat(mon.getCcy()));
            }
        });

        combo_tf = combo_box.getEditor();
        cargarTiposPago();
        tc_name.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        tc_cant.setCellValueFactory(new PropertyValueFactory<>("Scantidad"));

        tc_tot.setCellValueFactory(new PropertyValueFactory<>("Stotal_vent"));

        tc_precio.setCellValueFactory(new PropertyValueFactory<>("total_vent"));

        prov_create_btn.setOnAction((e) -> {
            ClienteFormController control = App.cargarVentanaModal("Crear Cliente", "fxml/ClientesForm", true);
            if (control != null) {
                cliente = control.getCliente();
                tab_window.getSelectionModel().getSelectedItem().setDisable(true);
                tab_window.getSelectionModel().selectNext();
                tab_window.getSelectionModel().getSelectedItem().setDisable(false);
                prov_lb.setText(cliente.getNombre());
            }

        });

        elim_prod.setOnAction((e) -> {
            eliminarProducto();
        });

        create_btn.setOnAction((e) -> {
            crearProducto();
        });
        combo_box.setOnAction((e) -> {
            System.out.println("Cmbo Action");
        });
        combo_box.setOnKeyPressed((e) -> {
            if (e.getCode() == KeyCode.ENTER && last_Select != null) {
                System.out.println(last_Select);
                ingresarTable(last_Select);
                last_Select = null;
                combo_box.setItems(null);
                combo_tf.setText("");
            }
        });
        combo_tf.setOnKeyPressed((e) -> {

        });
        combo_tf.setOnKeyReleased((e) -> {

            try {
                last_Select = combo_box.getSelectionModel().getSelectedItem();
            } catch (Exception t) {
                last_Select = null;
            }
            if (e.getCode() != KeyCode.ENTER) {
                if (e.getCode() != KeyCode.ENTER && e.getCode() != KeyCode.DOWN && e.getCode() != KeyCode.UP) {
                    InventarioBackend bck = new InventarioControllerImpl();
                    List<Producto> list = bck.buscarNombre(combo_tf.getText());
                    if (list != null && !list.isEmpty()) {
                        combo_box.setItems(FXCollections.observableArrayList(list));
                        combo_box.show();
                    }
                }
            }
        });
        cod_tf.setOnKeyReleased((e) -> {
            if (e.getCode() == KeyCode.ENTER) {

                InventarioBackend bck = new InventarioControllerImpl();
                List<Producto> list = bck.obtenerProducto(cod_tf.getText());
                if (list != null && !list.isEmpty()) {
                    Producto add = list.get(0);
                    ingresarTable(add);
                } else {

                }

                cod_tf.setText("");
            }
        });

        PagoBackend bck = new PagoImpl();
        List<Moneda> moneda = bck.obtenerMonedas();
        if (moneda != null) {
            moneda_Combo.setItems(FXCollections.observableArrayList(moneda));
        }

    }

    private void ingresarTable(Producto pr) {
        if (table_productos == null) {
            table_productos = new ArrayList<>();
            table_view.setItems(FXCollections.observableArrayList(table_productos));
        }
        TProducto lc = new TProducto(pr, 1.0);
        boolean exist = false;
        for (TProducto p : table_view.getItems()) {
            if (pr.getId().equals(p.getProducto().getId())) {
                exist = true;
                p.setCantidad(p.getCantidad() + 1.0);
                break;
            }
        }
        if (!exist) {
            table_view.getItems().add(lc);
        }

        table_view.refresh();
        monto_tot_lb.setText(String.format("%.2f", calcularTotal()));
    }

    private void crearProducto() {
        ProductoFormController control = App.cargarVentanaModal("Actualizar", "fxml/ProductoForm", false);
        control.setCloseform(true);
        Producto nuevo = control.getProducto();
        if (nuevo != null) {
            ingresarTable(nuevo);
        }
    }

    private void eliminarProducto() {
        if (table_view.getSelectionModel().getSelectedIndex() != -1) {
            table_view.getItems().remove(table_view.getSelectionModel().getSelectedIndex());
            table_view.refresh();
        }
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
        if (combo_pagos.getValue() != null) {

            if (listado_pagos == null) {
                listado_pagos = new ArrayList<>();
            }

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

                listado_pagos.add(pago);
                list_pagos.setItems(FXCollections.observableArrayList(listado_pagos));
                pago_lbl_restante.setText(String.format("%.2f / %.2f", montoPagado(), calcularTotal()));
                clearPagoForm();
            }
        }
    }

    private void clearPagoForm() {
        valor = null;
        combo_pagos.getSelectionModel().clearSelection();
        chk_parte.setSelected(false);
        mto_pagado.getValueFactory().setValue(0.0);
        //mto_pagado.setDisable(true);
        moneda_Combo.getSelectionModel().clearSelection();
        narra_pag.setText("");
        ref_pag.setText("");
        lb_img_nme.setText("Empty");

    }

    private Double montoPagado() {
        Double pagado = 0.0;
        for (Pago l : list_pagos.getItems()) {
            pagado += l.getMonto();
        }
        return pagado;
    }

    private Double calcularTotal() {
        Double dbl = 0.0;
        if (!table_view.getItems().isEmpty()) {
            for (TProducto l : table_view.getItems()) {
                dbl += l.getCantidad() * l.getPrecio_unit();
            }
        }

        return dbl;
    }

    private Factura crearFactura() {
        Factura factura = new Factura();
        factura.setIdCliente(cliente);
        List<LineaFactura> listado = new ArrayList<>();
        factura.setIVA(iva_value.getValue());
        factura.setExento(excento_value.getValue());
        table_view.getItems().forEach((e) -> {
            LineaFactura lnf = new LineaFactura();
            lnf.setCantidad(e.getCantidad());
            lnf.setId_producto(e.getProducto());
            lnf.setPrecio_unit(e.getPrecio_unit());
            listado.add(lnf);
        });
        factura.setLineas(listado);
        return factura;
    }

    private void guardarFactura() {
        Factura guardar = crearFactura();
        if (listado_pagos == null && chk_parte.isSelected() && combo_pagos.getValue() != null) {
            registroPago();
        }
        List<Pago> guardarPago = list_pagos.getItems();

        FacturaBackend backend = new FacturaControllerImpl();
        backend.guardarFactura(guardar, guardarPago);
        System.out.println("Guardado");

    }

    private void calcularValorTotal() {
        if (moneda_Combo.getSelectionModel().getSelectedItem() != null) {
            if (moneda_Combo.getSelectionModel().getSelectedItem().getConverted().equals("1")) {
                Double temp = valor.getValor() * (calcularTotal() - montoPagado());
                mto_pagado.getValueFactory().setValue(temp);
                return;
            }
        }
        Double temp = (calcularTotal() - montoPagado());
        mto_pagado.getValueFactory().setValue(temp);
    }

}
