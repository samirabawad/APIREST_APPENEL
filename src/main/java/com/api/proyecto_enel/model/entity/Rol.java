package com.api.proyecto_enel.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @ColumnDefault("nextval('rol_id_rol_seq'")
    @Column(name = "id_rol", nullable = false)
    private Integer idRol;

    @Column(name = "descripcion_rol", nullable = false, length = Integer.MAX_VALUE)
    private String descripcionRol;

    @OneToMany(mappedBy = "idRol")
    private Set<Empresa> empresas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idRol")
    private Set<Cliente> clientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idRol")
    private Set<Admin> admins = new LinkedHashSet<>();
}