/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Inicio;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Controllers.FacturaControllerImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.InventarioControllerImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.PagoImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Cliente;
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.LineaFactura;
import com.delanni.inversiones.frontend.Backend.Entity.Producto;
import com.delanni.inversiones.frontend.Backend.Entity.Proveedor;
import com.delanni.inversiones.frontend.Backend.Entity.Transacciones;
import com.delanni.inversiones.frontend.Backend.Interfaces.FacturaBackend;
import com.delanni.inversiones.frontend.Backend.Interfaces.InventarioBackend;
import com.delanni.inversiones.frontend.Backend.Interfaces.PagoBackend;
import com.delanni.inversiones.frontend.Backend.Interfaces.Transaccion;
import com.delanni.inversiones.frontend.ViewController.Factura.FacturaFormController;
import com.delanni.inversiones.frontend.ViewController.Factura.Table.TFacturaInicio;
import com.delanni.inversiones.frontend.ViewController.Factura.Table.TLineaFactura;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Alerta;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Getfile;
import com.delanni.inversiones.frontend.ViewController.Interfaces.Controladores;
import com.delanni.inversiones.frontend.ViewController.Pagos.PagoFacturaController;
import com.delanni.inversiones.frontend.ViewController.Producto.CategoriaFormController;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 *
 * @author Jesusecm
 */
public class FacturaController implements Initializable {

    private Parent lastRoot;

    private Parent newRoot;

    private StackPane stack_pane;

    @FXML
    private ImageView img_src;

    @FXML
    private TableView<TFacturaInicio> tv_factura;

    @FXML
    private TableColumn<TFacturaInicio, String> tc_nombre;

    @FXML
    private TableColumn<TFacturaInicio, Double> tc_monto;

    @FXML
    private TableColumn<TFacturaInicio, Double> tc_pagado;

    @FXML
    private TableView<TLineaFactura> tv_detalle;

    @FXML
    private TableColumn<TLineaFactura, String> td_pr;

    @FXML
    private TableColumn<TLineaFactura, Double> td_cnt;

    @FXML
    private TableColumn<TLineaFactura, Double> td_total;

    @FXML
    private Button create_btn;

    @FXML
    private Button export_factura;

    @FXML
    private Button pagar_btn;

    @FXML
    private Button clear_btn;

    @FXML
    private DatePicker date_pick;

    @FXML
    private Label total_lb;

    @FXML
    private ComboBox<Proveedor> cat_box1;

    @FXML
    private ComboBox<Cliente> cat_box2;

    @FXML
    private ComboBox<String> cat_box;

    @FXML
    private ComboBox<String> sts_box;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //   throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String[] find_box = new String[]{"Proveedor", "Cliente"};

        String[] sts_item = new String[]{"Activo", "Cancelado"};
        cat_box.setItems(FXCollections.observableArrayList(find_box));
        sts_box.setItems(FXCollections.observableArrayList(sts_item));

        create_btn.setOnMouseClicked((e) -> {
            loadForm();
        });
        tc_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tc_monto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        tc_pagado.setCellValueFactory(new PropertyValueFactory<>("pagado"));

        tv_factura.setOnMouseClicked((e) -> {
            TFacturaInicio tf = tv_factura.getSelectionModel().getSelectedItem();
            if (tf != null) {
                tv_detalle.setItems(FXCollections.observableArrayList(tf.getLineas()));
                total_lb.setText("Total: ".concat(String.valueOf(tf.getMonto()).concat("$")));
            }
            if(e.getClickCount()>1 && e.getButton().PRIMARY==MouseButton.PRIMARY){
                    PagoBackend bck = new PagoImpl();
                    List<Transacciones> valores = bck.obtenerPago(tv_factura.getSelectionModel().getSelectedItem().getFactura());
                    valores.forEach((p)->System.out.println(p));
            }
        });

        export_factura.setOnAction((e) -> {
            if (tv_factura.getSelectionModel().getSelectedItem() != null) {
                try {
                    Factura find_f = tv_factura.getSelectionModel().getSelectedItem().getFactura();
                    FacturaBackend bck = new FacturaControllerImpl();
                    InputStream stream = bck.reporteFactura(find_f);
                    FileChooser chooser = new FileChooser();
                    chooser.setTitle("Reporte Factura");
                    if (find_f.getIdCliente() != null) {
                        chooser.setInitialFileName(find_f.getIdCliente().getNombre().concat("-").concat(String.valueOf(find_f.getId())));
                    } else {
                        chooser.setInitialFileName(find_f.getIdProveedor().getNombre().concat("-").concat(String.valueOf(find_f.getId())));
                    }
                    chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
                    File guardar = chooser.showSaveDialog(tv_factura.getParent().getScene().getWindow());
                    if (guardar != null) {
                        byte[] buffer = new byte[4096];
                        FileOutputStream output = new FileOutputStream(guardar);
                        int bytesRead;
                        while ((bytesRead = stream.read(buffer)) != -1) {
                            output.write(buffer, 0, bytesRead);
                        }

                    }

                    if (Desktop.isDesktopSupported() && guardar.exists()) {
                        Desktop.getDesktop().open(guardar);
                        Alert alert = Alerta.getAlert(Alert.AlertType.INFORMATION, "Solicitud Completada", " ", null);
                        alert.show();
                    }
                } catch (IOException ex) {
                    Alert alert = Alerta.getAlert(Alert.AlertType.ERROR, "Solicitud no completada", " ", null);
                    alert.show();
                }

            }
        });

        td_pr.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        td_cnt.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        td_total.setCellValueFactory(new PropertyValueFactory<>("total"));

