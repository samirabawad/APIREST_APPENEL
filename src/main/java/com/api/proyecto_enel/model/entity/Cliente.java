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
@Table(name = "cliente", uniqueConstraints = {
        @UniqueConstraint(name = "cliente_rut_cliente_key", columnNames = {"rut_cliente"}),
        @UniqueConstraint(name = "cliente_correo_cliente_key", columnNames = {"correo_cliente"})
})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('cliente_id_cliente_seq'")
    @Column(name = "id_cliente", nullable = false)
    private Integer id;

    @NotNull
    @ColumnDefault("1")
    @JoinColumn(name = "idrol", nullable = false)
    private Integer idrol;

    @Size(max = 12)
    @Column(name = "rut_cliente", length = 12)
    private String rut_cliente;

    @Size(max = 50)
    @Column(name = "nombre_cliente", length = 50)
    private String nombre_cliente;

    @Size(max = 50)
    @Column(name = "apellido_cliente", length = 50)
    private String apellido_cliente;

    @Size(max = 100)
    @Column(name = "correo_cliente", length = 100)
    private String correo_cliente;

    @Size(max = 100)
    @Column(name = "clave_cliente", length = 100)
    private String clave_cliente;

    @Size(max = 15)
    @Column(name = "celular_cliente", length = 15)
    private String celular_cliente;

}