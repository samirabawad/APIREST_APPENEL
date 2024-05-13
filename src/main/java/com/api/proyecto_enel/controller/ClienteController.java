package com.api.proyecto_enel.controller;
import com.api.proyecto_enel.model.DTO.ClienteDTO;
import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.service.ClienteService;
import com.api.proyecto_enel.util.UtilConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.util.*;
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
  //  @GetMapping("getClienteRut/{rut_cliente}")
    //public ClienteDTO getClienteByRut(@PathVariable("rut_cliente") String rut_cliente) {
      //  Optional<Cliente> cliente = clienteService.getClienteByRut(rut_cliente);
  //      return cliente.map(UtilConversion::fromCliente).orElse(null);
  //  }

    //Obtiene un cliente por correo.
  //  @GetMapping("getClienteCorreo/{correo_cliente}")
    //public ClienteDTO getClienteByCorreo(@PathVariable("correo_cliente") String correo_cliente) {
      //  Optional<Cliente> cliente = clienteService.getClienteByCorreo(correo_cliente);
   //     return cliente.map(UtilConversion::fromCliente).orElse(null);
    //}

    //crea un cliente
    @PostMapping("/crear")
    public ResponseEntityDTO saveCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
            ResponseEntityDTO cliente = clienteService.saveCliente(clienteDTO);
            return cliente;
    }
}
