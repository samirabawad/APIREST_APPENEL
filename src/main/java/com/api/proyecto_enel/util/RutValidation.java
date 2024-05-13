package com.api.proyecto_enel.util;


//Modulo 11 es un algotirmo estandar para la validación de RUT.
//Se toma el rut sin guion ni digito verificador y se le añade el digito verificador calculado.
//Si el digito verificador obtenido coincide con el proporcionado, es un rut valido.
//DG = 10 (es k), DG = 11 (es 0) y DG = [0-9] (es igual al proporcionado).

public class RutValidation {

    //Funcion principal que valida y verifica RUT en base al modulo 11.
    public static Boolean validacionModule11(String rut) {
        // Formatea RUT: elimina puntos y guiones, convierte k en mayuscula.
        if(rut.contains("[.\\-]")){
            rut = rut.replaceAll("[.\\-]", "").toUpperCase();
        }else{
            return false;
        }
        //Se separa el RUT.
        //Obtiene RUT.
        String numeroRUT = rut.substring(0, rut.length() - 1);
        //Obtiene DV
        String dv = rut.substring(rut.length() - 1);

        //Valida rut y verifica con Modulo 11
        Boolean rutValidado = ValidaRutEnviado(numeroRUT, dv);
        if(rutValidado==false) {
            return false;
        }else{

            //Obtiene DV del Modulo 11.
            String dvEsperado = calcularDigitoVerificadorEsperado(numeroRUT);
            //Compara DV de Modulo 11 con el proporcionado.
            System.out.println("dvEsperado:"+dvEsperado+ "dv entregado: "+dv);
            if(dvEsperado.equals("igual")){
                return true;
            }else if(dvEsperado.equals("0") && dv.equals("0")){
                return true;
            }else if(dvEsperado.equals("K") && dv.equals("K")){
                return true;
            }else{
                return false;
            }
        }
    }

    //Valida formato de RUT enviado.
    public static Boolean ValidaRutEnviado(String numeroRUT, String dv) {
        //valida numeroRUT
        if (StringValidation.IsOnlyNumeric(numeroRUT) == false) {
            return false;
        } else {
            //Valida DV
            if (StringValidation.IsOnlyNumeric(dv) == false) {
                if (dv.equals("K") == false) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
    }

    //Calculo DV en base a RUT preparado con modulo 11
    public static String calcularDigitoVerificadorEsperado(String numeroRUT) {
        Object[] array = new Object[]{numeroRUT};

        Object[] rutInvertido = invertirRut(array); //retorna el rut invertido
        String dvEsperado = calculoDV(rutInvertido, array);//retorna el dv del modulo 11

        System.out.println("digito verificador esperado: "+dvEsperado);
        return dvEsperado;
    }


    //Prepara RUT en base a modulo 11, para sacar el DV en base al modulo 11.
    public static Object[] invertirRut(Object[] array) {
        Object[] invertir_int = new Object[array.length];
        int maximo = array.length;

        for (int i = 0; i < array.length; i++) {
            Object j = array[maximo - 1];
            invertir_int[maximo - 1] = array[i];
            maximo--;
        }
        return invertir_int;
    }



    //Calcula el digito verificador con modulo 11
    public static String calculoDV(Object[] rutInvertido, Object[] array) {

        //Calculo para verificar el rut invertido
        int a = 2;
        int rutSumado = 0;
        for (int i = 0; i < rutInvertido.length; i++) {
            rutSumado = 0;
            array[i] = Integer.parseInt((String) array[i]) * a;
            rutSumado += Integer.parseInt(String.valueOf(array[i]));
            if (a == 7) {
                a = 1;
            }
            a++;
        }
        int resto = rutSumado % 11;
        //Digito dará como resultado el digito verificador.
        String Digito = String.valueOf(11 - resto);
        if (Digito.equals("11")) {
            Digito = "0";
        }
        if (Digito.equals("10")) {
            Digito = "K";
        }else{
            Digito = "igual";
        }
        return Digito;
    }

}
