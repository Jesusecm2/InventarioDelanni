package com.delanni.inversiones.frontend;

import com.delanni.inversiones.frontend.Backend.Authentication.AuthenticationImpl;
import com.delanni.inversiones.frontend.Backend.Authentication.AuthenticationInfo;
import com.delanni.inversiones.frontend.Backend.Authentication.IAuthentication;
import com.delanni.inversiones.frontend.Backend.Conection.Transaccional;
import com.delanni.inversiones.frontend.Backend.Entity.Role;
import com.delanni.inversiones.frontend.Backend.Entity.Usuario;
import com.delanni.inversiones.frontend.ViewController.Ingresos.Precarga.NormalImage;
import com.delanni.inversiones.frontend.ViewController.Inicio.Helper.Getfile;
import com.delanni.inversiones.frontend.ViewController.Inicio.InicioController;
import com.delanni.inversiones.frontend.ViewController.Inicio.LogExportController;
import com.delanni.inversiones.frontend.ViewController.Interfaces.Controladores;
import com.delanni.inversiones.frontend.ViewController.Login.InicioSesionController;
import com.delanni.inversiones.frontend.ViewController.Pagos.ValorMonedaFormController;
import com.delanni.inversiones.frontend.ViewController.Producto.ProveedorFormController;
import com.delanni.inversiones.frontend.ViewObject.Previews.PreloadFXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.kordamp.bootstrapfx.BootstrapFX;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static FXMLLoader loadctual;
    public static Stage stage;
    public static AuthenticationInfo authinfo;
    private static boolean sizehigh;
    public static InicioController bodycenter;
    public static Thread hilocentral;
    public static String AppIP;
    public static String provider = "Inversiones Delanni App 1.0";
    public static String system = System.getProperty("os.name");

    @Override
    public void start(Stage stage) throws IOException {
        //AppIP = "http://192.168.0.123:8090";
        AppIP = "http://localhost:8090";

        /* Usuario user = new Usuario();
        user.setUsername("app_user");
        user.setPassword("123456");
        user.setNombre("Usuario de Aplicacion");
        user.setEmail("email@promedio");

        AuthenticationInfo us = new AuthenticationImpl().getToken(user);
        if(us!=null){
            authinfo = us;
        }*/
        App.stage = stage;
        NormalImage.precarga();
        PreloadFXML.loadParent();
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setTitle("Inversiones Delanni 2005 C.A");
        stage.getIcons().add(Getfile.getIcono("minilogo.png").getImage());
        ChangeListener<Number> resize = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double horizontal = stage.getWidth();
                double vertical = stage.getWidth();
                System.out.println(horizontal + "/" + vertical);
                if (horizontal != 0 && vertical != 0) {
                    if (IsResize()) {
                        resizeHigh();
                    } else {
                        resizeMedium();
                    }
                }
            }
        };
        stage.widthProperty().addListener(resize);
        stage.heightProperty().addListener(resize);
        // scene = new Scene(loadFXML("fxml/Inicio"), 640, 480);
        FXMLLoader lo = App.getFMXL("fxml/InicioSesion");

        scene = new Scene(lo.load(), 800, 600);
        //  App.bodycenter = lo.getController();
        scene.getStylesheets().add(App.class.getResource("css/styles.css").toExternalForm());
        scene.setOnKeyReleased((e) -> {

            if (e.getCode() == KeyCode.F12) {
                cargarVentanaModal("Parametros", "fxml/ParametroForm", true);
            }

            if (e.getCode() == KeyCode.F1) {
                cargarVentanaModal("fxml/LogsForm", new LogExportController(), true, "Logs del sistema");
            }

            if (e.getCode() == KeyCode.F11) {

                //cargarVentanaModal("Parametros", "fxml/ParametroForm", true);
                cargarVentanaModal("fxml/ValorMonedaForm", new ValorMonedaFormController(), true, "Mantenimiento");
            }

        });
        //scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML("fxml/" + fxml));
    }

    public static void setRoot(Parent fxml) throws IOException {
        scene.setRoot(fxml);
    }

    public static boolean IsResize() {
        double horizontal = stage.getWidth();
        double vertical = stage.getWidth();
        return (vertical > 1280 && horizontal > 720) ? true : false;
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        loadctual = fxmlLoader;
        //fxmlLoader.setController(new InicioSesionController());
        return fxmlLoader.load();
    }

    public static Parent loadFXML(String fxml, Object fml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setController(fml);
        return fxmlLoader.load();
    }

    public static FXMLLoader getFMXL(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        App.loadctual = fxmlLoader;
        //fxmlLoader.setController(new InicioSesionController());
        return fxmlLoader;
    }

    private static void resizeHigh() {
        //Controladores control = loadctual.getController();
        //control.responsive1600();

        if (!sizehigh) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(App.class.getResource("css/stylesG.css").toExternalForm());
            // scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        }

        sizehigh = true;
    }

    private static void resizeMedium() {
        //Controladores control = loadctual.getController();
        //control.responsive800();
        //scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        if (sizehigh) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(App.class.getResource("css/styles.css").toExternalForm());
//            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        }

        sizehigh = false;

    }

    public static void main(String[] args) {
        launch();
    }

    public static <T> T cargarVentanaModal(String titulo, String param, boolean modal) {
        try {
            Parent parent = App.loadFXML(param);
            Stage stage1 = new Stage();
            Scene scene1 = new Scene(parent);
            stage1.setTitle(titulo);
            stage1.initStyle(StageStyle.DECORATED);
            stage1.getIcons().add(Getfile.getIcono("minilogo.png").getImage());
            if (modal) {
                stage1.initModality(Modality.APPLICATION_MODAL);
            } else {
                stage1.initModality(Modality.NONE);
            }

            stage1.setScene(scene1);
            stage1.centerOnScreen();
            scene1.getStylesheets().add(App.class.getResource("css/styles.css").toExternalForm());
            if (modal) {
                stage1.showAndWait();
            } else {
                stage1.show();
            }

            return loadctual.getController();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void cargarVentanaModal(String fxml, Object controller, boolean modal, String titulo) {
        try {
            Parent pantalla = App.loadFXML(fxml, controller);
            Stage stage1 = new Stage();
            Scene scene1 = new Scene(pantalla);
            stage1.setTitle(titulo);
            stage1.initStyle(StageStyle.DECORATED);
            stage1.getIcons().add(Getfile.getIcono("minilogo.png").getImage());
            if (modal) {
                stage1.initModality(Modality.APPLICATION_MODAL);
            } else {
                stage1.initModality(Modality.NONE);
            }
            stage1.setScene(scene1);
            stage1.centerOnScreen();
            scene1.getStylesheets().add(App.class.getResource("css/styles.css").toExternalForm());
            if (modal) {
                stage1.showAndWait();
            } else {
                stage1.show();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
