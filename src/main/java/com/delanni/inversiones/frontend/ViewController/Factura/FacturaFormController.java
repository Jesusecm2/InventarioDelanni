/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Factura;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Controllers.FacturaControllerImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.InventarioControllerImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.LineaFactura;
import com.delanni.inversiones.frontend.Backend.Entity.Producto;
import com.delanni.inversiones.frontend.Backend.Entity.Proveedor;
import com.delanni.inversiones.frontend.Backend.Interfaces.FacturaBackend;
import com.delanni.inversiones.frontend.ViewController.Factura.Table.TProducto;
import com.delanni.inversiones.frontend.ViewController.Ingresos.Precarga.NormalImage;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Getfile;
import com.delanni.inversiones.frontend.ViewController.Interfaces.Controladores;
import com.delanni.inversiones.frontend.ViewController.Producto.CategoriaFormController;
import com.delanni.inversiones.frontend.ViewController.Producto.ProductoBusquedaController;
import com.delanni.inversiones.frontend.ViewController.Producto.ProveedorFormController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Jesusecm
 */
public class FacturaFormController implements Controladores {

    private Parent lastRoot;

    private Parent newRoot;

    private StackPane stack_pane;

    private Producto producto_act;

    @FXML
    private Label categoria_lb;
    @FXML
    private Button adicionar_btn;

    @FXML
    private Button cancelar_btn;

    @FXML
    private Button quitar_btn;

    @FXML
    private Label total_lbl;

    @FXML
    private Button guardar_factura;

    @FXML
    private Button btn_add;

    @FXML
    private Button buscar_btn;

    @FXML
    private Button btn_rest;

    @FXML
    private GridPane error_cnt;

    @FXML
    private GridPane sucess_cnt;

    @FXML
    private Label error_msg;

    @FXML
    private Label success_msg;

    @FXML
    private Label advice_msg;

    @FXML
    private TextArea text_area;

    @FXML
    private TextField cod_tf;
    @FXML
    private TextField nombre_tf;

    @FXML
    private TableView<TProducto> table_view;

    @FXML
    private TableColumn<TProducto, Double> tb_cantidad;

    @FXML
    private TableColumn<TProducto, Double> tb_unit;

    @FXML
    private TableColumn<TProducto, Double> tb_total;

    @FXML
    private TableColumn<TProducto, String> tb_nombre;

    @FXML
    private Spinner<Double> precio_unit;

    @FXML
    private Spinner<Double> cant_unit;

    private Double cantidad;

    private Producto producto;
    @FXML
    private TextField cantidad_tf;

    private List<TProducto> listadoProductos;

    private Double total;

    private Proveedor prov;

    private TProducto modificacion;

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
    public void responsive800() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void responsive1600() {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setRollovers800() {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public void setRollovers1600() {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        //setRollovers800();
        categoria_lb.setOnMouseClicked((e) -> {
            cargarFormulario();
        });
        guardar_factura.setOnMouseClicked((e) -> {
            guardarFactura();
        });
        buscar_btn.setOnMouseClicked((e) -> {
            BuscarProducto();
        });

        cantidad = 0.0;
        total = 0.0;
        total_lbl.setText("0.0$");
        btn_add.setGraphic(Getfile.getIcono("normal/add64.png"));
        btn_rest.setGraphic(Getfile.getIcono("normal/rest64.png"));
        tb_cantidad.setCellValueFactory(new PropertyValueFactory<>("Scantidad"));
        tb_total.setCellValueFactory(new PropertyValueFactory<>("Stotal"));
        tb_unit.setCellValueFactory(new PropertyValueFactory<>("Sprecio_unit"));
        tb_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cod_tf.setOnKeyReleased((e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                buscarCodigo();
            }
            //System.out.println(e.getCode());
        });
        adicionar_btn.setOnMouseClicked((e) -> {
            cargarproducto();
        });

        btn_add.setOnMouseClicked((e) -> {

            //cantidad_tf.setText(String.valueOf(++cantidad));
        });
        btn_rest.setOnMouseClicked((e) -> {

            // cantidad_tf.setText(String.valueOf(--cantidad));
        });
        cantidad_tf.setOnKeyReleased((e) -> {
            //System.out.println(e.getCode());
            if (e.getCode() == KeyCode.ENTER) {

                if (!cantidad_tf.getText().isBlank()) {
                    try {
                        cantidad = Double.parseDouble(cantidad_tf.getText());
                    } catch (Exception ex) {
                        cantidad_tf.setText(String.valueOf(cantidad));
                    }

                }
            }
        });

        table_view.setOnMouseClicked((e) -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                if (e.getClickCount() == 2) {
                    if (table_view.getSelectionModel().getSelectedItem() != null) {
                        modificacion = table_view.getSelectionModel().getSelectedItem();
                        cancelar_btn.setVisible(true);
                        cod_tf.setText(modificacion.getProducto().getCodigo());
                        nombre_tf.setText(modificacion.getProducto().getNombre());
                        //text_area.setText(modificacion.getProducto().getDescripcion());
                        cant_unit.getValueFactory().setValue(modificacion.getCantidad());
                        precio_unit.getValueFactory().setValue(modificacion.getPrecio_unit());
                        //this.window.close();
                    }
                }
            }
        });

