package com.api.proyecto_enel.util;

import com.api.proyecto_enel.model.DTO.AdminDTO;
import com.api.proyecto_enel.model.DTO.EmpresaDTO;
import com.api.proyecto_enel.model.DTO.ClienteDTO;
import com.api.proyecto_enel.model.entity.Admin;
import com.api.proyecto_enel.model.entity.Empresa;
import com.api.proyecto_enel.model.entity.Cliente;

public class UtilConversion {
    // Método para convertir un EmpresaDTO a una entidad Empresa
    public static Empresa toEmpresa(EmpresaDTO empresaDTO) {
        Empresa empresa = new Empresa();
        empresa.setId(empresaDTO.getId());
        empresa.setIdRol(empresaDTO.getIdRol());
        empresa.setNombreEmpresa(empresaDTO.getNombreEmpresa());
        empresa.setApellidoEmpresa(empresaDTO.getApellidoEmpresa());
        empresa.setCorreoEmpresa(empresaDTO.getCorreoEmpresa());
        empresa.setClaveEmpresa(empresaDTO.getClaveEmpresa());
        empresa.setRunEmpresa(empresaDTO.getRunEmpresa());
        empresa.setCelularEmpresa(empresaDTO.getCelularEmpresa());
        empresa.setDireccionEmpresa(empresaDTO.getDireccionEmpresa());
        return empresa;
    }

    // Método para convertir una entidad Empresa a un EmpresaDTO
    public static EmpresaDTO fromEmpresa(Empresa empresa) {
        return new EmpresaDTO(
                empresa.getId(),
                empresa.getIdRol(),
                empresa.getNombreEmpresa(),
                empresa.getApellidoEmpresa(),
                empresa.getCorreoEmpresa(),
                empresa.getClaveEmpresa(),
                empresa.getRunEmpresa(),
                empresa.getCelularEmpresa(),
                empresa.getDireccionEmpresa()
        );
    }

    // Método para convertir un ClienteDTO a una entidad Cliente
    public static Cliente toCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setIdRol(clienteDTO.getIdRol());
        cliente.setNombreCli(clienteDTO.getNombreCli());
        cliente.setApellidoCli(clienteDTO.getApellidoCli());
        cliente.setCorreoCli(clienteDTO.getCorreoCli());
        cliente.setClaveCli(clienteDTO.getClaveCli());
        cliente.setRutCli(clienteDTO.getRutCli());
        cliente.setCelularCli(clienteDTO.getCelularCli());
        cliente.setDireccionCli(clienteDTO.getDireccionCli());
        return cliente;
    }
    // Método para convertir una entidad Cliente a un ClienteDTO
    public static ClienteDTO fromCliente(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getIdRol(),
                cliente.getNombreCli(),
                cliente.getApellidoCli(),
                cliente.getCorreoCli(),
                cliente.getClaveCli(),
                cliente.getRutCli(),
                cliente.getCelularCli(),
                cliente.getDireccionCli()
        );
    }

    // Método para convertir una AdminDTO a una entidad Admin
    public static Admin toAdmin(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setId(adminDTO.getId());
        admin.setIdRol(adminDTO.getIdRol());
        admin.setNombreAdmin(adminDTO.getNombreAdmin());
        admin.setApellidoAdmin(adminDTO.getApellidoAdmin());
        admin.setCorreoAdmin(adminDTO.getCorreoAdmin());
        admin.setClaveAdmin(adminDTO.getClaveAdmin());
        admin.setRutAdmin(adminDTO.getRutAdmin());
        admin.setCelularAdmin(adminDTO.getCelularAdmin());
        admin.setDireccionAdmin(adminDTO.getDireccionAdmin());
        return admin;
    }

    // Método para convertir una entidad Admin a un AdminDTO
    public static AdminDTO fromAdmin(Admin admin) {
        return new AdminDTO(
                admin.getId(),
                admin.getIdRol(),
                admin.getNombreAdmin(),
                admin.getApellidoAdmin(),
                admin.getCorreoAdmin(),
                admin.getClaveAdmin(),
                admin.getRutAdmin(),
                admin.getCelularAdmin(),
                admin.getDireccionAdmin()
        );
    }
}
