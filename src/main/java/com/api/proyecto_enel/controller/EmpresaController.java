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
@RequestMapping(path = "empresas")
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
        return empresaService.getEmpresas();
}
    //envia correo electronico para la recuperacion de contrasena.
    //Recibe correo del cliente mediante URL, ejemplo: http://localhost:8080/api/v1/empresas/getEmpresaCorreo/2@gmail.cl
    @GetMapping("/getEmpresaCorreo/{correo_empresa}")
    public ResponseEntityDTO getEmpresaCorreo(@PathVariable("correo_empresa") String correo_empresa) {
        ResponseEntityDTO empresa = empresaService.getEmpresaByCorreo(correo_empresa);
        return empresa;
    }

    //envia SMS al celular para la recuperacion de contrasena.
    //Recibe correo del cliente mediante URL, ejemplo: http://localhost:8080/api/v1/empresas/getEmpresaCelular/99348741
    @GetMapping("/getEmpresaCelular/{celular_empresa}")
    public ResponseEntityDTO getEmpresaCelular(@PathVariable("celular_empresa") String celular_empresa) {
        ResponseEntityDTO empresa = empresaService.getEmpresaByCelular(celular_empresa);
        return empresa;
    }

    //crea una empresa
    @PostMapping("/registro/empresa")
    public ResponseEntityDTO saveEmpresa(@Valid @RequestBody EmpresaDTO empresaDTO, BindingResult resultado) {
        ResponseEntityDTO empresa = empresaService.saveEmpresa(empresaDTO);
        if(resultado.hasErrors()){
            return new ResponseEntityDTO("Ocurrió un error al intentar registrar la empresa", "400");
        }
        return empresa;
    }

}
