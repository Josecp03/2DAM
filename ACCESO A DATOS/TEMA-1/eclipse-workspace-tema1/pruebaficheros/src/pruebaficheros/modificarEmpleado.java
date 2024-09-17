package pruebaficheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class modificarEmpleado {

	public static void main(String[] args) throws IOException {

		File fichero = new File(".\\AleatorioEmple.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");

		int registro = 4;// id a modificar

		long posicion = (registro - 1) * 36; // (4+20+4+8) modifico salario y dep
		posicion = posicion + 4 + 20; // sumo el tamaño de ID+apellido
		file.seek(posicion); // nos posicionamos
		file.writeInt(40); // modif departamento a 40
		file.writeDouble(4000.87);// modif salario

		// Cerrar fichero
		file.close();

	}

}
