/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module InventarioDelanni {
    requires javafx.controlsEmpty;
    requires javafx.controls;
    requires javafx.graphicsEmpty;
    requires javafx.graphics;
    requires javafx.baseEmpty;
    requires javafx.base;
    requires javafx.fxmlEmpty;
    requires javafx.fxml;
    requires java.desktop;
    requires java.net.http;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires org.apache.commons.io;
    
    
    exports com.delanni.inversiones.frontend;
    exports com.delanni.inversiones.frontend.Backend.Entity;
    exports com.delanni.inversiones.frontend.Backend.util;
    opens com.delanni.inversiones.frontend.Backend.util;
    opens com.delanni.inversiones.frontend.ViewController.Login;
    opens com.delanni.inversiones.frontend.ViewController.Producto.TableObject;

    //exports org.controlsfx.controls;
    exports com.delanni.inversiones.frontend.ViewController.Login;
    exports com.delanni.inversiones.frontend.ViewController.Inicio;
    exports com.delanni.inversiones.frontend.ViewController.Factura;
    exports com.delanni.inversiones.frontend.ViewController.Factura.Table;
    exports com.delanni.inversiones.frontend.ViewController.Pagos;
    exports com.delanni.inversiones.frontend.ViewController.Ventas;
    exports com.delanni.inversiones.frontend.ViewController.Ventas.TEntity;
    exports com.delanni.inversiones.frontend.Backend.Entity.Pagos;
    exports com.delanni.inversiones.frontend.ViewController.Inicio.TCuerpoEntity;
    
    opens com.delanni.inversiones.frontend.ViewController.Inicio.TCuerpoEntity;
    opens com.delanni.inversiones.frontend.Backend.Entity.Pagos;
    opens com.delanni.inversiones.frontend.ViewController.Inicio;
        exports com.delanni.inversiones.frontend.ViewController.Producto;
        exports com.delanni.inversiones.frontend.ViewController.Producto.TableObject;
    opens com.delanni.inversiones.frontend.ViewController.Producto;
    opens com.delanni.inversiones.frontend.ViewController.Factura;
    opens com.delanni.inversiones.frontend.ViewController.Factura.Table;
    opens com.delanni.inversiones.frontend.ViewController.Pagos;
    opens com.delanni.inversiones.frontend.ViewController.Ventas;
    opens com.delanni.inversiones.frontend.ViewController.Ventas.TEntity;
    
            exports com.delanni.inversiones.frontend.ViewController.Ingresos;
    opens com.delanni.inversiones.frontend.ViewController.Ingresos;
    
}
