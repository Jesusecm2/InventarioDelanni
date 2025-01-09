/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Ingresos;

import com.delanni.inversiones.frontend.App;
import com.delanni.inversiones.frontend.ViewController.Ingresos.Precarga.NormalImage;
import static com.delanni.inversiones.frontend.ViewController.Ingresos.Precarga.NormalImage.left_btn;
import com.delanni.inversiones.frontend.ViewController.Inicio.CuerpoHomeController;
import com.delanni.inversiones.frontend.ViewController.Interfaces.Controladores;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Jesusecm
 */
public class EgresoFormController implements Controladores {

    @FXML
    private ImageView img_src;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_rest;

    @FXML
    private Button volver_btn;

    private Parent lastRoot;

    private Parent newRoot;

    private StackPane stack_pane;

    public void setLastRoot(Parent lastRoot) {
        this.lastRoot = lastRoot;
    }

    public void setNewRoot(Parent newRoot) {
        this.newRoot = newRoot;
    }

    public void setStack_pane(StackPane stack_pane) {
        this.stack_pane = stack_pane;
    }

    @Override
    public void responsive800() {
        btn_add.setOnMouseEntered((e) -> {
            btn_add.setGraphic(NormalImage.add_btn_rol);
            btn_add.setCursor(Cursor.HAND);
        });

        btn_add.setOnMouseExited((e) -> {
            btn_add.setGraphic(NormalImage.add_btn);
            btn_add.setCursor(Cursor.DEFAULT);
        });
        btn_add.setOnMousePressed((e) -> {
            btn_add.setGraphic(NormalImage.add_btn_click);
            btn_add.setCursor(Cursor.CLOSED_HAND);

        });
        btn_add.setOnMouseReleased((e) -> {
            btn_add.setGraphic(NormalImage.add_btn_rol);
            btn_add.setCursor(Cursor.HAND);
        });

        btn_rest.setOnMouseEntered((e) -> {
            btn_rest.setGraphic(NormalImage.rest_btn_rol);
            btn_rest.setCursor(Cursor.HAND);
        });

        btn_rest.setOnMouseExited((e) -> {
            btn_rest.setGraphic(NormalImage.rest_btn);
            btn_rest.setCursor(Cursor.DEFAULT);
        });
        btn_rest.setOnMousePressed((e) -> {
            btn_rest.setGraphic(NormalImage.rest_btn_click);
            btn_rest.setCursor(Cursor.CLOSED_HAND);

        });
        btn_rest.setOnMouseReleased((e) -> {
            btn_rest.setGraphic(NormalImage.rest_btn_rol);
            btn_rest.setCursor(Cursor.HAND);
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
    public void responsive1600() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setRollovers800() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setRollovers1600() {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        responsive800();
        img_src.setImage(NormalImage.addimg_btn.getImage());
        btn_add.setGraphic(NormalImage.add_btn);
        btn_rest.setGraphic(NormalImage.rest_btn);
        volver_btn.setGraphic(NormalImage.left_btn);
    }

    private void loadHomeForm() {
        try {
            //this.setLastRoot(getNewRoot());
            Parent root = App.loadFXML("fxml/CuerpoHome");
            CuerpoHomeController control = App.loadctual.getController();
            control.setLastRoot(root);
            control.setNewRoot(root);
            control.setStack_pane(stack_pane);
            this.setNewRoot(root);
            Scene secene = stack_pane.getScene();
            root.translateXProperty().set(secene.getWidth());
            stack_pane.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(t -> {
                stack_pane.getChildren().remove(lastRoot);

            });
            timeline.play();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
