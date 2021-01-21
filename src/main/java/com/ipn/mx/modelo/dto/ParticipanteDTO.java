/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Participante;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Ivan
 */
@Data
@AllArgsConstructor
public class ParticipanteDTO implements Serializable{
   private Participante entidad;
   
     public ParticipanteDTO(){
         entidad = new Participante();
     }
     
     @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Id Intercambio : ").append(getEntidad().getIdIntercambio()).append("\n");
        sb.append("Id Usuario : ").append(getEntidad().getIdUsuario()).append("\n");
        sb.append("Nombre Participante : ").append(getEntidad().getNombreParticipante()).append("\n");
        sb.append("Decision : ").append(getEntidad().getParticipa()).append("\n");
        return sb.toString();
    }
}
