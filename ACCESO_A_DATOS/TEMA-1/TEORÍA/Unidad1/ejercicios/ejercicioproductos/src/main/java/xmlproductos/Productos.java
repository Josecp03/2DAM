package xmlproductos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "productos")
public class Productos {

	private ArrayList<Producto> lista;
	
	public Productos() {}

	public Productos(ArrayList<Producto> lista) {
		super();
		this.lista = lista;
	}

	//@XmlElementWrapper(name = " ")  
	@XmlElement(name = "producto")
	public ArrayList<Producto> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Producto> lista) {
		this.lista = lista;
	}
	
}
