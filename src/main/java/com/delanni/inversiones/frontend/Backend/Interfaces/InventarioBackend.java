/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Interfaces;

import com.delanni.inversiones.frontend.Backend.Entity.Categoria;
import com.delanni.inversiones.frontend.Backend.Entity.Cliente;
import com.delanni.inversiones.frontend.Backend.Entity.Factura;
import com.delanni.inversiones.frontend.Backend.Entity.ImagenProducto;
import com.delanni.inversiones.frontend.Backend.Entity.Producto;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jesusecm
 */
public interface InventarioBackend {

    public Categoria GuardarCategoria(Categoria categoria);

    public Categoria ObtenerCategoriaId(Categoria categoria);

    public List<Categoria> ListadoCategoria();

    public Producto GuardarProducto(Producto producto, String categoria, Double valor);

    public List<Producto> ListadoProducto();

    public List<Producto> ListadoProducto(Categoria cat);

    public List<Producto> obtenerProducto(String cod);

    public List<Producto> buscarNombre(String nombre);

    public void EliminarImagen(ImagenProducto obj);

    public Cliente guardarCliente(Cliente save);

    public Cliente BuscarCedulaCliente(String cedula);

    public List<Cliente>listadoCliente();
    
    public List<Producto> buscarCategoriaNombre(Categoria cat,String nombre);
    
    public InputStream reporteProductoCategoria(Categoria categoria);
    
    
    public List<String> getErrors();
    
}
