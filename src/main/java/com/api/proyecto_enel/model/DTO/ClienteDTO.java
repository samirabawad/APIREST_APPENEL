package com.api.proyecto_enel.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.*;
import java.util.Set;

//representa un objeto de transferencia de datos
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    //@NotNull (este si validar que no sean letras)
    private Integer id;

    //@NotNull(message="idRol no debe ser nulo")
    //@Min(value = 1, message ="el valor debe ser de una longitud min 1" )
    //@Max(value = 1, message = "el valor debe ser de una longitud max 1")
    //@Size(max = 1)
    //@NotEmpty(message = "no vacio")
   // @Pattern(regexp = "^[0-9]+$", message = "El idRol debe ser un número entero")
    private Integer idRol;

    @NotNull(message="nombre no debe ser nulo")
    @Size(min = 2, max = 4,message="debe tener un max de 4 caracteres")
    private String nombreCli;

    //@NotNull(message="apellido no debe ser nulo")
    private String apellidoCli;

    //@NotNull(message="correo no debe ser nulo")
    private String correoCli;

    //@NotNull(message="clave no debe ser nula")
    private String claveCli;

    //@NotNull(message="rut no debe ser nulo")
    private String rutCli;

    //@NotNull(message="celular no debe ser nulo")
    private String celularCli;

    //@NotNull(message="direccion no debe ser nulo")
    private String direccionCli;


    public Set<ConstraintViolation<ClienteDTO>> validars(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(this);
    }
}