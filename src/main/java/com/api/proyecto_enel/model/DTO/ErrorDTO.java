package com.api.proyecto_enel.model.DTO;

import lombok.Data;

@Data
public class ErrorDTO {
    private String mensaje;
    private String codigoError;

    public ErrorDTO(String mensaje, String codigoError) {
        this.mensaje = mensaje;
        this.codigoError = codigoError;
    }
}
