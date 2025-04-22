/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Inicio;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Controllers.FacturaControllerImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.InventarioControllerImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Cliente;
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.Proveedor;
import com.delanni.inversiones.frontend.Backend.Interfaces.FacturaBackend;
import com.delanni.inversiones.frontend.Backend.Interfaces.InventarioBackend;
import com.delanni.inversiones.frontend.ViewController.Factura.FacturaFormControllerV2;
import com.delanni.inversiones.frontend.ViewController.Factura.Table.TFacturaInicio;
import com.delanni.inversiones.frontend.ViewController.Factura.Table.TLineaFactura;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Alerta;
import com.delanni.inversiones.frontend.ViewController.Pagos.PagoFacturaController;
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
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

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
    private TextField fnd_factura;

    @FXML
    private DatePicker date_pick;

    @FXML
    private Label total_lb;

    @FXML
    private Label rest_lbl;

    @FXML
    private ComboBox<Proveedor> cat_box1;

    @FXML
    private ComboBox<Cliente> cat_box2;

    @FXML
    private ComboBox<String> cat_box;

    @FXML
    private ComboBox<String> sts_box;

    private boolean limpiar;

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
        limpiar = false;
        String[] sts_item = new String[]{"Activo", "Cancelado"};
        cat_box.setItems(FXCollections.observableArrayList(find_box));
        sts_box.setItems(FXCollections.observableArrayList(sts_item));

        create_btn.setOnMouseClicked((e) -> {
            loadForm();
        });
        tc_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tc_monto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        tc_pagado.setCellValueFactory(new PropertyValueFactory<>("pagado"));

        pagar_btn.setDisable(true);
        
        fnd_factura.setOnAction(event -> {
            String textoBuscado = fnd_factura.getText();
            
            FacturaBackend backend = new FacturaControllerImpl();
            // Aquí puedes implementar la lógica de búsqueda
            try{
                List<Factura> l = new ArrayList<>();
                l.add(backend.FacturaById(Long.parseLong(textoBuscado)));
                llenarTable(l);
            }catch(Exception e){
                Alerta.getAlert(Alert.AlertType.ERROR, "Error de texto", "El texto debe ser númerico", img_src).showAndWait();
            }
            
        });

        
        tv_factura.setOnMouseClicked((e) -> {
            TFacturaInicio tf = tv_factura.getSelectionModel().getSelectedItem();
            if (tf != null) {
                tv_detalle.setItems(FXCollections.observableArrayList(tf.getLineas()));
                total_lb.setText("Total: ".concat(String.format("%.2f", tf.getMonto()).concat("$")));
                rest_lbl.setText("Restante:".concat(String.format("%.2f", tf.getMonto() - tf.getPagado()).concat("$")));
                if (tf.getFactura().getStatus().equals("C")) {
                    pagar_btn.setDisable(true);
                } else {
                    pagar_btn.setDisable(false);
                }
            }
            if (e.getClickCount() > 1 && e.getButton().PRIMARY == MouseButton.PRIMARY) {
                Factura f = tv_factura.getSelectionModel().getSelectedItem().getFactura();
                boolean venta = false;
                if (f.getIdCliente() != null) {
                    venta = true;
                }
                FacturaFormControllerV2 control = new FacturaFormControllerV2(f, venta);
                App.cargarVentanaModal("fxml/FacturaFormV2", control, true, "Modificar Factura");
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
                    ex.printStackTrace();
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
            limpiar = true;
            cat_box.getSelectionModel().clearSelection();
            cat_box1.getSelectionModel().clearSelection();
            cat_box2.getSelectionModel().clearSelection();
            sts_box.getSelectionModel().clearSelection();
            date_pick.setValue(null);
            fnd_factura.setText("");
            limpiar = false;
            try {
                buscarFacturas();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        pagar_btn.setOnMouseClicked((e) -> {
            if (tv_factura.getSelectionModel().getSelectedItem() != null && tv_factura.getSelectionModel().getSelectedItem().getFactura() != null) {
                loadPago();
            } else {
                Alerta.getAlert(Alert.AlertType.ERROR, "No se ha seleccionado una factura válida", "", img_src).showAndWait();
            }

        });

        cat_box1.setOnAction((e) -> {
            try {

                buscarFacturas();

            } catch (Exception ex) {
                Alert a = Alerta.getAlert(Alert.AlertType.ERROR, "Error de conexión", ex.getMessage(), null);
                a.showAndWait();
                ex.printStackTrace();
            }
        });

        cat_box2.setOnAction((e) -> {
            try {
                buscarFacturas();
            } catch (Exception ex) {
                Alert a = Alerta.getAlert(Alert.AlertType.ERROR, "Error de conexión", ex.getMessage(), null);
                a.showAndWait();
                ex.printStackTrace();
            }
        });

        sts_box.setOnAction((e) -> {
            try {
                buscarFacturas();
            } catch (Exception ex) {
                Alert a = Alerta.getAlert(Alert.AlertType.ERROR, "Error de conexión", ex.getMessage(), null);
                a.showAndWait();
                ex.printStackTrace();
            }
        });

        date_pick.setOnAction((e) -> {
            if (date_pick.getValue() != null) {
                cat_box.setDisable(true);
                cat_box1.setDisable(true);
                cat_box2.setDisable(true);
            } else {
                cat_box.setDisable(false);
                cat_box1.setDisable(false);
                cat_box2.setDisable(false);
            }
            try {
                buscarFacturas();
            } catch (Exception ex) {
                Alert a = Alerta.getAlert(Alert.AlertType.ERROR, "Error de conexión", ex.getMessage(), null);
                a.showAndWait();
                ex.printStackTrace();
            }
        });

        cat_box.setOnAction((e) -> {
            if (!limpiar) {
                String item = cat_box.getSelectionModel().getSelectedItem();
                if (item.equals("Cliente")) {
                    cat_box2.setVisible(true);
                    cat_box1.setVisible(false);

                } else {
                    cat_box2.setVisible(false);
                    cat_box1.setVisible(true);
                }
            }

            try {

                buscarFacturas();

            } catch (Exception ex) {
                Alert a = Alerta.getAlert(Alert.AlertType.ERROR, "Error de conexión", ex.getMessage(), null);
                a.showAndWait();
                ex.printStackTrace();
            }
        });
        try {
            cargarDatos();
            buscarFacturas();
        } catch (Exception ex) {
            Alert a = Alerta.getAlert(Alert.AlertType.ERROR, "Error de conexión", ex.getMessage(), null);
            a.showAndWait();
        }

    }

    private void loadPago() {
        PagoFacturaController control = new PagoFacturaController(tv_factura.getSelectionModel().getSelectedItem().getFactura());
        App.cargarVentanaModal("fxml/PagoForm", control, true, "Pagar Factura");
        try {
            cargarDatos();
        } catch (Exception ex) {
            Alert a = Alerta.getAlert(Alert.AlertType.ERROR, "Error de conexión", ex.getMessage(), null);
            a.showAndWait();
        }
    }

    private void loadForm() {
        FacturaFormControllerV2 control = new FacturaFormControllerV2(null, false);
        App.bodycenter.cargarBody("fxml/FacturaFormV2", control);
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
        if (!limpiar) {

            List<Factura> listado = null;
            FacturaBackend facturaService = new FacturaControllerImpl();
            String ch = null;
            if (sts_box.getValue() != null) {
                ch = (sts_box.getValue().equals("Activo") ? "A" : "C");
            }
            if (date_pick.getValue() != null) {
                Date dt1 = Date.from(date_pick.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

                if (cat_box.getSelectionModel().getSelectedItem() != null) {
                    if (cat_box.getSelectionModel().getSelectedItem().equals("Cliente")) {
                        listado = facturaService.listadoVentas(dt1);
                    } else {
                        listado = facturaService.listadoFacturas(dt1);
                    }
                } else {
                    listado = facturaService.listadoFacturas(dt1);
                }
                llenarTable(listado);
                return;
            }

            if (cat_box.getSelectionModel().getSelectedItem() != null) {
                if (cat_box1.getSelectionModel().getSelectedItem() != null && cat_box1.isVisible()) {
                    if (cat_box1.getValue() != null) {
                        if (sts_box.getValue() != null) {
                            listado = facturaService.listadoFacturas(cat_box1.getValue(), ch);
                        } else {
                            listado = facturaService.listadoFacturas(cat_box1.getValue());
                        }
                    } else {
                        if (sts_box.getValue() != null) {
                            listado = facturaService.listadoFacturas(ch);
                        } else {
                            listado = facturaService.listadoFacturas();
                        }
                    }
                }

                if (cat_box2.getSelectionModel().getSelectedItem() != null && cat_box2.isVisible()) {
                    if (cat_box2.getValue() != null) {
                        if (sts_box.getValue() == null) {
                            listado = facturaService.listadoVentas(cat_box2.getValue());
                        } else {

                            listado = facturaService.listadoVentas(cat_box2.getValue(), ch);
                        }
                    } else {
                        if (sts_box.getValue() != null) {

                            listado = facturaService.listadoVentas(ch);
                        } else {
                            listado = facturaService.listadoVentas();
                        }
                    }
                }

            }
            if (cat_box.getSelectionModel().getSelectedItem() == null && date_pick.getValue() == null && sts_box.getValue() == null && listado == null) {
                listado = facturaService.listadoFacturas();
            }
            if (cat_box.getSelectionModel().getSelectedItem() != null && listado == null) {
                
                if (cat_box1.isVisible()) {
                    if(ch!=null){
                        listado = facturaService.listadoFacturas(ch);
                    }else{
                        listado = facturaService.listadoFacturasNotNull();
                    }
                    
                } else {
                    if(ch!=null){
                        listado = facturaService.listadoVentas(ch);
                    }else{
                        listado = facturaService.listadoVentas();
                    }
                    
                }
            }
            llenarTable(listado);
            return;
        }

    }

    private void llenarTable(List<Factura> facturas) throws Exception {

        List<TFacturaInicio> inicio = getTFacturas(facturas);
        if (facturas != null && !facturas.isEmpty()) {
            tv_factura.getItems().setAll(inicio);

        } else {
            tv_factura.getItems().clear();
        }
        tv_detalle.getItems().clear();
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