        cancelar_btn.setOnMouseClicked((e) -> {
            limpiar();
            cancelar_btn.setVisible(false);
        });

    }

    private void buscarCodigo() {

        if (!cod_tf.getText().isEmpty() && !cod_tf.getText().isBlank()) {

            InventarioControllerImpl control = new InventarioControllerImpl();
            List<Producto> busqueda = control.obtenerProducto(cod_tf.getText());
            if (busqueda != null && !busqueda.isEmpty()) {
                advice_msg.setVisible(false);
                producto_act = busqueda.get(0);
                nombre_tf.setText(producto_act.getNombre());
                nombre_tf.setDisable(true);
                //text_area.setDisable(true);
                precio_unit.getValueFactory().setValue(producto_act.getPrecio_unit());
                // text_area.setText(producto_act.getDescripcion());
            } else {
                advice_msg.setVisible(true);
                advice_msg.setText("Producto no registrado");
            }
        }
    }

    private void cargarproducto() {
        boolean cargado = false;
        boolean existe = false;
        int ref = 0;
        if (producto_act == null) {
            if (cod_tf.getText().isBlank() || nombre_tf.getText().isBlank()) {
                return;
            } else {
                producto_act = new Producto();
                producto_act.setCodigo(cod_tf.getText());
                producto_act.setNombre(nombre_tf.getText());
                // producto_act.setDescripcion(producto_act.getDescripcion());
                producto_act.setPrecio_unit(precio_unit.getValue());

            }
        }

        if (listadoProductos == null) {
            listadoProductos = new ArrayList<>();
            table_view.setItems(FXCollections.observableList(listadoProductos));
        }
        for (TProducto pr : table_view.getItems()) {
            if (pr.getProducto().getCodigo().equals(producto_act.getCodigo())
                    || (pr.getProducto().getId() != null && pr.getProducto().getId().equals(producto_act.getId()))) {
                existe = true;
                break;
            }
            ref++;
        }
        if (existe) {
            TProducto edit = table_view.getItems().get(ref);
            if (cancelar_btn.isVisible()) {
                edit.setCantidad(cant_unit.getValue());
            } else {
                edit.setCantidad(edit.getCantidad() + cant_unit.getValue());
            }
            edit.setPrecio_unit(precio_unit.getValue());
            edit.setTotal(edit.getPrecio_unit() * edit.getCantidad());
            // total += edit.getTotal();
            //  total_lbl.setText(String.valueOf(total) + "$");
            table_view.refresh();
            cargado = true;
            cant_unit.getValueFactory().setValue(0.0);
            if (cancelar_btn.isVisible()) {
                cancelar_btn.setVisible(false);
            }

        } else {
            TProducto nuevo = new TProducto();
            nuevo.setProducto(producto_act);
            nuevo.setNombre(producto_act.getNombre());
            nuevo.setPrecio_unit(precio_unit.getValue());
            nuevo.setCantidad(cant_unit.getValue());
            nuevo.setTotal(nuevo.getCantidad() * nuevo.getPrecio_unit());
            // total += nuevo.getTotal();
            // total_lbl.setText(String.format("%.2f$", total));
            cant_unit.getValueFactory().setValue(0.0);
            table_view.getItems().add(nuevo);
            table_view.refresh();
            cargado = true;
            if (cancelar_btn.isVisible()) {
                cancelar_btn.setVisible(false);
            }
        }
        total = 0.0;
        for (TProducto pr : table_view.getItems()) {
            if (pr != null) {
                total += pr.getTotal();
            }
        }
        total_lbl.setText(String.format("%.2f$", total));
        if (cargado) {
            limpiar();
        }

    }

    private void limpiar() {
        producto_act = null;
        this.cantidad = 0.0;
        nombre_tf.setDisable(false);
        //text_area.setDisable(false);
        nombre_tf.setText("");
        //text_area.setText("");
        precio_unit.getValueFactory().setValue(0.0);
        cod_tf.setText("");
    }

    private void BuscarProducto() {
        ProductoBusquedaController control = App.cargarVentanaModal("Buscar Producto", "fxml/ProductoBusqueda", true);
        if(control.getProducto()!=null){
            producto_act = control.getProducto();
            cargarproducto();
        }
    }

    private void cargarFormulario() {
        ProveedorFormController control = App.cargarVentanaModal("Agregar Producto", "fxml/ProveedorForm", true);

    }

    private void guardarFactura() {
        Factura factura = new Factura();
        factura.setIdProveedor(prov);
        factura.setDescripcion(text_area.getText());
        List<LineaFactura> lineas = new ArrayList<>();
        if (table_view.getItems() == null || table_view.getItems().isEmpty()) {
            error_msg.setText("El contenido se encuentra vacío");
            ErrorMsg();
            return;
        }
        table_view.getItems().forEach((e) -> {
            LineaFactura linea = new LineaFactura(null, e.getCantidad(), e.getPrecio_unit(), e.getProducto(), null);
            lineas.add(linea);
        });
        factura.setLineas(lineas);
        factura.setSaldo(total);
        FacturaBackend back = new FacturaControllerImpl();
    //        back.guardarFactura(factura);

    }

    private void ErrorMsg() {
        error_cnt.setVisible(true);
        error_cnt.setOpacity(-1);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), error_cnt);
        fadeTransition.setToValue(100);
        fadeTransition.setByValue(-1);
        fadeTransition.setOnFinished((e) -> {
            try {
                Thread.sleep(1500);
                FadeTransition fadeTransition3 = new FadeTransition(Duration.millis(500), error_cnt);
                fadeTransition3.setByValue(-1);
                fadeTransition3.setOnFinished((l) -> {
                    error_cnt.setVisible(false);

                });
                fadeTransition3.play();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        fadeTransition.play();
    }
    
    private void cargarActual(Producto pr){
        this.producto_act = pr;
        this.nombre_tf.setText(pr.getNombre());
        //this.get
    }
    
    
}
