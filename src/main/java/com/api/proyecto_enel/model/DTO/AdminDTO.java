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

     private String rut_admin;

     private String nombre_admin;

     private String apellido_admin;

     private String correo_admin;

     private String clave_admin;

     private String celular_admin;
}
