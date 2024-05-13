package com.api.proyecto_enel.util;


//Modulo 11 es un algotirmo estandar para la validación de RUT.
//Se toma el rut sin guion ni digito verificador y se le añade el digito verificador calculado.
//Si el digito verificador obtenido coincide con el proporcionado, es un rut valido.
//DG = 10 (es k), DG = 11 (es 0) y DG = [0-9] (es igual al proporcionado).

public class RutValidation {

    public static String validaFormato( String rut) {
        //Elimina puntos y guiones del formato original
        String rutSinFormato = rut.replaceAll("[.-]", "");
        System.out.println("rutSinFormato2: "+rutSinFormato); //209807602

        //Verifica si el RUT tiene el formato correcto (7-8 dígitos y un dígito verificador)
        if (rutSinFormato.matches("\\d{7,9}[0-9kK]")) {
            System.out.println("Primer if matches con regex");
            //Verifica longitud mínima y máxima del RUT
            if (rutSinFormato.length() >= 8 && rutSinFormato.length() <= 9) {
                //Agrega puntos y guion al formato deseado
                System.out.println("rut valido");
                return "rut valido";
            } else {
                System.out.println("el rut debe tener entre 7 y 8 digitos");
                return "El RUT debe tener entre 8 y 9 dígitos.";
            }
        } else {
            System.out.println("El RUT no tiene el formato valido");
            return "El RUT no tiene el formato válido. Debe tener puntos, guion y digito verificador";
        }
    }
    //Funcion principal que valida y verifica RUT en base al modulo 11.
    public static String validacionModule11(String rut) {
        String validacionFormato = validaFormato(rut);
        if (validacionFormato.equals("rut valido")) {
            System.out.println("en validacionmodulo11, primer if equals");
            String rutSinFormato = rut.replaceAll("[.-]", "");
            System.out.println("rutSinFormato en validacionmodulo11: "+rutSinFormato); //209807602

            //Se separa el RUT.
            //Obtiene RUT.
            String numeroRUT = rutSinFormato.substring(0, rutSinFormato.length() - 1);
            System.out.println("numeroRUT en validacionmodulo11: "+numeroRUT);
            //Obtiene DV
            String dv = rutSinFormato.substring(rutSinFormato.length() - 1);
            System.out.println("dv en validacionmodulo11: "+dv);

                //Obtiene DV del Modulo 11.
                String dvEsperado = calcularDigitoVerificadorEsperado(numeroRUT);
                //Compara DV de Modulo 11 con el proporcionado.
                System.out.println("dvEsperado:" + dvEsperado + "dv entregado: " + dv);
                if (dvEsperado.equals("igual")) {
                    return "RUT valido";
                } else if (dvEsperado.equals("0") && dv.equals("0")) {
                    return "RUT valido";
                } else if (dvEsperado.equals("K") && dv.equals("K")) {
                    return "RUT valido";
                } else {
                    return "RUT invalido";
                }
            }
        else{
            System.out.println("caigo en el else de el rut no tiene un formato valido"); //209807602
            return "El RUT no tiene el formato valido. "+validacionFormato;
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
