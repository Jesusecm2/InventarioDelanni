/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Entity;

import java.util.Date;

/**
 *
 * @author Jesusecm
 */
public class DailyAudit {
    

	private Long id;
	
	private Long idusuario;

	// **************IP
	private String con_ip;
	// **************Ruta destino
	// **************Metodo
	private String route;
	// **************Objeto String
	private String entity_obj;
	// **************Fecha ejecucion
	private Date fecha;
	// **************Navegador o Applicacion
	private String tp_acceso;

	// *************Sistema Operativo
	private String sys_op;

	// *************
	private String servicio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getCon_ip() {
        return con_ip;
    }

    public void setCon_ip(String con_ip) {
        this.con_ip = con_ip;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getEntity_obj() {
        return entity_obj;
    }

    public void setEntity_obj(String entity_obj) {
        this.entity_obj = entity_obj;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTp_acceso() {
        return tp_acceso;
    }

    public void setTp_acceso(String tp_acceso) {
        this.tp_acceso = tp_acceso;
    }

    public String getSys_op() {
        return sys_op;
    }

    public void setSys_op(String sys_op) {
        this.sys_op = sys_op;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }
        
        
}
