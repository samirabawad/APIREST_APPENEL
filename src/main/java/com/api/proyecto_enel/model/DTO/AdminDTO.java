package com.api.proyecto_enel.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

//representa un objeto de transferencia de datos
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {

     private Integer id;

     private Integer idrol;

     private String rut;

     private String nombre;

     private String apellido;

     private String correo;

     private String clave;

     private String celular;
}
