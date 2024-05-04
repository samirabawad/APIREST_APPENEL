package com.api.proyecto_enel.controller;

import com.api.proyecto_enel.model.DTO.ClienteDTO;
import com.api.proyecto_enel.model.DTO.EmpresaDTO;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.model.entity.Empresa;
import com.api.proyecto_enel.service.EmpresaService;
import com.api.proyecto_enel.util.UtilConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/empresas")
public class EmpresaController {

    //Inyeccion de la instancia de IEmpresaRepository en el controlador
    @Autowired
    private EmpresaService empresaService;


    //Obtiene todas las empresas
    //stream() crea una secuencia de elementos para ser procesados de manera secuencial.
    //map() convierte cada elemento DTO a entidad
    //collect() convierte el resultado en una lista.
    @GetMapping
    public List<EmpresaDTO> getAllEmpresas() {
        List<Empresa> empresas = empresaService.getEmpresas();
        return empresas.stream()
                .map(UtilConversion::fromEmpresa)
                .collect(Collectors.toList());
    }

    //Obtiene una empresa por el ID.
    //0ptional permite manejar los valores nulos de la busqueda.
    //Devuelve el valor en DTO.
    @GetMapping("getEmpresa/{id_empresa}")
    public EmpresaDTO getEmpresaById(@PathVariable("id_empresa") Integer id_empresa){
         Optional <Empresa> empresa = empresaService.getEmpresaById(id_empresa);
         return empresa.map(UtilConversion::fromEmpresa).orElse(null);
    }

    //Obtiene una empresa por el Run.
    @GetMapping("getEmpresa/{run_empresa}")
    public EmpresaDTO getEmpresaByRun(@PathVariable("run_empresa") String run_empresa) {
        Optional <Empresa> empresa = empresaService.getEmpresaByRun(run_empresa);
        return empresa.map(UtilConversion::fromEmpresa).orElse(null);
    }

    //Obtiene una empresa por el Correo.
    @GetMapping("getEmpresa/{correo_empresa}")
    public EmpresaDTO getEmpresaByCorreo(@PathVariable("correo_empresa") String correo_empresa){
        Optional<Empresa> empresa = empresaService.getEmpresaByCorreo(correo_empresa);
        return empresa.map(UtilConversion::fromEmpresa).orElse(null);
    }

}
