package xmlAlumnos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "numalumno", "nombre", "localidad", "numasig", "notamedia", "listaNotas" })
public class Alumno {

	// Atributos
	private int numalumno;
	private String nombre;
	private String localidad;
	private int numasig;
	private float notamedia;
	private ArrayList<Nota> listaNotas;

	// Constructor con todos los parámetros
	public Alumno(int numalumno, String nombre, String localidad, int numasig, float notamedia,
			ArrayList<Nota> listaNotas) {
		this.numalumno = numalumno;
		this.nombre = nombre;
		this.localidad = localidad;
		this.numasig = numasig;
		this.notamedia = notamedia;
		this.listaNotas = listaNotas;
	}

	// Constructor por defecto
	public Alumno() {
		this.numalumno = 0;
		this.nombre = "";
		this.localidad = "";
		this.numasig = 0;
		this.notamedia = 0;
		this.listaNotas = new ArrayList<Nota>();
	}

	// Getters y Setters
	public int getNumalumno() {
		return numalumno;
	}

	public void setNumalumno(int numalumno) {
		this.numalumno = numalumno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public int getNumasig() {
		return numasig;
	}

	public void setNumasig(int numasig) {
		this.numasig = numasig;
	}

	public float getNotamedia() {
		return notamedia;
	}

	public void setNotamedia(float notamedia) {
		this.notamedia = notamedia;
	}

	@XmlElementWrapper(name = "ListaNotas")
	@XmlElement(name = "notaasig")
	public ArrayList<Nota> getListaNotas() {
		return listaNotas;
	}

	public void setListaNotas(ArrayList<Nota> listaNotas) {
		this.listaNotas = listaNotas;
	}

}
