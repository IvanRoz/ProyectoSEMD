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
public class Amigo implements Serializable{
    private int idUsuario;
    private int idAmigo;
    private String nombreAmigo;
}
