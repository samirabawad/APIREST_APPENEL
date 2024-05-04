package com.api.proyecto_enel.repository;

import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.model.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//aca van las querys
public interface IRolRepository extends JpaRepository <Rol, Integer> {
    @Override
    List<Rol> findAllById(Iterable<Integer> integers);
}
