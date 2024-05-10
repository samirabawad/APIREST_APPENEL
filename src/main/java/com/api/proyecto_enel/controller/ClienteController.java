package com.api.proyecto_enel.controller;
import com.api.proyecto_enel.model.DTO.ClienteDTO;
import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.service.ClienteService;
import com.api.proyecto_enel.util.UtilConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.spring.web.json.Json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/v1/clientes")
public class ClienteController {
    //Inyeccion de la instancia de IClienteRepository en el controlador
    @Autowired
    private ClienteService clienteService;

    //stream() crea una secuencia de elementos para ser procesados de manera secuencial.
    //map() convierte cada elemento DTO a entidad
    //collect() convierte el resultado en una lista.
    @GetMapping
    public List<ClienteDTO> getAllClientes() {
        List<Cliente> clientes = clienteService.getClientes();
        return clientes.stream()
                .map(UtilConversion::fromCliente)
                .collect(Collectors.toList());
    }

    //Obtiene un cliente por el ID.
    //0ptional permite manejar los valores nulos de la busqueda.
    //Devuelve el valor en DTO.
    @GetMapping("getClienteId/{id_cliente}")
    public ClienteDTO getClienteById(@PathVariable("id_cliente") Integer id_cliente) {
        Optional<Cliente> cliente = clienteService.getClienteById(id_cliente);
        return cliente.map(UtilConversion::fromCliente).orElse(null);
    }

    //Obtiene un cliente por el Rut.
    @GetMapping("getClienteRut/{rut_cliente}")
    public ClienteDTO getClienteByRut(@PathVariable("rut_cliente") String rut_cliente) {
        Optional<Cliente> cliente = clienteService.getClienteByRut(rut_cliente);
        return cliente.map(UtilConversion::fromCliente).orElse(null);
    }

    //Obtiene un cliente por correo.
    @GetMapping("getClienteCorreo/{correo_cliente}")
    public ClienteDTO getClienteByCorreo(@PathVariable("correo_cliente") String correo_cliente) {
        Optional<Cliente> cliente = clienteService.getClienteByCorreo(correo_cliente);
        return cliente.map(UtilConversion::fromCliente).orElse(null);
    }

    @PostMapping("/crear")
    public ResponseEntityDTO saveCliente(@RequestBody String json) {
        try {
            ResponseEntityDTO cliente = clienteService.saveCliente(json);
            return cliente;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            //+ex.getMessage();
            return new ResponseEntityDTO("Se ha producido un error al intentar crear el cliente, intente mas tarde"+ex, "400");
        }
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationExceptions(MethodArgumentNotValidException ex) {
        return "excepcion"+ ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    }
}
