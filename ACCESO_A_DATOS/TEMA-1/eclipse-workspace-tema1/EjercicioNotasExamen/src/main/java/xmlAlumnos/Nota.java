package xmlAlumnos;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "asignatura", "nota" })
public class Nota {

	// Atributos
	private String asignatura;
	private float nota;

	// Constructor con todos los parámetros
	public Nota(String asignatura, float nota) {
		this.asignatura = asignatura;
		this.nota = nota;
	}

	// Constructor por defecto
	public Nota() {
		this.asignatura = "";
		this.nota = 0;
	}

	// Getters y Setters
	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

}
