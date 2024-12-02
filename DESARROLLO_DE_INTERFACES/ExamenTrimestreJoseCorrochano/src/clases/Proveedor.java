/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author josec
 */
public class Proveedor {

    // Atributos
    private String dni;
    private String empresa;
    private String direccion;
    private int telefono;
    private String pais;
    private String correo;

    // Constructor con todos los parámetros
    public Proveedor(String dni, String empresa, String direccion, int telefono, String pais, String correo) {
        this.dni = dni;
        this.empresa = empresa;
        this.direccion = direccion;
        this.telefono = telefono;
        this.pais = pais;
        this.correo = correo;
    }

    // Constructor por defecto
    public Proveedor() {
        this.dni = "";
        this.empresa = "";
        this.direccion = "";
        this.telefono = 0;
        this.pais = "";
        this.correo = "";
    }

    // Getters y Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
}
