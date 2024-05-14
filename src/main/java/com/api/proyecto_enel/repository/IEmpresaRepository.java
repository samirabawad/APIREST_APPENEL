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


    //recuperaciones de contrasena
    Optional<Empresa> findByRutEmpresa(String rut);

    Optional<Empresa> findByCorreo_empresa(String correo);

    Optional<Empresa> findByCelular_empresa(String celular);

    //logins
    Optional<Empresa> findByCorreo_empresaAndAndClave_empresa(String correo, String clave);

    Optional<Empresa> findByCeular_empresaAndAndClave_empresa(String correo, String clave);

    Optional<Empresa> findByRutEmpresaAndClave_empresa(String rut, String clave);
}
