package xmlProductosVentas;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"unidadesvendidas", "fecha"})
public class Venta {

	// Atributos
	private int unidadesvendidas;
	private String fecha;
	
	// Constructor con todos los parámetros
	public Venta(int unidadesvendidas, String fecha) {
		this.unidadesvendidas = unidadesvendidas;
		this.fecha = fecha;
	}
	
	// Constructor por defecto
	public Venta() {
		this.unidadesvendidas = 0;
		this.fecha = "";
	}

	// Getters y Setters
	public int getUnidadesvendidas() {
		return unidadesvendidas;
	}

	public void setUnidadesvendidas(int unidadesvendidas) {
		this.unidadesvendidas = unidadesvendidas;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
