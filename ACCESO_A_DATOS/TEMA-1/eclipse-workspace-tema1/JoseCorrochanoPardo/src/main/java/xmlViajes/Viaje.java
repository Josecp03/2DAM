package xmlViajes;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "codviaje", "nombreviaje", "precio", "numplazas", "operacion"})
public class Viaje {

	// Atributos
	private int codviaje;
	private String nombreviaje;
	private int precio;
	private int numplazas;
	private String operacion;
	
	// Constructor con todos los parámetros
	public Viaje(int coviaje, String nombreviaje, int precio, int numplazas, String operacion) {
		this.codviaje = coviaje;
		this.nombreviaje = nombreviaje;
		this.precio = precio;
		this.numplazas = numplazas;
		this.operacion = operacion;
	}
	
	// Constructor por defecto
	public Viaje() {
		this.codviaje = 0;
		this.nombreviaje = "";
		this.precio = 0;
		this.numplazas = 0;
		this.operacion = "";
	}

	// Getters y Setters
	public int getCodviaje() {
		return codviaje;
	}

	public void setCodviaje(int coviaje) {
		this.codviaje = coviaje;
	}

	public String getNombreviaje() {
		return nombreviaje;
	}

	public void setNombreviaje(String nombreviaje) {
		this.nombreviaje = nombreviaje;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getNumplazas() {
		return numplazas;
	}

	public void setNumplazas(int numplazas) {
		this.numplazas = numplazas;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
		
}
