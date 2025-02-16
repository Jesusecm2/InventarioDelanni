/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Inicio;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Controllers.FacturaControllerImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.ImageControllerImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.InventarioControllerImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Categoria;
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.Producto;
import com.delanni.inversiones.frontend.Backend.Interfaces.FacturaBackend;
import com.delanni.inversiones.frontend.Backend.Interfaces.ImagenController;
import com.delanni.inversiones.frontend.Backend.Interfaces.InventarioBackend;
import com.delanni.inversiones.frontend.Backend.util.ImageConverter;
import com.delanni.inversiones.frontend.ViewController.Producto.TableObject.TProducto;
import com.delanni.inversiones.frontend.ViewController.Ingresos.Precarga.NormalImage;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Alerta;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Getfile;
import com.delanni.inversiones.frontend.ViewController.Interfaces.Controladores;
import com.delanni.inversiones.frontend.ViewController.Producto.ProductoFormController;
import com.delanni.inversiones.frontend.ViewController.Size.NormalSize;
import com.delanni.inversiones.frontend.ViewObject.Previews.PreloadFXML;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 *
 * @author Jesusecm
 */
public class ProductoController implements Controladores {

    @FXML
    private Button agregar_btn;

    @FXML
    private Button create_btn;
    
        @FXML
    private Button clear_btn;

    @FXML
    private Parent lastPage;

    @FXML
    private Button volver_btn;

    @FXML
    private GridPane main_grid;

    @FXML
    private Label name_tf;

    @FXML
    private Label precio_unt;
    
    @FXML
    private Button exportar_btn;

    @FXML
    private Label precio_vnt;

    private Parent lastRoot;

    private Parent newRoot;

    private StackPane stack_pane;

    private Pagination pg_pagination;

    private Pagination pg_nation;

    @FXML
    private TextField tc_busqueda;

    private List<ImageView> img_viewls;

    @FXML
    private TableView<TProducto> table_producto;

    @FXML
    private TableColumn<TProducto, String> tc_nombre;

    @FXML
    private TableColumn<TProducto, String> tc_cantidad;

    @FXML
    private TableColumn<TProducto, String> tc_accion;

    @FXML
    private TableColumn<TProducto, String> tc_unt;

    @FXML
    private TableColumn<TProducto, String> tc_vent;

    @FXML
    private TableColumn<TProducto, Button> tc_mod;

    private Parent carrusel;

    private CarruselController controlcarrusel;

    @FXML
    private ComboBox<Categoria> cat_box;

    public StackPane getStack_pane() {
        return stack_pane;
    }

    public void setStack_pane(StackPane stack_pane) {
        this.stack_pane = stack_pane;
    }

    @Override
    public void responsive800() {

    }

    @Override
    public void responsive1600() {

    }

    public Parent getLastPage() {
        return lastPage;
    }

    public void setLastPage(Parent lastPage) {
        this.lastPage = lastPage;
    }
    
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //agregar_btn.setGraphic(NormalImage.add_btn);
        volver_btn.setGraphic(NormalImage.left_btn);
        try {
            this.carrusel = App.loadFXML("fxml/Carrusel");
            this.controlcarrusel = App.loadctual.getController();
            this.pg_pagination = controlcarrusel.getPg_nation();
            carrusel.setVisible(false);
            main_grid.add(carrusel, 1, 0, GridPane.REMAINING, GridPane.REMAINING);
        } catch (IOException ex) {

        }
        clear_btn.setOnAction((e)->{
            cat_box.getSelectionModel().clearSelection();
            exportar_btn.setDisable(true);
            tc_busqueda.setText("");
            
        });
        
        /*table_producto.setOnMouseClicked((e) -> {
            Producto seleccion = table_producto.getSelectionModel().getSelectedItem().getProducto();
            if (seleccion != null) {
                name_tf.setText(seleccion.getNombre());
                precio_unt.setText(String.valueOf(seleccion.getPrecio_unit()) + "$");
                precio_vnt.setText(String.valueOf(seleccion.getPrecio_vent()) + "$");
                System.out.println(seleccion.getCant_actual());
                loadImage();
            } else {
                name_tf.setText("No seleccionado");
                precio_unt.setText(0 + "$");
                precio_vnt.setText(0 + "$");
            }
        });*/
        
