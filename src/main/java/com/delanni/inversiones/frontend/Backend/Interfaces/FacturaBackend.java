/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Interfaces;

import com.delanni.inversiones.frontend.Backend.Entity.Cliente;
import com.delanni.inversiones.frontend.Backend.Entity.Cuenta;
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.LineaFactura;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Pago;
import com.delanni.inversiones.frontend.Backend.Entity.Proveedor;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jesusecm
 */
public interface FacturaBackend {

	public Proveedor guardarProveedor(Proveedor save);
	
	public Cuenta guardarCuenta(Cuenta save);
	
	public List<Proveedor> listadoProveedor();
	
	public List<Factura> listadoFacturas();

	
	public List<Factura> listadoFacturas(Proveedor prov);
	
	public List<Factura> listadoFacturas(Proveedor prov,String parametro);
	
	public List<Factura> listadoFacturas(Date start);
	
	public List<Factura> listadoFacturas(String status);
	
	public List<Factura> listadoFacturasNotNull();
	
	public List<Factura> listadoVentas();
	
	public List<Factura> listadoVentas(Cliente cl);
	
	public List<Factura> listadoVentas(Cliente cl,String status);
	
	public List<Factura> listadoVentas(Date date);
	
	public List<Factura> listadoVentas(Cliente cl,Date dt);
	
	public Factura guardarFactura(Factura save,List<Pago> pagos);
	
            
        
        public InputStream reporteFactura(Factura factura);

            }
