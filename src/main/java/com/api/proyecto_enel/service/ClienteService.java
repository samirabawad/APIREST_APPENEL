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
    //Inyecta instancia repositorioCliente
    @Autowired
    IClienteRepository clienteRepository;

    //Obtiene todos los clientes
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    //Obtiene cliente por ID. Si no existe, se maneja el nulo con Optional
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
            //Itera y valida los campos del cliente enviado en la peticion.
            String respuestaIteracion = IterateObject.IterateObjectDTO(clienteDTO);
            System.out.println("IDROL DE SAVECLIENTE: "+clienteDTO.getIdrol());
            if (respuestaIteracion.equals("Validaciones exitosas")) {
                //guarda cliente
                return new ResponseEntityDTO("Se guardara el cliente", "200");
            } else {
                //Captura errores de validacion.
                return new ResponseEntityDTO("Error al registrar el cliente. " + respuestaIteracion, "400");
            }

            //Captura cualquier excepcion al iterar el cliente de la peticion.
        } catch (Exception e) {
            return new ResponseEntityDTO("Error al datos del registro.  Compruebe que el nombre de los campos y los valores sean los correctos", "400");
        }
    }
}



