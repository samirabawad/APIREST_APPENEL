package com.api.proyecto_enel.controller;

import com.api.proyecto_enel.model.DTO.AdminDTO;
import com.api.proyecto_enel.model.DTO.ClienteDTO;
import com.api.proyecto_enel.model.DTO.EmpresaDTO;
import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;
import com.api.proyecto_enel.model.entity.Admin;
import com.api.proyecto_enel.model.entity.Empresa;
import com.api.proyecto_enel.service.AdminService;
import com.api.proyecto_enel.service.ClienteService;
import com.api.proyecto_enel.service.EmpresaService;
import com.api.proyecto_enel.util.UtilConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class AdminController {
    //Inyeccion de la instancia de IAdminRepository en el controlador

    @Autowired
    private AdminService adminService;

    //obtiene a todos los clientes
    //stream() crea una secuencia de elementos para ser procesados de manera secuencial.
    //map() convierte cada elemento DTO a entidad
    //collect() convierte el resultado en una lista.
    @GetMapping("all/clientes")
    public List<ClienteDTO> getAllClientes() {
        return adminService.getClientes();
    }

    //Obtiene todos los Admins
    //stream() crea una secuencia de elementos para ser procesados de manera secuencial.
    //map() convierte cada elemento DTO a entidad
    //collect() convierte el resultado en una lista.
    @GetMapping("all/admins")
    public List<AdminDTO> getAllAdmins() {
        List<Admin> admins = adminService.getAdmins();
        return admins.stream()
                .map(UtilConversion::fromAdmin)
                .collect(Collectors.toList());
    }
    //Obtiene todas las empresas
    //stream() crea una secuencia de elementos para ser procesados de manera secuencial.
    //map() convierte cada elemento DTO a entidad
    //collect() convierte el resultado en una lista.
    @GetMapping("all/empresas")
    public List<EmpresaDTO> getAllEmpresas() {
        return adminService.getEmpresas();
    }


    //envia correo electronico para la recuperacion de contrasena.
    //Recibe correo del cliente mediante URL, ejemplo: http://localhost:8080/api/v1/admins/getAdminCorreo/2@gmail.cl
    @GetMapping("admins/getAdminCorreo/{correo_admin}")
    public ResponseEntityDTO getAdminCorreo(@PathVariable("correo_admin") String correo_admin) {
        ResponseEntityDTO admin = adminService.getAdminByCorreo(correo_admin);
        return admin;
    }
    //envia SMS al celular para la recuperacion de contrasena.
    //Recibe correo del cliente mediante URL, ejemplo: http://localhost:8080/api/v1/admins/getAdminCelular/99348741
    @GetMapping("admins/getAdminCelular/{celular_admin}")
    public ResponseEntityDTO getAdminCelular(@PathVariable("celular_admin") String celular_admin) {
        ResponseEntityDTO admin = adminService.getAdminByCelular(celular_admin);
        return admin;
    }

    //Registra un cliente en la bd
    @PostMapping("/registro/admin")
    public ResponseEntityDTO saveCliente(@Valid @RequestBody AdminDTO adminDTO, BindingResult resultado) {
        ResponseEntityDTO admin = adminService.saveAdmin(adminDTO);
        if(resultado.hasErrors()){
            return new ResponseEntityDTO("Ocurrió un error al intentar registrar el admin", "400");
        }
        return admin;
    }

}
