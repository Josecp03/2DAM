package pruebaficheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class borrarUTF {

	public static void main(String[] args) throws IOException {

		File fichero = new File(".\\AleatorioEmple.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");

		int registro = 4;// id a modificar

		long posicion = (registro - 1) * 36;
		file.seek(posicion); // nos posicionamos
		file.writeInt(0); // Pongo a 0 el identificador (se considera hueco o borrado)

		file.close();

	}

}
