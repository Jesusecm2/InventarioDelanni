/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.ViewController.Inicio.Helper;

import com.delanni.inversiones.frontend.App;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Jesusecm
 */
public class ApplicationProperties {

    private Properties prop;
    private final String prop_name = "config.properties";
    private final String key_aes = "Delanni_2005conf";
    private final String[] keys = {"usuario", "password", "recordar","op"};
    private final SecretKey secretKey;
    private final Cipher cifrador_in;
    private FileOutputStream readed;
    private final Cipher cifrador_out;
    //System.getProperty("user.dir").concat("\\src\\main\\java\\com\\delanni\\inversiones\\frontend\\Backend\\data\\");
    private final String url;

    public ApplicationProperties() throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidKeyException, URISyntaxException {
        String aux = App.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        this.url = new File(aux).getParent().concat("\\");
        System.out.println(url);
        this.secretKey = new SecretKeySpec(key_aes.getBytes(), "AES");
        this.cifrador_in = Cipher.getInstance("AES");
        this.cifrador_out = Cipher.getInstance("AES");
        this.cifrador_in.init(Cipher.ENCRYPT_MODE, secretKey);
        this.cifrador_out.init(Cipher.DECRYPT_MODE, secretKey);
        initializeProperties();
    }

    private void initializeProperties() throws IOException {
        if (prop == null) {
            prop = new Properties();
        }
        File f1 = new File(url.concat(prop_name));
        if (!f1.exists()) {
            System.out.println("no existe");
            f1.createNewFile();
            for (String key : keys) {
                prop.setProperty(key, "");
            }
            try (FileOutputStream out = new FileOutputStream(f1)) {
                prop.store(out, "Configuración de sistema");
            }
            System.out.println("Success");

        } else {
            prop.load(new FileReader(f1));
            
            readed = new FileOutputStream(f1);
            prop.store(readed, "Applicacion de servicio");
            System.out.println("Sucess");
        }

    }

    public String getKeyCifrada(String clave) {
        if (this.prop != null) {
            String value = prop.getProperty(clave);
            if (value != null) {
                try {
                    byte[] descifrado = cifrador_out.doFinal(Base64.getDecoder().decode(value));
                    return new String(descifrado);
                } catch (IllegalBlockSizeException | BadPaddingException ex) {
                    return null;
                }
            }

        }
        return null;
    }

    public boolean setKeyCifrada(String clave, String valor) {
        if (this.prop != null) {
            if (!valor.isBlank()) {
                try {
                    byte[] cifrado = cifrador_in.doFinal(valor.getBytes());
                    String txt_cifrado = Base64.getEncoder().encodeToString(cifrado);
                    prop.setProperty(clave, txt_cifrado);
                    prop.store(readed, clave);
                    return true;
                } catch (IllegalBlockSizeException | BadPaddingException ex) {
                    return false;
                } catch (IOException ex) {
                    return false;
                }
            } else {
                prop.setProperty(clave, valor);
            }
        }
        return false;
    }

    public String getKey(String clave) {
        if (this.prop != null) {
            String value = prop.getProperty(clave);
            if (value != null) {
                return value;
            }

        }
        return null;
    }

    public boolean setKey(String clave, String valor) {
        if (!valor.isBlank()) {
            try {
                prop.setProperty(clave, valor);
                prop.store(readed, "Archivo de configuración");
                return true;
            } catch (IOException ex) {
               return false;
            }
        }
        return false;
    }
}
