package com.api.proyecto_enel.util;

import com.api.proyecto_enel.model.DTO.ClienteDTO;
import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;

import java.lang.reflect.Field;

public class IterateObject {
    public static String IterateObjectDTO(Object objectDTO) {

        try {
            //Obtiene los campos del objetoDTO
            Class<?> objectDTOClass = objectDTO.getClass();
            Field[] objectDTOFields = objectDTOClass.getDeclaredFields();

            if (objectDTOFields.length != 0) {
                //Recorre campos del objectDTO
                for (Field f : objectDTOFields) {
                    f.setAccessible(true);

                    //Obtiene tipo de dato del campo DTO
                    Class<?> fclassType = f.getType();
                    System.out.print("class type:\t" + fclassType + "\t");

                    //Obtiene nombre del campo en DTO.
                    Object nombreCampo = f.getName();
                    System.out.print("Field name:\t" + f.getName() + "\t");

                    //Se transforma campo objeto campoDTO a String para validaciones.
                    String nombreCampoString = (String) nombreCampo;

                    //Valor enviado por el usuario para el campo.
                    Object campoEntregado = f.get(objectDTO);
                    System.out.print("valor entregado:\t" + campoEntregado + "\t");

                    //Validaciones para campos String
                    if (fclassType == String.class && campoEntregado.getClass() == String.class) {
                        String campoEntregadoString = (String) campoEntregado;

                        //Validaciones especificas para cada campo
                        String campoValidado = ValidacionPorCampo.validacionPorCampo(nombreCampoString, campoEntregadoString);
                        if (campoValidado == "Campo correcto") {
                            System.out.println("Campo correcto");
                        } else {
                            //Retorna respuesta personalizada del campo especifico.
                            return "Se ha producido un error en el registro del campo. "+campoValidado;
                        }

                        //Validaciones para campos Integer
                    } else if (fclassType == Integer.class && campoEntregado.getClass() == Integer.class) {
                        System.out.println("Campo correcto");
                        if(nombreCampoString.equals("idrol")){
                            if(objectDTOClass == ClienteDTO.class){
                                //setear siempre en 1
                                ((ClienteDTO) objectDTO).setIdrol(1);
                                System.out.println(objectDTO);
                            }
                        }
                    } else {
                        String campoEntregadoString = (String) campoEntregado;
                        return "El valor del campo " + campoEntregadoString + " debe ser un numero entero";
                    }

                }
            }
            //Retorna respuesta exitosa, al haber pasado por las validaciones de los campos.
            return "Validaciones exitosas";
        }catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
                return "Error al acceder a uno de los campos "+e.getMessage();
        }

        }
    }
