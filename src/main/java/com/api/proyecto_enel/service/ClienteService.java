package com.api.proyecto_enel.service;
import com.api.proyecto_enel.model.DTO.ClienteDTO;
import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.repository.IClienteRepository;
import com.api.proyecto_enel.util.RutValidation;
import com.api.proyecto_enel.util.UtilConversion;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import springfox.documentation.spring.web.json.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

import com.api.proyecto_enel.util.UtilConversion;

import static org.apache.logging.log4j.ThreadContext.isEmpty;


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

    // Acceder a los campos del JSON
    //String nombre = jsonNode.get("nombreCli").asText();
    //if(Objects.equals(nombre, "juan")){
    //  return "bad nombre"+nombre;
    //}else{
    //  return "nombre a guardar"+nombre;
    //}
    // Cliente cliente = UtilConversion.toCliente(clienteDTO);
    //   ResponseEntityDTO clienteSAVED = clienteService.saveCliente(clienteDTO);
    // clienteService.saveCliente(clienteDTO);
    //return new ResponseEntityDTO("cliente guardado", "200");
    // Manejador de excepciones para capturar errores de validación
    // return "nombre"+clienteDTO.getNombreCli();

    public ResponseEntityDTO saveCliente(ClienteDTO clienteDTO) {
        try {
            //se obtiene la clase y sus campos.
            Class<?> clienteDTOClass = clienteDTO.getClass();
            Field[] clienteDTOFields = clienteDTOClass.getDeclaredFields();
            for (Field f : clienteDTOFields) {
                f.setAccessible(true); //hace accesible cada campo de clienteDTO.
                System.out.print("Field name:\t" + f.getName() + "\t"); // nombre del campo/atributo
                try {
                    Class<?> fclassType = f.getType(); // la clase del campo
                    System.out.print("class type:\t" + fclassType + "\t");
                    Object obj = f.get(clienteDTO); // el valor del campo
                    System.out.println("\tvalue:\t" + obj);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

            return new ResponseEntityDTO("holi desde try service", "400");
        }catch(Exception e){
            return new ResponseEntityDTO("mal", "400");}
    }
}