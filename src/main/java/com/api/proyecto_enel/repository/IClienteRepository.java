package com.api.proyecto_enel.repository;

import com.api.proyecto_enel.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//Repositorio de Cliente con utilización de JPA
//0ptional permite manejar los valores nulos de la busqueda
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

    //JPA provee las funciones de consulta a la bd implicitamente como: finAll(), findBy(), etc.
    @Override
    List<Cliente> findAllById(Iterable<Integer> integers);
  //  Optional<Cliente> findClienteByCorreo_cliente(String correo_cliente);
//    Optional<Cliente> findByRut_cliente(String rut_cliente);

}
