package com.api.proyecto_enel.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaDTO {
    //@NotNull (este si validar que no sean letras)
    private Integer id;
    //@NotNull(message = "Id no debe ser nulo")
    private Integer idRol;

    //@NotNull(message = "nombre no debe ser nulo")
    private String nombreEmpresa;

    //@NotNull(message = "apellido no debe ser nulo")
    private String apellidoEmpresa;

    //@NotNull(message = "correo no debe ser nulo")
    private String correoEmpresa;

    //@NotNull(message = "clave no debe ser nula")
    private String claveEmpresa;

    //@NotNull(message = "Run no debe ser nulo")
    private String runEmpresa;

    //@NotNull(message = "Celular no debe ser nulo")
    private String celularEmpresa;

    @NotNull(message = "direccion no debe ser nulo")
    private String direccionEmpresa;
}