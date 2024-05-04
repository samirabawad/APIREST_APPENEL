package com.api.proyecto_enel.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;


@Getter
@Setter
@Entity
@Table(name = "admin", uniqueConstraints = {
        @UniqueConstraint(name = "admin_correo_admin_key", columnNames = {"correo_admin"}),
        @UniqueConstraint(name = "admin_rut_admin_key", columnNames = {"rut_admin"})
})

public class Admin {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @ColumnDefault("nextval('admin_id_admin_seq'")
        @Column(name = "id_admin", nullable = false)
        private Integer id;

        @ColumnDefault("1")
        @JoinColumn(name = "id_rol", nullable = false)
        private Integer idRol;

        @Column(name = "nombre_admin", length = 50)
        private String nombreAdmin;

        @Column(name = "apellido_admin", length = 50)
        private String apellidoAdmin;

        @Column(name = "correo_admin", length = 100)
        private String correoAdmin;

        @Column(name = "clave_admin", length = 100)
        private String claveAdmin;

        @Column(name = "rut_admin", length = 12)
        private String rutAdmin;

        @Column(name = "celular_admin", length = 15)
        private String celularAdmin;

        @Column(name = "direccion_admin", length = 100)
        private String direccionAdmin;

}
