package com.api.proyecto_enel.service;
import com.api.proyecto_enel.model.DTO.ClienteDTO;
import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.repository.IClienteRepository;
import com.api.proyecto_enel.util.RutValidation;
import com.api.proyecto_enel.util.StringValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.api.proyecto_enel.util.StringValidation.*;

@Service
public class ClienteService {

    //inyectando instancia de repositorio de cliente.
    @Autowired
    IClienteRepository clienteRepository;



    //Obtiene una lista de todos los clientes de la base de datos
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    //Obtiene un cliente por el ID.
    //0ptional permite manejar los valores nulos de la busqueda
    public Optional<Cliente> getClienteById(Integer id_cliente) {
        return clienteRepository.findById(id_cliente);
    }

    //Obtiene un cliente por el Rut
    public Optional<Cliente> getClienteByRut(String rut_cliente) {
        return clienteRepository.findClienteByRutCli(rut_cliente);
    }

    //Obtiene un cliente por el Correo
    public Optional<Cliente> getClienteByCorreo(String correo_cliente) {
        return clienteRepository.findClienteByCorreoCli(correo_cliente);
    }
    //falta verificar rango (por ejemplo, edad desde 18 a 150 años) y longitud del campo (en nombres y contrasenas)
    public ResponseEntityDTO saveCliente(ClienteDTO clienteDTO) {
        try {
            //se obtiene la clase de clienteDTO y sus campos.
            Class<?> clienteDTOClass = clienteDTO.getClass();
            Field[] clienteDTOFields = clienteDTOClass.getDeclaredFields();

            //se recorren los campos de clienteDTO para realizar validaciones.
            for (Field f : clienteDTOFields) {
                f.setAccessible(true); //hace accesible cada campo de clienteDTO.

                System.out.print("Field name:\t" + f.getName() + "\t"); // el nombre del campo


                try {
                    Class<?> fclassType = f.getType(); // el tipo de dato esperado del campo.
                    System.out.print("class type:\t" + fclassType + "\t");

                    // Valida tipos de datos String
                    if (fclassType == String.class) {
                        Object obj = f.get(clienteDTO); // el valor del campo
                        if (obj instanceof String) { // Verificar si obj es una instancia de String
                            String text = (String) obj; // Convertir obj a String

                            // Verificar si la cadena es nula o vacía
                            if (text == null || text.isEmpty()) {
                                System.out.println("La cadena es nula o vacía.");
                            } else {

                                //Validacion nombre y apellido
                                if(f.getName() == "nombreCli" || f.getName() == "apellidoCli"){
                                    Boolean result = StringValidation.IsOnlyAlphabetic(text);
                                    if (result == false) {
                                        System.out.println("la cadena debe tener solo letras");
                                    }
                                }
                                //validacion de Rut
                                else if(f.getName() == "rutCli"){
                                    Boolean result = RutValidation.validacionModule11(text);
                                    System.out.println("rut es igual:  "+result);
                                    if(result == false){
                                        System.out.println("el rut no es valido");
                                    }else{
                                        System.out.println("el rut es valido");
                                    }
                                }
                            }
                        }
                    }

                    //Valida tipos de datos Integer
                    else if (fclassType == Integer.class){
                        Object obj = f.get(clienteDTO); // el valor del campo
                        if (obj instanceof Integer) {
                            System.out.println("el valor SI es entero");
                        }else{
                            System.out.println("el valor NO es entero");
                        } // Verificar si obj es una instancia de String
                    }

                        Object obj = f.get(clienteDTO); // el valor del campo
                    System.out.println("\tvalue:\t" + obj);

                    //cae aca si no pudo acceder al campo
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

            return new ResponseEntityDTO("holi desde try service", "400");
        }catch(Exception e){
            return new ResponseEntityDTO("mal", "400");}
    }
}