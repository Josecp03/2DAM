package xmlProductosVentas;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"unidadesvendidas", "fecha", "importe"})
public class Venta {

	// Atributos
	private int unidadesvendidas;
	private String fecha;
	private double importe;
	
	// Constructor con todos los parámetros
	public Venta(int unidadesvendidas, String fecha, double importe) {
		this.unidadesvendidas = unidadesvendidas;
		this.fecha = fecha;
		this.importe = importe;
	}
	
	// Constructor por defecto
	public Venta() {
		this.unidadesvendidas = 0;
		this.fecha = "";
		this.importe = 0;
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

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	
}
