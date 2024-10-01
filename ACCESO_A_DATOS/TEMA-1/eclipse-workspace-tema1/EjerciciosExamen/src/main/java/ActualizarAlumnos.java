import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

public class ActualizarAlumnos {

	static int LON_Alumnos = 92;
	static int LON_Notas = 48;
	
	public static void main(String[] args) throws IOException {
		
		// Inicializar los objetos File
		File ficheroAlumnos = new File(".\\Alumnos.dat");
		File ficheroNotas = new File(".\\Notas.dat");
		
		// Declarar el fichero de acceso aleatorio
		RandomAccessFile fileAlumnos = new RandomAccessFile(ficheroAlumnos, "rw");
		RandomAccessFile fileNotas = new RandomAccessFile(ficheroNotas, "r");
				
		// Inicializar la posicion
		int posicionAlumnos = 0;
		int posicionNotas = 0;
		
		// Inicializar variables
		int numAlumnoNotas;
		String Asignatura;
		int contadorAsignaturas = 0;
		
		// Recorrer el fichero
		while (posicionNotas + 4 <= fileNotas.length()) {
			
			// Posicionarnos en el número de alumno correspondiente
			fileNotas.seek(posicionNotas);
			
			// Leer sus datos
			numAlumnoNotas = fileNotas.readInt();
			
			// Inicializar variables auxiliares
			int numAlumnoAux;
			
			
			// Recorrer otra vez el fichero para contar el número de asignaturas y hacer la nota media
			for (;;) {
				
				// Actualizar la posicion
				posicionNotas = 0;
				
				// Posicionarnos correctamente
				fileNotas.seek(posicionNotas);

				// Leer el número de alumno actual
				numAlumnoAux = fileNotas.readInt();
				
				// Comprobar que sea el mismo alumno que se esta leyendo en el primer bucle
				if (numAlumnoAux == numAlumnoNotas) {
					
					
					
				} else {
					posicionNotas += (LON_Notas + 44);
				}
				
			}
			
			
			//Calcular la posción en la que estaría en el otro fichero de alumnos 
			posicionAlumnos = (numAlumnoNotas - 1) * LON_Alumnos;
			
			// Comprobar que ese alumno esté en el otro fichero
			if (posicionAlumnos >= fileAlumnos.length()) {
				System.out.println("Alumno no localizado");
			} else {
				
				// Posicionaros correctamente
				fileAlumnos.seek(posicionAlumnos);
				
			}

			
			
			posicionNotas += LON_Notas;
		}
		
		// Cerrar los ficheros
		fileNotas.close();
		fileAlumnos.close();
				
		
	}
	
}
