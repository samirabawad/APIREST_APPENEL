package com.api.proyecto_enel.util;

public class RutValidation {
    public static boolean validacionRut(String rut) {

        //rut debe ser en este formato: 12.345.678-9
        //Asgura consistencia de datos
        rut =  rut.toUpperCase();
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");

        //Se convierte la parte numerica del rut en Integer
        int rutInt = Integer.parseInt(rut.substring(0, rut.length() - 1));

        //Se obtiene el digito verificador del rut.
        char dv = rut.charAt(rut.length() - 1);

        //se inicializan las variables para el calculo del digito verificador
        int m = 0, s = 1;

        //se itera en cada digito del rut y se realiza calculo del algoritmo modulo 11.
        for (; rutInt != 0; rutInt /= 10) {
            s = (s + rutInt % 10 * (9 - m++ % 6)) % 11;
        }

        //se comprueba si el calculo del dv del algoritmo modulo 11 coincide con el dv ingresado.
        if (dv == (char) (s != 0 ? s + 47 : 75)) {
            return true;
        }
        //si no coinciden, el rut es invalido.
        else{
            return false;
        }
    }
}
