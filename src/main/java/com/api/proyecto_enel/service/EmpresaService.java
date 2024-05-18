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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    //inyectando instancia de repositorio de empresa.
    @Autowired
    IEmpresaRepository empresaRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntityDTO getEmpresaByCorreo(String correo_empresa) {
        Optional<Empresa> empresa = empresaRepository.findByCorreoEmpresa(correo_empresa);
        System.out.println(empresa.isPresent());
        System.out.println(empresa);
        if (empresa.isPresent()) {
            return new ResponseEntityDTO("Se ha enviado un correo electronico para el cambio de su clave", "200");
        }else{
            return new ResponseEntityDTO("El correo electronico no se encuentra registrado", "400");
        }
    }

    public ResponseEntityDTO getEmpresaByCelular(String celular_empresa) {
        Optional<Empresa> empresa = empresaRepository.findByCelularEmpresa(celular_empresa);
        System.out.println(empresa.isPresent());
        System.out.println(empresa);
        if(empresa.isPresent()) {
            return new ResponseEntityDTO("Se ha enviado un SMS para el cambio de su clave", "200");
        }else{
            return new ResponseEntityDTO("El celular no se encuentra registrado", "400");
        }
    }


    public ResponseEntityDTO saveEmpresa(EmpresaDTO empresaDTO){
        try {
            //Itera y valida los campos del cliente enviado en la peticion.
            String respuestaIteracion = IterateObject.IterateObjectDTO(empresaDTO);
            if (respuestaIteracion.equals("Validaciones exitosas")) {
                //guarda cliente
                try{
               //     String claveHash = HashingPassword.hashPassword(empresaDTO.getClave());
                 //   if (claveHash.equals("NO HASH")){
                   //     return new ResponseEntityDTO("Ocurrió un error al guardar su clave", "200");
                  //  }else{
                    //    empresaDTO.setClave(claveHash);
                        String hashedPassword = passwordEncoder.encode(empresaDTO.getClave());
                        empresaDTO.setClave(hashedPassword);
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
