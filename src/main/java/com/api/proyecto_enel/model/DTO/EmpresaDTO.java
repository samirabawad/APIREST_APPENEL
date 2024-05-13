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

    private String rut_empresa;

    private String nombre_empresa;

    private String giro_empresa;

    private String correo_empresa;

    private String clave_empresa;

    private String celular_empresa;

}