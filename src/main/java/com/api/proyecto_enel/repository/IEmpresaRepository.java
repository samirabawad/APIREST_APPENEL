package com.api.proyecto_enel.repository;
import com.api.proyecto_enel.model.entity.Cliente;
import com.api.proyecto_enel.model.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

//Repositorio de Empresa con utilización de JPA
//0ptional permite manejar los valores nulos de la busqueda

public interface IEmpresaRepository extends JpaRepository<Empresa, Integer> {

    //JPA provee las funciones de consulta a la bd implicitamente como: finAll(), findBy(), etc.
    @Override
    List<Empresa> findAllById(Iterable<Integer> integers);

    //Optional<Empresa> findEmpresaByRunEmpresa(String rutEmpresa);
    //Optional<Empresa> findEmpresaByCorreoEmpresa(String correoEmpresa);
}
