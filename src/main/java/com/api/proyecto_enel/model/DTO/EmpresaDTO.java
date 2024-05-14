package com.api.proyecto_enel.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaDTO {

    private Integer id;

    private Integer idrol;

    private String rut;

    private String nombre;

    private String giro;

    private String correo;

    private String clave;

    private String celular;

}