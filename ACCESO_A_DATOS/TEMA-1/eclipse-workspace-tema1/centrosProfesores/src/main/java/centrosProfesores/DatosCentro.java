package centrosProfesores;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "datoscentro")
public class DatosCentro {

	// Atributos
	private ArrayList<UnCentro> listaCentros;

	// Constructor por defecto
	public DatosCentro() {
		this.listaCentros = new ArrayList<UnCentro>();
	}

	// Constructor con todos los parámetros
	public DatosCentro(ArrayList<UnCentro> listaCentros) {
		this.listaCentros = listaCentros;
	}

	// Definir el nombre de las etiquetas
	@XmlElement(name = "uncentro")
	// Getters y Setters
	public ArrayList<UnCentro> getListaCentros() {
		return listaCentros;
	}

	public void setListaCentros(ArrayList<UnCentro> listaCentros) {
		this.listaCentros = listaCentros;
	}

}
