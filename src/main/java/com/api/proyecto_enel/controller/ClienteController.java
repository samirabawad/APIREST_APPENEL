package com.api.proyecto_enel.controller;
import com.api.proyecto_enel.model.DTO.ClienteDTO;
import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.service.ClienteService;
import com.api.proyecto_enel.util.UtilConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="clientes")
public class ClienteController {
    //Inyeccion de la instancia de IClienteRepository en el controlador
    @Autowired
    private ClienteService clienteService;

    //obtiene a todos los clientes
    //stream() crea una secuencia de elementos para ser procesados de manera secuencial.
    //map() convierte cada elemento DTO a entidad
    //collect() convierte el resultado en una lista.
    @GetMapping
    public List<ClienteDTO> getAllClientes() {
        return clienteService.getClientes();
    }

    //envia correo electronico para la recuperacion de contrasena.
    //Recibe correo del cliente mediante URL, ejemplo: http://localhost:8080/api/v1/clientes/getClienteCorreo/2@gmail.cl
    @GetMapping("/getClienteCorreo/{correo_cliente}")
    public ResponseEntityDTO getClienteCorreo(@PathVariable("correo_cliente") String correo_cliente) {
            ResponseEntityDTO cliente = clienteService.getClienteByCorreo(correo_cliente);
            return cliente;
    }
    //envia SMS al celular para la recuperacion de contrasena.
    //Recibe correo del cliente mediante URL, ejemplo: http://localhost:8080/api/v1/clientes/getClienteCelular/99348741
    @GetMapping("/getClienteCelular/{celular_cliente}")
    public ResponseEntityDTO getClienteCelular(@PathVariable("celular_cliente") String celular_cliente) {
        ResponseEntityDTO cliente = clienteService.getClienteByCelular(celular_cliente);
        return cliente;
    }

    //Registra un cliente en la bd
    @PostMapping("/registro/cliente")
    public ResponseEntityDTO saveCliente(@Valid @RequestBody ClienteDTO clienteDTO, BindingResult resultado) {
            ResponseEntityDTO cliente = clienteService.saveCliente(clienteDTO);
            if(resultado.hasErrors()){
                return new ResponseEntityDTO("Ocurrió un error al intentar registrar el cliente", "400");
            }
            return cliente;
    }
}
