package clases;
// Generated 12 dic 2024, 16:30:20 by Hibernate Tools 5.4.33.Final

import java.math.BigInteger;
import java.util.Date;

/**
 * Matriculas generated by hbm2java
 */
public class Matriculas implements java.io.Serializable {

	private MatriculasId id;
	private Asignaturas asignaturas;
	private Alumnos alumnos;
	private Date fechamatr;
	private BigInteger notaasig;

	public Matriculas() {
	}

	public Matriculas(MatriculasId id, Asignaturas asignaturas, Alumnos alumnos) {
		this.id = id;
		this.asignaturas = asignaturas;
		this.alumnos = alumnos;
	}

	public Matriculas(MatriculasId id, Asignaturas asignaturas, Alumnos alumnos, Date fechamatr, BigInteger notaasig) {
		this.id = id;
		this.asignaturas = asignaturas;
		this.alumnos = alumnos;
		this.fechamatr = fechamatr;
		this.notaasig = notaasig;
	}

	public MatriculasId getId() {
		return this.id;
	}

	public void setId(MatriculasId id) {
		this.id = id;
	}

	public Asignaturas getAsignaturas() {
		return this.asignaturas;
	}

	public void setAsignaturas(Asignaturas asignaturas) {
		this.asignaturas = asignaturas;
	}

	public Alumnos getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(Alumnos alumnos) {
		this.alumnos = alumnos;
	}

	public Date getFechamatr() {
		return this.fechamatr;
	}

	public void setFechamatr(Date fechamatr) {
		this.fechamatr = fechamatr;
	}

	public BigInteger getNotaasig() {
		return this.notaasig;
	}

	public void setNotaasig(BigInteger notaasig) {
		this.notaasig = notaasig;
	}

}