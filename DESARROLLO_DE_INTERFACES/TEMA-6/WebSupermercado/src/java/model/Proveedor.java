/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author josec
 */
public class Proveedor implements Serializable {

    private String nit;
    private String nombre;
    private String telefono;
    private String direccion;
    private String correo_electronico;

    public Proveedor() {
        this.nit = "";
        this.nombre = "";
        this.telefono = "";
        this.direccion = "";
        this.correo_electronico = "";
    }

    public Proveedor(String nit, String nombre, String telefono, String direccion, String correo_electronico) {
        this.nit = nit;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo_electronico = correo_electronico;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }
    
}
