package com.api.proyecto_enel.service;
import com.api.proyecto_enel.model.DTO.ClienteDTO;
import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.repository.IClienteRepository;
import com.api.proyecto_enel.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  //  public Optional<Cliente> getClienteByRut(String rut_cliente) {
    //    return clienteRepository.findByRut_cliente(rut_cliente);
   // }

    //Obtiene un cliente por el Correo
   // public Optional<Cliente> getClienteByCorreo(String correo_cliente) {
     //   return clienteRepository.findClienteByCorreo_cliente(correo_cliente);
   // }
    //falta verificar rango (por ejemplo, edad desde 18 a 150 años) y longitud del campo (en nombres y contrasenas)


    public ResponseEntityDTO saveCliente(ClienteDTO clienteDTO) {
        try {
            //se obtiene la clase de clienteDTO y sus campos.
            Class<?> clienteDTOClass = clienteDTO.getClass();
            Field[] clienteDTOFields = clienteDTOClass.getDeclaredFields();

            //se recorren los campos de clienteDTO para realizar validaciones.
            for (Field f : clienteDTOFields) {
                f.setAccessible(true); //hace accesible cada campo de clienteDTO.

                //imprimiendo por consola
                System.out.print("Field name:\t" + f.getName() + "\t"); //el nombre del campo en DTO: "rut", "nombre", etc.


                try {
                    Class<?> fclassType = f.getType(); // obtiene el tipo de dato de DTO: String, Integer, etc.
                    System.out.print("class type:\t" + fclassType + "\t");

                    Object nombreCampo = f.getName(); //el nombre del campo en DTO: "rut", "nombre", etc. checkeado
                    String nombreCampoString = (String) nombreCampo;

                    Object campoEntregado = f.get(clienteDTO); //este es el valor del campo entregado por el usuario

                    System.out.print("valor entregado:\t" +campoEntregado + "\t");

                    System.out.println("fclasstuype: "+fclassType);


                    // Validacion de campos declarados como String desde DTO
                    if (fclassType == String.class) {
                        //Obtiene y valida valor entregado del campo.
                        if (campoEntregado.getClass() == String.class) {
                            String campoEntregadoString = (String) campoEntregado;
                            Boolean campoNuloOrEmpty = StringValidation.IsEmptyOrNull(campoEntregadoString);

                            if(campoNuloOrEmpty==true){
                                return new ResponseEntityDTO("El campo "+nombreCampoString+" no puede ser vacio o nulo", "400");
                            }else{
                                String campoValidado = ValidacionPorCampo.validacionPorCampo(nombreCampoString, campoEntregadoString);
                                if(campoValidado=="Campo correcto"){
                                    System.out.println("Campo correcto");
                                }else{
                                    return new ResponseEntityDTO("Error al guardar cliente. "+campoValidado, "400");
                                }
                            }
                        }else{
                            return new ResponseEntityDTO("El campo "+nombreCampoString+" debe ser un string", "400");
                        }

                        // Validacion de campos declarados como Integer
                    }else if (fclassType == Integer.class) {
                        if (campoEntregado.getClass() == Integer.class) {
                            System.out.println("Campo correcto");
                        }else{
                            String campoEntregadoString = (String) campoEntregado;
                            return new ResponseEntityDTO("El valor del campo "+campoEntregadoString+" debe ser un numero entero", "400");
                        }
                    }
                    //cae aca si no pudo acceder al campo
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                    return new ResponseEntityDTO("Error con el campo del cliente. "+e.getMessage(), "400");
                }

            }
            return new ResponseEntityDTO("Cliente guardado", "200");

        }catch(Exception e){
            return new ResponseEntityDTO("El nombre del campo enviado, no coincide con ninguno de nuestra app", "400");}
    }
}