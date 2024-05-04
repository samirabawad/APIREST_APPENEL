package com.api.proyecto_enel.repository;

import com.api.proyecto_enel.model.entity.Admin;
import com.api.proyecto_enel.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//Repositorio de Administrador con utilización de JPA
//0ptional permite manejar los valores nulos de la busqueda

public interface IAdminRepository extends JpaRepository<Admin, Integer> {
    //JPA provee las funciones de consulta a la bd implicitamente como: finAll(), findBy(), etc.
    @Override
    List<Admin> findAllById(Iterable<Integer> integers);

    Optional<Admin> findAdminByRutAdmin(String rutAdmin);
    Optional<Admin> findAdminBycorreoAdmin(String correoAdmin);
}
