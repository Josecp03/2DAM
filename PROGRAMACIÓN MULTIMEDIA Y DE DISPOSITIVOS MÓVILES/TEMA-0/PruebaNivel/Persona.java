package PruebaNivel;

public class Persona {

	// Atributos
	protected String nombre;
	protected String apellidos;
	protected String telefono;
	
	// Constructor con todos los parámetros
	public Persona(String nombre, String apellidos, String telefono) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
	}
	
	// Constructor por defecto
	public Persona() {
		this.nombre = "";
		this.apellidos = "";
		this.telefono = "";
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	// ToString
	@Override
	public String toString() {
		return "Nivel9a [nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + "]";
	}

}
