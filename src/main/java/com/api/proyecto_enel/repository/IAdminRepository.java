package com.api.proyecto_enel.repository;

import com.api.proyecto_enel.model.entity.Admin;
import com.api.proyecto_enel.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

//Repositorio de Administrador con utilización de JPA
//0ptional permite manejar los valores nulos de la busqueda

public interface IAdminRepository extends JpaRepository<Admin, Integer> {
    //JPA provee las funciones de consulta a la bd implicitamente como: finAll(), findBy(), etc.
    @Override
    List<Admin> findAllById(Iterable<Integer> integers);

    @Query("SELECT e FROM Admin e WHERE e.correo_admin =:correo_admin")
    Optional<Admin> findByCorreoAdmin(@Param("correo_admin") String correo_admin);

    @Query("SELECT e FROM Admin e WHERE e.celular_admin =:celular_admin")
    Optional<Admin> findByCelularAdmin(@Param("celular_admin") String celular_admin);

}
