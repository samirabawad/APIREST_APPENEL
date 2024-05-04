package com.api.proyecto_enel.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "empresa", uniqueConstraints = {
        @UniqueConstraint(name = "empresa_correo_empresa_key", columnNames = {"correo_empresa"}),
        @UniqueConstraint(name = "empresa_rut_empresa_key", columnNames = {"rut_empresa"})
})
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('empresa_id_empresa_seq'")
    @Column(name = "id_empresa", nullable = false)
    private Integer id;

    @ColumnDefault("2")
    @JoinColumn(name = "id_rol", nullable = false)
    private Integer idRol;

    @Column(name = "nombre_empresa", length = 50)
    private String nombreEmpresa;

    @Column(name = "apellido_empresa", length = 50)
    private String apellidoEmpresa;

    @Column(name = "correo_empresa", length = 100)
    private String correoEmpresa;

    @Column(name = "clave_empresa", length = 100)
    private String claveEmpresa;

    @Column(name = "run_empresa", length = 12)
    private String runEmpresa;

    @Column(name = "celular_empresa", length = 15)
    private String celularEmpresa;

    @Column(name = "direccion_empresa", length = 100)
    private String direccionEmpresa;

}