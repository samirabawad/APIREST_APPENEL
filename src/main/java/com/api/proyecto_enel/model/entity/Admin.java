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
@Table(name = "admin", uniqueConstraints = {
        @UniqueConstraint(name = "admin_rut_admin_key", columnNames = {"rut_admin"}),
        @UniqueConstraint(name = "admin_correo_admin_key", columnNames = {"correo_admin"})
})
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('admin_id_admin_seq'")
    @Column(name = "id_admin", nullable = false)
    private Integer id;

    @NotNull
    @ColumnDefault("3")
    @JoinColumn(name = "idrol", nullable = false)
    private Integer idrol;

    @Size(max = 12)
    @Column(name = "rut_admin", length = 12)
    private String rut_admin;

    @Size(max = 50)
    @Column(name = "nombre_admin", length = 50)
    private String nombre_admin;

    @Size(max = 50)
    @Column(name = "apellido_admin", length = 50)
    private String apellido_admin;

    @Size(max = 100)
    @Column(name = "correo_admin", length = 100)
    private String correo_admin;

    @Size(max = 100)
    @Column(name = "clave_admin", length = 100)
    private String clave_admin;

    @Size(max = 15)
    @Column(name = "celular_admin", length = 15)
    private String celular_admin;

}