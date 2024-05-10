package com.api.proyecto_enel.service;
import com.api.proyecto_enel.model.DTO.ClienteDTO;
import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.repository.IClienteRepository;
import com.api.proyecto_enel.util.RutValidation;
import com.api.proyecto_enel.util.UtilConversion;
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

    public ResponseEntityDTO saveCliente(String json) {
        // Acceder a los campos del JSON
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String fieldName = entry.getKey();
                JsonNode fieldValue = entry.getValue();

                // Validar y verificar por tipo de dato:

                //campos tipo String
                if (fieldValue.isTextual()) {
                    String value = fieldValue.asText();
                    if (value.isEmpty()) {
                        return new ResponseEntityDTO("El campo '" + fieldName + "' no puede estar vacío", "400");
                    } else{
                        //return new ResponseEntityDTO("hols")
                        System.out.println("El campo '" + fieldName + "' es un string: " + value);
                    }
                }
                //Otros tipos de datos
                else {
                    // Lógica para otros tipos de datos
                }
            }
            // Si todas las validaciones pasan, devolver una respuesta exitosa
            return new ResponseEntityDTO("Cliente guardado correctamente", "200");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntityDTO("Error al procesar el JSON: " + e.getMessage(), "500");
        }
    }
}

                //validación y verificación por tipo de dato.
       //   if (fieldValue.isTextual()) {
        //            String value = fieldValue.asText();
          //          if (value.isEmpty()) {
            //            String mensajeError = "El campo '" + fieldName + "' no puede estar vacío";
         //               return new ResponseEntityDTO(mensajeError, "400");
           //             System.out.println("El campo '" + fieldName + "' no puede estar vacío");
             //       } else {
               //         System.out.println("El campo '" + fieldName + "' es un string: " + value);
                 //   }
   //             } else if (fieldValue.isNumber()) {
     //               int value = fieldValue.asInt();
       //             System.out.println("El campo '" + fieldName + "' es un número: " + value);
         //       }else {
           //         System.out.println("El campo '" + fieldName + "' tiene un tipo de dato no soportado");
             //   }
      //      });
   //     } catch (Exception e) {
     //       e.printStackTrace();
    //    }
   // }
//}
//if(nombreNode != null && nombreNode.isTextual()){
              //  String nombre = jsonNode.get("nombreCli").asText();
              //  if(nombre.isEmpty()){
                //    return new ResponseEntityDTO("El campo nombre no puede ser un valor vacio", "400");
             //   }else{
               //     return new ResponseEntityDTO("Bienvenido cliente "+nombre, "200");
             //   }
         //   }else{
           //     return new ResponseEntityDTO("El campo nombre es requerido y debe ser de tipo texto", "400");
           // }
       // }catch(JsonParseException e){
         //   e.printStackTrace();
           // return new ResponseEntityDTO("El tipo de dato no es reconocido. Se espera un tipo de dato String" , "400");
     //   }catch(Exception e){
       //     e.printStackTrace();
         //   return new ResponseEntityDTO("Error al guardar el cliente: "+e , "400");
            //+e.getMessage();
     //   }
//    }
//}
      //
    //}
//}
        //Validaciones campos nulos estan en clienteDTO.

       // if (clienteDTO == null) {
         //   return new ResponseEntityDTO("El cliente no puede ser nulo", "400");
        //}

       // if (clienteDTO.getIdRol() != null) {
         //   try {
           //     String cadena = clienteDTO.getIdRol().toString();
             //   Integer.parseInt(cadena);
               // if (cadena.matches("[0-9]+")) {
                 //   if (clienteDTO.getIdRol() != 2) {
                   //     return new ResponseEntityDTO("El cliente no puede ser distinto de 2", "400");
               //     }
                 //   return new ResponseEntityDTO("El cliente se encuentra registrado", "400");
              //  } else {
                //    return new ResponseEntityDTO("El cliente no es letra", "400");
       //         }
         //   } catch (Exception e) {
           //     return new ResponseEntityDTO("El cliente no es num", "400");
         //   }
       // }


        //Se cae con esto, validar esto:  "idRol": -1000000932842398,Numeric value (42398234324) out of range of int (-2147483648 - 2147483647)
        //Se cae con esto, validar esto: a o "a" o true, etc.
        //en si, validar tipos de datos y rangos

        //}catch(Exception e){
        //  return new ResponseEntityDTO("El campo rol debe ser un entero", "400");
        //}

 //       if (clienteDTO.getNombreCli() == null || clienteDTO.getNombreCli().isEmpty()) {
   //         return new ResponseEntityDTO("El nombre del cliente no puede ser nulo o vacio", "400");
     //   }
       // if (clienteDTO.getApellidoCli() == null || clienteDTO.getApellidoCli().isEmpty()) {
         //   return new ResponseEntityDTO("El apellido del cliente no puede ser nulo o vacio", "400");
 //       }
  //      if (clienteDTO.getCorreoCli() == null || clienteDTO.getCorreoCli().isEmpty()) {
   //         return new ResponseEntityDTO("El correo del cliente no puede ser nulo o vacio", "400");
    //    }
     //   if (clienteDTO.getClaveCli() == null || clienteDTO.getClaveCli().isEmpty()) {
      //      return new ResponseEntityDTO("La clave del cliente no puede ser nulo o vacio", "400");
      //  }
      //  if (clienteDTO.getRutCli() == null || clienteDTO.getRutCli().isEmpty()) {
      //      return new ResponseEntityDTO("El rut del cliente no puede ser nulo o vacio", "400");
      //  }
        //if (clienteDTO.getCelularCli() == null || clienteDTO.getCelularCli().isEmpty()) {
        //    return new ResponseEntityDTO("El celular del cliente no puede ser nulo o vacio", "400");
       // }
      //  if (clienteDTO.getDireccionCli() == null || clienteDTO.getDireccionCli().isEmpty()) {
        //    return new ResponseEntityDTO("La direccion del cliente no puede ser nulo o vacio", "400");
      //  }

        //Validaciones Constraints:
   //     if (!(clienteDTO.getId() instanceof Integer)) {
       //     return new ResponseEntityDTO("El id del cliente debe ser entero", "400");
     //   }
    //    if (!RutValidation.validacionRut(clienteDTO.getRutCli())) {
      //      return new ResponseEntityDTO("El rut del cliente no es valido", "400");
   //     }
     //   return new ResponseEntityDTO("jola", "400");
   // }}


        //try{
            //Convierte ClienteDTO en entidad Cliente.
            //Cliente cliente = UtilConversion.toCliente(clienteDTO);
          //  clienteRepository.save(cliente);
            //return new ResponseEntityDTO("El cliente se ha creado exitosamente", "200");

        //}catch(Exception e){
           // if (e.getMessage().contains("cliente_correo_cli_key")) {
                //String mensaje = "El correo electrónico ya está registrado";
              //  return new ResponseEntityDTO(mensaje, "400");

            //} else if (e.getMessage().contains("cliente_rut_cli_key")) {
               // String mensaje = "El rut ya está registrado";
             //   return new ResponseEntityDTO(mensaje, "400");
           // }else{
             //   String mensaje = "Error al guardar el cliente desde SERVICE";
           //     return new ResponseEntityDTO(mensaje+e, "400");
         //   }
       // }


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