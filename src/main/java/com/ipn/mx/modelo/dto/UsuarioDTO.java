/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;
import com.ipn.mx.modelo.entidades.Usuario;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 *
 * @author Ivan
 */
@Data
@AllArgsConstructor
public class UsuarioDTO implements Serializable{
    private Usuario entidad;
    
    public UsuarioDTO(){
        entidad = new Usuario();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Id Usuario : ").append(getEntidad().getIdUsuario()).append("\n");
        sb.append("Alias : ").append(getEntidad().getAlias()).append("\n");
        sb.append("Nombre : ").append(getEntidad().getNombre()).append("\n");
        sb.append("Correo : ").append(getEntidad().getCorreo()).append("\n");
        sb.append("Clave Usuario : ").append(getEntidad().getClave()).append("\n\n");
        return sb.toString();
    }
    
       
}
