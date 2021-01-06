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
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable{
        private int idUsuario;
        private String alias;
        private String nombre;
        private String correo;
        private String clave;
}