        /*tv_factura.setRowFactory(tv -> new TableRow<TFacturaInicio>() {
            @Override
            protected void updateItem(TFacturaInicio item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setStyle("");
                } else {
                    if(item.getPagado()==0.0){
                        
                    }
                    setStyle("-fx-background-color: lightgreen;");
                }

            }
        });*/ 
        clear_btn.setOnAction((e) -> {
            cat_box.getSelectionModel().clearSelection();
            cat_box1.getSelectionModel().clearSelection();
            cat_box2.getSelectionModel().clearSelection();
            sts_box.getSelectionModel().clearSelection();
            date_pick.setValue(null);
        });

        pagar_btn.setOnMouseClicked((e) -> {
            loadPago();
        });
        cat_box1.setOnAction((e) -> {
            try {

                buscarFacturas();

            } catch (Exception ex) {
                Alert a = Alerta.getAlert(Alert.AlertType.ERROR, "Error de conexión", ex.getMessage(), null);
                a.showAndWait();
            }
        });

        cat_box2.setOnAction((e) -> {
            try {
                buscarFacturas();
            } catch (Exception ex) {
                Alert a = Alerta.getAlert(Alert.AlertType.ERROR, "Error de conexión", ex.getMessage(), null);
                a.showAndWait();
            }
        });

        sts_box.setOnAction((e) -> {
            try {
                buscarFacturas();
            } catch (Exception ex) {
                Alert a = Alerta.getAlert(Alert.AlertType.ERROR, "Error de conexión", ex.getMessage(), null);
                a.showAndWait();
            }
        });

        date_pick.setOnAction((e) -> {
            try {
                buscarFacturas();
            } catch (Exception ex) {
                Alert a = Alerta.getAlert(Alert.AlertType.ERROR, "Error de conexión", ex.getMessage(), null);
                a.showAndWait();
            }
        });

        cat_box.setOnAction((e) -> {
            String item = cat_box.getSelectionModel().getSelectedItem();
            if (item.equals("Cliente")) {
                cat_box2.setVisible(true);
                cat_box1.setVisible(false);

            } else {
                cat_box2.setVisible(false);
                cat_box1.setVisible(true);
            }
        });
        try {
            cargarDatos();

        } catch (Exception ex) {
            Alert a = Alerta.getAlert(Alert.AlertType.ERROR, "Error de conexión", ex.getMessage(), null);
            a.showAndWait();
        }
    }

    private void loadPago() {
        PagoFacturaController control = App.cargarVentanaModal("Cargar Pago", "fxml/PagoForm", false);
        control.setFactura(tv_factura.getSelectionModel().getSelectedItem().getFactura());
    }

    private void loadForm() {
        App.bodycenter.cargarBody("fxml/FacturaFormV2");
    }

    public void cargarDatos() throws Exception {
        FacturaBackend bk = new FacturaControllerImpl();
        InventarioBackend back = new InventarioControllerImpl();

        List<Proveedor> prov_list = bk.listadoProveedor();
        if (prov_list != null) {
            cat_box1.setItems(FXCollections.observableArrayList(bk.listadoProveedor()));
        } else {
            throw new Exception("Error no se pudo cargar el formulario");
        }
        List<Cliente> cliente = back.listadoCliente();
        cat_box2.setItems(FXCollections.observableArrayList(cliente));
        buscarFacturas();
    }

    private void buscarFacturas() throws Exception {

        List<Factura> listado = null;
        FacturaBackend facturaService = new FacturaControllerImpl();

        if (cat_box.getSelectionModel().getSelectedIndex() == -1) {
            listado = facturaService.listadoFacturasNotNull();
            llenarTable(listado);
            return;
        }

        if (cat_box1.isVisible() && cat_box1.getSelectionModel().getSelectedItem() != null) {
            String status = sts_box.getSelectionModel().getSelectedItem();
            if (status != null) {
                if (status.equals("Activo")) {
                    listado = facturaService.listadoFacturas(cat_box1.getValue(), "A");
                } else {
                    listado = facturaService.listadoFacturas(cat_box1.getValue(), "C");
                }
            } else {
                listado = facturaService.listadoFacturas(cat_box1.getValue());
            }
            llenarTable(listado);
            return;
        }

        if (cat_box2.isVisible() && cat_box2.getSelectionModel().getSelectedItem() != null && date_pick.getValue() == null) {
            String status = sts_box.getSelectionModel().getSelectedItem();
            if (status != null) {
                if (status.equals("Activo")) {
                    listado = facturaService.listadoVentas(cat_box2.getValue(), "A");
                } else {
                    listado = facturaService.listadoVentas(cat_box2.getValue(), "C");
                }
            } else {
                listado = facturaService.listadoVentas(cat_box2.getValue());
            }
            llenarTable(listado);
            return;
        }

        if (date_pick.getValue() != null) {
            if (cat_box.getSelectionModel().getSelectedItem() != null) {
                if (cat_box.getSelectionModel().getSelectedItem().equals("Cliente")) {
                    listado = facturaService.listadoVentas(cat_box2.getValue(), Date.from(date_pick.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                } else {
                    listado = facturaService.listadoFacturas(Date.from(date_pick.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                }

            }
            llenarTable(listado);
            return;
        }

    }

    private void llenarTable(List<Factura> facturas) throws Exception {
        List<TFacturaInicio> inicio = getTFacturas(facturas);
        tv_factura.setItems(FXCollections.observableArrayList(inicio));
        tv_factura.refresh();
    }

    private List<TFacturaInicio> getTFacturas(List<Factura> listado) {
        List<TFacturaInicio> inicio = new ArrayList<>();
        listado.forEach((e) -> {
            TFacturaInicio t = new TFacturaInicio(e);
            inicio.add(t);
        });
        return inicio;
    }

}