        exportar_btn.setOnAction((e)->{
            if (cat_box.getSelectionModel().getSelectedItem() != null) {
                try {
                    Categoria find_f = cat_box.getSelectionModel().getSelectedItem();
                    InventarioBackend bck = new InventarioControllerImpl();
                    InputStream stream = bck.reporteProductoCategoria(find_f);
                    FileChooser chooser = new FileChooser();
                    chooser.setTitle("Reporte Factura");
                    chooser.setInitialFileName("Reporte productos - ".concat(find_f.getNombre()));
                    chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
                    File guardar = chooser.showSaveDialog(cat_box.getParent().getScene().getWindow());
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
                } catch (IOException ex) {
                    Alert alert = Alerta.getAlert(Alert.AlertType.ERROR, "Solicitud no completada", " ", null);
                    alert.show();
                }

            }
        });
        
        
        
        table_producto.setOnMouseClicked((e) -> {
            if (table_producto.getSelectionModel().getSelectedItem() != null) {
                Producto seleccion = table_producto.getSelectionModel().getSelectedItem().getProducto();
                if (seleccion != null) {
                    loadCarrusel();
                } else {

                }
            }

        });

        cat_box.valueProperty().addListener(new ChangeListener<Categoria>() {
            @Override
            public void changed(ObservableValue<? extends Categoria> observable, Categoria oldValue, Categoria newValue) {
                if (newValue != null) {
                    cargarProductos(newValue);
                    exportar_btn.setDisable(false);
                }
            }

        });
        tc_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tc_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tc_unt.setCellValueFactory(new PropertyValueFactory<>("pu"));
        tc_vent.setCellValueFactory(new PropertyValueFactory<>("pv"));
        tc_mod.setCellValueFactory(new PropertyValueFactory<>("btn_modify"));
        //tc_accion.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        tc_busqueda.setOnKeyTyped((e -> {
            
