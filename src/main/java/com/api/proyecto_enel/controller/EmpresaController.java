package com.api.proyecto_enel.controller;

import com.api.proyecto_enel.model.DTO.ClienteDTO;
import com.api.proyecto_enel.model.DTO.EmpresaDTO;
import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.model.entity.Empresa;
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
    //@GetMapping("get_empresa/{id}")
    //public EmpresaDTO getEmpresaById(@PathVariable("id") Integer id){
         //Optional <Empresa> empresa = empresaService.getEmpresaById(id);
       //  return empresa.map(UtilConversion::fromEmpresa).orElse(null);
    //}


    //crea una empresa
    @PostMapping("/registro/empresa")
    public ResponseEntityDTO saveEmpresa(@Valid @RequestBody EmpresaDTO empresaDTO, BindingResult resultado) {
        ResponseEntityDTO empresa = empresaService.saveEmpresa(empresaDTO);
        if(resultado.hasErrors()){
            return new ResponseEntityDTO("Ocurrió un error al intentar registrar la empresa", "400");
        }
        return empresa;
    }

    //Si prefieres seguir los principios de diseño RESTful, es posible que te inclines
    // hacia @PathVariable, ya que tiende a proporcionar URLs más descriptivas y semánticamente significativas. Por ejemplo,
    ///usuarios/{id} podría ser más legible que /login?correo=ejemplo@dominio.com&contrasena=1234.
    //Login mediante metodo post
    //@GetMapping
    //public ResponseEntityDTO recuperaClaveRut(@RequestParam Integer id){

    //}

    //Obtiene una empresa por el Correo.
    //@GetMapping("getEmpresa/{correo_empresa}")
   // public EmpresaDTO getEmpresaByCorreo(@PathVariable("correo_empresa") String correo_empresa){
     //   Optional<Empresa> empresa = empresaService.getEmpresaByCorreo(correo_empresa);
       // return empresa.map(UtilConversion::fromEmpresa).orElse(null);
    //}

}
