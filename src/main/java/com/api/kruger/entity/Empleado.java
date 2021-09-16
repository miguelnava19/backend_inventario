package com.api.kruger.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "es requerido")
    @Size(min = 10, max = 10, message = "el tamaño debe ser 10 números")
    @Column(unique = true, length = 10, name = "cedula", nullable = false)
    private String cedula;

    @NotEmpty(message = "es requerido")
    @Column(name = "nombres", nullable = false)
    private String nombres;

    @NotEmpty(message = "es requerido")
    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @NotEmpty(message = "es requerido")
    @Email(message = "dirección de correo no valida")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "domicilio")
    private String domicilio;

    @Column(length = 10, name = "telefono")
    private String telefono;

    @Column(name = "estado_vacunacion")
    private String estadoVacunacion;//Vacunado / No Vacunado

    @Column(name = "tipo_vacuna")
    private String tipoVacuna;//Sputnik, AstraZeneca, Pfizer y Jhonson&Jhonson

    @Column(name = "numero_dosis")
    private String numeroDosis;

    @JsonIgnoreProperties(value = {"empleado", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @OneToOne(mappedBy = "empleado")
    private Usuario usuario;


    public Empleado(Integer id) {
        this.id = id;
    }

    public Empleado() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstadoVacunacion() {
        return estadoVacunacion;
    }

    public void setEstadoVacunacion(String estadoVacunacion) {
        this.estadoVacunacion = estadoVacunacion;
    }

    public String getTipoVacuna() {
        return tipoVacuna;
    }

    public void setTipoVacuna(String tipoVacuna) {
        this.tipoVacuna = tipoVacuna;
    }

    public String getNumeroDosis() {
        return numeroDosis;
    }

    public void setNumeroDosis(String numeroDosis) {
        this.numeroDosis = numeroDosis;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private static final long serialVersionUID = 1L;
}
