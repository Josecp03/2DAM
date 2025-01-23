package objetos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Estudiantes {

	private int codestudiante;
	private String nombre;
	private String direcion;
	private String tlf;
	private Date fechaalta;
	private List <Participa> participaen;
	
	public Estudiantes() {
		this.codestudiante = 0;
		this.nombre = "";
		this.direcion = "";
		this.tlf = "";
		this.fechaalta = null;
		this.participaen = new ArrayList<>();
	}
	
	public Estudiantes(int codestudiante, String nombre, String direcion, String tlf, Date fechaalta,
			List<Participa> participaen) {
		this.codestudiante = codestudiante;
		this.nombre = nombre;
		this.direcion = direcion;
		this.tlf = tlf;
		this.fechaalta = fechaalta;
		this.participaen = participaen;
	}
	
	public Estudiantes(int codestudiante, String nombre, String direcion, String tlf, Date fechaalta) {
		this.codestudiante = codestudiante;
		this.nombre = nombre;
		this.direcion = direcion;
		this.tlf = tlf;
		this.fechaalta = fechaalta;
		this.participaen = new ArrayList<Participa>();
	}

	public int getCodestudiante() {
		return codestudiante;
	}

	public void setCodestudiante(int codestudiante) {
		this.codestudiante = codestudiante;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDirecion() {
		return direcion;
	}

	public void setDirecion(String direcion) {
		this.direcion = direcion;
	}

	public String getTlf() {
		return tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public Date getFechaalta() {
		return fechaalta;
	}

	public void setFechaalta(Date fechaalta) {
		this.fechaalta = fechaalta;
	}

	public List<Participa> getParticipaen() {
		return participaen;
	}

	public void setParticipaen(List<Participa> participaen) {
		this.participaen = participaen;
	}
	
	
}
