package com.api.proyecto_enel.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('rol_idrol_seq'")
    @Column(name = "idrol", nullable = false)
    private Integer idrol;

    @NotNull
    @Column(name = "descripcion_rol", nullable = false, length = Integer.MAX_VALUE)
    private String descripcionRol;

    @OneToMany(mappedBy = "idrol")
    private Set<Empresa> empresas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idrol")
    private Set<Cliente> clientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idrol")
    private Set<Admin> admins = new LinkedHashSet<>();


}