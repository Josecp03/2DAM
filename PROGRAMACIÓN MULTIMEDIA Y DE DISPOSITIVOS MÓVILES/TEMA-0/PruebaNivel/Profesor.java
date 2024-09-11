package PruebaNivel;

public class Profesor extends Persona{

	// Atibutos
	private int numAlumnos;

	// Constructor con todos los parámetros
	public Profesor(String nombre, String apellidos, String telefono, int numAlumnos) {
		super(nombre, apellidos, telefono);
		this.numAlumnos = numAlumnos;
	}
	
	// Constructor por defecto
	public Profesor() {
		super();
		this.numAlumnos = 0;
	}

	// Getters y Setters
	public int getNumAlumnos() {
		return numAlumnos;
	}

	public void setNumAlumnos(int numAlumnos) {
		this.numAlumnos = numAlumnos;
	}

	// ToString
	@Override
	public String toString() {
		return "Profesor [numAlumnos=" + numAlumnos + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono="
				+ telefono + "]";
	}
	
	// Prueba Unitaria
	public static void main(String[] args) {
		Profesor p = new Profesor("Luis", "Miguel Morales", "259107456", 64);
		System.out.println(p.toString());
	}
	
}
