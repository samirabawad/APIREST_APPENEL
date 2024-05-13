package com.api.proyecto_enel.model.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ErrorDTO {
    private String mensaje;
    private String codigoError;

}
