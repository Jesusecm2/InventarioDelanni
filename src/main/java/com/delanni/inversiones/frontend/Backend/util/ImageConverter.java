/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javafx.scene.image.Image;

import org.apache.commons.io.FileUtils;

/**
 *
 * @author Jesusecm
 */
public class ImageConverter {

    private File img;
    private String imgString;
    private Image image;

    public ImageConverter(File img) {
        this.img = img;
    }

    public ImageConverter(Image image) {
        this.image = image;
    }

    public ImageConverter(String img) {
        this.imgString = img;
    }

    public Image getImage() {
        if (imgString != null) {
            byte[] decodedbytes = Base64.getDecoder().decode(imgString);
            ByteArrayInputStream bis = new ByteArrayInputStream(decodedbytes);
            Image image = new Image(bis);
            //BufferedImage Imagen = ImageIO.read(bis);
            return image;
//portada.setIcon(Imagen);
        }
        if (img != null) {
            return new Image(img.toURI().toString());
        }
        if(image!=null){
            return image;
        }

        return null;
    }

    public String getbase64img() {
        try {
            if (image != null) {
                String[] valor = image.getUrl().split("/",2);
                System.out.println(valor[1]);
                String url = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(new File(valor[1])));
                
                return url;
            } else {
                String url = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(img));
                return url;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public File getImg() {
        return img;
    }

    public void setImg(File img) {
        this.img = img;
    }

}
