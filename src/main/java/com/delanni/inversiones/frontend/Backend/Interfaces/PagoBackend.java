/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Interfaces;

import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Moneda;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.Pago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.TipodePago;
import com.delanni.inversiones.frontend.Backend.Entity.Pagos.ValorMoneda;
import com.delanni.inversiones.frontend.Backend.Entity.TpIngreso;
import com.delanni.inversiones.frontend.Backend.Entity.Transacciones;
import com.delanni.inversiones.frontend.ViewController.Pagos.PagoFacturaController;
import java.util.List;

/**
 *
 * @author Jesusecm
 */
public interface PagoBackend {

    public List<TipodePago> obtenerTiposPago();

    public List<Moneda> obtenerMonedas();

    public ValorMoneda obtenerValorMonedaHoy(Moneda param);

    public ValorMoneda guardarValorMoneda(ValorMoneda param);

    public Pago guardarPagoFactura(Factura factura, Pago pago);

    public List<TpIngreso> obtenerIngreso(String tipo);

    public Pago guardarPagoIngreso(TpIngreso ingreso, Pago pago);

    public List<Transacciones> obtenerEgresos();

    public List<Transacciones> obtenerIngresos();
}
