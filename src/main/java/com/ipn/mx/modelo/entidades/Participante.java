/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * @author Ivan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participante implements Serializable{
    
    private int idIntercambio;
    private int idUsuario;
    private String nombreParticipante;
    private boolean participa;
    
    public boolean getParticipa(){
        return this.participa;
    }
    
}
