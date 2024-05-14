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

    public ResponseEntityDTO saveCliente(ClienteDTO clienteDTO) {
        try {
            //Itera y valida los campos del cliente enviado en la peticion.
            String respuestaIteracion = IterateObject.IterateObjectDTO(clienteDTO);
            if (respuestaIteracion.equals("Validaciones exitosas")) {
                //guarda cliente
                try{
                    Cliente cliente = UtilConversion.toCliente(clienteDTO);
                    clienteRepository.save(cliente);
                    return new ResponseEntityDTO("Su usuario ha sido registrado correctamente", "200");
                }catch(Exception e){
                    System.out.println("Error: "+e.getMessage());
                    return new ResponseEntityDTO("Error en la conversion de DTO a entity", "400");
                }
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



