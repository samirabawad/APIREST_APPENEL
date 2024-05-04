package com.api.proyecto_enel.controller;

import com.api.proyecto_enel.model.DTO.AdminDTO;
import com.api.proyecto_enel.model.DTO.EmpresaDTO;
import com.api.proyecto_enel.model.entity.Admin;
import com.api.proyecto_enel.model.entity.Empresa;
import com.api.proyecto_enel.service.AdminService;
import com.api.proyecto_enel.service.EmpresaService;
import com.api.proyecto_enel.util.UtilConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="api/v1/admins")
public class AdminController {
    //Inyeccion de la instancia de IAdminRepository en el controlador

    @Autowired
    private AdminService adminService;

    //Obtiene todos los Admins
    //stream() crea una secuencia de elementos para ser procesados de manera secuencial.
    //map() convierte cada elemento DTO a entidad
    //collect() convierte el resultado en una lista.
    @GetMapping
    public List<AdminDTO> getAllAdmins() {
        List<Admin> admins = adminService.getAdmins();
        return admins.stream()
                .map(UtilConversion::fromAdmin)
                .collect(Collectors.toList());
    }
    //Obtiene un admin por el Rut.
    //0ptional permite manejar los valores nulos de la busqueda.
    //Devuelve el valor en DTO.
    @GetMapping("getAdminId/{id_admin}")
    public AdminDTO getAdminById(@PathVariable("id_admin") Integer id_admin) {
        Optional<Admin> admin = adminService.getAdminById(id_admin);
        return admin.map(UtilConversion::fromAdmin).orElse(null);
    }

    //Obtiene un admin por el Rut.
    @GetMapping("getAdminRut/{rut_admin}")
    public AdminDTO getAdminByRut(@PathVariable("rut_admin") String rut_admin) {
        Optional<Admin> admin = adminService.getAdminByRut(rut_admin);
        return admin.map(UtilConversion::fromAdmin).orElse(null);
    }

    //Obtiene un admin por el Correo.
    @GetMapping("getAdminCorreo/{correo_admin}")
    public AdminDTO getAdminByCorreo(@PathVariable("correo_admin") String correo_admin){
        Optional<Admin> admin = adminService.getAdminByCorreo(correo_admin);
        return admin.map(UtilConversion::fromAdmin).orElse(null);
    }

}
