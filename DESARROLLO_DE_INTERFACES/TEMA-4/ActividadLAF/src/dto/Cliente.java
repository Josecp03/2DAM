/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author josec
 */
public class Cliente {

    private String nombre;
    private String apellidos;
    private String nif;
    private String correo;
    private String nacionalidad;
    private String recibirNoticias;
    private String preferencias;

    public Cliente(String nombre, String apellidos, String nif, String correo, String nacionalidad, String recibirNoticias, String preferencias) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.correo = correo;
        this.nacionalidad = nacionalidad;
        this.recibirNoticias = recibirNoticias;
        this.preferencias = preferencias;
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

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getRecibirNoticias() {
        return recibirNoticias;
    }

    public void setRecibirNoticias(String recibirNoticias) {
        this.recibirNoticias = recibirNoticias;
    }

    public String getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(String preferencias) {
        this.preferencias = preferencias;
    }

    public String[] toArrayString() {
        String[] s = new String[7]; // Cambiar a 7 para incluir 'preferencias'
        s[0] = nombre;
        s[1] = apellidos;
        s[2] = nif;
        s[3] = correo;
        s[4] = nacionalidad;
        s[5] = recibirNoticias;
        s[6] = preferencias; // Ahora es válido
        return s;
    }

}
