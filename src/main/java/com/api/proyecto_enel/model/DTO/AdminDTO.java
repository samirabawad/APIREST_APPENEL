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
     //@NotNull (este si validar que no sean letras)
     private Integer id;

     //@NotNull(message="idRol no debe ser nulo")
     private Integer idRol;

     //@NotNull(message="nombre no debe ser nulo")
     private String nombreAdmin;

     //@NotNull(message="apellido no debe ser nulo")
     private String apellidoAdmin;

     //@NotNull(message="correo no debe ser nulo")
     private String correoAdmin;

     //@NotNull(message="clave no debe ser nula")
     private String claveAdmin;

     //@NotNull(message="rut no debe ser nulo")
     private String rutAdmin;

     //@NotNull(message="celular no debe ser nulo")
     private String celularAdmin;

     //@NotNull(message="direccion no debe ser nulo")
     private String direccionAdmin;
}
