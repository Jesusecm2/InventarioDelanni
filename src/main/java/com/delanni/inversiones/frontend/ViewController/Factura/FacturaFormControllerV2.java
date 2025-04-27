/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Factura;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Controllers.ConfigSystemImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.FacturaControllerImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.InventarioControllerImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.PagoImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Cliente;
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.ImagenProducto;
import com.delanni.inversiones.frontend.Backend.Entity.LineaFactura;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.ComprobantePago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Moneda;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Pago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.TipodePago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.ValorMoneda;
import com.delanni.inversiones.frontend.Backend.Entity.Producto;
import com.delanni.inversiones.frontend.Backend.Entity.Proveedor;
import com.delanni.inversiones.frontend.Backend.Entity.SystemParam;
import com.delanni.inversiones.frontend.Backend.Entity.Transacciones;
import com.delanni.inversiones.frontend.Backend.Interfaces.ConfigSystem;
import com.delanni.inversiones.frontend.Backend.Interfaces.FacturaBackend;
import com.delanni.inversiones.frontend.Backend.Interfaces.InventarioBackend;
import com.delanni.inversiones.frontend.Backend.Interfaces.PagoBackend;
import com.delanni.inversiones.frontend.Backend.Interfaces.Transaccion;
import com.delanni.inversiones.frontend.Backend.util.ImageConverter;
import com.delanni.inversiones.frontend.Backend.util.SelecionArchivos;
import com.delanni.inversiones.frontend.ViewController.Factura.Table.TLineaFactura;
import com.delanni.inversiones.frontend.ViewController.Factura.Table.TProducto;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Alerta;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Validadores;
import com.delanni.inversiones.frontend.ViewController.Pagos.PagoFacturaController;
import com.delanni.inversiones.frontend.ViewController.Pagos.ValorMonedaFormController;
import com.delanni.inversiones.frontend.ViewController.Producto.ProductoFormController;
import com.delanni.inversiones.frontend.ViewController.Producto.ProveedorFormController;
import java.io.File;
import java.net.URL;
import java.security.Key;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.dialog.LoginDialog;

/**
 *
 * @author Jesusecm
 */
public class FacturaFormControllerV2 implements Initializable {

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
    private TextField rif_ci;

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
    private TableView<TLineaFactura> table_view;

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
    private Label mto_lbl_act;

    @FXML
    private Label lbl_monto;

    @FXML
    private DatePicker fecha_ejec;

    @FXML
    private DatePicker f_factura;

    @FXML
    private ListView<Pago> list_pagos;

    @FXML
    private Button agregar_pago;

    @FXML
    private List<Producto> listado_productos;

    @FXML
    private ComboBox<Moneda> moneda_Combo;

    @FXML
    private TableColumn<TLineaFactura, String> tc_name;

    @FXML
    private TableColumn<TLineaFactura, String> tc_cant;

    @FXML
    private TableColumn<TLineaFactura, String> tc_ad;

    @FXML
    private TableColumn<TLineaFactura, String> tc_tot;

    @FXML
    private TableColumn<TLineaFactura, String> tc_precio;

    @FXML
    private TextField ref_pag;

    @FXML
    private TextField fnd_factura;

    @FXML
    private Button fin_factura;

    @FXML
    private Button upd_amnt;

    @FXML
    private Spinner<Double> upd_spin;

    @FXML
    private Spinner<Double> mto_spin;

    @FXML
    private TextField narra_pag;

    @FXML
    private Label monto_tot_lb;

    @FXML
    private Label pago_lbl_restante;

    private Proveedor proveedor;

    private Cliente cliente;

    @FXML
    private Button nxt_btn;

    @FXML
    private Button bck_btn;

    @FXML
    private Button clc_pago;

    private File file;
    @FXML
    private Label lb_cliente;

    @FXML
    private Label ci_cliente;

    private Factura modificada;

    private List<Pago> listado_pagos;
    private ValorMoneda valor;

    private boolean venta;

    public FacturaFormControllerV2(Factura modificada, boolean venta) {
        this.modificada = modificada;
        this.venta = venta;
    }

