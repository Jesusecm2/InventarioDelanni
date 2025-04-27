/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Factura;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Controllers.FacturaControllerImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.InventarioControllerImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Cliente;
import com.delanni.inversiones.frontend.Backend.Entity.Cuenta;
import com.delanni.inversiones.frontend.Backend.Entity.Proveedor;
import com.delanni.inversiones.frontend.Backend.Interfaces.FacturaBackend;
import com.delanni.inversiones.frontend.Backend.Interfaces.InventarioBackend;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Alerta;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Getfile;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Jesusecm
 */
public class ClienteFormController implements Initializable{

    private Cliente cliente;

    @FXML
    private TextField rif_tf;

    @FXML
    private TextField name_tf;

    @FXML
    private TextField email_tf;

    @FXML
    private TextField cuent_tf;
    @FXML
    private TextField tel_tf;

    @FXML
    private Button volver_btn;

    @FXML
    private Button save_btn;

    @FXML
    private Button save_btn1;

    private Cuenta cuenta;

    @FXML
    private ListView<Cliente> list_cat;

    private boolean loaded;
    
    private String ci_inicial;

    public boolean isLoaded() {
        return loaded;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteFormController() {
    }

    public ClienteFormController(String ci_inicial) {
        this.ci_inicial = ci_inicial;
    }
    
    
    
    
    
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        volver_btn.setGraphic(Getfile.getIcono("normal/left20.png"));
        volver_btn.setOnMouseClicked((e) -> {
            cerrar();
        });
        save_btn.setOnMouseClicked((e) -> {
            guardarCliente();
        });

        save_btn1.setOnMouseClicked((e) -> {
            cargarFormulario();
        });

        if(ci_inicial!=null){
            rif_tf.setText(ci_inicial);
        }
        list_cat.setOnMouseClicked((e) -> {
            Cliente seleccion = list_cat.getSelectionModel().getSelectedItem();
            if (seleccion != null) {
                cliente = seleccion;
               cerrar();
            }
        });

        boolean recargar = false;
        do {
            List<Cliente> listado = null;
            InventarioBackend back = new InventarioControllerImpl();
            try {
                listado = back.listadoCliente();
            } catch (Exception e) {
                listado = null;
            }

            if (listado != null) {
                list_cat.setItems(FXCollections.observableList(listado));
                recargar = true;
                loaded = false;
            } else {
                Alert alert = Alerta.getAlert(Alert.AlertType.CONFIRMATION, "¿Desea reintentar?", "No se pudo cargar su formulario", null);
                Optional<ButtonType> act = alert.showAndWait();
                if (act.get() == ButtonType.OK) {
                    recargar = false;
                } else {
                    recargar = true;
                    loaded = false;
                }
            }

        } while (!recargar);

    }

    

    private void cargarFormulario() {
        CuentaFormController categoria = App.cargarVentanaModal("Agregar Productor", "fxml/CuentaForm", true);
    }
    
    private void cerrar(){
        Stage stg = (Stage)this.cuent_tf.getParent().getScene().getWindow();
        stg.close();
    }
    
    private void guardarCliente(){
        cliente = new Cliente();
        cliente.setRif(rif_tf.getText());
        cliente.setNombre(name_tf.getText());
        cliente.setEmail(email_tf.getText());
        InventarioBackend bck = new InventarioControllerImpl();
        Cliente save = bck.guardarCliente(cliente);
        if(save!=null){
            System.out.println("Saved");
        }
    }
    
}
