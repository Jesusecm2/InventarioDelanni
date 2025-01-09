/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Authentication;

import java.time.Duration;


/**
 *
 * @author Jesusecm
 */
public class RuntimeAuthentication implements Runnable{
    
    private AuthenticationInfo info;

    public AuthenticationInfo getInfo() {
        return info;
    }

    public void setInfo(AuthenticationInfo info) {
        this.info = info;
    }

    @Override
    public void run() {
        
        while(true){
            try {
                Duration time  = Duration.ofSeconds(1L);
                Thread.sleep(time.getSeconds());                
                Long lon = Long.valueOf(info.getExpires_in());
                lon = lon-1;
                info.setExpires_in(String.valueOf(lon));
                System.out.println(info);
            } catch (InterruptedException ex) {
                //Logger.getLogger(RuntimeAuthentication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
