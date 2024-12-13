package ejercicio;

import java.io.*;
import java.net.*;

public class ClienteServidor {
	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 6000);

				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

			int number;
			String input;

			// Bucle principal para la interacción con el usuario
			do {
				System.out.print("Introduce un número (0 para salir): ");
				input = reader.readLine();
				try {
					number = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					System.out.println("Por favor, ingresa un número válido.");
					continue;
				}

				// Enviar el número al servidor
				dos.writeInt(number);

				// Si el número es 0, salir del bucle
				if (number == 0) {
					System.out.println("Fin de la comunicación. Saliendo.");
					break;
				}

				// Recibir el número binario desde el servidor
				String binary = dis.readUTF();
				System.out.println("El número " + number + " en binario es: " + binary);

			} while (true);

		} catch (IOException e) {
			System.err.println("Error en la comunicación con el servidor: " + e.getMessage());
		}
	}
}