    public FacturaFormControllerV2(boolean venta) {
        this.venta = venta;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Validadores.tooltipAndValidadorShowingField(ref_pag, 30);
        Validadores.tooltipAndValidadorShowingField(narra_pag, 30);

        rif_ci.setOnAction((e -> {
            if (venta) {

                InventarioBackend bck = new InventarioControllerImpl();
                Cliente cliente = bck.BuscarCedulaCliente(rif_ci.getText());
                if (cliente == null) {

                    Alert excet = Alerta.getAlert(Alert.AlertType.CONFIRMATION, "Cliente no existe", "Desea agregar el cliente", null);
                    excet.showAndWait();
                    if (excet.getResult().equals(ButtonType.OK)) {
                        prov_create_btn.fire();
                    }

                } else {
                    this.cliente = cliente;
                    System.out.println("cliente si encontrado");
                    //cliente = control.getCliente();
                    prov_lb.setText(cliente.getNombre());
                    nxt_btn.fire();
                }
            } else {

                FacturaBackend backend = new FacturaControllerImpl();
                this.proveedor = backend.ProveedorbyRif(rif_ci.getText());
                if (this.proveedor == null) {
                    Alert excet = Alerta.getAlert(Alert.AlertType.CONFIRMATION, "Proveedor no existe", "Desea agregar el proveedor", null);
                    excet.showAndWait();
                    if (excet.getResult().equals(ButtonType.OK)) {
                        prov_create_btn.fire();
                    }
                }else{
                    nxt_btn.fire();
                    prov_lb.setText(this.proveedor.getNombre());
                }
            }
        }));
        mto_pagado.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 999999999, 0));
        upd_spin.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 999999999, 0));
        iva_value.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 999999999, 0));
        excento_value.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 999999999, 0));
        mto_spin.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 999999999, 0));
        iva_value.getValueFactory().setValue(0.0);
        excento_value.getValueFactory().setValue(0.0);
        mto_spin.setVisible(true);
        tc_precio.setEditable(true);
        ConfigSystem backSystem = new ConfigSystemImpl();
        SystemParam iva_param = backSystem.obtenerParametro(100, "IVA");
        if (iva_param != null) {
            iva_value.getValueFactory().setValue(iva_param.getValueNum());
        }

        clc_pago.setOnAction((e) -> {
            clearPagoForm();
        });
        cod_tf.setOnAction((e) -> {
            InventarioBackend bck = new InventarioControllerImpl();
            List<Producto> busqueda = bck.obtenerProducto(cod_tf.getText().trim());
            if (busqueda != null && !busqueda.isEmpty()) {
                ingresarTable(null, busqueda.get(0), 1.0);
                cod_tf.setText("");
            } else {
                cod_tf.setText("");
                Alert alerta = Alerta.getAlert(Alert.AlertType.ERROR, "Producto no encontrado", null, null);
                alerta.showAndWait();
            }
        });
        chk_parte.setSelected(true);
        chk_parte.setOnAction((e) -> {
            if (modificada == null) {

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
        f_factura.setOnAction((e) -> {
            if (f_factura.getValue() != null) {
                fecha_ejec.setValue(f_factura.getValue());
            }
        });

        fin_factura.setOnAction((e) -> {
            guardarFactura();
        });

        table_view.setOnMouseClicked((e) -> {
            if (table_view.getSelectionModel().getSelectedIndex() != -1) {
                upd_amnt.setDisable(false);
                upd_spin.setDisable(false);
                mto_spin.setDisable(false);
                upd_spin.getValueFactory().setValue(table_view.getSelectionModel().getSelectedItem().getCantidad());
                mto_spin.getValueFactory().setValue(table_view.getSelectionModel().getSelectedItem().getMonto());
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
            mto_spin.setDisable(true);
            TLineaFactura pr = table_view.getSelectionModel().getSelectedItem();

            pr.setMonto(mto_spin.getValue());

            pr.setCantidad(upd_spin.getValue());
            table_view.refresh();
            table_view.getSelectionModel().clearSelection();
            monto_tot_lb.setText(String.format("%.2f $", calcularTotal()));
            upd_spin.getValueFactory().setValue(0.0);
        });
        list_pagos.setOnMouseClicked((e) -> {
            Pago pago = list_pagos.getSelectionModel().getSelectedItem();
            if (pago != null) {
                PagoFacturaController control = new PagoFacturaController(pago, modificada);
                App.cargarVentanaModal("fxml/PagoForm", control, true, "Pagar Factura");

            }
        });
        moneda_Combo.setOnAction((e) -> {
            Moneda mon = moneda_Combo.getValue();
            if (mon != null && mon.getConverted().equals("1")) {
                PagoBackend bl = new PagoImpl();
                if (!chk_fecha.isSelected()) {
                    valor = bl.obtenerValorMonedaHoy(mon);
                } else {
                    valor = bl.obtenerValorMoneda(mon, Date.from(fecha_ejec.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                }
                if (valor == null) {

                    ValorMonedaFormController control = new ValorMonedaFormController(mon, fecha_ejec.getValue());
                    App.cargarVentanaModal("fxml/ValorMonedaForm", control, true, "Registrar Tasa");
                    if (control.getValorMoneda() != null) {
                        valor = control.getValorMoneda();
                        amnt_lbl.setText(String.format("%.2f", valor.getValor()));
                        calcularValorTotal();
                    } else {
                        moneda_Combo.getSelectionModel().clearSelection();
                    }

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

        combo_tf = combo_box.getEditor();
        cargarTiposPago();
        tc_name.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        tc_cant.setCellValueFactory(new PropertyValueFactory<>("cformat"));

        tc_tot.setCellValueFactory(new PropertyValueFactory<>("tformat"));

        tc_precio.setCellValueFactory(new PropertyValueFactory<>("mformat"));

        if (venta) {
            prov_create_btn.setText("Buscar Cliente");
            lb_cliente.setText("Cliente");
            ci_cliente.setText("Cédula:");
            mto_lbl_act.setVisible(false);
            mto_spin.setVisible(false);

        } else {
            prov_create_btn.setText("Buscar Proveedor");
            lb_cliente.setText("Proveedor");
            ci_cliente.setText("RIF:");
        }

        prov_create_btn.setOnAction((e) -> {

            if (venta) {
                ClienteFormController control = null;
                if (!rif_ci.getText().isEmpty() && !rif_ci.getText().isBlank()) {
                    control = App.cargarVentanaModal("Crear Cliente", "fxml/ClientesForm", true);
                } else {
                    control = new ClienteFormController(rif_ci.getText());
                    App.cargarVentanaModal("fxml/ClientesForm", control, true, "Crear Cliente");

                }
                if (control != null && control.getCliente() != null) {
                    cliente = control.getCliente();
                    prov_lb.setText(cliente.getNombre());
                    nxt_btn.fire();

                }
            } else {
                ProveedorFormController control = App.cargarVentanaModal("Crear Proveedor", "fxml/ProveedorForm", true);
                if (control.getProveedor() != null) {
                    proveedor = control.getProveedor();
                    prov_lb.setText(proveedor.toString());
                    nxt_btn.fire();
                }
            }

        });

        elim_prod.setOnAction((e) -> {
            eliminarProducto();
        });

        create_btn.setOnAction((e) -> {
            crearProducto();
        });

        InventarioBackend bckl = new InventarioControllerImpl();
        listado_productos = bckl.ListadoProducto();
        if (listado_productos != null && !listado_productos.isEmpty()) {
            combo_box.getItems().addAll(listado_productos);
        }
        AutoCompletionBinding<Producto> autoCompletado = TextFields.bindAutoCompletion(combo_box.getEditor(), combo_box.getItems());
        autoCompletado.setOnAutoCompleted(event -> {
            Producto p = event.getCompletion();
            ingresarTable(null, p, 1.0);
            combo_box.getEditor().setText("");
            combo_box.hide();

        });

        PagoBackend bck = new PagoImpl();
        List<Moneda> moneda = bck.obtenerMonedas();
        if (moneda != null) {
            moneda_Combo.setItems(FXCollections.observableArrayList(moneda));
        }

        if (modificada != null) {
            setModificada();
        }
    }

    private void ingresarTable(LineaFactura mod, Producto pr, Double cantidad) {
        Double valor = null;
        if (venta) {
            valor = pr.getPrecio_vent();
        } else {
            valor = pr.getPrecio_unit();
        }
        LineaFactura ln = null;
        if (mod == null) {
            ln = new LineaFactura(null, cantidad, valor, pr, null);
        } else {
            ln = mod;
        }

        TLineaFactura nuevaLinea = new TLineaFactura(ln);

        boolean exist = false;
        if (pr.getId() != null) {

            for (TLineaFactura p : table_view.getItems()) {
                if (pr.getId().equals(p.getLinea().getId_producto().getId())) {
                    exist = true;
                    p.setCantidad(p.getCantidad() + 1.0);
                    break;
                }
            }
        }
        if (!exist) {
            table_view.getItems().add(nuevaLinea);
        }

        table_view.refresh();
        monto_tot_lb.setText(String.format("%.2f", calcularTotal()));
    }

    private void crearProducto() {
        ProductoFormController control = new ProductoFormController(null, true);
        App.cargarVentanaModal("fxml/ProductoForm", control, true, "Modificar Producto");
        Producto nuevo = control.getProducto();
        if (nuevo != null) {
            ingresarTable(null, nuevo, 1.0);
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
        if (validarPago()) {
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
            if (pago.getTipo() == null && pago.getMoneda() == null) {

                return;
            }
            if (pago.getMoneda().getConverted().equals("1")) {
                pago.setMonto(mto_pagado.getValue() / valor.getValor());
                pago.setValor(valor);
            } else {
                pago.setMonto(mto_pagado.getValue());
            }

            if (chk_parte.isSelected()) {

                System.out.println("CalcularT:" + calcularTotal() + " / " + "MontoP" + montoPagado() + " / " + "Pago: " + pago.getMonto());
                if (calcularTotal() < (montoPagado() + pago.getMonto())) {
                    System.out.println("Retorna 1");
                    return;
                }
                System.out.println("flg 2");
                if (file != null) {
                    ImageConverter convertidor = new ImageConverter(file);
                    ComprobantePago comprobante = new ComprobantePago();
                    comprobante.setImagen(convertidor.getbase64img());
                    pago.setComprobante(comprobante);
                    file = null;
                }
                System.out.println("flg 3");
                System.out.println("Agregar pago parcial 1 ");
                listado_pagos.add(pago);
                list_pagos.setItems(FXCollections.observableArrayList(listado_pagos));
                pago_lbl_restante.setText(String.format("%.2f / %.2f", montoPagado(), calcularTotal()));
                clearPagoForm();
            } else {
                if (calcularTotal() < (montoPagado() + pago.getMonto())) {
                    System.out.println("Retorna 2");
                    return;
                }
                System.out.println("flg 2");
                if (file != null) {
                    ImageConverter convertidor = new ImageConverter(file);
                    ComprobantePago comprobante = new ComprobantePago();
                    comprobante.setImagen(convertidor.getbase64img());
                    pago.setComprobante(comprobante);
                    file = null;
                }
                System.out.println("flg 3");
                System.out.println("Agregar pago parcial 2");
                listado_pagos.add(pago);
                list_pagos.setItems(FXCollections.observableArrayList(listado_pagos));
                pago_lbl_restante.setText(String.format("%.2f / %.2f", montoPagado(), calcularTotal()));
                clearPagoForm();
            }
        }
    }

    private boolean validarPago() {
        String msg = null;
        if (combo_pagos.getValue() == null) {
            msg = "Debe seleccionar un método de pago";
        }

        if (moneda_Combo.getValue() == null) {
            msg = "Debe seleccionar una moneda de pago";
        }

        if (mto_pagado.getValue() < 1) {
            msg = "El monto debe ser mayor a 0";
        }

        if (msg != null) {
            Alert alerta = Alerta.getAlert(Alert.AlertType.ERROR, "Error", msg, null);
            alerta.showAndWait();
            return false;
        }

        return true;
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

    private Double SaldoOriginal() {
        Double dbl = 0.0;
        if (!table_view.getItems().isEmpty()) {
            for (TLineaFactura l : table_view.getItems()) {
                dbl += l.getTotal();
            }
        }

        return dbl;
    }

    private Double calcularTotal() {
        Double dbl = 0.0;
        Double iva = iva_value.getValue();
        Double exento = excento_value.getValue();
        dbl += SaldoOriginal();
        if (iva != 0) {
            dbl = dbl + (SaldoOriginal() * (iva_value.getValue() / 100));
        }
        if (exento != 0) {
            dbl = dbl - exento;
        }

        return dbl;
    }

    private Factura crearFactura() {
        Factura factura = new Factura();
        if (this.proveedor != null) {
            factura.setIdProveedor(proveedor);
        } else {
            factura.setIdCliente(cliente);
        }

        if (f_factura.getValue() != null) {
            factura.setCreate_at(Date.from(f_factura.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        List<LineaFactura> listado = new ArrayList<>();
        factura.setIVA(iva_value.getValue());
        factura.setExento(excento_value.getValue());
        table_view.getItems().forEach((e) -> {
            boolean saved = false;
            if (modificada != null) {
                for (LineaFactura ln : modificada.getLineas()) {
                    if (e.getLinea().getId_producto().equals(ln.getId_producto())) {
                        ln.setCantidad(e.getCantidad());
                        ln.setPrecio_unit(e.getMonto());
                        ln.setId_factura(modificada);
                        listado.add(ln);
                        saved = true;
                    }
                }
            }
            if (modificada == null && !saved) {

                listado.add(e.getLinea());
            }

        });
        factura.setLineas(listado);
        if (modificada != null) {
            factura.setId(modificada.getId());
        }
        return factura;
    }

    private void guardarFactura() {
        Factura guardar = crearFactura();
        if (listado_pagos == null && chk_parte.isSelected() && combo_pagos.getValue() != null) {
            System.out.println("registros de pago");
            registroPago();
        }
        if (!chk_parte.isSelected() && combo_pagos.getValue() != null) {
            Alert alert = Alerta.getAlert(Alert.AlertType.INFORMATION, "Error", "Debe finalizar el pago parcial", null);
            alert.showAndWait();
        }
        List<Pago> guardarPago = list_pagos.getItems();
        if (facturaEsValida(guardar, guardarPago)) {
            FacturaBackend backend = new FacturaControllerImpl();
            Factura saved = backend.guardarFactura(guardar, guardarPago);
            if (saved != null) {
                System.out.println("Guardado");
                Alert alerta = Alerta.getAlert(Alert.AlertType.INFORMATION, "Guardado con éxito", "Aceptado", null);
                alerta.showAndWait();
                resetearFormulario();

                if (this.modificada != null) {
                    Stage stg = (Stage) agregar_pago.getParent().getScene().getWindow();
                    stg.close();
                }
            } else {
                Alert alerta = Alerta.getAlert(Alert.AlertType.ERROR, "Ha ocurrido un error", "error", null);
                alerta.showAndWait();
            }

        }

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

    private void mostrarError(String error) {
        Alert alerta = Alerta.getAlert(Alert.AlertType.ERROR, "Error", error, null);
        alerta.showAndWait();
    }

    private boolean facturaEsValida(Factura factura, List<Pago> pagos) {
        boolean asignatario = false;
        if (factura.getLineas().isEmpty()) {
            mostrarError("Factura debe contener productos");
            return false;
        }

        if (factura.getIdProveedor() == null && factura.getIdCliente() == null) {
            mostrarError("Factura debe contener cliente asignado");
            return false;
        }
        return true;
    }

    private Double montoRestante() {
        return (calcularTotal() - montoPagado());
    }

    public Factura getModificada() {
        return modificada;
    }

    public void setModificada() {
        if (this.modificada.getIdProveedor() == null) {
            this.cliente = modificada.getIdCliente();
        } else {
            this.proveedor = modificada.getIdProveedor();
        }
        f_factura.setValue(this.modificada.getCreate_at().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        prov_create_btn.setDisable(true);
        mto_spin.setVisible(true);
        tc_precio.setEditable(true);
        tc_precio.setCellFactory(TextFieldTableCell.forTableColumn());
        tab_window.getSelectionModel().selectNext();
        tab_window.getSelectionModel().getSelectedItem().setDisable(false);
        table_view.refresh();
        modificada.getLineas().forEach((e) -> {
            ingresarTable(e, e.getId_producto(), e.getCantidad());
        });
        iva_value.getValueFactory().setValue(modificada.getIVA());
        excento_value.getValueFactory().setValue(modificada.getExento());
        List<Transacciones> trns = new PagoImpl().obtenerTransacciones(modificada);
        listado_pagos = new ArrayList<>();
        if (trns != null && !trns.isEmpty()) {
            trns.forEach((e) -> listado_pagos.add(e.getPago()));

            list_pagos.setItems(FXCollections.observableArrayList(listado_pagos));
        }

    }

    private void resetearFormulario() {
        this.cliente = null;
        this.proveedor = null;
        this.prov_lb.setText("");
        this.cod_tf.setText("");
        this.excento_value.getValueFactory().setValue(0.0);
        ConfigSystem backSystem = new ConfigSystemImpl();
        SystemParam iva_param = backSystem.obtenerParametro(100, "IVA");
        System.out.println("flg1");
        if (iva_param != null) {
            iva_value.getValueFactory().setValue(iva_param.getValueNum());
        }
        System.out.println("flg2");
        list_pagos.getItems().clear();
        //listado_productos.clear();
        table_view.getItems().clear();
        System.out.println("flg3");
        tab_window.getSelectionModel().getSelectedItem().setDisable(true);
        tab_window.getSelectionModel().select(0);
        tab_window.getSelectionModel().getSelectedItem().setDisable(false);
        clearPagoForm();
    }

}
