/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Inicio;

import com.delanni.inversiones.frontend.Backend.Controllers.InventarioControllerImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.PagoImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Categoria;
import com.delanni.inversiones.frontend.Backend.Interfaces.InventarioBackend;
import com.delanni.inversiones.frontend.Backend.Interfaces.PagoBackend;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Alerta;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.FileChooser;

/**
 *
 * @author Jesusecm
 */
public class ExportarTransacciones implements Initializable{

    
    @FXML
    private DatePicker start_date;
    
    @FXML
    private DatePicker end_date;
    
    
    @FXML
    private Button save_btn;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        save_btn.setOnAction((e)->{
            exportar();
        });
        
    }
    
    private void exportar(){
        Date dt1 = Date.from(start_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dt2 = Date.from(end_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        PagoBackend bt = new PagoImpl();
          try {
                    InputStream stream = bt.reporteTransacciones(dt1, dt2);
                    if(stream!=null){
                        
                    
                    FileChooser chooser = new FileChooser();
                    chooser.setTitle("Reporte Factura");
                    chooser.setInitialFileName("Reporte Transacciones - ");
                    chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
                    File guardar = chooser.showSaveDialog(start_date.getParent().getScene().getWindow());
                    if (guardar != null) {
                        byte[] buffer = new byte[4096];
                        FileOutputStream output = new FileOutputStream(guardar);
                        int bytesRead;
                        while ((bytesRead = stream.read(buffer)) != -1) {
                            output.write(buffer,0,bytesRead);
                        }
                    }
                    Alert alert = Alerta.getAlert(Alert.AlertType.INFORMATION, "Solicitud Completada", " ", null);
                   alert.show();
                  if(Desktop.isDesktopSupported() && guardar.exists()){
                       Desktop.getDesktop().open(guardar);
                   }
                  }else{
                        System.out.println("No trajo nada");
                    }
                } catch (IOException ex) {
                    Alert alert = Alerta.getAlert(Alert.AlertType.ERROR, "Solicitud no completada", " ", null);
                    alert.show();
                }

            }
    }
    

