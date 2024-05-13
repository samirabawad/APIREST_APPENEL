package com.api.proyecto_enel.util;

import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;

public class ValidacionPorCampo {
    public static String validacionPorCampo(String nombreCampoString, String campoEntregadoString) {
        //Validacion campos nombre y apellido
        if (nombreCampoString.equals("nombre") || nombreCampoString.equals("apellido")) {
            Boolean result = StringValidation.IsOnlyAlphabetic(campoEntregadoString);
            if (result == true) {
                return "Campo correcto";
            } else {
                return "El nombre debe tener solo letras";
            }
        } else if (nombreCampoString.equals("rut")) {
            Boolean result = RutValidation.validacionModule11(campoEntregadoString);
            if (result == true) {
                return "Campo correcto";
            } else {
                return "El rut no es valido";
            }
        } else if (nombreCampoString.equals("correo")) {
            Boolean result = CorreoValidation.validacionCorreo(campoEntregadoString);
            if (result == true) {
                return "Campo correcto";
            } else {
                return "El correo no es valido";
            }
        }else if(nombreCampoString.equals("clave")){
            Boolean result = ClaveValidation.ClaveValidate(campoEntregadoString);
            if (result == true) {
                return "Campo correcto";
            }else{
                return "La clave no es valida";
            }
        }else if(nombreCampoString.equals("celular")){
            Boolean result = CelularValidation.ValidateCelular(campoEntregadoString);
            if (result == true) {
                return "Campo correcto";
            }else{
                return "El celular no es valido";
            }
        } else{
            return "el campo no existe";
        }
    }
}