            buscarProductos(tc_busqueda.getText());
        }));

        setRollovers800();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                cargarProductos();
            }
        });
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                cargarCategoria();
            }
        });
        //cargarProductos();

    }

    @Override
    public void setRollovers800() {

        create_btn.setOnMouseEntered((e) -> {
            //create_btn.setGraphic(Getfile.getIcono("normal/add64-rol.png"));
            create_btn.setCursor(Cursor.HAND);
            System.out.println("location");
        });

        create_btn.setOnMouseExited((e) -> {
            //create_btn.setGraphic(Getfile.getIcono("normal/add64.png"));
            create_btn.setCursor(Cursor.DEFAULT);
        });

        create_btn.setOnMousePressed((e) -> {
            //agregar_btn.setGraphic(Getfile.getIcono("normal/add64-click.png"));
            create_btn.setCursor(Cursor.CLOSED_HAND);

        });
        create_btn.setOnMouseReleased((e) -> {
            //agregar_btn.setGraphic(Getfile.getIcono("normal/add64-rol.png"));
            create_btn.setCursor(Cursor.HAND);
            loadForm();
        });
        volver_btn.setOnMouseEntered((e) -> {
            volver_btn.setGraphic(NormalImage.left_btn_rol);
            volver_btn.setCursor(Cursor.HAND);
        });

        volver_btn.setOnMouseExited((e) -> {
            volver_btn.setGraphic(NormalImage.left_btn);
            volver_btn.setCursor(Cursor.DEFAULT);
        });
        volver_btn.setOnMousePressed((e) -> {
            volver_btn.setGraphic(NormalImage.left_btn);
            volver_btn.setCursor(Cursor.CLOSED_HAND);

        });
        volver_btn.setOnMouseReleased((e) -> {
            volver_btn.setGraphic(NormalImage.left_btn_rol);
            volver_btn.setCursor(Cursor.HAND);
            loadHomeForm();
        });
    }

    @Override
    public void setRollovers1600() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void loadHomeForm() {
        App.bodycenter.cargarBody("fxml/CuerpoHome",null);
    }

    private void loadForm() {
         ProductoFormController control = new ProductoFormController();
        App.bodycenter.cargarBody("fxml/ProductoForm",control);
    }

    public Parent getNewRoot() {
        return newRoot;
    }

    public void setNewRoot(Parent newRoot) {
        this.newRoot = newRoot;
    }

    public Parent getLastRoot() {
        return lastRoot;
    }

    public void setLastRoot(Parent lastRoot) {
        this.lastRoot = lastRoot;
    }

    private void cargarCategoria() {
        InventarioControllerImpl cat = new InventarioControllerImpl();
        List<Categoria> categorias = cat.ListadoCategoria();
        if (categorias != null) {
            cat_box.setItems(FXCollections.observableList(categorias));
        }
    }

    private void cargarProductos() {
        InventarioControllerImpl control = new InventarioControllerImpl();
        List<Producto> producto = control.ListadoProducto();
        if (producto != null) {

            table_producto.setItems(FXCollections.observableList(convertir(producto)));
        }
    }

    private void cargarProductos(Categoria cat) {
        InventarioControllerImpl control = new InventarioControllerImpl();
        List<Producto> producto = control.ListadoProducto(cat);
        if (producto != null) {
            //table_producto.getItems().clear();
            table_producto.setItems(FXCollections.observableList(convertir(producto)));
            table_producto.refresh();
        }
    }

    private void buscarProductos(String value) {
        InventarioControllerImpl control = new InventarioControllerImpl();
        List<Producto> producto = null;
        if(cat_box.getSelectionModel().getSelectedIndex()!=-1){
            producto=control.buscarCategoriaNombre(cat_box.getSelectionModel().getSelectedItem(),value);
        }else{
            producto=control.buscarNombre(value);
        }
        if (producto != null) {
            //table_producto.getItems().clear();
            table_producto.setItems(FXCollections.observableList(convertir(producto)));
        }
    }

    private void loadImage() {
        Producto buscar = table_producto.getSelectionModel().getSelectedItem().getProducto();
        if (this.img_viewls != null) {
            this.img_viewls.clear();
        }

        if (buscar != null && buscar.getImagenes() != null) {
            ImagenController imagenes = new ImageControllerImpl();
            int cantidad_imagenes = buscar.getImagenes().size();
            if (cantidad_imagenes > 0) {
                this.img_viewls = new ArrayList<>();
                for (int i = 0; i < cantidad_imagenes; i++) {
                    String imagen = imagenes.imageString(buscar.getImagenes().get(i).getImagen());
                    if (imagen == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("No se pudieron cargar las imagenes");
                        alert.show();
                        this.img_viewls = null;
                        break;
                    }

                    if (imagen != null) {
                        ImageConverter convertidor = new ImageConverter(imagen);
                        ImageView view = new ImageView(convertidor.getImage());

                        view.setFitHeight(200);
                        view.setFitWidth(200);
                        view.getStyleClass().add("img_pagin");
                        img_viewls.add(view);
                    }
                }

            } else {
                this.img_viewls = null;

            }
        }
    }

    private List<TProducto> convertir(List<Producto> param) {
        List<TProducto> e = new ArrayList<>();
        param.forEach((l -> {
            TProducto valor = new TProducto(l);
            valor.getBtn_modify().setOnAction((p) -> {
                ProductoFormController control = new ProductoFormController(l);
                App.cargarVentanaModal("fxml/ProductoForm", control, true, "Modificar Producto");
                cargarProductos();
            });
            e.add(valor);
        }));
        return e;
    }

    private void loadCarrusel() {
        loadImage();
        if (this.img_viewls != null) {
            controlcarrusel.setImg_viewls(img_viewls);
            carrusel.setVisible(true);
        }
    }

}
