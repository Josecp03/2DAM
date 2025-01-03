package clases;
// Generated 12 dic 2024, 16:30:20 by Hibernate Tools 5.4.33.Final

/**
 * Cursoasig generated by hbm2java
 */
public class Cursoasig implements java.io.Serializable {

	private CursoasigId id;
	private Cursos cursos;
	private Asignaturas asignaturas;
	private String aula;

	public Cursoasig() {
	}

	public Cursoasig(CursoasigId id, Cursos cursos, Asignaturas asignaturas) {
		this.id = id;
		this.cursos = cursos;
		this.asignaturas = asignaturas;
	}

	public Cursoasig(CursoasigId id, Cursos cursos, Asignaturas asignaturas, String aula) {
		this.id = id;
		this.cursos = cursos;
		this.asignaturas = asignaturas;
		this.aula = aula;
	}

	public CursoasigId getId() {
		return this.id;
	}

	public void setId(CursoasigId id) {
		this.id = id;
	}

	public Cursos getCursos() {
		return this.cursos;
	}

	public void setCursos(Cursos cursos) {
		this.cursos = cursos;
	}

	public Asignaturas getAsignaturas() {
		return this.asignaturas;
	}

	public void setAsignaturas(Asignaturas asignaturas) {
		this.asignaturas = asignaturas;
	}

	public String getAula() {
		return this.aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

}
