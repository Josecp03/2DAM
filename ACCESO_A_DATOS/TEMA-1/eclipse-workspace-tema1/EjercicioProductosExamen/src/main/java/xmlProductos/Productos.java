package xmlProductos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "productos")
public class Productos {

	// Atributos
	private ArrayList<Producto> listaProductos;

	// Constructor con todos los parámetros
	public Productos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	// Constructor por defecto
	public Productos() {
		this.listaProductos = new ArrayList<Producto>();
	}

	// Getters y Setters
	@XmlElement(name = "producto")
	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	
	
	
	
	
	
}
