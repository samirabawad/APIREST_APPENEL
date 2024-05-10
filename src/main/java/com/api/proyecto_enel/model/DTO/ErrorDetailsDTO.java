package com.api.proyecto_enel.model.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
public class ErrorDetailsDTO {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ErrorDetailsDTO(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}
