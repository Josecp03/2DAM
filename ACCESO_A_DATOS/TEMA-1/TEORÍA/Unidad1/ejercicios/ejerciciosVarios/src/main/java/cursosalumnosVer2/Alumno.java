package cursosalumnosVer2;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"nombre", "notamedia"})
public class Alumno {

	 private String nombre;
	 private float notamedia;
	 
	 
	 
	 public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getNotamedia() {
		return notamedia;
	}

	public void setNotamedia(float notamedia) {
		this.notamedia = notamedia;
	}

	public Alumno() {}
	 
	public Alumno(String nombre, float notamedia) {
		super();
		this.nombre = nombre;
		this.notamedia = notamedia;
	}
	 
      
}
