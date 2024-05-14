package com.api.proyecto_enel.service;

import com.api.proyecto_enel.model.DTO.EmpresaDTO;
import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.model.entity.Empresa;
import com.api.proyecto_enel.repository.IEmpresaRepository;
import com.api.proyecto_enel.util.IterateObject;
import com.api.proyecto_enel.util.UtilConversion;
import io.swagger.models.Response;
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

    //Recuperaciones de claves
    public Optional<Empresa> getEmpresaByRut(String rut_empresa){
        return empresaRepository.findByRutEmpresa(rut_empresa);
    }
    public Optional<Empresa> getEmpresaByCorreo(String correo_empresa){
        return empresaRepository.findByCorreo_empresa(correo_empresa);
    }
    public Optional<Empresa> getEmpresaByCelular(String celular_empresa){
        return empresaRepository.findByCelular_empresa(celular_empresa);
    }

    //logins
    public Optional<Empresa> getEmpresaByRutAndClave(String rut, String clave){
        return empresaRepository.findByRutEmpresaAndClave_empresa(rut, clave);
    }
    public Optional<Empresa> getEmpresaByCelularAndClave(String celular, String clave){
        return empresaRepository.findByCeular_empresaAndAndClave_empresa(celular, clave);
    }
    public Optional<Empresa> getEmpresaByCorreoAndClave(String correo, String clave){
        return empresaRepository.findByCorreo_empresaAndAndClave_empresa(correo, clave);
    }




    public ResponseEntityDTO saveEmpresa(EmpresaDTO empresaDTO){
        try {
            //Itera y valida los campos del cliente enviado en la peticion.
            String respuestaIteracion = IterateObject.IterateObjectDTO(empresaDTO);
            if (respuestaIteracion.equals("Validaciones exitosas")) {
                //guarda cliente
                try{
                    Empresa empresa = UtilConversion.toEmpresa(empresaDTO);
                    empresaRepository.save(empresa);
                    return new ResponseEntityDTO("Su empresa se ha sido registrado correctamente", "200");
                }catch(Exception e){
                    System.out.println("Error: "+e.getMessage());
                    return new ResponseEntityDTO("Error en la conversion de DTO a entity", "400");
                }
            } else {
                //Captura errores de validacion.
                return new ResponseEntityDTO("Error al registrar la empresa. " + respuestaIteracion, "400");
            }

            //Captura cualquier excepcion al iterar el cliente de la peticion.
        } catch (Exception e) {
            return new ResponseEntityDTO("Error al datos del registro.  Compruebe que el nombre de los campos y los valores sean los correctos", "400");
        }
    }
}
