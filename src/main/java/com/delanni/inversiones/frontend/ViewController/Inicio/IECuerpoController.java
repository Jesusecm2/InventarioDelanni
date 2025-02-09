/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Inicio;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Controllers.PagoImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Cliente;
import com.delanni.inversiones.frontend.Backend.Entity.Proveedor;
import com.delanni.inversiones.frontend.Backend.Entity.TpIngreso;
import com.delanni.inversiones.frontend.Backend.Entity.Transacciones;
import com.delanni.inversiones.frontend.Backend.Interfaces.PagoBackend;
import com.delanni.inversiones.frontend.ViewController.Ingresos.EgresoFormController;
import com.delanni.inversiones.frontend.ViewController.Ingresos.IngresoFormController;
import com.delanni.inversiones.frontend.ViewController.Inicio.TCuerpoEntity.TIngreso;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Jesusecm
 */
public class IECuerpoController implements Initializable {

    @FXML
    private Button clear_btn;

    @FXML
    private TableView<TIngreso> tb_egresos;

    private TableColumn<TIngreso, TpIngreso> tb_descripcion;

    private TableColumn<TIngreso, String> tb_ref;

    private TableColumn<TIngreso, String> tb_monto;

    @FXML
    private TableView<TIngreso> tb_ingreso;

    private TableColumn<TIngreso, TpIngreso> tb_descripcion1;

    private TableColumn<TIngreso, String> tb_ref1;

    private TableColumn<TIngreso, String> tb_monto1;

    @FXML
    private ComboBox<Proveedor> cat_box1;

    @FXML
    private GridPane main_grid;

    @FXML
    private ImageView imagen;
    
    @FXML
    private DatePicker date_pick;
    
    @FXML
    private DatePicker date_pick1;

    @FXML
    private ComboBox<Cliente> cat_box2;

    @FXML
    private ComboBox<String> cat_box;

    @FXML
    private ComboBox<String> sts_box;

    private Parent carrusel;

    private CarruselController controlcarrusel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        tb_descripcion = new TableColumn<>("Descripcion");
        tb_descripcion.setCellValueFactory(new PropertyValueFactory<>("ingreso"));

        tb_ref = new TableColumn<>("Referencia");
        tb_ref.setCellValueFactory(new PropertyValueFactory<>("transref"));

        tb_monto = new TableColumn<>("Monto");
        tb_monto.setCellValueFactory(new PropertyValueFactory<>("fmonto"));

        tb_descripcion1 = new TableColumn<>("Descripcion");
        tb_descripcion1.setCellValueFactory(new PropertyValueFactory<>("ingreso"));

        tb_ref1 = new TableColumn<>("Referencia");
        tb_ref1.setCellValueFactory(new PropertyValueFactory<>("transref"));

        tb_monto1 = new TableColumn<>("Monto");
        tb_monto1.setCellValueFactory(new PropertyValueFactory<>("fmonto"));

        tb_egresos.getColumns().clear();
        tb_egresos.getColumns().add(tb_descripcion);
        tb_egresos.getColumns().add(tb_ref);
        tb_egresos.getColumns().add(tb_monto);

        tb_ingreso.getColumns().clear();
        tb_ingreso.getColumns().add(tb_descripcion1);
        tb_ingreso.getColumns().add(tb_ref1);
        tb_ingreso.getColumns().add(tb_monto1);
        date_pick.setOnAction((e)->{
            cargarDatos();
        });
         date_pick1.setOnAction((e)->{
            cargarDatos();
        });
        try {
            this.carrusel = App.loadFXML("fxml/Carrusel");
            this.controlcarrusel = App.loadctual.getController();
            //this.pg_pagination = controlcarrusel.getPg_nation();
            carrusel.setVisible(false);
            main_grid.add(carrusel, 1, 0, GridPane.REMAINING, GridPane.REMAINING);
        } catch (IOException ex) {

        }
        tb_ingreso.setOnMouseClicked((e)->{
            TIngreso selected = tb_ingreso.getSelectionModel().getSelectedItem();
            if(selected.getTrans().getPago()!=null && selected.getTrans().getPago()!=null){
                IngresoFormController control = App.cargarVentanaModal("Ver Ingreso", "fxml/IngresoForm", false);
                control.setPago(selected.getTrans());
            }
        });
        
        tb_egresos.setOnMouseClicked((e)->{
            TIngreso selected = tb_egresos.getSelectionModel().getSelectedItem();
            if(selected.getTrans().getPago()!=null && selected.getTrans().getPago()!=null){
                EgresoFormController control = App.cargarVentanaModal("Ver Ingreso", "fxml/EgresoForm", false);
                control.setPago(selected.getTrans());
            }
        });

    }
    
    private void cargarDatos(){
        LocalDate ld1 = date_pick.getValue();
        LocalDate ld2 = date_pick1.getValue();
        if(ld1!=null && ld2!=null){
            Date dt1 = Date.from(ld1.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date dt2 = Date.from(ld2.atStartOfDay(ZoneId.systemDefault()).toInstant());
            PagoBackend pagos = new PagoImpl();
            List<Transacciones> listado = pagos.obtenerIngresosEgresos(dt1, dt2);
            List<TIngreso> is = new ArrayList<>();
            List<TIngreso> es = new ArrayList<>();
            if(!listado.isEmpty()){
                listado.forEach((e)->{
                    TIngreso nuevo = new TIngreso(e);
                    if(e.getTpIngreso().getTipo().equals("E")){
                        es.add(nuevo);
                    }else{
                        is.add(nuevo);
                    }
                });
                tb_ingreso.setItems(FXCollections.observableArrayList(is));
                tb_egresos.setItems(FXCollections.observableArrayList(es));
            }
            
        }
    }

    private void loadCarrusel() {
        if (this.imagen != null) {
            List<ImageView> listado = new ArrayList<>();
            listado.add(imagen);
            controlcarrusel.setImg_viewls(listado);
            carrusel.setVisible(true);
        }
    }

}
