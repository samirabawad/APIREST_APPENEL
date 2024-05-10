package com.api.proyecto_enel.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cliente", uniqueConstraints = {
        @UniqueConstraint(name = "cliente_correo_cli_key", columnNames = {"correo_cli"}),
        @UniqueConstraint(name = "cliente_rut_cli_key", columnNames = {"rut_cli"})
})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('cliente_id_cli_seq'")
    @Column(name = "id_cli", nullable = false)
    @NotNull(message="idRol no debe ser nulo")
    @Pattern(regexp = "^[0-9]+$", message = "El idRol debe ser un número entero")
    @Min(value = 1, message ="el valor debe ser de una longitud min 1" )
    @Max(value = 1, message = "el valor deve ser de una longitud max 1")
    private Integer id;

    @ColumnDefault("2")
    @JoinColumn(name = "id_rol", nullable = false)
    @NotNull(message="idRol no debe ser nulo")
    @Min(value = 1, message ="el valor debe ser de una longitud min 1" )
    @Max(value = 1, message = "el valor deve ser de una longitud max 1")
    @Size(max = 1)
    @NotEmpty(message = "no vacio")
    @Pattern(regexp = "^[0-9]+$", message = "El idRol debe ser un número entero")
    private Integer idRol;

    @Column(name = "nombre_cli", length = 50)
    private String nombreCli;

    @Column(name = "apellido_cli", length = 50)
    private String apellidoCli;

    @Column(name = "correo_cli", length = 100)
    private String correoCli;

    @Column(name = "clave_cli", length = 100)
    private String claveCli;

    @Column(name = "rut_cli", length = 12)
    private String rutCli;

    @Column(name = "celular_cli", length = 15)
    private String celularCli;

    @Column(name = "direccion_cli", length = 100)
    private String direccionCli;


}