/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Interfaces;

import com.delanni.inversiones.frontend.Backend.Entity.SystemParam;
import java.util.List;

/**
 *
 * @author Jesusecm
 */
public interface ConfigSystem {

    public SystemParam guardarParametro(SystemParam guardar);

    public SystemParam obtenerParametro(Integer table, String referencia);

    public List<SystemParam> obtenerParametro(Integer table);
}
