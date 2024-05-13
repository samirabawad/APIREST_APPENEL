package com.api.proyecto_enel.service;

import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.model.entity.Empresa;
import com.api.proyecto_enel.repository.IEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    //inyectando instancia de repositorio de empresa.
    @Autowired
    IEmpresaRepository empresaRepository;

    //obtiene una lista de todos los empresa de la base de datos
    public List<Empresa> getEmpresas(){
        return empresaRepository.findAll();
    }

    //Obtiene una empresa por el ID.
    //0ptional permite manejar los valores nulos de la busqueda
    public Optional<Empresa> getEmpresaById(Integer id_empresa){
        return empresaRepository.findById(id_empresa);
    }

    //Obtiene una empresa por el RUN.
    //public Optional<Empresa> getEmpresaByRun(String run_empresa){
      //  return empresaRepository.findEmpresaByRunEmpresa(run_empresa);
   // }

    //Obtiene una empresa por el Correo.
   // public Optional<Empresa> getEmpresaByCorreo(String correo_empresa){
     //   return empresaRepository.findEmpresaByCorreoEmpresa(correo_empresa);
    //}

}
