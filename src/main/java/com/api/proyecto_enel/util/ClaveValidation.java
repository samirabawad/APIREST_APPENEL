package com.api.proyecto_enel.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClaveValidation {
    public static Boolean ClaveValidate(String claveCli){

        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";
        Pattern pattern = Pattern.compile(regex);
        // Create instance of matcher
        Matcher matcher = pattern.matcher(claveCli);

        // Imprimir resultado de la validación
        System.out.println(claveCli + " : " + matcher.matches());

        return matcher.matches();
    }
}
//EXPLICACION REGEX:

//^: Regla o patron que le sigue, se aplica solo al principio de la cadena regex.

// (?=.*[0-9]): Indica que al menos debe haber un digito.

//(?= ... ): Sirve para indicar que debe haber algo en el regex.
// El () indica un patron que se espera encontrar.
// El ?= indica que se buscará el match con la regla que le sigue, sin embargo, este no será capturado.
// .* :
// El . indica que puede aparecer cualquier caracter (excepto salto de linea)
// El * indica que puede aparecer 0 o mas veces
// [0-9]: cualquier caracter que coincida en este rango de 0 a 1.

//(?=\S+$) : Al menos un caracter y sin espacios en blanco
// \S representa cualquier caracter que no sea espacio en blanco.
// + : indica que debe haber uno o mas del caracter que le antecede.
// $ : indica que el patron definido que le antecede, debe llegar hasta el final de la cadena regex

//.{8,16}$ : la cadena debe ser entre 8 y 16 caracteres.
//. : cualquier caracter.
//{8,16} : lo que le antecede debe tener entre 8 y 16 caracteres.

//Pruebas de contrasenas validas: Abc123@def, P@ssw0rd
//Pruebas de contrasenas invalidas: abcdefgh, 123456789, Abcdefgh, P@ss, Abcdefghijklmnopqrstuvwxyz1234567890

