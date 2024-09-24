package centrosProfesores;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"codigocentro", "nombrecentro", "direccion", "director"})
public class Centro {

	// Atributos
	private String codigocentro;
	private String nombrecentro;
	private String direccion;
	private Profesor director;
	
	// Constructor por defecto
	public Centro() {
		this.codigocentro = "";
		this.nombrecentro = "";
		this.direccion = "";
		this.director = new Profesor();
	}
	
	// Constructor con todos los parámetros
	public Centro(String codigocentro, String nombrecentro, String direccion, Profesor director) {
		this.codigocentro = codigocentro;
		this.nombrecentro = nombrecentro;
		this.direccion = direccion;
		this.director = director;
	}

	// Getters y Setters
	public String getCodigocentro() {
		return codigocentro;
	}

	public void setCodigocentro(String codigocentro) {
		this.codigocentro = codigocentro;
	}

	public String getNombrecentro() {
		return nombrecentro;
	}

	public void setNombrecentro(String nombrecentro) {
		this.nombrecentro = nombrecentro;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@XmlElement(name = "director")
	public Profesor getDirector() {
		return director;
	}

	public void setDirector(Profesor director) {
		this.director = director;
	}
	
}
