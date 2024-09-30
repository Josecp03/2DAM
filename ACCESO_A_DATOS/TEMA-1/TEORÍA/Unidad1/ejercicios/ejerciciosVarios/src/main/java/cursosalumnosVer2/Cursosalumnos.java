package cursosalumnosVer2;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cursosalumnos")
public class Cursosalumnos {

	private ArrayList<Curso> curso;

	
	public Cursosalumnos() {}
	
	public Cursosalumnos(ArrayList<Curso> curso) {
		super();
		this.curso = curso;
	}

	public ArrayList<Curso> getCurso() {
		return curso;
	}

	public void setCurso(ArrayList<Curso> curso) {
		this.curso = curso;
	}
	
}
