package com.api.proyecto_enel.controller;
import com.api.proyecto_enel.model.DTO.ErrorDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

//Controller Advice manejará todas las excepciones que ocurran en los controladores.
//ResponseEntityExceptionHandler es una clase conveniente que proporciona gestion de excepciones internas de spring boot.

//El manejo lo hace mediante el metodo de la anotacion @ExceptionHandler.
//@ExceptionHandler: permite modificar las respuestas de las excepciones, para mayor entendimiento.
//@ExceptionHandler: Recibe como parametro la excepcion o lista de excepciones que queremos que gestione.

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMensaje("El objeto es nulo.");
        errorDTO.setCodigoError(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return ResponseEntity.status(status).body(errorDTO);    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
                                                                     HttpHeaders headers,
                                                                     HttpStatus status,
                                                                     WebRequest request){
        return ResponseEntity.status(status).body(ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
     //   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en el formato del JSON enviado. Por favor, verifique que los datos estén en el formato correcto.");
   // }
    ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMensaje("Error en el formato del JSON enviado. Por favor, verifique que los datos estén en el formato correcto.");
        errorDTO.setCodigoError(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }
}
