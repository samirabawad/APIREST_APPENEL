package com.api.proyecto_enel.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "empresa", uniqueConstraints = {
        @UniqueConstraint(name = "empresa_rut_empresa_key", columnNames = {"rut_empresa"}),
        @UniqueConstraint(name = "empresa_correo_empresa_key", columnNames = {"correo_empresa"})
})
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('empresa_id_empresa_seq'")
    @Column(name = "id_empresa", nullable = false)
    private Integer id;

    @NotNull
    @ColumnDefault("2")
    @JoinColumn(name = "idrol", nullable = false)
    private Integer idrol;

    @Size(max = 12)
    @Column(name = "rut_empresa", length = 12)
    private String rut_empresa;

    @Size(max = 50)
    @Column(name = "nombre_empresa", length = 50)
    private String nombre_empresa;

    @Size(max = 50)
    @Column(name = "giro_empresa", length = 50)
    private String giro_empresa;

    @Size(max = 100)
    @Column(name = "correo_empresa", length = 100)
    private String correo_empresa;

    @Size(max = 100)
    @Column(name = "clave_empresa", length = 100)
    private String clave_empresa;

    @Size(max = 15)
    @Column(name = "celular_empresa", length = 15)
    private String celular_empresa;

}