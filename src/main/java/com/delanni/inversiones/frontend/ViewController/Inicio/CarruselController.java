/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Inicio;

import com.delanni.inversiones.frontend.Backend.Entity.Producto;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 *
 * @author Jesusecm
 */
public class CarruselController implements Initializable {

    private Producto p;

    @FXML
    private Button exit_btn;

    @FXML
    private Button btn_nxt;
    @FXML
    private Button btn_aft;

    @FXML
    private Pagination pg_nation;

    private List<ImageView> img_viewls;

    private Integer page;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        exit_btn.setOnAction((e) -> {
            exit_btn.getParent().setVisible(false);
            this.p = null;
        });
        btn_nxt.setOnAction((e) -> {

            if (page < pg_nation.getPageCount() - 1) {
                pg_nation.setCurrentPageIndex(++page);
            }
            if (page == pg_nation.getPageCount() - 1) {
                btn_nxt.setDisable(true);
            }
        });
        btn_aft.setOnAction((e) -> {
            if (page >= 0) {
                pg_nation.setCurrentPageIndex(--page);
            }
            if (page == 0) {
                btn_aft.setDisable(true);
            }
        });
        pg_nation.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {

                if (img_viewls == null) {
                    return new Label("Contenedor Vacío");
                } else {
                    page = param;
                    return img_viewls.get(param);

                }
            }
        });

    }

    public Producto getP() {
        return p;

    }

    public Pagination getPg_nation() {
        return pg_nation;
    }

    public void setPg_nation(Pagination pg_nation) {
        this.pg_nation = pg_nation;
    }

    public List<ImageView> getImg_viewls() {
        return img_viewls;
    }

    public void setImg_viewls(List<ImageView> img_viewls) {
        this.img_viewls = img_viewls;
        pg_nation.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {

                if (img_viewls == null) {
                    return new Label("Contenedor Vacío");
                } else {
                    page = param;
                    return img_viewls.get(param);

                }
            }
        });
        pg_nation.setCurrentPageIndex(0);
        btn_aft.setDisable(true);
        pg_nation.setPageCount(img_viewls.size());
        if (pg_nation.getPageCount() <= 1) {
            btn_nxt.setDisable(true);
        }
    }

    public void setP(Producto p) {
        this.p = p;

    }

    public Button getExit_btn() {
        return exit_btn;
    }

    public void setExit_btn(Button exit_btn) {
        this.exit_btn = exit_btn;
    }

}
