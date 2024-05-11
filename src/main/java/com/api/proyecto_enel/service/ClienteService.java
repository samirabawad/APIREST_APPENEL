package com.api.proyecto_enel.service;
import com.api.proyecto_enel.model.DTO.ClienteDTO;
import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.repository.IClienteRepository;
import com.api.proyecto_enel.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.proyecto_enel.util.ValidacionPorCampo.*;

import java.lang.reflect.Field;
import java.util.*;

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

                    Object nombreCampo = f.getName();
                    String nombreCampoString = (String) nombreCampo;

                    Object campoEntregado = f.get(clienteDTO);


                    // Validacion de campos declarados como String
                    if (fclassType == String.class) {
                        //Obtiene y valida valor entregado del campo.
                        if (campoEntregado instanceof String) {
                            String campoEntregadoString = (String) campoEntregado;
                            Boolean campoNuloOrEmpty = StringValidation.IsEmptyOrNull(campoEntregadoString);

                            if(campoNuloOrEmpty==true){
                                System.out.println("Campo nulo o vacio");
                            }else{
                                Boolean campoValidado = ValidacionPorCampo.validacionPorCampo(nombreCampoString, campoEntregadoString);
                                if(campoValidado==true){
                                    System.out.println("Campo correcto");
                                }else{
                                    System.out.println("Campo incorrecto");
                                }
                            }
                        }else{
                            System.out.println("Campo entregado debe ser String");
                        }

                        // Validacion de campos declarados como Integer
                    }else if (fclassType == Integer.class) {
                        if (campoEntregado instanceof Integer) {
                            System.out.println("el valor SI es entero");
                        }else{
                            System.out.println("el valor NO es entero");
                        }
                    }
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