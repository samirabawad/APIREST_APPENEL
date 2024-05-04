package com.api.proyecto_enel.service;
import com.api.proyecto_enel.model.DTO.ClienteDTO;
import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.repository.IClienteRepository;
import com.api.proyecto_enel.util.RutValidation;
import com.api.proyecto_enel.util.UtilConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.api.proyecto_enel.util.UtilConversion;

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

    public ResponseEntityDTO saveCliente(ClienteDTO clienteDTO) {
        //Validaciones campos nulos estan en clienteDTO.
        if(clienteDTO == null){
            return new ResponseEntityDTO("El cliente no puede ser nulo", "400");
        }
        if (clienteDTO.getIdRol() == null) {
            return new ResponseEntityDTO("El campo rol no puede ser nulo o distinto de cliente", "400");
        }
        if (clienteDTO.getNombreCli() == null || clienteDTO.getNombreCli().isEmpty()) {
            return new ResponseEntityDTO("El nombre del cliente no puede ser nulo o vacio", "400");
        }
        if (clienteDTO.getApellidoCli() == null || clienteDTO.getApellidoCli().isEmpty()) {
            return new ResponseEntityDTO("El apellido del cliente no puede ser nulo o vacio", "400");
        }
        if (clienteDTO.getCorreoCli() == null || clienteDTO.getCorreoCli().isEmpty()) {
            return new ResponseEntityDTO("El correo del cliente no puede ser nulo o vacio", "400");
        }
        if (clienteDTO.getClaveCli() == null || clienteDTO.getClaveCli().isEmpty()) {
            return new ResponseEntityDTO("La clave del cliente no puede ser nulo o vacio", "400");
        }
        if (clienteDTO.getRutCli() == null || clienteDTO.getRutCli().isEmpty()) {
            return new ResponseEntityDTO("El rut del cliente no puede ser nulo o vacio", "400");
        }
        if (clienteDTO.getCelularCli() == null || clienteDTO.getCelularCli().isEmpty()) {
            return new ResponseEntityDTO("El celular del cliente no puede ser nulo o vacio", "400");
        }
        if (clienteDTO.getDireccionCli() == null || clienteDTO.getDireccionCli().isEmpty()) {
            return new ResponseEntityDTO("La direccion del cliente no puede ser nulo o vacio", "400");
        }

        //Validaciones Constraints:
        if (!(clienteDTO.getId() instanceof Integer)) {
            return new ResponseEntityDTO("El id del cliente debe ser entero", "400");
        }
        if (clienteDTO.getIdRol() != 2) {
            return new ResponseEntityDTO("El rol del cliente debe ser un numero 2", "400");
        }
        if (!RutValidation.validacionRut(clienteDTO.getRutCli())) {
            return new ResponseEntityDTO("El rut del cliente no es valido", "400");
        }
        try{
            //Convierte ClienteDTO en entidad Cliente.
            Cliente cliente = UtilConversion.toCliente(clienteDTO);
            clienteRepository.save(cliente);
            return new ResponseEntityDTO("El cliente se ha creado exitosamente", "200");

        }catch(Exception e){
            if (e.getMessage().contains("cliente_correo_cli_key")) {
                String mensaje = "El correo electrónico ya está registrado";
                return new ResponseEntityDTO(mensaje, "400");

            } else if (e.getMessage().contains("cliente_rut_cli_key")) {
                String mensaje = "El rut ya está registrado";
                return new ResponseEntityDTO(mensaje, "400");
            }else{
                String mensaje = "Error al guardar el cliente desde bd";
                return new ResponseEntityDTO(mensaje+e, "400");
            }
        }
  }
}

      //      try{
        //        //save
         //   }catch(RuntimeException e){
           //     if (e.getMessage().contains("El correo electrónico ya está registrado")) {
             //       String mensaje = "El correo electrónico ya está registrado";
               //     return mensaje;
       //         } else if (e.getMessage().contains("El rut ya está registrado")) {
         //           String mensaje = "El rut ya está registrado";
           //         return mensaje;
             //   }else{
               //     String mensaje = "Error al guardar el cliente";
                 //   return mensaje;
               // }
  //          }
    //    }

        //poner validacion de rut con modulo 11. validar tipos de datos, por ejemplo, que en el id sea un numero
        //clienteRepository.save(cliente);
    //}

   // private boolean validarEmail(String email) {
    //   if (email == null || email.isEmpty()) {
      //      return false;
       // }
       // return true;
   // }

 //   public void deleteCliente(Integer id) {
   //     clienteRepository.deleteById(id);

    //}
//}