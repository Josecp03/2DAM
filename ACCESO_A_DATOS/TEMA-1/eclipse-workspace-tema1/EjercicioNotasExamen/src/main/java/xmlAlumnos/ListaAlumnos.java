package xmlAlumnos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ListaAlumnos")
public class ListaAlumnos {

	// Atributos
	private ArrayList<Alumno> listaAlumnos;

	// Constructor con todos los parámetros
	public ListaAlumnos(ArrayList<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}

	// Constructor por defecto
	public ListaAlumnos() {
		this.listaAlumnos = new ArrayList<Alumno>();
	}

	// Getter y Setters

	@XmlElement(name = "Alumno")
	public ArrayList<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}

	public void setListaAlumnos(ArrayList<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}

}
