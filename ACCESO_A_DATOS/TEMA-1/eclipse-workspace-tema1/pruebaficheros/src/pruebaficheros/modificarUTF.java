package pruebaficheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class modificarUTF {

	public static void main(String[] args) throws IOException {

		File fichero = new File(".\\AleatorioEmple.dat");

		// Declarar el fichero de acceso aleatorio
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");

		int registro = 4;
		Double salario = 0.0;
		int dep = 0;

		long posicion = (registro - 1) * 36; // Posición inicial
		if (posicion < file.length()) {
			file.seek(posicion); // nos posicionamos al inicio y leemos
			int id = file.readInt();
			if (id == registro) {
				// leemos la cadena, y nos volvemos a posicionar después de leerla
				String apellidoS = file.readUTF();
				file.seek(file.getFilePointer());
				// ya podemos modoficar
				file.writeInt(dep);
				file.writeDouble(salario);
				System.out.println("MODIFICADO :" + registro + " * " + dep + " * " + salario);

			} else
				System.out.println("NO MODIFICADO NO LOCALIZADO :" + registro);

		} else
			System.out.println("TE HAS PASADO :" + registro);
		file.close();

	}

}
