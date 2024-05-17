package com.api.proyecto_enel.repository;
import com.api.proyecto_enel.model.entity.Admin;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.model.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

//Repositorio de Empresa con utilización de JPA
//0ptional permite manejar los valores nulos de la busqueda

public interface IEmpresaRepository extends JpaRepository<Empresa, Integer> {

    //JPA provee las funciones de consulta a la bd implicitamente como: finAll(), findBy(), etc.
    @Override
    List<Empresa> findAllById(Iterable<Integer> integers);

    @Query("SELECT e FROM Empresa e WHERE e.correo_empresa =:correo_empresa")
    Optional<Empresa> findByCorreoEmpresa(@Param("correo_empresa") String correo_empresa);

    @Query("SELECT e FROM Empresa e WHERE e.celular_empresa =:celular_empresa")
    Optional<Empresa> findByCelularEmpresa(@Param("celular_empresa") String celular_empresa);

}
