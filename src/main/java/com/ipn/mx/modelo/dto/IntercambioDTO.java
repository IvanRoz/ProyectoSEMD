/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Intercambio;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Ivan
 */
@Data
@AllArgsConstructor
public class IntercambioDTO implements Serializable{
    private Intercambio entidad;
    
    
    public IntercambioDTO(){
        entidad = new Intercambio();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Id Intercambio : ").append(getEntidad().getIdIntercambio()).append("\n");
        sb.append("nombre Intercambio : ").append(getEntidad().getNombreIntercambio()).append("\n");
        sb.append("Tema : ").append(getEntidad().getTema()).append("\n");
        sb.append("Fecha : ").append(getEntidad().getFechaIntercambio()).append("\n");
        sb.append("Monto Maximo : ").append(getEntidad().getMontoMax()).append("\n\n");
        return sb.toString();
    }
}
