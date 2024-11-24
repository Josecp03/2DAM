package com.example.fragmentosbiblioteca;

import android.os.Parcel;
import android.os.Parcelable;


public class Book implements Parcelable {

    // Atributos
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String descricpion;

    // Constructor con todos los parámetros
    public Book(String titulo, String autor, int anioPublicacion, String descricpion) {
        this.titulo = titulo;
        this.descricpion = descricpion;
        this.anioPublicacion = anioPublicacion;
        this.autor = autor;
    }

    protected Book(Parcel in) {
        titulo = in.readString();
        autor = in.readString();
        anioPublicacion = in.readInt();
        descricpion = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricpion() {
        return descricpion;
    }

    public void setDescricpion(String descricpion) {
        this.descricpion = descricpion;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(titulo);
        parcel.writeString(autor);
        parcel.writeInt(anioPublicacion);
        parcel.writeString(descricpion);
    }
}
