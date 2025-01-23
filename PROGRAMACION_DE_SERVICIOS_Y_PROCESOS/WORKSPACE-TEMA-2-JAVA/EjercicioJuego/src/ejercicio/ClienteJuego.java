package ejercicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteJuego {

    public static void main(String[] args) {
        
        // Inicializar variables
        String host = "localhost";
        int puerto = 6000;
        
        try (Socket socket = new Socket(host, puerto)) {
        	
            System.out.println("Conectado al servidor en " + host + ":" + puerto);
            
            // Streams de entrada y salida
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            
            // Inicializar el Scanner
            Scanner sc = new Scanner(System.in);
            
            while (true) {
            	
                // Pedimos al usuario que introduzca una palabra
                System.out.print("Introduce una palabra: ");
                String palabraUsuario = sc.nextLine();
                
                // Enviamos la palabra al servidor
                salida.println(palabraUsuario);
                
                // Leemos la respuesta del servidor
                String respuesta = entrada.readLine();
                
                if (respuesta == null) {
                	
                    // Si no recibimos nada, el servidor ha cerrado la conexión
                    System.out.println("El servidor ha cerrado la conexión.");
                    break;
                    
                }
                
                System.out.println("Servidor: " + respuesta);
                
                // Comprobamos si el servidor indica el fin del juego
                if (respuesta.contains("Has ganado") || respuesta.contains("Has perdido")) {
                    break;
                }
                
            }
            
            sc.close();
            entrada.close();
            salida.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
