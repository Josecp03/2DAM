/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionclientes.dto;

import java.util.Date;


/**
 *
 * @author josec
 */
public class Cliente {
    
    private String nombre;
    private String apellidos;
    private Date fecha;
    private String provincia;

    public Cliente(String nombre, String apellidos, Date fecha, String provincia) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha = fecha;
        this.provincia = provincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    public String[] toArrayString() {
        String[] s = new String[4];
        s[0] = nombre;
        s[1] = apellidos;
        s[2] = fecha.toString();
        s[3] = provincia;
        return s;
    }
    
}

