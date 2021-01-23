
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Amigo;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Ivan
 */
@Data
@AllArgsConstructor
public class AmigoDTO implements Serializable{
    private Amigo entidad;
    
    public AmigoDTO(){
        entidad = new Amigo();
    }
    
}
