package pruebaficheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class leerUTF {

	public static void main(String[] args) throws IOException {
		
		File fichero = new File(".\\AleatorioEmpleUTF.dat");
		
		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		
		// Inicializar variables
		int id, dep, posicion = 0;
		Double salario;

		// Recorrer el fichero
		for (;;) { 
			
			// Situarse en la posición inicializada
			file.seek(posicion);
			
			// Obtener los datos 
			id = file.readInt();
			String apellidoS = file.readUTF();
			dep = file.readInt();
			salario = file.readDouble();
			
			// Mostrar los datos del empleado
			System.out.println("ID: "+id+", Apellido: "+apellidoS+", Departamento: "+dep+", Salario: "+salario+"€");
			
			// Mostrar los datos de ejecución
			System.out.println("Posicion="+posicion+", filepointer= "+file.getFilePointer()+",  file leng: "+file.length());
			
			// Posicionarse en el siguiente empleado
			posicion = posicion + 36;
			
			// Salirse del for cuando haya recorrido todos los bytes
			if (posicion >= file.length())
				break;

		}
		
		// Cerrar el fichero
		file.close();

	}
	
}
