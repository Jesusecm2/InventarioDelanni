/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Authentication;

//import com.delanni.pos.inventoryfrontend.ExcepcionesPantalla;
import com.delanni.inversiones.frontend.App;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 *
 * @author Jesusecm
 */
public class AuthenticationFiles {
    
    private Properties file;
    private String url; //= System.getProperty("user.dir").concat("\\src\\main\\java\\com\\delanni\\inversiones\\frontend\\Backend\\data\\");
    private final String namefile = "Authentication.properties";
    
    public AuthenticationFiles() {
        try {
            file = new Properties();
            String aux = App.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            this.url = new File(aux).getParent().concat("\\");
            File f1 = new File(url.concat(namefile));
            if (!f1.exists()) {
                System.out.println("no existe");
                f1.createNewFile();
                
                storeFile();
                System.out.println("Success");
            } else {
                FileOutputStream readed = new FileOutputStream(f1);
                file.load(new FileReader(f1));
                file.store(readed, "Applicacion de servicio");
            }
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            //  ExcepcionesPantalla.error("Error al encontrar archivo", "No se pudo encontrar el archivo de autenticación");
        } catch (IOException ex) {
            ex.printStackTrace();
            // ExcepcionesPantalla.error("Error al encontrar archivo", "No se pudo encontrar el archivo de autenticación");
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
    
    public String getMessage(String key) {
        if (file != null) {
            return (String) file.get(key);
        }
        
        return null;
    }
    
    public void setMessage(String key, String value) {
        if (file != null) {
            file.setProperty(key, value);
        }
        
    }
    
    public void storeFile() {
        if (file != null) {
            try {
                file.store(new FileWriter(url.concat("\\").concat(namefile)), "comentario");
            } catch (IOException ex) {
                //   Logger.getLogger(AuthenticationFiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
