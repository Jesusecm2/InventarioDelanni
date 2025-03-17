/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module InventarioDelanni {

    requires javafx.controls;
    requires javafx.fxml;
    //requires javafx.graphics;
    requires javafx.swing;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    requires org.apache.commons.io;
    requires org.controlsfx.controls;
    
    opens com.delanni.inversiones.frontend to javafx.fxml,javafx.controls,javafx.graphics,javafx.swing;
    
    exports com.delanni.inversiones.frontend.ViewController.Factura;
    exports com.delanni.inversiones.frontend.ViewController.Inicio;
    exports com.delanni.inversiones.frontend.ViewController.Ingresos;
    exports com.delanni.inversiones.frontend.ViewController.Interfaces;
    exports com.delanni.inversiones.frontend.ViewController.Pagos;
    exports com.delanni.inversiones.frontend.ViewController.Producto;
    exports com.delanni.inversiones.frontend.ViewController.Size;
    exports com.delanni.inversiones.frontend.ViewController.Ventas;
    exports com.delanni.inversiones.frontend.Backend.Entity;
    exports com.delanni.inversiones.frontend;
    exports com.delanni.inversiones.frontend.ViewController.Login;
    
    
    
    opens com.delanni.inversiones.frontend.ViewController.Factura to javafx.fxml,javafx.controls,javafx.graphics,javafx.swing;
    opens com.delanni.inversiones.frontend.ViewController.Inicio to javafx.fxml,javafx.controls,javafx.graphics,javafx.swing;
    opens com.delanni.inversiones.frontend.ViewController.Ingresos to javafx.fxml,javafx.controls,javafx.graphics,javafx.swing ;
    opens com.delanni.inversiones.frontend.ViewController.Interfaces to javafx.fxml,javafx.controls,javafx.graphics,javafx.swing;
    opens com.delanni.inversiones.frontend.ViewController.Pagos to javafx.fxml,javafx.controls,javafx.graphics,javafx.swing;
    opens com.delanni.inversiones.frontend.ViewController.Producto to javafx.fxml,javafx.controls,javafx.graphics,javafx.swing;
    opens com.delanni.inversiones.frontend.ViewController.Size to javafx.fxml,javafx.controls,javafx.graphics,javafx.swing;
    opens com.delanni.inversiones.frontend.ViewController.Ventas to javafx.fxml,javafx.controls,javafx.graphics,javafx.swing;
    
    opens com.delanni.inversiones.frontend.ViewController.Login to javafx.fxml,javafx.controls,javafx.graphics,javafx.swing;
}
