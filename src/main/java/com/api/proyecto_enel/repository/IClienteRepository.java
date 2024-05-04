package com.api.proyecto_enel.repository;

import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.model.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//Repositorio de Cliente con utilización de JPA
//0ptional permite manejar los valores nulos de la busqueda
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

    //JPA provee las funciones de consulta a la bd implicitamente como: finAll(), findBy(), etc.
    @Override
    List<Cliente> findAllById(Iterable<Integer> integers);

    Optional<Cliente> findClienteByRutCli(String rutCli);
    Optional<Cliente> findClienteByCorreoCli(String correoCli);
}
