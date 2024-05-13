package com.api.proyecto_enel.util;

import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;

public class ValidacionPorCampo {
    public static String validacionPorCampo(String nombreCampoString, String campoEntregadoString) {

        //Valida cadenas vacias o nulas
        Boolean campoNuloOrEmpty = StringValidation.IsEmptyOrNull(campoEntregadoString);
        if(campoNuloOrEmpty==true) {
            return "El campo " + nombreCampoString + " no puede ser vacio o nulo";
        }

        //Valida campos nombre y apellido
        if (nombreCampoString.equals("nombre") || nombreCampoString.equals("apellido")) {
            Boolean result = StringValidation.IsOnlyAlphabetic(campoEntregadoString);
            if (result == true) {
                return "Campo correcto";
            } else {
                return "El nombre debe tener solo letras";
            }

        //Valida campo rut
        } else if (nombreCampoString.equals("rut")) {
            String result = RutValidation.validacionModule11(campoEntregadoString);
            if (result == "RUT valido") {
                return "Campo correcto";
            } else {
                return "El RUT es invalido. "+result;
            }

        //Valida campo correo
        } else if (nombreCampoString.equals("correo")) {
            Boolean result = CorreoValidation.validacionCorreo(campoEntregadoString);
            if (result == true) {
                return "Campo correcto";
            } else {
                return "El formato de correo es invalido." +
                        "Al menos una letra mayuscula o minuscula,"
                +"digito o un caracter especial, seguido de un @ y posteriormente un . ";
            }

        //Valida campo clave
        }else if(nombreCampoString.equals("clave")){
            Boolean result = ClaveValidation.ClaveValidate(campoEntregadoString);
            if (result == true) {
                return "Campo correcto";
            }else{
                return "Clave Invalida. Debe contener al menos una letra mayuscula, una minuscula, " +
                        "un digito, un caracter especial. " +
                        "Ademas no debe tener espacios en blanco y su longitud debe ser entre 8 y 16 caracteres.";
            }

        //Valida campo celular
        }else if(nombreCampoString.equals("celular")){
            Boolean result = CelularValidation.ValidateCelular(campoEntregadoString);
            if (result == true) {
                return "Campo correcto";
            }else{
                return "El celular no es valido";
            }

        //Entrega resultado para campo que no tiene regla de validacion
        }
        else{
            return "Este campo no tiene validacion";
        }
    }
}
