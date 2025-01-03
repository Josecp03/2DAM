package clasesMapeadas;
// Generated 10 dic 2024, 11:13:39 by Hibernate Tools 5.4.33.Final

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Ciclistas generated by hbm2java
 */
public class Ciclistas implements java.io.Serializable {

	private BigInteger codigociclista;
	private Ciclistas ciclistas;
	private Equipos equipos;
	private String nombreciclista;
	private Date fechanacimiento;
	private BigDecimal peso;
	private Set llevas = new HashSet(0);
	private Set resumenCamisetases = new HashSet(0);
	private Set ciclistases = new HashSet(0);
	private Set tramospuertoses = new HashSet(0);
	private Set etapases = new HashSet(0);

	public Ciclistas() {
	}

	public Ciclistas(BigInteger codigociclista, Equipos equipos, String nombreciclista, Date fechanacimiento,
			BigDecimal peso) {
		this.codigociclista = codigociclista;
		this.equipos = equipos;
		this.nombreciclista = nombreciclista;
		this.fechanacimiento = fechanacimiento;
		this.peso = peso;
	}

	public Ciclistas(BigInteger codigociclista, Ciclistas ciclistas, Equipos equipos, String nombreciclista,
			Date fechanacimiento, BigDecimal peso, Set llevas, Set resumenCamisetases, Set ciclistases,
			Set tramospuertoses, Set etapases) {
		this.codigociclista = codigociclista;
		this.ciclistas = ciclistas;
		this.equipos = equipos;
		this.nombreciclista = nombreciclista;
		this.fechanacimiento = fechanacimiento;
		this.peso = peso;
		this.llevas = llevas;
		this.resumenCamisetases = resumenCamisetases;
		this.ciclistases = ciclistases;
		this.tramospuertoses = tramospuertoses;
		this.etapases = etapases;
	}

	public BigInteger getCodigociclista() {
		return this.codigociclista;
	}

	public void setCodigociclista(BigInteger codigociclista) {
		this.codigociclista = codigociclista;
	}

	public Ciclistas getCiclistas() {
		return this.ciclistas;
	}

	public void setCiclistas(Ciclistas ciclistas) {
		this.ciclistas = ciclistas;
	}

	public Equipos getEquipos() {
		return this.equipos;
	}

	public void setEquipos(Equipos equipos) {
		this.equipos = equipos;
	}

	public String getNombreciclista() {
		return this.nombreciclista;
	}

	public void setNombreciclista(String nombreciclista) {
		this.nombreciclista = nombreciclista;
	}

	public Date getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public BigDecimal getPeso() {
		return this.peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public Set getLlevas() {
		return this.llevas;
	}

	public void setLlevas(Set llevas) {
		this.llevas = llevas;
	}

	public Set getResumenCamisetases() {
		return this.resumenCamisetases;
	}

	public void setResumenCamisetases(Set resumenCamisetases) {
		this.resumenCamisetases = resumenCamisetases;
	}

	public Set getCiclistases() {
		return this.ciclistases;
	}

	public void setCiclistases(Set ciclistases) {
		this.ciclistases = ciclistases;
	}

	public Set getTramospuertoses() {
		return this.tramospuertoses;
	}

	public void setTramospuertoses(Set tramospuertoses) {
		this.tramospuertoses = tramospuertoses;
	}

	public Set getEtapases() {
		return this.etapases;
	}

	public void setEtapases(Set etapases) {
		this.etapases = etapases;
	}

}
