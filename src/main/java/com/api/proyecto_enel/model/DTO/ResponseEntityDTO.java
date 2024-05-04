package com.api.proyecto_enel.model.DTO;

import lombok.Data;

@Data
public class ResponseEntityDTO {
    private String mensaje;
    private String codigoRespuesta;

    public ResponseEntityDTO(String mensaje, String codigoRespuesta) {
        this.mensaje = mensaje;
        this.codigoRespuesta = codigoRespuesta;
    }
}
