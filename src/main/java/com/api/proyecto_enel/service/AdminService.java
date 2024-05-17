package com.api.proyecto_enel.service;

import com.api.proyecto_enel.model.DTO.AdminDTO;
import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;
import com.api.proyecto_enel.model.entity.Admin;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.repository.IAdminRepository;
import com.api.proyecto_enel.util.IterateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    //inyectando instancia de repositorio de admin.
    @Autowired
    IAdminRepository adminRepository;

    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }
    public ResponseEntityDTO getAdminByCorreo(String correo_admin) {
        Optional<Admin> admin = adminRepository.findByCorreoAdmin(correo_admin);
        System.out.println(admin.isPresent());
        System.out.println(admin);
        if (admin.isPresent()) {
            return new ResponseEntityDTO("Se ha enviado un correo electronico para el cambio de su clave", "200");
        }else{
            return new ResponseEntityDTO("El correo electronico no se encuentra registrado", "400");
        }
    }

    public ResponseEntityDTO getAdminByCelular(String celular_admin) {
        Optional<Admin> admin = adminRepository.findByCelularAdmin(celular_admin);
        System.out.println(admin.isPresent());
        System.out.println(admin);
        if(admin.isPresent()) {
            return new ResponseEntityDTO("Se ha enviado un SMS para el cambio de su clave", "200");
        }else{
            return new ResponseEntityDTO("El celular no se encuentra registrado", "400");
        }
    }


    //este sera para update solamente, no tiene registro.
    public ResponseEntityDTO updateAdmin(AdminDTO adminDTO){
        try {
            //Itera y valida los campos del cliente enviado en la peticion.
            String respuestaIteracion = IterateObject.IterateObjectDTO(adminDTO);
            System.out.println("IDROL DE SAVECLIENTE: "+adminDTO.getIdrol());
            if (respuestaIteracion.equals("Validaciones exitosas")) {
                //guarda cliente
                return new ResponseEntityDTO("Se guardara el admin", "200");
            } else {
                //Captura errores de validacion.
                return new ResponseEntityDTO("Error al registrar el admin " + respuestaIteracion, "400");
            }

            //Captura cualquier excepcion al iterar el cliente de la peticion.
        } catch (Exception e) {
            return new ResponseEntityDTO("Error al datos del registro.  Compruebe que el nombre de los campos y los valores sean los correctos", "400");
        }
    }
}


