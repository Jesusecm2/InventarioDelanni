/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Interfaces;

import com.delanni.inversiones.frontend.Backend.Entity.Cliente;
import com.delanni.inversiones.frontend.Backend.Entity.Cuenta;
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Pago;
import com.delanni.inversiones.frontend.Backend.Entity.Proveedor;
import java.util.List;

/**
 *
 * @author Jesusecm
 */
public interface FacturaBackend {

    public Proveedor guardarProveedor(Proveedor save);

    public Cuenta guardarCuenta(Cuenta cuenta);

    public List<Proveedor> listadoProveedor();

    public Factura guardarFactura(Factura factura, List<Pago> pagos);

    public List<Factura> facturaProveedor(Integer id);

    public List<Factura> buscarPorProveedorStatus(Proveedor prov, String parametro);

    public List<Factura> buscarFacturasStatus(String parametro);

    public List<Factura> listadoFactura();

    public List<Factura> listadoFacturaNonuloProveedor();

    public List<Factura> obtenerVentasClientes();

    public List<Factura> obtenerVentasPorCliente(Cliente cl);
    
    public List<Factura> obtenerVentasPorCliente(Cliente cl,String sts);
            

            }
