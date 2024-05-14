package com.api.proyecto_enel.util;

import com.api.proyecto_enel.model.DTO.AdminDTO;
import com.api.proyecto_enel.model.DTO.EmpresaDTO;
import com.api.proyecto_enel.model.DTO.ClienteDTO;
import com.api.proyecto_enel.model.DTO.ResponseEntityDTO;
import com.api.proyecto_enel.model.entity.Admin;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.model.entity.Empresa;

public class UtilConversion {
    // Método para convertir un EmpresaDTO a una entidad Empresa
    public static Empresa toEmpresa(EmpresaDTO empresaDTO) {
        Empresa empresa = new Empresa();
        empresa.setId(empresaDTO.getId());
        empresa.setIdrol(empresaDTO.getIdrol());
        empresa.setRut_empresa(empresaDTO.getRut());
        empresa.setNombre_empresa(empresaDTO.getNombre());
        empresa.setGiro_empresa(empresaDTO.getGiro());
        empresa.setCorreo_empresa(empresaDTO.getCorreo());
        empresa.setClave_empresa(empresaDTO.getClave());
        empresa.setCelular_empresa(empresaDTO.getCelular());
        return empresa;
    }

    // Método para convertir una entidad Empresa a un EmpresaDTO
    public static EmpresaDTO fromEmpresa(Empresa empresa) {
        return new EmpresaDTO(
                empresa.getId(),
                empresa.getIdrol(),
                empresa.getRut_empresa(),
                empresa.getNombre_empresa(),
                empresa.getGiro_empresa(),
                empresa.getCorreo_empresa(),
                empresa.getClave_empresa(),
                empresa.getCelular_empresa()
        );
    }

    // Método para convertir un ClienteDTO a una entidad Cliente
    public static Cliente toCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setIdrol(clienteDTO.getIdrol());
        cliente.setRut_cliente(clienteDTO.getRut());
        cliente.setNombre_cliente(clienteDTO.getNombre());
        cliente.setApellido_cliente(clienteDTO.getApellido());
        cliente.setCorreo_cliente(clienteDTO.getCorreo());
        cliente.setClave_cliente(clienteDTO.getClave());
        cliente.setCelular_cliente(clienteDTO.getCelular());
        return cliente;
    }

    // Método para convertir una entidad Cliente a un ClienteDTO
    public static ClienteDTO fromCliente(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getIdrol(),
                cliente.getRut_cliente(),
                cliente.getNombre_cliente(),
                cliente.getApellido_cliente(),
                cliente.getCorreo_cliente(),
                cliente.getClave_cliente(),
                cliente.getCelular_cliente()
        );
    }

    // Método para convertir una AdminDTO a una entidad Admin
    public static Admin toAdmin(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setId(adminDTO.getId());
        admin.setIdrol(adminDTO.getIdrol());
        admin.setRut_admin(adminDTO.getRut());
        admin.setNombre_admin(adminDTO.getNombre());
        admin.setApellido_admin(adminDTO.getApellido());
        admin.setCorreo_admin(adminDTO.getCorreo());
        admin.setClave_admin(adminDTO.getClave());
        admin.setCelular_admin(adminDTO.getCelular());
        return admin;
    }

    // Método para convertir una entidad Admin a un AdminDTO
    public static AdminDTO fromAdmin(Admin admin) {
        return new AdminDTO(
                admin.getId(),
                admin.getIdrol(),
                admin.getRut_admin(),
                admin.getNombre_admin(),
                admin.getApellido_admin(),
                admin.getCorreo_admin(),
                admin.getClave_admin(),
                admin.getCelular_admin()
        );
    }
}
