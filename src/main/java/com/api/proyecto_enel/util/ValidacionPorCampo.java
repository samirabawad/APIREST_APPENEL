package com.api.proyecto_enel.util;

public class ValidacionPorCampo {
    public static Boolean validacionPorCampo(String nombreCampoString, String campoEntregadoString) {
        //Validacion campos nombre y apellido
        if (nombreCampoString.equals("nombreCli") || nombreCampoString.equals("apellidoCli")) {
            Boolean result = StringValidation.IsOnlyAlphabetic(campoEntregadoString);
            if (result == true) {
                return true;
            } else {
                return false;
            }
        } else if (nombreCampoString.equals("rutCli")) {
            Boolean result = RutValidation.validacionModule11(campoEntregadoString);
            System.out.println("rut es igual:  " + result);
            if (result == false) {
                return false;
            } else {
                return true;
            }
        } else if (nombreCampoString.equals("correoCli")) {
            Boolean result = CorreoValidation.validacionCorreo(campoEntregadoString);
            if (result == false) {
                return false;
            } else {
                return true;
            }
        }else if(nombreCampoString.equals("claveCli")){
            Boolean result = ClaveValidation.ClaveValidate(campoEntregadoString);
            if (result == false) {
                return false;
            }else{
                return true;
            }
        }else if(nombreCampoString.equals("celularCli")){
            Boolean result = CelularValidation.ValidateCelular(campoEntregadoString);
            if (result == false) {
                return false;
            }else{
                return true;
            }
        } else{
            return true;
        }
    }
}
