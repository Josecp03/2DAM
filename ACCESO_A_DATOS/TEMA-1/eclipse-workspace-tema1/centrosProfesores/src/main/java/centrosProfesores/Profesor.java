package centrosProfesores;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"codigoProfesor", "nombreprofe", "salario"})
public class Profesor {

	// Atributos
	private int codigoProfesor;
	private String nombreprofe;
	private double salario;
	
	// Constructor por defecto
	public Profesor() {
		this.codigoProfesor = 0;
		this.nombreprofe = "";
		this.salario = 0;
	}
	
	// Constructor con todos los parámetros
	public Profesor(int codigoProfesor, String nombreprofe, double salario) {
		this.codigoProfesor = codigoProfesor;
		this.nombreprofe = nombreprofe;
		this.salario = salario;
	}

	// Getters y Setters
	public int getCodigoProfesor() {
		return codigoProfesor;
	}

	public void setCodigoProfesor(int codigoProfesor) {
		this.codigoProfesor = codigoProfesor;
	}

	public String getNombreprofe() {
		return nombreprofe;
	}

	public void setNombreprofe(String nombreprofe) {
		this.nombreprofe = nombreprofe;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
}
