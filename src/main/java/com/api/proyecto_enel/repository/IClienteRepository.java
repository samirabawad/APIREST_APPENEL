package com.api.proyecto_enel.repository;

import com.api.proyecto_enel.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

//Repositorio de Cliente con utilización de JPA
//0ptional permite manejar los valores nulos de la busqueda
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

    //JPA provee las funciones de consulta a la bd implicitamente como: finAll(), findBy(), etc.
    @Override
    List<Cliente> findAllById(Iterable<Integer> integers);

    @Query("SELECT e FROM Cliente e WHERE e.correo_cliente =:correo_cliente")
    Optional<Cliente> findByCorreoCliente(@Param("correo_cliente") String correo_cliente);

    @Query("SELECT e FROM Cliente e WHERE e.celular_cliente =:celular_cliente")
    Optional<Cliente> findByCelularCliente(@Param("celular_cliente") String celular_cliente);

}
