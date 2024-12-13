package ejercicio;

import java.io.*;
import java.net.*;

public class ServidorBinario {

	public static void main(String[] args) {

		try (ServerSocket serverSocket = new ServerSocket(6000)) {

			System.out.println("Esperando conexiones...");

			// Bucle principal
			while (true) {

				try (Socket clientSocket = serverSocket.accept();

						DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
						DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream())) {

					System.out.println("Cliente conectado.");

					int number;

					// Bucle para recibir datos del cliente y procesarlos
					while (true) {
						
						// Recibir el número del cliente
						try {
							number = dis.readInt();
						} catch (EOFException e) {
							
							// Mostrar mensaje adecuado
							System.out.println("Cliente cerró la conexión.");
							break;
							
						}

						// Si el número es 0, terminamos la comunicación con este cliente
						if (number == 0) {
							
							System.out.println("Número 0 recibido. Cerrando conexión con el cliente.");
							break;
							
						}

						// Convertir el número a binario
						String binary = Integer.toBinaryString(number);
						System.out.println(number + " = " + binary);

						// Enviar el número binario al cliente
						dos.writeUTF(binary);
						
					}

				} catch (IOException e) {
					
					// Mostrar mensaje de error
					System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
					
				}
			}

		} catch (IOException e) {
			
			// Mostrar mensaje de error
			System.err.println("Error en el servidor: " + e.getMessage());
			
		}
	}

}
