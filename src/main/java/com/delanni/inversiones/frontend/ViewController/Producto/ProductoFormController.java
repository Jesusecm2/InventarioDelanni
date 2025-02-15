/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Producto;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.Backend.Conection.Conexion;
import com.delanni.inversiones.frontend.Backend.Controllers.ImageControllerImpl;
import com.delanni.inversiones.frontend.Backend.Controllers.InventarioControllerImpl;
import com.delanni.inversiones.frontend.Backend.Entity.Categoria;
import com.delanni.inversiones.frontend.Backend.Entity.ImagenProducto;
import com.delanni.inversiones.frontend.Backend.Entity.Producto;
import com.delanni.inversiones.frontend.Backend.Interfaces.ImagenController;
import com.delanni.inversiones.frontend.Backend.Interfaces.InventarioBackend;
import com.delanni.inversiones.frontend.Backend.util.ImageConverter;
import com.delanni.inversiones.frontend.Backend.util.SelecionArchivos;
import com.delanni.inversiones.frontend.ViewController.Ingresos.Precarga.NormalImage;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Alerta;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Getfile;
import com.delanni.inversiones.frontend.ViewController.Inicio.ProductoController;
import com.delanni.inversiones.frontend.ViewController.Interfaces.Controladores;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 *
 * @author Jesusecm
 */
public class ProductoFormController implements Controladores {

    @FXML
    private ImageView img_src;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_rest;

    @FXML
    private Label categoria_lb;

    @FXML
    private TextField cantidad_tf;
    @FXML
    private Button volver_btn;

    @FXML
    private Button guardar_producto;

    @FXML
    private Pagination pg_pagination;

    @FXML
    private TextField cod_tf;

    @FXML
    private TextField nombre_tf;

    @FXML
    private TextArea text_area;

    @FXML
    private TextArea nombre_area;

    @FXML
    private Label success_msg;

    @FXML
    private Label error_msg;

    @FXML
    private CheckBox chk_tf;

    @FXML
    private GridPane sucess_cnt;

    @FXML
    private GridPane error_cnt;

    private StackPane stack_pane;

    private Parent lastRoot;

    private Parent newRoot;

    private Double cantidad;

    private Categoria cat;
    @FXML
    private Spinner<Double> precio_unit;
    @FXML
    private Spinner<Double> precio_vent;
    @FXML
    private Spinner<Double> stock_min;

    @FXML
    private ListView<String> error_list;

    private List<ImagenProducto> display;

    private ImageConverter converter;

    private Stage window;

    private Producto producto;

    @FXML
    private Button dlt_img;

    private Integer pagina_act;

    private boolean closeform;

    @FXML
    private Spinner<Double> j_spim;

    public ProductoFormController(Producto producto) {
        this.producto = producto;
    }

    public ProductoFormController() {
    }
    

    public Producto getProducto() {
        return producto;
    }

    public void setCloseform(boolean closeform) {
        this.closeform = closeform;
    }

    private void modificarProducto() {
        nombre_area.setText(producto.getNombre());
        if (producto.getCodigo() != null && !producto.getCodigo().isBlank()) {
            cod_tf.setText(producto.getCodigo());
            cod_tf.setDisable(true);
        }
        precio_unit.getValueFactory().setValue(producto.getPrecio_unit());
        precio_vent.getValueFactory().setValue(producto.getPrecio_vent());
        text_area.setText(producto.getDescripcion());
        stock_min.getValueFactory().setValue(producto.getStock_min());
        cat = producto.getCat();
        j_spim.getValueFactory().setValue(producto.getCant_actual());
        categoria_lb.setText(cat.getNombre());
        display = producto.getImagenes();
        if (display != null && !display.isEmpty()) {
            display.forEach((e) -> {
                System.out.println(e);
                ImagenController controller = new ImageControllerImpl();
                String imagen = controller.imageString(e.getImagen());
                ImageConverter cvn = new ImageConverter(imagen);
                e.setFrontView(getSize(cvn));
            });
            if (display != null) {
                pg_pagination.setPageCount(display.size());
            } else {
                pg_pagination.setPageCount(0);
            }

        } else {
            pg_pagination.setDisable(true);
            dlt_img.setDisable(true);

        }
    }

    public StackPane getMain() {
        return stack_pane;
    }

    public void setMain(StackPane main) {
        this.stack_pane = main;
    }

    @Override
    public void responsive800() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void responsive1600() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setRollovers800() {

    }

