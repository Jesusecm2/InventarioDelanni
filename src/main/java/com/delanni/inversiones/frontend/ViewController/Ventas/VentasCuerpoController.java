/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Ventas;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Controllers.FacturaControllerImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.PagoImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.Transacciones;
import com.delanni.inversiones.frontend.Backend.Interfaces.FacturaBackend;
import com.delanni.inversiones.frontend.Backend.Interfaces.PagoBackend;
import com.delanni.inversiones.frontend.ViewController.Factura.Table.TFacturaInicio;
import com.delanni.inversiones.frontend.ViewController.Ventas.TEntity.VentaTrans;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Jesusecm
 */
public class VentasCuerpoController implements Initializable {

    @FXML
    private Button create_btn;

    @FXML
    private TableView<VentaTrans> table_producto;

    @FXML
    private TableColumn<VentaTrans, String> tc_nombre;
    
    @FXML
    private TableColumn<VentaTrans, Long> tc_id;

    @FXML
    private TableColumn<VentaTrans, String> tc_pago;

    @FXML
    private TableColumn<VentaTrans, Double> tc_monto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        tc_id.setCellValueFactory(new PropertyValueFactory<>("idtrn"));
        tc_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tc_pago.setCellValueFactory(new PropertyValueFactory<>("tc_pago"));
        tc_monto.setCellValueFactory(new PropertyValueFactory<>("monto_pag"));
        
        create_btn.setOnMouseClicked((e) -> {
            App.cargarVentanaModal("Modo Ventas", "fxml/VentaForm", true);
        });
        cargarTabla();
    }

    private void cargarTabla() {
       /* FacturaBackend backend = new FacturaControllerImpl();
        List<Factura> ventas = backend.listadoVentas();
        if (ventas != null) {
            List<TFacturaInicio> listado = ventas.stream().map(venta -> new TFacturaInicio(venta)).collect(Collectors.toList());
            table_producto.setItems(FXCollections.observableArrayList(listado));
        }*/
        PagoBackend backend = new PagoImpl();
        List<Transacciones> ventas = backend.obtenerVentasHoy();
        if(ventas!=null){
            List<VentaTrans> listado = ventas.stream().map(trn -> new VentaTrans(trn)).collect(Collectors.toList());
            table_producto.setItems(FXCollections.observableArrayList(listado));
        }
        
        
        
    }

}
