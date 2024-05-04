package com.api.proyecto_enel.service;

import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.model.entity.Rol;
import com.api.proyecto_enel.repository.IClienteRepository;
import com.api.proyecto_enel.repository.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {
    @Autowired
    IRolRepository rolRepository;

    public List<Rol> getRoles(){
        return rolRepository.findAll();
    }
    public Optional<Rol> getRolById(Integer id){
        return rolRepository.findById(id);
    }
    //ver que hacer en caso de que alguien intente insertar un rol nuevo, no permitir
    //public void saveOrUpdateRol(Rol rol){
      //  rolRepository.save(rol);
    //}
    //tampoco permitir
    //public void deleteRol(Integer id){
      //  rolRepository.deleteById(id);
    //}
}
