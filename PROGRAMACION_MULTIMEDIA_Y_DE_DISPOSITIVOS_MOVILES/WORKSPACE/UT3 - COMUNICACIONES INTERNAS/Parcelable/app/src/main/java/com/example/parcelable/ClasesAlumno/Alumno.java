package com.example.parcelable.ClasesAlumno;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Alumno implements Parcelable {

    // Atributos
    private String nombre;
    private int edad;
    private float notaMedia;
    private ArrayList<Asignatura> asignaturas;

    public Alumno(String nombre, ArrayList<Asignatura> asignaturas, int edad, float notaMedia) {
        this.nombre = nombre;
        this.asignaturas = asignaturas;
        this.edad = edad;
        this.notaMedia = notaMedia;
    }

    protected Alumno(Parcel in) {
        nombre = in.readString();
        edad = in.readInt();
        notaMedia = in.readFloat();
        asignaturas = in.createTypedArrayList(Asignatura.CREATOR);
    }

    public static final Creator<Alumno> CREATOR = new Creator<Alumno>() {
        @Override
        public Alumno createFromParcel(Parcel in) {
            return new Alumno(in);
        }

        @Override
        public Alumno[] newArray(int size) {
            return new Alumno[size];
        }
    };

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public float getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(float notaMedia) {
        this.notaMedia = notaMedia;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {

        String datosAlumno = "\nNombre Alumno = " + nombre + "\nEdad Alumno = " + edad + "\nNota Media Alumno = " + notaMedia;

        String datosAsignaturas = "\nAsignaturas Alumno:\n";

        for (Asignatura a : asignaturas) {
            datosAsignaturas += "\n\t\t\tNombre Asignatura = " + a.getNombre() + "\n\t\t\tNota Asignatura = " + a.getNota() + "\n";
        }

        return datosAlumno + datosAsignaturas;

    }

    // Método para convertir el objeto a JSON
    public String toJson() {

        // Crear el objeto Gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Crear un mapa para asegurarnos del orden de los atributos
        LinkedHashMap<String, Object> orderedMap = new LinkedHashMap<>();
        orderedMap.put("nombre", this.nombre);
        orderedMap.put("edad", this.edad);
        orderedMap.put("notaMedia", this.notaMedia);
        orderedMap.put("asignaturas", this.asignaturas);

        // Convertir a JSON formateado
        return gson.toJson(orderedMap);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeInt(edad);
        parcel.writeFloat(notaMedia);
        parcel.writeTypedList(asignaturas);
    }
}
