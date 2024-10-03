package xmlViajes;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ListaViajes")
public class ListaViajes {

	// Atributos
	private ArrayList<Viaje> listaViajes;

	// Constructor con todos los parámetros
	public ListaViajes(ArrayList<Viaje> listaViajes) {
		this.listaViajes = listaViajes;
	}
	
	// Constructor por defecto
	public ListaViajes() {
		this.listaViajes = new ArrayList<Viaje>();
	}

	// Getters y Setters
	@XmlElement(name = "Viaje")
	public ArrayList<Viaje> getListaViajes() {
		return listaViajes;
	}

	public void setListaViajes(ArrayList<Viaje> listaViajes) {
		this.listaViajes = listaViajes;
	}

}
