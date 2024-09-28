package parte2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class insertarUltimo {

	public static void main(String[] args) throws IOException {
		
		// Inicializar el objeto File
        File fichero = new File(".\\AleatorioEmple.dat");
        
        // Declarar el fichero de acceso aleatorio
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");
        
        // Establecer el tamaño de los registros
        int tamaño = 36;
        
        // Establecer el id que va a tener el último registro
        int id = calcularUltimoId(file, tamaño);
        
        // Establecer los datos de las otras variables
        String apellido = "Pardo";
        int dep = 20;
        double salario = 1800;
        
        // Posicionarnos al final
        file.seek(file.length());
        
        // Esribimos el id
        file.writeInt(id);
        
        // Buffer para almacenar el apellido
     	StringBuffer buffer = null;
		
		// Establecer 10 caracteres para el apellido
		buffer = new StringBuffer(apellido);
		buffer.setLength(10);
		file.writeChars(buffer.toString());
		
		// Escribir los datos restantes
		file.writeInt(dep);
		file.writeDouble(salario);
		
		// Cerrar el fichero
		file.close();
		
	}

	// Método para calcular el último id
	private static int calcularUltimoId(RandomAccessFile file, long tamaño) throws IOException {
		
		// Establecer la posición en la que se encuentra el último id
		long posicion = file.length() - tamaño; 
		
		// Posicionarnos correctamente en el fichero
		file.seek(posicion);
		
		// Leemos el idAnterior y le sumamos uno
		int idActual = file.readInt() + 1;
		
		// Devolvemos el id actual
		return idActual;
		
	}
	
}