    @Override
    public void setRollovers1600() {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        j_spim.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 9999, 0));
        volver_btn.setVisible(false);
        cod_tf.setDisable(true);
        chk_tf.setSelected(false);
        pg_pagination.setPageCount(3);
        cantidad = 0.0;
        volver_btn.setOnMouseClicked((e) -> {
            if (window == null) {
                loadHomeForm();
            } else {
                cerrar();
            }
        });
        pg_pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                if (display == null || display.isEmpty()) {
                    return new Label("Contenedor vacío");
                } else {
                    pagina_act = pageIndex;
                    return display.get(pageIndex).getFrontView();

                }
            }
        });

        chk_tf.setOnMouseClicked((e) -> {
            if (chk_tf.isSelected() == true) {
                cod_tf.setDisable(false);
            } else {
                cod_tf.setDisable(true);
            }
        });

        btn_add.setOnMouseClicked((e) -> {

            cantidad_tf.setText(String.valueOf(++cantidad));
            // setCantidadCero();
        });
        btn_rest.setOnMouseClicked((e) -> {

            cantidad_tf.setText(String.valueOf(--cantidad));
            // setCantidadCero();
        });
        cantidad_tf.setOnKeyReleased((e) -> {
            System.out.println(e.getCode());
            if (e.getCode() == KeyCode.ENTER) {

                if (!cantidad_tf.getText().isBlank()) {
                    try {
                        cantidad = Double.parseDouble(cantidad_tf.getText());
                        // setCantidadCero();
                    } catch (Exception ex) {
                        cantidad_tf.setText(String.valueOf(cantidad));
                    }

                }
            }
        });
        setRollovers800();
        img_src.setOnMouseClicked((e) -> {
            AgregarImagen();
        });
        categoria_lb.setOnMouseClicked((e) -> {
            cargarFormulario();
        });
        img_src.setImage(NormalImage.addimg_btn.getImage());
        btn_add.setGraphic(NormalImage.add_btn);
        btn_rest.setGraphic(NormalImage.rest_btn);
        volver_btn.setGraphic(Getfile.getIcono("normal/left20.png"));
        Rectangle clip = new Rectangle(
                img_src.getFitWidth(), img_src.getFitHeight()
        );

        clip.setArcWidth(20);
        clip.setArcHeight(20);
        clip.setStroke(Color.BLACK);
        img_src.setClip(clip);

        //snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = img_src.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        img_src.setClip(null);

        // apply a shadow effect.
        img_src.setEffect(new DropShadow(20, Color.BLACK));

        // store the rounded image in the imageView.
        img_src.setImage(image);
        guardar_producto.setOnMouseClicked((e) -> {
            //GuardarProducto();
            Alert alerta = Alerta.getAlert(Alert.AlertType.CONFIRMATION, "Confirmar", "¿Desea guardar el producto?", Getfile.getIcono("minilogo.png"));
            Optional<ButtonType> type = alerta.showAndWait();
            if (type.get() == ButtonType.OK) {
                GuardarProducto();
                cerrar();
            }
        });

        dlt_img.setOnAction((e) -> {
            Alert sc = Alerta.getAlert(Alert.AlertType.CONFIRMATION, "¿Está Seguro?", "Desea eliminar la imagen", null);
            Optional<ButtonType> type = sc.showAndWait();
            if (type.get() == ButtonType.OK) {
                boolean eliminar = true;
                if (display != null && !display.isEmpty()) {
                    ImagenProducto pr = display.get(pagina_act);
                    if (pr.getId() != null) {
                        InventarioBackend bc = new InventarioControllerImpl();
                        bc.EliminarImagen(pr);
                        if (Conexion.getStatus() == 200) {
                            Alert alerta = Alerta.getAlert(Alert.AlertType.INFORMATION, "Informativo", "Se ha eliminado correctamente", Getfile.getIcono("minilogo.png"));
                            alerta.showAndWait();
                        } else {
                            eliminar = false;
                            Alert error = Alerta.getAlert(Alert.AlertType.ERROR, "Error", "Error al eliminar", img_src);
                            error.showAndWait();
                        }
                    }
                    if (eliminar) {
                        display.remove(pg_pagination.getCurrentPageIndex());
                        pg_pagination.setPageCount(display.size());
                        pg_pagination.setCurrentPageIndex(pg_pagination.getCurrentPageIndex() - 1);
                    }

                    if (display.isEmpty()) {
                        pg_pagination.setDisable(true);
                        dlt_img.setDisable(true);
                    }

                }
            }
        });
        
        if(producto!=null){
            modificarProducto();
        }

    }

    private void setCantidadCero() {
        cantidad_tf.setText("0");
        cantidad_tf.setDisable(true);
        cantidad = 0.0;
        btn_add.setDisable(true);
        btn_rest.setDisable(true);
    }

    private void AgregarImagen() {
        File fl = SelecionArchivos.seleccionarImagen();
        if (fl != null) {
            converter = new ImageConverter(fl);
            if (display == null) {
                pg_pagination.setDisable(false);
                dlt_img.setDisable(false);
                display = new ArrayList<>();
                ImagenProducto pr = new ImagenProducto(getSize(converter));
                display.add(pr);
                pg_pagination.setPageCount(display.size());
                pg_pagination.setCurrentPageIndex(0);
            } else {
                ImagenProducto pr = new ImagenProducto(getSize(converter));
                display.add(pr);
                pg_pagination.setPageCount(display.size());
                pg_pagination.setCurrentPageIndex(0);
                pg_pagination.setDisable(false);
                dlt_img.setDisable(false);
            }
        }
    }

    private void loadHomeForm() {
        App.bodycenter.cargarBody("fxml/ProductoCuerpo");

    }

    private ImageView getSize(ImageConverter converter) {
        ImageView view = new ImageView(converter.getImage());
        view.setFitHeight(pg_pagination.getHeight() - 100);
        view.setFitWidth(pg_pagination.getWidth() - 100);
        view.getStyleClass().add("carrusel");
        return view;
    }

    private void Loadform() {

    }

    public void setStack_pane(StackPane stack_pane) {
        this.stack_pane = stack_pane;
    }

    public void setLastRoot(Parent lastRoot) {
        this.lastRoot = lastRoot;
    }

    public void setNewRoot(Parent newRoot) {
        this.newRoot = newRoot;
    }

    private void GuardarProducto() {
        error_list.setVisible(false);
        if (this.producto == null) {
            producto = new Producto();
        }
        producto.setNombre(nombre_area.getText());
        if (chk_tf.isSelected()) {
            producto.setCodigo(cod_tf.getText());
        }
        producto.setPrecio_unit(precio_unit.getValue());
        producto.setPrecio_vent(precio_vent.getValue());
        producto.setDescripcion(text_area.getText());
        producto.setStock_min(stock_min.getValue());
        producto.setCant_actual(j_spim.getValue());
        producto.setCat(cat);
        //*****************Imagenes

        if (display != null) {
            display.forEach((e) -> {
                if (e.getId() == null) {
                    e.setProducto(producto);
                    ImageConverter convertidor = new ImageConverter(e.getFrontView().getImage());
                    e.setImagen(convertidor.getbase64img());
                }
            });
            producto.setImagenes(display);
        }
        //**************Fin 

        InventarioBackend implement = new InventarioControllerImpl();
        if (producto.getId() == null) {
            Producto response = implement.GuardarProducto(producto, "crear", j_spim.getValue());
            mensaje(response, implement);
        } else {

            Producto response = implement.GuardarProducto(producto, "actualizar", 0.0);
            mensaje(response, implement);
        }

        //
    }

    private void mensaje(Producto response, InventarioBackend bck) {
        if (response != null) {
            success_msg.setText("Ha sido guardado correctamente");
            SuccessMsg();
            limpiarCampos();

        } else {
            if (bck.getErrors() != null) {
                error_list.setItems(FXCollections.observableArrayList(bck.getErrors()));
                error_list.setVisible(true);
            }
            error_msg.setText("Error no se ha guardado correctamente");
            ErrorMsg();
        }
    }

    private void cargarFormulario() {
        try {
            Parent parent = App.loadFXML("fxml/CategoriaForm");
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            CategoriaFormController categoria = App.loadctual.getController();
            stage.setTitle("Agregar Producto");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.centerOnScreen();
            scene.getStylesheets().add(App.class.getResource("css/styles.css").toExternalForm());
            categoria.setWindow(stage);
            if (categoria.isLoaded()) {
                stage.showAndWait();
                if (this.cat == null) {
                    this.cat = categoria.getCategoria();
                } else {
                    if (categoria.getCategoria() != null) {
                        this.cat = categoria.getCategoria();
                    }
                }
                if (cat != null) {
                    categoria_lb.setText(cat.getNombre());
                }
            }

            //  System.out.println(cat.getNombre());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void limpiarCampos() {
        cantidad_tf.setText("");
        nombre_area.setText("");
        cod_tf.setText("");
        precio_unit.getValueFactory().setValue(0.0);
        precio_vent.getValueFactory().setValue(0.0);
        j_spim.getValueFactory().setValue(0.0);
        text_area.setText("");
        categoria_lb.setText("Sin categoría");
        this.cat = null;
    }

    private void SuccessMsg() {
        System.out.println("acepted");
        sucess_cnt.setVisible(true);
        sucess_cnt.setOpacity(-1);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), sucess_cnt);
        fadeTransition.setToValue(100);
        fadeTransition.setByValue(-1);
        fadeTransition.setOnFinished((e) -> {
            try {
                Thread.sleep(500);
                FadeTransition fadeTransition3 = new FadeTransition(Duration.millis(500), sucess_cnt);
                fadeTransition3.setByValue(-1);
                fadeTransition3.setOnFinished((l) -> {
                    sucess_cnt.setVisible(false);
                    if (producto.getId() != null) {
                        cerrar();
                    }

                });
                fadeTransition3.play();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        fadeTransition.play();

    }

    private void ErrorMsg() {
        System.out.println("Error");
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

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
        setCantidadCero();
    }

    private void cerrar() {
        if (closeform) {
            Stage stg = (Stage) this.categoria_lb.getParent().getScene().getWindow();
            stg.close();
        } else {
            loadHomeForm();
        }

    }
}
