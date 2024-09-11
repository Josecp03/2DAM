package PruebaNivel;

public class Alumno extends Persona{

	// Atributos 
	private int numAsignaturasCursadas;

	// Constructor con todos los parámetros
	public Alumno(String nombre, String apellidos, String telefono, int numAsignaturasCursadas) {
		super(nombre, apellidos, telefono);
		this.numAsignaturasCursadas = numAsignaturasCursadas;
	}
	
	// Constructor por defecto
	public Alumno() {
		super();
		this.numAsignaturasCursadas = 0;
	}

	// Getters y Setters
	public int getNumAsignaturasCursadas() {
		return numAsignaturasCursadas;
	}

	public void setNumAsignaturasCursadas(int numAsignaturasCursadas) {
		this.numAsignaturasCursadas = numAsignaturasCursadas;
	}

	// ToString
	@Override
	public String toString() {
		return "Alumno [numAsignaturasCursadas=" + numAsignaturasCursadas + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", telefono=" + telefono + "]";
	}
	
	// Prueba Unitaria
	public static void main(String[] args) {
		Alumno a = new Alumno("José", "Corrochano Pardo", "666756194", 5);
		System.out.println(a.toString());
	}
	
}
