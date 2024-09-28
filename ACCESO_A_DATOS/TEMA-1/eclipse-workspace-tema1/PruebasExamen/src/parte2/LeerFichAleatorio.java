package parte2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LeerFichAleatorio {

	public static void main(String[] args) throws IOException {
		
		// Inicializar el objeto File
		File fichero = new File(".\\AleatorioEmple.dat");
		
		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "r");
		
		// Inicializar variables 
		Double salario;
		char apellido[] = new char[10];
		char aux;
		int id;
		int dep;
		
		// Establecer la posición a 0 para que empiece desde el principio
		int posicion = 0;
		
		// Recorrer el fichero
		for (;;) {
			
			// Situarse en la posición inicializada
			file.seek(posicion);
			
			// Obtener el id del empleado
			id = file.readInt();
			
			// Obtener el apellido del empleado recorriendo uno a uno los caracteres 
			for (int i = 0; i < apellido.length; i++) {
				aux = file.readChar(); // Leer el caracter
				apellido[i] = aux; // Guardarlo en el array formando el apellido completo
			}
			
			// Convertir el array en un string
			String apellidoS = new String(apellido);
			
			// Obtener los datos restantes
			dep = file.readInt();
			salario = file.readDouble();
			
			// Mostrar los datos del empleado
			System.out.println("ID: "+id+", Apellido: "+apellidoS+", Departamento: "+dep+", Salario: "+salario+"€");
			
			// Posicionarse en el siguiente empleado
			posicion = posicion + 36; // 36 es la suma de lo que ocupa cada dato (4+20+4+8)
			
			
			// Salir del for cuando haya recorrido todos los bytes (Y cerrar el file)
			if (file.getFilePointer()==file.length()) {
				file.close();
				break;
			}
				
		}
		
	}
	
}
