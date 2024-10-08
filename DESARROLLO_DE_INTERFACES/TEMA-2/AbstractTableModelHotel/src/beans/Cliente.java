/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;

/**
 *
 * @author josec
 */
public class Cliente {
    
    private String nombre;
    private String apellidos;
    private Date chekin;
    private Date chekout;
    private int numeroHabitacion;
    private String tipo;
    private String dni;
    private String nacionalidad;
    private Date fechaNacimiento;

    public Cliente(String nombre, String apellidos, Date chekin, Date chekout, int numeroHabitacion, String tipo, String dni, String nacionalidad, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.chekin = chekin;
        this.chekout = chekout;
        this.numeroHabitacion = numeroHabitacion;
        this.tipo = tipo;
        this.dni = dni;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Date getChekin() {
        return chekin;
    }

    public Date getChekout() {
        return chekout;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDni() {
        return dni;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    
}
