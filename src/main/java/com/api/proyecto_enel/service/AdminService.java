package com.api.proyecto_enel.service;

import com.api.proyecto_enel.model.DTO.AdminDTO;
import com.api.proyecto_enel.model.DTO.ClienteDTO;
import com.api.proyecto_enel.model.DTO.EmpresaDTO;
import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;
import com.api.proyecto_enel.model.entity.Admin;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.model.entity.Empresa;
import com.api.proyecto_enel.repository.IAdminRepository;
import com.api.proyecto_enel.repository.IClienteRepository;
import com.api.proyecto_enel.repository.IEmpresaRepository;
import com.api.proyecto_enel.util.IterateObject;
import com.api.proyecto_enel.util.UtilConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    //inyectando instancia de repositorio de admin.
    @Autowired
    IAdminRepository adminRepository;

    @Autowired
    IEmpresaRepository empresaRepository;

    @Autowired
    IClienteRepository clienteRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    //Entrega una lista de todas las empresas
    public List<EmpresaDTO> getEmpresas(){
        List<Empresa> empresas = empresaRepository.findAll();
        return empresas.stream()
                .map(UtilConversion::fromEmpresa)
                .collect(Collectors.toList());

    }

    //Entrega una lista de todos los clientes
    public List<ClienteDTO> getClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(UtilConversion::fromCliente)
                .collect(Collectors.toList());

    }

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
    public ResponseEntityDTO saveAdmin(AdminDTO adminDTO) {
        try {
            //Itera y valida los campos del cliente enviado en la peticion.
            String respuestaIteracion = IterateObject.IterateObjectDTO(adminDTO);
            if (respuestaIteracion.equals("Validaciones exitosas")) {
                //guarda cliente
                try{
                    //String claveHash = HashingPassword.hashPassword(clienteDTO.getClave());
                    //if (claveHash.equals("NO HASH")){
                    // return new ResponseEntityDTO("Ocurrió un error al guardar su clave", "200");
                    //}else{
                    //clienteDTO.setClave(claveHash);
                    String hashedPassword = passwordEncoder.encode(adminDTO.getClave());
                    adminDTO.setClave(hashedPassword);
                    Admin admin = UtilConversion.toAdmin(adminDTO);
                    adminRepository.save(admin);
                    return new ResponseEntityDTO("Su usuario ha sido registrado correctamente", "200");
                    //        }
                }catch(Exception e){
                    System.out.println("Error: "+e.getMessage());
                    return new ResponseEntityDTO("Error en la conversion de DTO a entity", "400");
                }
            } else {
                //Captura errores de validacion.
                return new ResponseEntityDTO("Error al registrar el cliente. " + respuestaIteracion, "400");
            }

            //Captura cualquier excepcion al iterar el cliente de la peticion.
        } catch (Exception e) {
            return new ResponseEntityDTO("Error al datos del registro.  Compruebe que el nombre de los campos y los valores sean los correctos", "400");
        }
    }
}


