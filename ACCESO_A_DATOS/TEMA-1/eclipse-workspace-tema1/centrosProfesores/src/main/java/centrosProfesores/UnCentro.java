package centrosProfesores;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"centro", "listaProfesores"})
public class UnCentro {

	// Atributos
	private Centro centro;
	private ArrayList<Profesor> listaProfesores;
	
	// Constructor por defecto
	public UnCentro() {
		this.centro = new Centro();
		this.listaProfesores = new ArrayList<Profesor>();
	}
	
	// Constructor con todos los parámetros
	public UnCentro(Centro centro, ArrayList<Profesor> listaProfesores) {
		this.centro = centro;
		this.listaProfesores = listaProfesores;
	}

	@XmlElement(name = "centro")
	// Getters y Setters
	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	@XmlElementWrapper(name = "profesores")
	@XmlElement(name = "profe")
	public ArrayList<Profesor> getListaProfesores() {
		return listaProfesores;
	}

	public void setListaProfesores(ArrayList<Profesor> listaProfesores) {
		this.listaProfesores = listaProfesores;
	}
		
}
