package com.api.proyecto_enel.service;

import com.api.proyecto_enel.model.entity.Admin;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.repository.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    //inyectando instancia de repositorio de admin.
    @Autowired
    IAdminRepository adminRepository;

    //Obtiene una lista de todos los admins de la base de datos
    public List<Admin> getAdmins(){
        return adminRepository.findAll();
    }

    //Obtiene un admin por el ID.
    //0ptional permite manejar los valores nulos de la busqueda.
    //Devuelve el valor en DTO.
    public Optional<Admin> getAdminById(Integer id_admin) {
        return adminRepository.findById(id_admin);
    }

    //Obtiene un admin por el Rut.
    public Optional<Admin> getAdminByRut(String rut_admin){
        return adminRepository.findAdminByRutAdmin(rut_admin);
    }

    //Obtiene un admin por el Correo.
    public Optional<Admin> getAdminByCorreo(String correo_admin){
        return adminRepository.findAdminBycorreoAdmin(correo_admin);
    }
}
