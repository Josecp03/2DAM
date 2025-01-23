package ejercicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorJuego {

    public static void main(String[] args) {
        
        // Ajustar el puerto donde escuchará el servidor
        int puerto = 6000;
        
        // Inicializar variables
        String palabraSecreta = "gato";
        int intentosMaximos = 4;
        
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            
            System.out.println("Servidor escuchando en el puerto " + puerto + "...");
            
            // Esperamos la conexión del cliente
            try (Socket clientSocket = serverSocket.accept()) {
            	
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                
                // Streams para recibir y enviar datos
                BufferedReader entrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter salida = new PrintWriter(clientSocket.getOutputStream(), true);
                
                // Inicializar los intentos
                int intentosRestantes = intentosMaximos;
                
                // Bucle de juego
                while (intentosRestantes > 0) {
                	
                    // Leemos la palabra que envía el cliente
                    String palabraRecibida = entrada.readLine();
                    
                    if (palabraRecibida == null) {
                    	
                        // Si no recibimos nada, asumimos que el cliente cerró la conexión
                        System.out.println("El cliente cerró la conexión.");
                        break;
                        
                    }
                    
                    // Comprobamos si es la palabra secreta
                    if (palabraRecibida.equalsIgnoreCase(palabraSecreta)) {
                    	
                        // Enviamos mensaje de victoria
                        salida.println("Has ganado. ¡La palabra era '" + palabraSecreta + "'!");
                        System.out.println("El cliente ha adivinado la palabra correctamente.");
                        break; 
                        
                    } else {
                    	
                        // Actualizar la variable de los intentos
                        intentosRestantes--;
                        
                        // Compronar si se acabaron los intentos
                        if (intentosRestantes == 0) {
                            salida.println("Has perdido. La palabra secreta era '" + palabraSecreta + "'.");
                            System.out.println("El cliente ha agotado los intentos. La palabra era '" + palabraSecreta + "'.");
                            break;
                            
                        } else {
                        	
                            // Informamos cuántos intentos quedan
                            salida.println("Palabra incorrecta. Te quedan " + intentosRestantes + " intento(s).");
                            
                        }
                    }
                }
                
                // Cierre de recursos
                entrada.close();
                salida.close();
            }
            
            System.out.println("Servidor cerrado.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
