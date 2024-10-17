package creacionProcesos;

import java.io.*;

public class Ejemplo2 {

	public static void main(String[] args) throws IOException {

		// C ejecuta el cmd y luego finaliza.
		Process p = new ProcessBuilder("CMD", "/C", "DIR").start();

		// Mostramos carácter a carácter la salida generada por DIR.
		try {

			// Leemos lo que el comando escribió en la consola.
			InputStream is = p.getInputStream();

			int c;
			while ((c = is.read()) != -1) {
				System.out.print((char) c); // Muestra cada carácter de la salida.
			}
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Comprobación de error: 0 bien y -1 mal.
		int exitVal;

		try {

			// Recoge la salida de System.exit().
			exitVal = p.waitFor(); // Espera a que el proceso termine y recoge el valor de salida.

			// Muestra el valor de salida.
			System.out.println("\nValor de salida: " + exitVal);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
