package com.api.proyecto_enel.service;
import com.api.proyecto_enel.model.DTO.ClienteDTO;
import com.api.proyecto_enel.model.DTO.EmpresaDTO;
import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.model.entity.Empresa;
import com.api.proyecto_enel.repository.IClienteRepository;
import com.api.proyecto_enel.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    //Inyecta instancia repositorioCliente
    @Autowired
    IClienteRepository clienteRepository;

    @Autowired
    PasswordEncoder passwordEncoder;




    //Entrega una lista de todas las empresas
    public List<ClienteDTO> getClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(UtilConversion::fromCliente)
                .collect(Collectors.toList());

    }

    //Obtiene cliente por ID. Si no existe, se maneja el nulo con Optional
    public Optional<Cliente> getClienteById(Integer id_cliente) {
        return clienteRepository.findById(id_cliente);
    }

    public ResponseEntityDTO getClienteByCorreo(String correo_cliente) {
        Optional<Cliente> cliente = clienteRepository.findByCorreoCliente(correo_cliente);
        System.out.println(cliente.isPresent());
        System.out.println(cliente);
        if (cliente.isPresent()) {
            return new ResponseEntityDTO("Se ha enviado un correo electronico para el cambio de su clave", "200");
        }else{
            return new ResponseEntityDTO("El correo electronico no se encuentra registrado", "400");
        }
    }

    public ResponseEntityDTO getClienteByCelular(String celular_cliente) {
        Optional<Cliente> cliente = clienteRepository.findByCelularCliente(celular_cliente);
        System.out.println(cliente.isPresent());
        System.out.println(cliente);
        if(cliente.isPresent()) {
            return new ResponseEntityDTO("Se ha enviado un SMS para el cambio de su clave", "200");
        }else{
            return new ResponseEntityDTO("El celular no se encuentra registrado", "400");
        }
    }

    public ResponseEntityDTO saveCliente(ClienteDTO clienteDTO) {
        try {
            //Itera y valida los campos del cliente enviado en la peticion.
            String respuestaIteracion = IterateObject.IterateObjectDTO(clienteDTO);
            if (respuestaIteracion.equals("Validaciones exitosas")) {
                //guarda cliente
                try{
                    //String claveHash = HashingPassword.hashPassword(clienteDTO.getClave());
                    //if (claveHash.equals("NO HASH")){
                       // return new ResponseEntityDTO("Ocurrió un error al guardar su clave", "200");
                    //}else{
                        //clienteDTO.setClave(claveHash);
                    String hashedPassword = passwordEncoder.encode(clienteDTO.getClave());
                    clienteDTO.setClave(hashedPassword);
                    Cliente cliente = UtilConversion.toCliente(clienteDTO);
                        clienteRepository.save(cliente);
                        return new ResponseEntityDTO("Su usuario ha sido registrado correctamente", "200");
            //        }
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



